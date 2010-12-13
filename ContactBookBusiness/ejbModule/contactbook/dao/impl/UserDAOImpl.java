package contactbook.dao.impl;

import java.util.List;

import javax.ejb.Local;
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
	public User addUser(User user) {
		if(loginNameTaken(user.getLoginName()))
			return null;
		em.persist(user);
		return user;
	}

	@Override
	public void removeUser(User user) {
		em.remove(user);
	}
	
	@Override
	public User updateUser(User user) {
		if(loginNameTaken(user.getLoginName()))
			return null;

		em.merge(user);
		em.flush();
		return user;
	}
	
	@Override
	public User refreshUser(User user) {
		em.refresh(user);
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> getContacts(User user) {
		List<Contact> list = null;
		try {
			Query query = em.createQuery("SELECT c from Contact c WHERE c.user = :user");
			query.setParameter("user", user);
			list =  query.getResultList();
			
		} catch(Exception e) {
			System.err.println("------------------------------------------");
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getGroup(User user) {
		Query query = em.createQuery("SELECT g from Group g WHERE g.user = :user");
		query.setParameter("user", user);
		return query.getResultList();
	}

	private boolean loginNameTaken(String loginName) {
		Query query = em.createQuery("SELECT u from User u WHERE u.loginName = :login");
		query.setParameter("login", loginName);
		return query.getResultList().size() > 0;
	}
}
