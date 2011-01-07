

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import junit.framework.Assert;
import junit.framework.TestCase;
import contactbook.model.Contact;
import contactbook.model.Group;
import contactbook.model.User;
import contactbook.service.ContactServiceRemote;

public class ContactTest extends TestCase {
	private ContactServiceRemote contactService;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		try {
			Context ctx = new InitialContext();
			contactService = (ContactServiceRemote) ctx.lookup(ContactServiceRemote.JNDI);
		}
		catch(NamingException e) {
			System.err.println(e.getMessage());
			System.err.println(e.getExplanation());
			e.printStackTrace();
		}
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
		for(User user: contactService.getUsers())
			contactService.removeUser(user);
	}
	
	public void testAddContact() throws Exception {
		User user = new User("antho", "toto", "antho@me.com", true);
		user = contactService.addUser(user);
		assertTrue("User id validated", user.getId() > 0);
		
		Contact c = new Contact(user);
		c.setFirstName("Florian");
		c.setLastName("Blois");
		c.setAddress1("3 rue Henri Expert");
		c.setAddress2("Appt. 24");
		c.setZipCode("33300");
		c.setCity("Bordeaux");
		c.setState("Gironde");
		c.setCountry("FRANCE");
		//Calendar cal = Calendar.getInstance();
		//cal.clear();
		//cal.set(1988, Calendar.SEPTEMBER, 22);
		//c.setDateOfBirth(cal);
		c.setCellPhone("0627498448");
		c.setHomePhone("0556391930");
		c.setEmail("blaster33300@hotmail.com");
		
		
		// Insert a new group
		Group group = new Group(user, "Famille");
		group = contactService.addGroup(group);
		Assert.assertTrue("group id validated", group.getId() > 0);

		c.setGroup(group);
		c = contactService.addContact(c);
		Assert.assertTrue("contact id valuated", c.getId() > 0);
		
		Contact c2 = contactService.getContactsByUser(user).get(0);
		Assert.assertTrue("contact retrieved", c2.getId() > 0);

		contactService.removeGroup(group, false);
		c = contactService.refreshContact(c);
		
		// Assign a new group to the contact
		Group newGroup = new Group(user, "New group");
		newGroup = contactService.addGroup(newGroup);
		c.setGroup(newGroup);
		c = contactService.updateContact(c);
		Assert.assertTrue("group id validated", newGroup.getId() > 0);
		Assert.assertTrue("group validated for contact", c.getGroup().getId() > 0);
		
		// Try to add an new user with the same login name
		User newUser = new User("antho", "1234", "antho@simonet.com", false);
		newUser = contactService.addUser(newUser);
		
		Assert.assertNull("check identical user insert failed", newUser);
		
		contactService.removeGroup(newGroup, true);
		Assert.assertTrue(contactService.getContactsByUser(user).isEmpty());
		contactService.removeUser(user);
	}
	
	public void testDefaultGroup() throws Exception {
		User user = new User("antho", "tata", "az@tg.fr", true);
		user = contactService.addUser(user);
		assertTrue("User id validated", user.getId() > 0);
		
		Contact c = new Contact(user);
		c.setFirstName("Florian");
		c.setLastName("Blois");
		c.setAddress1("3 rue Henri Expert");
		c.setAddress2("Appt. 24");
		c.setZipCode("33300");
		c.setCity("Bordeaux");
		c.setState("Gironde");
		c.setCountry("FRANCE");
		//Calendar cal = Calendar.getInstance();
		//cal.clear();
		//cal.set(1988, Calendar.SEPTEMBER, 22);
		//c.setDateOfBirth(cal);
		c.setCellPhone("0627498448");
		c.setHomePhone("0556391930");
		c.setEmail("blaster33300@hotmail.com");
		
		c = contactService.addContact(c);
		Assert.assertTrue("contact id valuated", c.getId() > 0);
		Assert.assertTrue("contact group id valuated", c.getGroup().getId() > 0);
		Assert.assertTrue("user default group id valuated", user.getDefaultGroup().getId() > 0);
		Assert.assertTrue("check contact group id", c.getGroup().getId() == user.getDefaultGroup().getId());
		Assert.assertTrue("check contact group name", c.getGroup().getName().equals("Default"));
	}
}
