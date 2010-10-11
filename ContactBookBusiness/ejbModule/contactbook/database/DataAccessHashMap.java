package contactbook.database;

import java.util.*;

import javax.ejb.Local;
import javax.ejb.Stateless;

import contacbook.data.Contact;

@Stateless
@Local(DataAccess.class)
public class DataAccessHashMap implements DataAccess {
	private static DataAccessHashMap instance = new DataAccessHashMap();
	private Map<Integer, Contact> contacts;
	private Map<String, String> accounts;
	private boolean isLoggedIn;
	
	private DataAccessHashMap () {
		contacts = new Hashtable<Integer, Contact>();
		accounts = new Hashtable<String, String>();
	}
	
	public static DataAccessHashMap getInstance() {
		return instance;
	}
	
	public void addContact (Contact c) {
		if(contacts.containsKey(c.getId()))
			throw new IllegalArgumentException("This contact is already in the address book.");
		contacts.put(c.getId(), c);
	}
	
	public void removeContact (Contact c) {
		contacts.remove(c.getId());
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		Iterator<Contact> it = contacts.values().iterator();
		while(it.hasNext())
			sb.append(it.next()).append(System.getProperty("line.separator"));
		
		return sb.toString();
	}

	public Iterator<Contact> findBy(int field, Object value) {
		List<Contact> l = new ArrayList<Contact>();
		
		switch(field) {
		case DataAccess.ID:
			l.add(contacts.get(value));
			break;
		default:
			throw new IllegalArgumentException("This field does not exists.");
		}
		
		return l.iterator();
	}

	public Iterator<Contact> getContacts() {
		return contacts.values().iterator();
	}

	public void createAccount(String username, String password) {
		if(accounts.containsKey(username))
			throw new IllegalArgumentException("This account already exists.");
		accounts.put(username, password);
	}

	public void login(String username, String password) {
		if(accounts.containsKey(username) && accounts.get(username).equals(password))
			isLoggedIn = true;
	}

	public void logout() {
		isLoggedIn = false;
	}
}
