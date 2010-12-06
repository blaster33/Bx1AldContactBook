package contactbook.dao.impl;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import contactbook.dao.UserDAO;
import contactbook.model.Contact;
import contactbook.model.Group;
import contactbook.model.User;

@Stateless
@Local(UserDAO.class)
public class UserDAOImpl implements UserDAO {
	private static UserDAOImpl instance = new UserDAOImpl();

	@PersistenceContext(unitName="ContactBookPU")
	protected EntityManager em;

	public static UserDAOImpl getInstance() {
		return instance;
	}

	@Override
	public void addUser(User user) {
		em.persist(user);
	}

	@Override
	public void removeUser(User user) {
		em.remove(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> getContacts(User user) {
		Query query = em.createQuery("SELECT c from Contact c WHERE c.user = :user");
		query.setParameter("user", user);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getGroup(User user) {
		Query query = em.createQuery("SELECT g from Group g WHERE g.user = :user");
		query.setParameter("user", user);
		return query.getResultList();
	}

	@Override
	public void updateUser(User user) {
		em.merge(user);
		em.flush();
	}

}
