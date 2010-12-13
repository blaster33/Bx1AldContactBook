package contactbook.dao;

import java.util.List;

import contactbook.model.Contact;

public interface ContactDAO {
	public Contact addContact(Contact c);

	public void removeContact(Contact c);
	
	public Contact updateContact(Contact c);
	
	public Contact refreshContact(Contact c);
	
	public List<Contact> getContacts();
}