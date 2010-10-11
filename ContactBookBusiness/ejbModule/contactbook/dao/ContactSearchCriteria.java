package contactbook.dao;

import java.util.Calendar;

import contacbook.model.Contact;

public class ContactSearchCriteria {
	protected Contact c;
	
	public ContactSearchCriteria() {
		c = new Contact();
	}
	
	public boolean match(Contact c) {
		boolean match = true;
		
		if(c.id)
		
		return false;
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

	public Calendar getDateOfBirth() {
		return c.getDateOfBirth();
	}

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

	public void setDateOfBirth(Calendar dateOfBirth) {
		c.setDateOfBirth(dateOfBirth);
	}

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
}
