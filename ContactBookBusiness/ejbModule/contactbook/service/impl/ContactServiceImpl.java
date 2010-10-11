package contactbook.service.impl;

import java.util.*;

import javax.annotation.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import contacbook.model.Contact;
import contactbook.dao.ContactDAO;
import contactbook.dao.ContactSearchCriteria;
import contactbook.dao.impl.ContactDAOHashMap;
import contactbook.service.ContactServiceRemote;

@Stateless
@Remote(ContactServiceRemote.class)
public class ContactServiceImpl implements ContactServiceRemote {
	@EJB
	protected ContactDAO data;
	
	public ContactServiceImpl() {
		data = ContactDAOHashMap.getInstance();
	}
	
	public Contact addContact(Contact c) {
		data.addContact(c);
		return c;
	}

	public Iterator<Contact> getContacts() {
		return data.getContacts();
	}

	public void removeContact(Contact c) {
		data.removeContact(c);
	}

	public Iterator<Contact> findBy(ContactSearchCriteria criteria) {
		return data.findBy(criteria);
	}
}
