package bean;

public class UserBean {
	private int id;
	private boolean isAdmin;
	private String loginName;
	private String email;
	private long lastLogin;
	private GroupBean defaultGroup;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(long lastLogin) {
		this.lastLogin = lastLogin;
	}

	public GroupBean getDefaultGroup() {
		return defaultGroup;
	}

	public void setDefaultGroup(GroupBean defaultGroup) {
		this.defaultGroup = defaultGroup;
	}
}
