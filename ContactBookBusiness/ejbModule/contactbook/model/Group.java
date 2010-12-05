package contactbook.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "contact_group")
public class Group implements Serializable {
	private static final long serialVersionUID = -4999296189458099485L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String groupName;
	
	public Group(String name) {
		setName(name);
	}
	
	public Group() {
		this("");
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
