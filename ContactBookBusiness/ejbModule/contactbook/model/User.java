package contactbook.model;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User implements Serializable {
	private static final long serialVersionUID = 6377250892589200980L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(nullable=false)
	private boolean isAdmin;
	
	@Column(unique=true, nullable=false)
	private String loginName;
	
	@Column(nullable=false)
	private String password;
	
	@Column(nullable=true)
	private long lastLogin;

	public User() {
		
	}
	
	public User(String login, String password, boolean isAdmin) {
		this.loginName = login;
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			this.password = convertToHex(md.digest(password.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		this.isAdmin = isAdmin;
	}
	
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

	public long getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(long lastLogin) {
		this.lastLogin = lastLogin;
	}

	public boolean checkPassword(String pass) {
		String sha1 = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			sha1 = convertToHex(md.digest(pass.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return sha1.equals(password);
	}

	private static String convertToHex(byte[] data) { 
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < data.length; i++) { 
			int halfbyte = (data[i] >>> 4) & 0x0F;
			int two_halfs = 0;
			do { 
				if ((0 <= halfbyte) && (halfbyte <= 9)) 
					buf.append((char) ('0' + halfbyte));
				else 
					buf.append((char) ('a' + (halfbyte - 10)));
				halfbyte = data[i] & 0x0F;
			} while(two_halfs++ < 1);
		} 
		return buf.toString();
	}
}
