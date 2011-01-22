package bean;

public class GroupBean {
	private int id;
	private String groupName;
	private UserBean user = null;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return groupName;
	}
	public void setName(String groupName) {
		this.groupName = groupName;
	}
	public UserBean getUser() {
		return user;
	}
	public void setUser(UserBean user) {
		this.user = user;
	}
}
