package contactbook.dao;

import java.util.List;

import contactbook.model.Contact;
import contactbook.model.Group;

public interface GroupDAO {
	public void addGroup(Group group);
	
	public void removeGroup(Group group, Boolean removeContacts);
	
	public void updateGroup(Group group);
	
	public List<Group> getGroups();
	
	public List<Contact> getContacts(Group group);
}
