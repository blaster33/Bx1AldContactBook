package contactbook.dao;

import java.util.List;

import contactbook.model.Contact;

public interface ContactDAO {
	public static final int ID = 0x0;
	public static final int FIRST_NAME = 0x1;
	public static final int LAST_NAME = 0x2;
	
	public void addContact(Contact c);

	public void removeContact(Contact c);
	
	public void updateContact(Contact c);
	
	public List<Contact> getContacts();

	public List<Contact> findBy(ContactSearchCriteria criteria);
}