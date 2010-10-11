package contactbook.service;

import javax.annotation.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import contacbook.data.Contact;
import contactbook.database.DataAccess;
import contactbook.database.DataAccessHashMap;

@Stateless
@Remote(ContactServiceRemote.class)
public class ContactServiceImpl implements ContactServiceRemote {
	@EJB
	protected DataAccess data;
	
	public ContactServiceImpl() {
		data = DataAccessHashMap.getInstance();
	}
	
	public Contact addContact(Contact c) {
		data.addContact(c);
		return c;
	}
}
