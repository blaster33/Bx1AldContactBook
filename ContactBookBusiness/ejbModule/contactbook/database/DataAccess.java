package contactbook.database;

import java.util.Iterator;

import contacbook.data.Contact;

public interface DataAccess {
	public static final int ID = 0x0;
	public static final int FIRST_NAME = 0x1;
	public static final int LAST_NAME = 0x2;

	public void addContact(Contact c);

	public void removeContact(Contact c);
	
	public Iterator<Contact> getContacts();

	public Iterator<Contact> findBy(int field, Object value);
	
	public void login(String username, String password);
	
	public void logout();
	
	public void createAccount(String username, String password);
}