package contactbook.dao.impl;

import java.util.List;

import javax.annotation.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import contactbook.dao.GroupDAO;
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
	
	@EJB
	protected GroupDAO groupDAO;
	
	private UserDAOImpl () {
		groupDAO = GroupDAOImpl.getInstance();
	}

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

		try {
			em.remove(user);
			List<Group> groups = getGroup(user);
			
			for(Group g: groups)
				groupDAO.removeGroup(g, true);
			//user.setDefaultGroup(null);
			//user = userDAO.updateUser(user);
			em.flush();
		}
		catch(Exception e) {
			System.err.println("-------------- Exception in removeUser");
			e.printStackTrace();
		}
	}
	
	@Override
	public User updateUser(User user) {
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
	public List<User> getUsers() {
		return em.createQuery("from User").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getGroup(User user) {
		Query query = em.createQuery("SELECT g from Group g WHERE g.user = :user");
		query.setParameter("user", user);
		return query.getResultList();
	}

	@Override
	public boolean loginNameTaken(String loginName) {
		return getUserByName(loginName) != null;
	}

	@Override
	public User getUserByName(String loginName) {
		Query query = em.createQuery("SELECT u from User u WHERE u.loginName = :login");
		query.setParameter("login", loginName);
		List res = query.getResultList();
		if(res.size() != 1)
			return null;
		return (User) res.get(0);
	}
}
