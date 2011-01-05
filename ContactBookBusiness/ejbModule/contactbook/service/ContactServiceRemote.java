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
	
	public Contact refreshContact(Contact c);
	
	public Group addGroup(Group group);
	
	public void removeGroup(Group group, boolean removeContact);
	
	public Group updateGroup(Group g);
	
	public Group refreshGroup(Group g);
	
	public List<Contact> getContactsByGroup(Group g);
	
	public User addUser(User u);
	
	public void removeUser(User u);
	
	public User updateUser(User u);
	
	public User refreshUser(User u);
	
	public List<Contact> getContactsByUser(User user);
	
	public List<Group> getGroupsByUser(User user);
	
	public List<User> getUsers();
}
