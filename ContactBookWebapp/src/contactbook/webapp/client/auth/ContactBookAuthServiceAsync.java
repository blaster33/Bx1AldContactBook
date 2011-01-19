package contactbook.webapp.client.auth;

import com.google.gwt.user.client.rpc.AsyncCallback;

import contactbook.model.User;
import contactbook.webapp.client.dto.UserDTO;

public interface ContactBookAuthServiceAsync {
	void login(String username, String password, AsyncCallback<UserDTO> callback);
	
	void logout(AsyncCallback<Boolean> callback) throws RuntimeException;
	
	void createAccount(String username, String password, String email, AsyncCallback<Boolean> callback);

	void createAccount(String username, String password, String email, boolean isAdmin, AsyncCallback<Boolean> callback);

	void usernameAvailable(String username, AsyncCallback<Boolean> callback);
	
	void isLoggedIn(AsyncCallback<Boolean> callback);
}
