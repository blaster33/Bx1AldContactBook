package contactbook.dao.impl;

import java.util.*;

import javax.ejb.Local;
import javax.ejb.Stateless;

import contactbook.dao.ContactDAO;
import contactbook.dao.ContactSearchCriteria;
import contactbook.model.Contact;

@Stateless
@Local(ContactDAO.class)
public class ContactDAOHashMap implements ContactDAO {
	private static ContactDAOHashMap instance = new ContactDAOHashMap();
	private Map<Integer, Contact> contacts;
	
	private ContactDAOHashMap () {
		contacts = new Hashtable<Integer, Contact>();
	}
	
	public static ContactDAOHashMap getInstance() {
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
		case ContactDAO.ID:
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

	public Iterator<Contact> findBy(ContactSearchCriteria criteria) {
		Collection<Contact> list = contacts.values();
		
		for(Contact c: list)
			if(!criteria.match(c))
				list.remove(c);
		
		return list.iterator();
	}
}
