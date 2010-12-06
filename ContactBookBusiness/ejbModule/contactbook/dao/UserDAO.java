package contactbook.dao;

import java.util.List;

import contactbook.model.Contact;
import contactbook.model.Group;
import contactbook.model.User;

public interface UserDAO {
	public void addUser(User user);
	
	public void removeUser(User user);
	
	public void updateUser(User user);
	
	public List<Contact> getContacts(User user);
	
	public List<Group> getGroup(User user);
}
