package hau.kute.spring.tutorial.springbootmicroservice.bean;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@JsonFilter("UserFilter")
public class User extends RepresentationModel<User> {

    private Integer id;

    @NotNull(message = "Name can not be blank")
    @Size(min = 5, message = "Name should have atleast 5 characters.")
    private String userName;

    private Date birthDate;

    @NotNull(message = "Password can not be blank")
    private String password;

    @NotNull(message = "Email can not be blank")
    @Email
    private String email;

    public User() {

    }

    public User(Integer id, String name, Date birthDate, String password,
                    String email) {
        this.id = id;
        this.userName = name;
        this.birthDate = birthDate;
        this.password = password;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
