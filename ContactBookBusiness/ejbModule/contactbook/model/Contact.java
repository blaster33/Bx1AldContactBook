package contactbook.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.*;

import contactbook.model.Group;

@Entity
@Table(name = "contact")
public class Contact implements Serializable {
	private static final long serialVersionUID = 3167237684976390261L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
	private long dateOfBirth;
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER)
	private Group group = null;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE}, fetch = FetchType.EAGER)
	private User user = null;
	
	public Contact() {
		
	}
	
	public Contact(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setFirstName(String firstName) {
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
	
	public void setDateOfBirth(long dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public long getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setGroup(Group group) {
		this.group = group;
	}
	
	public Group getGroup() {
		return group;
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
		sb.append("Date of birth: " + DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.FRANCE).format(new Date(dateOfBirth * 1000)));

		return sb.toString();
	}
}
