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
	public static final String EMPTY_GROUP_NAME = "Group name cannot be empty";
	public static final String EMPTY_FIRST_NAME = "First name cannot be empty!";
	public static final String EMPTY_LAST_NAME = "Last name cannot be empty!";
	public static final String INVALID_DATE = "The provided date is invalid!";
	
	/*
	 * Groups
	 */
	public static final String GROUP = "Group";
	public static final String GROUP_NAME = "Group name";
	public static final String GROUP_NEW = "New group";
	public static final String ERROR_LOADING_GROUP_LIST = "An error has occured: unable to load group list";
	
	/*
	 * Contacts
	 */
	public static final String CONTACT_CREATE_SUCCESSFUL = "The new contact has been created.";
	public static final String CONTACT_UPDATE_SUCCESSFUL = "The contact has been updated.";
	public static final String CONTACT_NEW = "New contact";
	public static final String CONTACT = "Contact";
	public static final String CONTACT_LAST_NAME = "Last name";
	public static final String CONTACT_FIRST_NAME = "First name";
	public static final String CONTACT_HOME_PHONE = "Home phone";
	public static final String CONTACT_CELL_PHONE = "Cell phone";
	public static final String CONTACT_EMAIL = "Email address";
	public static final String CONTACT_ADDRESS = "Address";
	public static final String CONTACT_ZIP_CODE = "Zip code";
	public static final String CONTACT_CITY = "City";
	public static final String CONTACT_STATE = "State";
	public static final String CONTACT_COUNTRY = "Country";
	public static final String CONTACT_DOB = "Date of birth";
	
	/*
	 * Various strings
	 */
	public static final String BY_GROUP = "Groups";
	public static final String ALL = "All";
	public static final String SAVE = "Save";
	public static final String ERROR = "Error";
	public static final String LOGOUT = "Logout";
	public static final String ERROR_LOGOUT = "Unable to logout.\nPlease try again.";
	public static final String ERROR_LOADING_CONTACTS = "Unable to load contacts from server!";
	public static final String ERROR_SAVING_GROUP = "An error occured: unable to save the group.";
}
