package contactbook.webapp.client.business;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import contactbook.model.*;


@RemoteServiceRelativePath("business")
public interface ContactBookBusinessService extends RemoteService {
	/**
	 * Get the list of contacts owned by a given user
	 * @param user the user to get the contacts from
	 * @return a {@link java.util.List} of {@link Contact}
	 */
	public List<Contact> getContacts(User user);
	
	/**
	 * Get the list of groups owned by a given user
	 * @param user the user to get the groups from
	 * @return a {@link java.util.List} of {@link Group}
	 */
	public List<Group> getGroups(User user);
}
