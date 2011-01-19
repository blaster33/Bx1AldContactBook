package contactbook.webapp.client.business;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import contactbook.model.*;

public interface ContactBookBusinessServiceAsync {
	public void getContacts(User user, AsyncCallback<List<Contact>> callback);
	
	public void getGroups(User user, AsyncCallback<List<Group>> callback);
}
