package uz.epam.springmvc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uz.epam.springmvc.bean.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}
