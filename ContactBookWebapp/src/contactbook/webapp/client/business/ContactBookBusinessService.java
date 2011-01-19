package contactbook.webapp.client.business;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import contactbook.model.*;
import contactbook.webapp.client.dto.*;


@RemoteServiceRelativePath("business")
public interface ContactBookBusinessService extends RemoteService {
	/**
	 * Get the list of contacts owned by a given user
	 * @param user the user to get the contacts from
	 * @return a {@link java.util.List} of {@link Contact}
	 */
	public List<ContactDTO> getContacts(UserDTO user);
	
	/**
	 * Get the list of groups owned by a given user
	 * @param user the user to get the groups from
	 * @return a {@link java.util.List} of {@link Group}
	 */
	public List<GroupDTO> getGroups(UserDTO user);
	
	/**
	 * Add a new group to the address book
	 * @param group the dto that represents the group to add
	 * @return <code>true<code> if the group was successfully created, false otherwise
	 */
	public boolean addGroup(GroupDTO group);
}
