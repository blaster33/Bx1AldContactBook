package contactbook.webapp.shared;

public class Message {
	public static final String SERVER_ERROR = "An error occurred while "
		+ "attempting to contact the server. Please check your network "
		+ "connection and try again.";
	
	public static final String INVALID_LOGIN_INFO = "Invalid username or password!";
	public static final String LOGIN_SUCCESSFUL = "You are now logged in.";
	
	public static final String ACCOUNT_CREATE_FAILURE = "An error has occured while creating the account.<br >" +
		"Please try again.";
	public static final String ACCOUNT_CREATE_SUCCESSFUL = "Your account has been successfully created.<br />" +
		"You may now login using your username and password.";
	
	/*
	 * Form validation
	 */
	public static final String EMPTY_LOGIN = "Username cannot be empty";
	public static final String LOGIN_TAKEN = "This username is not available";
	public static final String LOGIN_AVAILABLE = "This username is available";
	public static final String EMPTY_PASWORD = "Password must not be empty";
	public static final String PASSWORD_TOO_SHORT = "Password must be at least 6 characters";
	public static final String CONFIRM_PASSWORD = "Please confirm password";
	public static final String PASSWORDS_DIFFERENT = "Passwords are different";
	public static final String EMPTY_EMAIL = "Email address cannot be empty";
	public static final String CONFIRM_EMAIL = "Please confirm your email address";
	public static final String EMAILS_DIFFERENT = "Email addresses are different";
	public static final String INVALID_EMAIL = "Please provide a valid email address";
	
	/*
	 * Various strings
	 */
	public static final String LOGOUT = "Logout";
	public static final String LOGOUT_ERROR = "Unable to logout.\nPlease try again.";
}
