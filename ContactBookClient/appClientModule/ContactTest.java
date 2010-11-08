import java.util.Calendar;
import java.util.Iterator;

import junit.framework.Assert;
import junit.framework.TestCase;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


import contactbook.dao.ContactSearchCriteria;
import contactbook.model.Contact;
import contactbook.service.ContactServiceRemote;

public class ContactTest extends TestCase {
	private ContactServiceRemote contactService;
	private ContactSearchCriteria searchCriteria;
	
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
		searchCriteria = new ContactSearchCriteria();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testAddContact() throws Exception {
		Contact c = new Contact();
		c.setFirstName("Florian");
		c.setLastName("Blois");
		c.setAddress1("3 rue Henri Expert");
		c.setAddress2("Appt. 24");
		c.setZipCode("33300");
		c.setCity("Bordeaux");
		c.setState("Gironde");
		c.setCountry("FRANCE");
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(1988, Calendar.SEPTEMBER, 22);
//		c.setDateOfBirth(cal);
		c.setCellPhone("0627498448");
		c.setHomePhone("0556391930");
		c.setEmail("blaster33300@hotmail.com");
		
		System.out.println(c);
		
		try {
			c = contactService.addContact(c);
			Assert.assertTrue("Id valuated", c.getId() > 0);
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
		
//		searchCriteria.setFirstName("flo");
//		Iterator<Contact> it = contactService.findBy(searchCriteria);
//		Contact c2 = it.next();
//		Assert.assertEquals(c2, c);
//		
//		Assert.assertEquals(c2.getFirstName(), "Florian");
//		Assert.assertEquals(c2.getLastName(), "Blois");
//		Assert.assertEquals(c2.getAddress1(), "3 rue Henri Expert");
//		Assert.assertEquals(c2.getAddress2(), "Appt. 24");
//		Assert.assertEquals(c2.getZipCode(), "33300");
//		Assert.assertEquals(c2.getCity(), "Bordeaux");
//		Assert.assertEquals(c2.getDateOfBirth().get(Calendar.DAY_OF_MONTH), 22);
//		Assert.assertEquals(c2.getDateOfBirth().get(Calendar.MONTH), Calendar.SEPTEMBER);
//		Assert.assertEquals(c2.getDateOfBirth().get(Calendar.YEAR), 1988);
//		Assert.assertEquals(c2.getCellPhone(), "0627498448");
//		Assert.assertEquals(c2.getHomePhone(), "0556391930");
//		Assert.assertEquals(c2.getEmail(), "blaster33300@hotmail.com");
//		
//		searchCriteria.clear();
//		searchCriteria.setLastName("xxx");
//		it = contactService.findBy(searchCriteria);
//		Assert.assertTrue(!it.hasNext());
//		
//		c = new Contact();
//		c.setFirstName("Anthony");
//		c.setLastName("Simonet");
//		contactService.addContact(c);
//		
//		Assert.assertEquals(c.getFirstName(), "Anthony");
//		Assert.assertEquals(c.getLastName(), "Simonet");
	}
}
