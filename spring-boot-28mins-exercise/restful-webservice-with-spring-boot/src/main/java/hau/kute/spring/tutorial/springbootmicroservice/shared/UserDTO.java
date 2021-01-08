package hau.kute.spring.tutorial.springbootmicroservice.shared;

import java.io.Serializable;
import java.util.Date;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = -5365852666102552068L;
	private String userId;
	private String userName;
	private Date birthDate;
	private String password;
	private String email;
	private String encryptedPassword;

	public UserDTO(){
	}

	public UserDTO(
					String userId, String userName, Date birthDate, String
					password, String
					email, String encryptedPassword) {

		this.userId = userId;
		this.userName = userName;
		this.birthDate = birthDate;
		this.password = password;
		this.email = email;
		this.encryptedPassword = encryptedPassword;
	}

	public String getUserId() {

		return userId;
	}

	public void setUserId(String userId) {

		this.userId = userId;
	}

	public String getUserName() {

		return userName;
	}

	public void setUserName(String userName) {

		this.userName = userName;
	}

	public String getEncryptedPassword() {

		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {

		this.encryptedPassword = encryptedPassword;
	}

	public Date getBirthDate() {

		return birthDate;
	}

	public void setBirthDate(Date birthDate) {

		this.birthDate = birthDate;
	}

	public String getPassword() {

		return password;
	}

	public void setPassword(String password) {

		this.password = password;
	}

	public String getEmail() {

		return email;
	}

	public void setEmail(String email) {

		this.email = email;
	}
}
