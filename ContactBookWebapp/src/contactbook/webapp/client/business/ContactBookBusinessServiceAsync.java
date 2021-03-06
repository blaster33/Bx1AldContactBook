package contactbook.webapp.client.business;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import contactbook.webapp.client.dto.*;

public interface ContactBookBusinessServiceAsync {
	public void getContacts(UserDTO user, AsyncCallback<List<ContactDTO>> callback);
	
	public void getContacts(GroupDTO group, AsyncCallback<List<ContactDTO>> callback);
	
	public void getGroups(UserDTO user, AsyncCallback<List<GroupDTO>> callback);
	
	public void addOrUpdateGroup(GroupDTO group, AsyncCallback<Boolean> callback);

	public void addOrUpdateContact(ContactDTO dto, AsyncCallback<Boolean> callback);
	
	public void removeGroup(GroupDTO group, boolean removeContacts, AsyncCallback<Boolean> callback);
	
	public void removeContact(ContactDTO contact, AsyncCallback<Boolean> callback);
}
