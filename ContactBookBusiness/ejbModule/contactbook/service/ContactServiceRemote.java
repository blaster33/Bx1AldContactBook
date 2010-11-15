package contactbook.service;

import java.util.List;

import contactbook.dao.ContactSearchCriteria;
import contactbook.model.Contact;

public interface ContactServiceRemote {
	public static String JNDI = "ContactBookEar/ContactServiceImpl/remote";
	
	public Contact addContact(Contact c);
	
	public void removeContact(Contact c);
	
	public List<Contact> getContacts();

	public List<Contact> findBy(ContactSearchCriteria criteria);
}
