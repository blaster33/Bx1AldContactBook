package contactbook.service.impl;

import java.util.List;

import javax.annotation.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import contactbook.dao.ContactDAO;
import contactbook.dao.ContactSearchCriteria;
import contactbook.dao.impl.ContactDAOMysql;
import contactbook.model.Contact;
import contactbook.service.ContactServiceRemote;

@WebService(serviceName="ContactBookService")
@Stateless
@Remote(ContactServiceRemote.class)
public class ContactServiceImpl implements ContactServiceRemote {
	@EJB
	protected ContactDAO data;
	
	public ContactServiceImpl() {
		data = ContactDAOMysql.getInstance();
	}
	
	@WebMethod
	public Contact addContact(Contact c) {
		data.addContact(c);
		return c;
	}

	@WebMethod
	public List<Contact> getContacts() {
		return data.getContacts();
	}

	@WebMethod
	public void removeContact(Contact c) {
		data.removeContact(c);
	}

	@WebMethod
	public List<Contact> findBy(ContactSearchCriteria criteria) {
		return data.findBy(criteria);
	}
}
