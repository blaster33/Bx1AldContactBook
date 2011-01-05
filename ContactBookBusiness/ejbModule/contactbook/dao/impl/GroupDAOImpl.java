package contactbook.dao.impl;

import java.util.List;

import javax.annotation.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import contactbook.dao.ContactDAO;
import contactbook.dao.GroupDAO;
import contactbook.model.Contact;
import contactbook.model.Group;

@Stateless
@Local(GroupDAO.class)
public class GroupDAOImpl implements GroupDAO {
	private static GroupDAOImpl instance = new GroupDAOImpl();

	@PersistenceContext(unitName="ContactBookPU")
	protected EntityManager em;
	
	@EJB
	protected ContactDAO contactDAO;
	
	private GroupDAOImpl () {
		contactDAO = ContactDAOImpl.getInstance();
	}

	public static GroupDAOImpl getInstance() {
		return instance;
	}

	@Override
	public Group addGroup(Group g) {
		if(alreadyExists(g))
			return null;
		try {
			em.persist(g);
		} catch(Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return g;
	}

	@Override
	public void removeGroup(Group group, boolean removeContact) {
		em.remove(group);
		List<Contact> contacts = getContacts(group);
		for(Contact c: contacts) {
			if ( removeContact )
			{
				contactDAO.removeContact(c);
			}
			else
			{
				c.setGroup(null);
				contactDAO.updateContact(c);
			}
		}
		em.flush();
	}
	
	@Override
	public Group updateGroup(Group g) {
		if(alreadyExists(g))
			return null;

		em.merge(g);
		em.flush();
		return g;
	}
	
	@Override
	public Group refreshGroup(Group g) {
		em.refresh(g);
		return g;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> getContacts(Group group) {
		Query query = em.createQuery("SELECT c from Contact c WHERE c.group = :group");
		query.setParameter("group", group);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getGroups() {
		return em.createQuery("SELECT g from Group g").getResultList();
	}

	private boolean alreadyExists(Group g) {
		Query query = em.createQuery("SELECT g from Group g WHERE g.user = :user AND g.groupName = :name");
		query.setParameter("user", g.getUser());
		query.setParameter("name", g.getName());
		return query.getResultList().size() > 0;
	}
}
