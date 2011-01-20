package contactbook.dao;

import java.util.List;

import contactbook.model.*;

public interface GroupDAO {
	public Group addGroup(Group group);
	
	public void removeGroup(Group group, boolean removeContact);
	
	public Group updateGroup(Group group);
	
	public Group refreshGroup(Group g);
	
	public List<Group> getGroups();
	
	public List<Contact> getContacts(Group group);
	
	public Group getGroup(String groupName, User user);
}
