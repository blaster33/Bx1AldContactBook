package contactbook.service.impl;

import java.util.*;

import javax.annotation.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import contactbook.dao.ContactDAO;
import contactbook.dao.ContactSearchCriteria;
import contactbook.dao.impl.ContactDAOHashMap;
import contactbook.model.Contact;
import contactbook.service.ContactServiceRemote;

@WebService(serviceName="ContactBookService")
@Stateless
@Remote(ContactServiceRemote.class)
public class ContactServiceImpl implements ContactServiceRemote {
	@EJB
	protected ContactDAO data;
	
	public ContactServiceImpl() {
		data = ContactDAOHashMap.getInstance();
	}
	
	@WebMethod
	public Contact addContact(Contact c) {
		data.addContact(c);
		return c;
	}

	@WebMethod
	public Iterator<Contact> getContacts() {
		return data.getContacts();
	}

	@WebMethod
	public void removeContact(Contact c) {
		data.removeContact(c);
	}

	@WebMethod
	public Iterator<Contact> findBy(ContactSearchCriteria criteria) {
		return data.findBy(criteria);
	}
}
