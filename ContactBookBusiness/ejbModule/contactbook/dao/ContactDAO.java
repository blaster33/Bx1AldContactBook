package contactbook.dao;

import java.util.List;

import contactbook.model.Contact;

public interface ContactDAO {
	public void addContact(Contact c);

	public void removeContact(Contact c);
	
	public void updateContact(Contact c);
	
	public List<Contact> getContacts();
}