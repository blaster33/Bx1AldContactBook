package contactbook.service;

import java.util.Iterator;

import contactbook.dao.ContactSearchCriteria;
import contactbook.model.Contact;

public interface ContactServiceRemote {
	public static String JNDI = "ContactBookEar/ContactServiceImpl/remote";
	
	public Contact addContact(Contact c);
	
	public void removeContact(Contact c);
	
	public Iterator<Contact> getContacts();

	public Iterator<Contact> findBy(ContactSearchCriteria criteria);
}
