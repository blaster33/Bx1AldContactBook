package contactbook.service.impl;

import java.util.List;

import javax.annotation.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import contactbook.dao.ContactDAO;
import contactbook.dao.ContactSearchCriteria;
import contactbook.dao.GroupDAO;
import contactbook.dao.impl.ContactDAOMysql;
import contactbook.dao.impl.GroupDAOImpl;
import contactbook.model.Contact;
import contactbook.model.Group;
import contactbook.service.ContactServiceRemote;

@WebService(serviceName="ContactBookService")
@Stateless
@Remote(ContactServiceRemote.class)
public class ContactServiceImpl implements ContactServiceRemote {
	@EJB
	protected ContactDAO contactDAO;
	@EJB
	protected GroupDAO groupDAO;
	
	public ContactServiceImpl() {
		contactDAO = ContactDAOMysql.getInstance();
		groupDAO = GroupDAOImpl.getInstance();
	}
	
	@WebMethod
	public Contact addContact(Contact c) {
		contactDAO.addContact(c);
		return c;
	}

	@WebMethod
	public List<Contact> getContacts1() {
		return contactDAO.getContacts();
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
	public List<Contact> findBy(ContactSearchCriteria criteria) {
		return contactDAO.findBy(criteria);
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
	public List<Group> getGroups() {
		return groupDAO.getGroups();
	}
	
	@WebMethod
	public List<Contact> getContacts2(Group g) {
		return groupDAO.getContacts(g);
	}
}
