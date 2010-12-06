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
	//private ContactSearchCriteria searchCriteria;
	
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
		//searchCriteria = new ContactSearchCriteria();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testAddContact() throws Exception {
		User user = new User("antho", "toto", true);
		contactService.addUser(user);
		
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
//		c.setDateOfBirth(cal);
		c.setCellPhone("0627498448");
		c.setHomePhone("0556391930");
		c.setEmail("blaster33300@hotmail.com");
		
		Group group = new Group(user, "Famille");
		c.setGroup(group);
		contactService.addGroup(group);
		contactService.addContact(c);
		
		Contact c2 = contactService.getContactsByUser(user).get(0);
		Assert.assertTrue("Id valuated", c2.getId() > 0);
		
		c2 = contactService.getContactsByGroup(contactService.getGroupsByUser(user).get(0)).get(0);
		Assert.assertTrue("Id valuated", c2.getId() > 0);
		
		contactService.removeGroup(group, false);
		
		Group newGroup = new Group(user, "New group");
		c.setGroup(newGroup);
		contactService.updateContact(c);
		
		contactService.removeGroup(newGroup, true);
		
		
		Assert.assertTrue(contactService.getContactsByUser(user).isEmpty());
	}
}
