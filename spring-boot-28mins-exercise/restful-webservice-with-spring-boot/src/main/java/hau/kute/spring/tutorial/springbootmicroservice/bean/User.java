package hau.kute.spring.tutorial.springbootmicroservice.bean;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Size;
import java.util.Date;

@JsonFilter("UserFilter")
public class User extends RepresentationModel<User> {

    private Integer id;

    @Size(min = 5, message = "Name should have atleast 5 characters.")
    private String name;

    private Date birthDate;

    @JsonIgnore
    private String password;

    public User() {

    }

    public User(Integer id, String name, Date birthDate, String password) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
