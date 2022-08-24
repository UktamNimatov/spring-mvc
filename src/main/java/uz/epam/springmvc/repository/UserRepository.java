package uz.epam.springmvc.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.epam.springmvc.bean.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByUsernameAndPassword(String username, String password);

    @Query("select u from User u where u.username= :username and u.password= :password")
    User searchByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
