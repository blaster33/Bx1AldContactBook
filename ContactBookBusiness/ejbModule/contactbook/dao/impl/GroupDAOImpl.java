package contactbook.dao.impl;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import contactbook.dao.GroupDAO;
import contactbook.model.Contact;
import contactbook.model.Group;

@Stateless
@Local(GroupDAO.class)
public class GroupDAOImpl implements GroupDAO {
	private static GroupDAOImpl instance = new GroupDAOImpl();
	
	@PersistenceContext(unitName="ContactBookPU")
	protected EntityManager em;

	public static GroupDAOImpl getInstance() {
		return instance;
	}

	@Override
	public void addGroup(Group g) {
		em.persist(g);
	}

	@Override
	public void removeGroup(Group group, Boolean removeContacts) {
		if(removeContacts) {
			for(Contact c: getContacts(group))
				ContactDAOMysql.getInstance().removeContact(c);
		}
		em.remove(group);
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
}
