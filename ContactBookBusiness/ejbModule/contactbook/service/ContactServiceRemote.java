package contactbook.service;

import java.util.List;

import contactbook.dao.ContactSearchCriteria;
import contactbook.model.Contact;
import contactbook.model.Group;

public interface ContactServiceRemote {
	public static String JNDI = "ContactBookEar/ContactServiceImpl/remote";
	
	public Contact addContact(Contact c);
	
	public void removeContact(Contact c);
	
	public Contact updateContact(Contact c);
	
	public List<Contact> getContacts1();

	public List<Contact> findBy(ContactSearchCriteria criteria);
	
	public Group addGroup(Group group);
	
	public void removeGroup(Group group, boolean removeContacts);
	
	public List<Group> getGroups();
	
	public List<Contact> getContacts2(Group g);
}
