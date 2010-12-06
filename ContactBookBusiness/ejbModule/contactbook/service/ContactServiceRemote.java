package contactbook.service;

import java.util.List;

import contactbook.model.Contact;
import contactbook.model.Group;
import contactbook.model.User;

public interface ContactServiceRemote {
	public static String JNDI = "ContactBookEar/ContactServiceImpl/remote";
	
	public Contact addContact(Contact c);
	
	public void removeContact(Contact c);
	
	public Contact updateContact(Contact c);
	
	public Group addGroup(Group group);
	
	public void removeGroup(Group group, boolean removeContacts);
	
	public List<Contact> getContactsByGroup(Group g);
	
	public void addUser(User user);
	
	public List<Contact> getContactsByUser(User user);
	
	public List<Group> getGroupsByUser(User user);
	
	public void removeUser(User user);
}
