package hau.kute.spring.tutorial.springbootmicroservice.data;

import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {

}