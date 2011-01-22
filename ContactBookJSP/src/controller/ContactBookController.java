package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;

import contactbook.model.User;
import contactbook.service.ContactServiceRemote;

public class ContactBookController extends HttpServlet implements javax.servlet.Servlet {
	private static final long serialVersionUID = 4356106233651710989L;
	
	private Map<String, User> currentSessions;
	private ContactServiceRemote contactService = null;
	
	public ContactBookController() {
		super();
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

	public void init() throws ServletException {
		super.init();
	}

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("do");
		if ( action.equals("login")) {
			UserBean admin = login(request);
			if ( admin != null ) {
				if ( admin.isAdmin() )
					OnLoginSuccess(request, response);
				else
					OnLoginFailure(request, response, Message.INVALID_LOGIN_ADMIN);
			}
			else 
				OnLoginFailure(request, response, Message.INVALID_LOGIN_INFO);
		}
		else if ( action.equals("logout")) {
			logout(request,response);
		}
		

	}
	
	public UserBean login(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = contactService.getUserByName(username);
		if(user == null || !user.checkPassword(password)) {
			currentSessions.remove(session.getId());
			session.invalidate();
			return null;
		}
		
		if ( !user.isAdmin() ) {
			currentSessions.remove(session.getId());
			session.invalidate();
			return BeanUtils.beanFromUser(user);
		}
		
		// user ok, register session
		currentSessions.put(session.getId(), user);
		user.setLastLogin(Calendar.getInstance().getTimeInMillis());
		contactService.updateUser(user);
		
		return BeanUtils.beanFromUser(user);
	}

	public boolean logout(HttpServletRequest request,HttpServletResponse response) throws ServletException, RuntimeException, IOException {
		HttpSession session = request.getSession();
		
		if(currentSessions.remove(session.getId()) == null)
			return false;
		
		session.invalidate();
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
		return true;
	}
	
	public boolean isLoggedIn(HttpServletRequest request) {
		return currentSessions.get(request.getSession().getId()) != null;
	}

	private void OnLoginSuccess(HttpServletRequest request,HttpServletResponse response)  throws ServletException, IOException {
		showUserList(request,response);
	}
	private void OnLoginFailure(HttpServletRequest request,HttpServletResponse response, String message)  throws ServletException, IOException {
		request.setAttribute("errorMessage", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showUserList(HttpServletRequest request,HttpServletResponse response)  throws ServletException, IOException {
		List<User> userList = contactService.getUsers();
		List<UserBean> beanList = new ArrayList<UserBean>();
		for(User u: userList ) {
			UserBean bean = BeanUtils.beanFromUser(u);
			beanList.add(bean);
		}

		String username = request.getParameter("username");
		request.setAttribute("username", username);
		request.setAttribute("beanList", beanList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/resultat.jsp");
		dispatcher.forward(request, response);
	}
	
	public void destroy() {
		super.destroy();
	}
}
