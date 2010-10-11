package contacbook.data;

import java.util.Calendar;

public class Contact {
	static private int nContact = 0;
	
	private int id;
	private String lastName;
	private String firstName;
	private String homePhone;
	private String cellPhone;
	private String email;
	private String address1;
	private String address2;
	private String zipCode;
	private String city;
	private String state;
	private String country;
	private Calendar dateOfBirth;
	
	public Contact () {
		id = nContact++;
	}
	
	public int getId () {
		return id;
	}
	
	public String getLastName () {
		return lastName;
	}
	
	public String getFirstName () {
		return firstName;
	}
	
	public void setLastName (String lastName) {
		this.lastName = lastName;
	}
	
	public void setFirstName (String firstName) {
		this.firstName = firstName;
	}
	
	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public void setDateOfBirth(Calendar dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Calendar getDateOfBirth() {
		return dateOfBirth;
	}

	public String toString() {
		String newline = System.getProperty("line.separator");
		StringBuffer sb = new StringBuffer(String.format("%d\t%s %s", id, lastName, firstName));
		
		sb.append(newline);
		sb.append("Address: " + newline +  address1 + newline + (address2.equals("")? "":address2 + newline));
		sb.append(String.format("%s %s (%s)", zipCode, city, state)).append(newline);
		sb.append(country + newline);
		sb.append("Home: " + homePhone + newline);
		sb.append("Cellphone: " + cellPhone + newline);
		sb.append("Email: " + email + newline);
		sb.append("Born: " + String.format("%d/%02d/%d", dateOfBirth.get(Calendar.DAY_OF_MONTH), dateOfBirth.get(Calendar.MONTH)+1, dateOfBirth.get(Calendar.YEAR)) + newline);

		return sb.toString();
	}
}
