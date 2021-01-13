package hau.kute.spring.tutorial.springbootmicroservice.shared;

import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class UserDTO extends RepresentationModel<UserDTO> implements Serializable {

	private static final long serialVersionUID = -5365852666102552068L;

	private String userId;

	@NotNull(message = "Name can not be blank")
	@Size(min = 5, message = "Name should have atleast 5 characters.")
	private String userName;

	private Date birthDate;

	@NotNull(message = "Password can not be blank")
	private String password;

	@NotNull(message = "Email can not be blank")
	@Email
	private String email;

	public UserDTO(){
	}

	public UserDTO(
					String userId, String userName, Date birthDate, String
					password, String
					email) {

		this.userId = userId;
		this.userName = userName;
		this.birthDate = birthDate;
		this.password = password;
		this.email = email;
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