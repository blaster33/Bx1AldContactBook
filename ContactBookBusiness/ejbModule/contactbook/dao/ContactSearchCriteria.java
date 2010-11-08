package contactbook.dao;

import java.util.Calendar;

import contactbook.model.Contact;

public class ContactSearchCriteria {
	protected Contact c;
	
	public ContactSearchCriteria() {
		c = new Contact();
	}
	
	public void clear() {
		c = new Contact();
	}
	
	public boolean match(Contact contact) {
		
		if(c.getAddress1() != null && !containsIgnoreCase(contact.getAddress1(), c.getAddress1()))
			return false;
		
		if(c.getAddress2() != null && !containsIgnoreCase(contact.getAddress2(), c.getAddress2()))
			return false;
		
		if(c.getCellPhone() != null && !containsIgnoreCase(contact.getCellPhone(), c.getCellPhone()))
			return false;
		
		if(c.getCity() != null && !containsIgnoreCase(contact.getCity(), c.getCity()))
			return false;
		
		if(c.getCountry() != null && !containsIgnoreCase(contact.getCountry(), c.getCountry()))
			return false;
		
//		if(c.getDateOfBirth() != null && !contact.getDateOfBirth().equals(c.getDateOfBirth()))
//			return false;
//		
		if(c.getEmail() != null && !containsIgnoreCase(contact.getEmail(), c.getEmail()))
			return false;
		
		if(c.getFirstName() != null && !containsIgnoreCase(contact.getFirstName(), c.getFirstName()))
			return false;
		
		if(c.getHomePhone() != null && !containsIgnoreCase(contact.getHomePhone(), c.getHomePhone()))
			return false;
		
		if(c.getLastName() != null && !containsIgnoreCase(contact.getLastName(), c.getLastName()))
			return false;
		
		if(c.getState() != null && !containsIgnoreCase(contact.getState(), c.getState()))
			return false;
		
		if(c.getZipCode() != null && !containsIgnoreCase(contact.getZipCode(), c.getZipCode()))
			return false;
		
		return true;
	}

	public String getAddress1() {
		return c.getAddress1();
	}

	public String getAddress2() {
		return c.getAddress2();
	}

	public String getCellPhone() {
		return c.getCellPhone();
	}

	public String getCity() {
		return c.getCity();
	}

	public String getCountry() {
		return c.getCountry();
	}

//	public Calendar getDateOfBirth() {
//		return c.getDateOfBirth();
//	}

	public String getEmail() {
		return c.getEmail();
	}

	public String getFirstName() {
		return c.getFirstName();
	}

	public String getHomePhone() {
		return c.getHomePhone();
	}

	public int getId() {
		return c.getId();
	}

	public String getLastName() {
		return c.getLastName();
	}

	public String getState() {
		return c.getState();
	}

	public String getZipCode() {
		return c.getZipCode();
	}

	public void setAddress1(String address1) {
		c.setAddress1(address1);
	}

	public void setAddress2(String address2) {
		c.setAddress2(address2);
	}

	public void setCellPhone(String cellPhone) {
		c.setCellPhone(cellPhone);
	}

	public void setCity(String city) {
		c.setCity(city);
	}

	public void setCountry(String country) {
		c.setCountry(country);
	}

//	public void setDateOfBirth(Calendar dateOfBirth) {
//		c.setDateOfBirth(dateOfBirth);
//	}

	public void setEmail(String email) {
		c.setEmail(email);
	}

	public void setFirstName(String firstName) {
		c.setFirstName(firstName);
	}

	public void setHomePhone(String homePhone) {
		c.setHomePhone(homePhone);
	}

	public void setLastName(String lastName) {
		c.setLastName(lastName);
	}

	public void setState(String state) {
		c.setState(state);
	}

	public void setZipCode(String zipCode) {
		c.setZipCode(zipCode);
	}

	public String toString() {
		return c.toString();
	}
	
	private boolean containsIgnoreCase(String str1, String str2) {
		return str1.toLowerCase().indexOf(str2.toLowerCase()) >= 0;
	}
}
