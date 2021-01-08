package hau.kute.spring.tutorial.springbootmicroservice.data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable{

	private static final long serialVersionUID = -266023322390663695L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column(nullable = false, unique = true)
	private String userId;

	@Column(nullable = false)
	private String userName;

	@Column
	private Date birthDate;

	@Column(nullable = false, length = 120, unique = true)
	private String email;

	@Column(nullable = false)
	private String encryptedPassword;

	public UserEntity() {

	}

	public UserEntity(
					String userId, String userName, Date birthDate,
					String email, String encryptedPassword) {

		this.userId = userId;
		this.userName = userName;
		this.birthDate = birthDate;
		this.email = email;
		this.encryptedPassword = encryptedPassword;
	}

	public long getId() {

		return id;
	}

	public void setId(long id) {

		this.id = id;
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

	public Date getBirthDate() {

		return birthDate;
	}

	public void setBirthDate(Date birthDate) {

		this.birthDate = birthDate;
	}

	public String getEmail() {

		return email;
	}

	public void setEmail(String email) {

		this.email = email;
	}

	public String getEncryptedPassword() {

		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {

		this.encryptedPassword = encryptedPassword;
	}
}
