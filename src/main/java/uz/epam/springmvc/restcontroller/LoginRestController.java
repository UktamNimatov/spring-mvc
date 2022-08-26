package uz.epam.springmvc.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uz.epam.springmvc.bean.Login;
import uz.epam.springmvc.bean.User;
import uz.epam.springmvc.exception.LoginFailureException;
import uz.epam.springmvc.repository.UserRepository;

import java.util.Optional;

@RestController
public class LoginRestController {
    private static final Logger logger = LoggerFactory.getLogger(LoginRestController.class);
    private UserRepository userRepository;

    @Autowired
    public LoginRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/hplus/rest/loginuser")
    public ResponseEntity loginUser(@RequestBody Login login) throws LoginFailureException {
        Optional<User> optionalUser = userRepository.findByUsernameAndPassword(login.getUsername(), login.getPassword());
        if (optionalUser.isPresent()) {
            return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
        }else {
            throw new LoginFailureException();
        }
    }
}
