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
	public Contact addContact(Contact c) {
		contactDAO.addContact(c);
		return c;
	}

	@WebMethod
	public void removeContact(Contact c) {
		contactDAO.removeContact(c);
	}
	
	@WebMethod
	public Contact updateContact(Contact c) {
		contactDAO.updateContact(c);
		return c;
	}

	@WebMethod
	public Group addGroup(Group group) {
		groupDAO.addGroup(group);
		return group;
	}

	@WebMethod
	public void removeGroup(Group group, boolean removeContacts) {
		groupDAO.removeGroup(group, removeContacts);
	}
	
	@WebMethod
	public List<Contact> getContactsByGroup(Group g) {
		return groupDAO.getContacts(g);
	}

	@WebMethod
	public void addUser(User user) {
		userDAO.addUser(user);
	}

	@WebMethod
	public List<Contact> getContactsByUser(User user) {
		return userDAO.getContacts(user);
	}

	@WebMethod
	public List<Group> getGroupsByUser(User user) {
		return userDAO.getGroup(user);
	}

	@WebMethod
	public void removeUser(User user) {
		userDAO.removeUser(user);
	}
}
