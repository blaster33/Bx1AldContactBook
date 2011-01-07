package contactbook.webapp.client.auth;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("auth")
public interface ContactBookAuthService extends RemoteService {
	/**
	 * Check the login info and register the session if they are correct
	 * @param username the account login name
	 * @param password the account password in clear text
	 * @return a text response form the server
	 */
	boolean login(String username, String password);
	
	/**
	 * Ask the server to destroy the session info in order to log out the current user
	 * @throws RuntimeException if no current user is logged in
	 * @return a text response from the server
	 */
	boolean logout() throws RuntimeException;
	
	/**
	 * Create a new user account
	 * @param username the account login name
	 * @param password the account password
	 * @param email the account email address
	 * @return a text response form the server
	 */
	boolean createAccount(String username, String password, String email);
	
	/**
	 * Can only be called by an authentified admin
	 * @param username the account login name
	 * @param password the account password
	 * @param email the account email address
	 * @param isAdmin indicates if the new user must get admin permissions
	 * @return a text response from the server
	 */
	boolean createAccount(String username, String password, String email, boolean isAdmin);
	
	/**
	 * Checks whether a given username is available, that is if no user already
	 * registered using this login name.
	 * @param username the login name to test against
	 * @return true if the login name is available, false otherwise
	 */
	boolean usernameAvailable(String username);
	
	
	/**
	 * Indicates whether the client is logged in
	 * @return true if the client is logged in, false otherwise
	 */
	boolean isLoggedIn();
}
