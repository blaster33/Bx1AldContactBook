package contactbook.webapp.client.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UserDTO implements IsSerializable {
	private int id;
	private boolean isAdmin;
	private String loginName;
	private String email;
	private long lastLogin;
	private GroupDTO defaultGroup;

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

	public GroupDTO getDefaultGroup() {
		return defaultGroup;
	}

	public void setDefaultGroup(GroupDTO defaultGroup) {
		this.defaultGroup = defaultGroup;
	}
}
