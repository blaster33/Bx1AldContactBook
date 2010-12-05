package contactbook.dao.impl;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import contactbook.dao.ContactDAO;
import contactbook.dao.ContactSearchCriteria;
import contactbook.model.Contact;

@Stateless
@Local(ContactDAO.class)
public class ContactDAOMysql implements ContactDAO {
	private static ContactDAOMysql instance = new ContactDAOMysql();
	
	@PersistenceContext(unitName="ContactBookPU")
	protected EntityManager em;
	
	public static ContactDAOMysql getInstance() {
		return instance;
	}

	@Override
	public void addContact(Contact c) {
		em.persist(c);
	}

	@Override
	public void removeContact(Contact c) {
		em.remove(c);
	}
	
	@Override
	public void updateContact(Contact c) {
		try {
			em.merge(c);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> getContacts() {
		return em.createQuery("SELECT c from Contact c").getResultList();
	}

	@Override
	public List<Contact> findBy(ContactSearchCriteria criteria) {
		throw new RuntimeException("Not implemented");
		//return null;
	}

}
