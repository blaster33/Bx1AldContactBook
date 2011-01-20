package contactbook.service.impl;

import java.util.List;

import javax.annotation.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import contactbook.dao.ContactDAO;
import contactbook.dao.GroupDAO;
import contactbook.dao.UserDAO;
import contactbook.dao.impl.ContactDAOImpl;
import contactbook.dao.impl.GroupDAOImpl;
import contactbook.dao.impl.UserDAOImpl;
import contactbook.model.Contact;
import contactbook.model.Group;
import contactbook.model.User;
import contactbook.service.ContactServiceRemote;

@WebService(serviceName="ContactBookService")
@Stateless
@Remote(ContactServiceRemote.class)
public class ContactServiceImpl implements ContactServiceRemote {
	@EJB
	protected ContactDAO contactDAO;
	@EJB
	protected GroupDAO groupDAO;
	@EJB
	protected UserDAO userDAO;

	public ContactServiceImpl() {
		contactDAO = ContactDAOImpl.getInstance();
		groupDAO = GroupDAOImpl.getInstance();
		userDAO = UserDAOImpl.getInstance();
	}

	@WebMethod
	@Override
	public Contact addContact(Contact c) {
		if(c.getGroup() == null)
			c.setGroup(c.getUser().getDefaultGroup());
		contactDAO.addContact(c);
		return c;
	}

	@WebMethod
	@Override
	public void removeContact(Contact c) {
		contactDAO.removeContact(c);
	}

	@WebMethod
	@Override
	public Contact updateContact(Contact c) {
		try {
			contactDAO.updateContact(c);
		} catch(Exception e) {
			System.err.println("------------------------------------------");
			e.printStackTrace();
		}
		return c;
	}
	
	@WebMethod
	@Override
	public Contact refreshContact(Contact c) {
		Contact c2 = null;
		try {
			c2 = contactDAO.refreshContact(c);
		} catch(Exception e) {
			System.err.println("------------------------------------------");
			e.printStackTrace();
		}
		return c2;
	}

	@WebMethod
	@Override
	public Group addGroup(Group group) {
		groupDAO.addGroup(group);
		return group;
	}

	@WebMethod
	@Override
	public void removeGroup(Group group, boolean removeContact) {
		try {
			groupDAO.removeGroup(group, removeContact);
		} catch(Exception e) {
			System.err.println("------------------------------------------");
			e.printStackTrace();
		}
	}

	@Override
	@WebMethod
	public Group updateGroup(Group g) {
		groupDAO.updateGroup(g);
		return g;
	}
	
	@WebMethod
	@Override
	public Group refreshGroup(Group g) {
		return groupDAO.refreshGroup(g);
	}

	@WebMethod
	@Override
	public List<Contact> getContactsByGroup(Group g) {
		return groupDAO.getContacts(g);
	}

	@WebMethod
	@Override
	public User addUser(User user) {
		try {
			user = userDAO.addUser(user);
			if(user != null) {
				user.setDefaultGroup(groupDAO.addGroup(new Group(user, "Default")));
				user = userDAO.updateUser(user);
			}
			return user;
		} catch(Exception e) {
			System.err.println("-------------- Exception in addUser");
			e.printStackTrace();
		}
		
		return null;
	}

	@WebMethod
	@Override
	public void removeUser(User user) {
		userDAO.removeUser(user);
	}

	@Override
	@WebMethod
	public User updateUser(User u) {
		return userDAO.updateUser(u);
	}
	
	@WebMethod
	@Override
	public User refreshUser(User u) {
		return userDAO.refreshUser(u);
	}

	@WebMethod
	@Override
	public List<Contact> getContactsByUser(User user) {
		try {
			return userDAO.getContacts(user);
		} catch(Exception e) {
			System.err.println("------------------------------------------");
			e.printStackTrace();
		}
		return null;
	}

	@WebMethod
	@Override
	public List<Group> getGroupsByUser(User user) {
		return userDAO.getGroup(user);
	}
	
	@WebMethod
	@Override
	public List<User> getUsers() {
		return userDAO.getUsers();
	}
	
	@WebMethod
	@Override
	public boolean loginNameAvailable(String loginName) {
		return !userDAO.loginNameTaken(loginName);
	}

	@WebMethod
	@Override
	public User getUserByName(String loginName) {
		return userDAO.getUserByName(loginName);
	}

	@Override
	public Group getGroupByName(String groupName, User user) {
		return groupDAO.getGroup(groupName, user);
	}
}
