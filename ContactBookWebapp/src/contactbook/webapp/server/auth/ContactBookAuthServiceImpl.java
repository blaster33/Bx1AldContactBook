package contactbook.webapp.server.auth;

import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.Calendar;

import contactbook.model.User;
import contactbook.service.ContactServiceRemote;
import contactbook.webapp.client.auth.ContactBookAuthService;

public class ContactBookAuthServiceImpl extends RemoteServiceServlet implements
		ContactBookAuthService {
	private static final long serialVersionUID = 6770522913163738297L;
	
	private Map<String, User> currentSessions;
	
	ContactServiceRemote contactService = null;

	public ContactBookAuthServiceImpl() {
		try {
			Context ctx = new InitialContext();
			contactService = (ContactServiceRemote) ctx.lookup(ContactServiceRemote.JNDI);
		}
		catch(NamingException e) {
			System.err.println(e.getMessage());
			System.err.println(e.getExplanation());
			e.printStackTrace();
		}
		
		currentSessions = new HashMap<String, User>();
	}

	@Override
	public boolean login(String username, String password) {
		HttpServletRequest req = this.getThreadLocalRequest();
		HttpSession session = req.getSession();
		
		User user = contactService.getUserByName(username);
		if(user == null || !user.checkPassword(password)) {
			currentSessions.remove(session.getId());
			session.invalidate();
			return false;
		}
		
		// user ok, register session
		currentSessions.put(session.getId(), user);
		user.setLastLogin(Calendar.getInstance().getTimeInMillis());
		contactService.updateUser(user);
		return true;
	}

	@Override
	public boolean logout() throws RuntimeException {
		HttpServletRequest req = this.getThreadLocalRequest();
		HttpSession session = req.getSession();
		
		if(currentSessions.remove(session.getId()) == null)
			return false;
		
		session.invalidate();
		return true;
	}

	@Override
	public boolean createAccount(String username, String password, String email) {
		return createAccount(username, password, email, false);
	}

	@Override
	public boolean createAccount(String username, String password, String email,
			boolean isAdmin) {
		User user = new User(username, password, email, isAdmin);
		user = contactService.addUser(user);
		
		return user != null;
	}

	@Override
	public boolean usernameAvailable(String username) {
		return contactService.loginNameAvailable(username);
	}

	@Override
	public boolean isLoggedIn() {
		return currentSessions.get(this.getThreadLocalRequest().getSession().getId()) != null;
	}
}
