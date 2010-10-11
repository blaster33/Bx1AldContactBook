package contactbook.service;

import java.util.Iterator;

import contacbook.model.Contact;
import contactbook.dao.ContactSearchCriteria;

public interface ContactServiceRemote {
	public static String JNDI = "ContactBookEar/ContactServiceImpl/remote";
	
	public Contact addContact(Contact c);
	
	public void removeContact(Contact c);
	
	public Iterator<Contact> getContacts();

	public Iterator<Contact> findBy(ContactSearchCriteria criteria);
}
