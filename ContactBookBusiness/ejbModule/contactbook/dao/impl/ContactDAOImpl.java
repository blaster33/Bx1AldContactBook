package contactbook.dao.impl;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import contactbook.dao.ContactDAO;
import contactbook.model.Contact;

@Stateless
@Local(ContactDAO.class)
public class ContactDAOImpl implements ContactDAO {
	private static ContactDAOImpl instance = new ContactDAOImpl();

	@PersistenceContext(unitName="ContactBookPU")
	protected EntityManager em;

	public static ContactDAOImpl getInstance() {
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
		em.merge(c);
		em.flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> getContacts() {
		return em.createQuery("SELECT c from Contact c").getResultList();
	}
}
