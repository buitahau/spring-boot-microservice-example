package hau.kute.spring.tutorial.springbootmicroservice.bean;

import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Size;
import java.util.Date;

public class User extends RepresentationModel<User> {

    private Integer id;

    @Size(min = 5, message = "Name should have atleast 5 characters.")
    private String name;

    private Date birthDate;

    public User() {

    }

    public User(Integer id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
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
}
