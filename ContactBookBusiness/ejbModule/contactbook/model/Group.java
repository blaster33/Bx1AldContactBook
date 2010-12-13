package contactbook.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="contact_group")
public class Group implements Serializable {
	private static final long serialVersionUID = -4999296189458099485L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(nullable=false, length=32)
	private String groupName;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	private User user = null;
	
	public Group() {
		
	}
	
	public Group(User user, String name) {
		setUser(user);
		setName(name);
	}
	
	private void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setName(String name) {
		this.groupName = name;
	}
	
	public String getName() {
		return groupName;
	}
}
