package contactbook.dao;

import java.util.Iterator;

import contactbook.model.Contact;

public interface ContactDAO {
	public static final int ID = 0x0;
	public static final int FIRST_NAME = 0x1;
	public static final int LAST_NAME = 0x2;
	
	public void addContact(Contact c);

	public void removeContact(Contact c);
	
	public Iterator<Contact> getContacts();

	public Iterator<Contact> findBy(ContactSearchCriteria criteria);
}