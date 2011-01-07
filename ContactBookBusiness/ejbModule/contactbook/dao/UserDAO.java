package contactbook.dao;

import java.util.List;

import contactbook.model.Contact;
import contactbook.model.Group;
import contactbook.model.User;

public interface UserDAO {
	public User addUser(User user);
	
	public void removeUser(User user);
	
	public User updateUser(User user);
	
	public User refreshUser(User u);
	
	public User getUserByName(String loginName);
	
	public List<Contact> getContacts(User user);
	
	public List<Group> getGroup(User user);
	
	public List<User> getUsers();
	
	public boolean loginNameTaken(String loginName);
}
