package uz.epam.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.epam.springmvc.bean.Login;
import uz.epam.springmvc.bean.User;
import uz.epam.springmvc.exception.ApplicationException;
import uz.epam.springmvc.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@SessionAttributes("login")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private UserRepository userRepository;

    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public String login(@ModelAttribute(name = "login")Login login, Model model, HttpSession httpSession)
            throws ApplicationException {
//        Optional<User> optionalUser = userRepository.findByUsernameAndPassword(login.getUsername(), login.getPassword());
//        if (optionalUser.isPresent()) {
//            model.addAttribute("userFromLogin", optionalUser.get());
//            return "index";
//        }else {
//            throw new IllegalArgumentException("this is illegal argument here");
//        }
//        return "search";
        User user = userRepository.searchByUsernameAndPassword(login.getUsername(), login.getPassword());
        if (user == null) {
            throw new ApplicationException("user not found");
        }
        model.addAttribute("userFromLogin", user);
            return "forward:/userprofile";
    }

//    @ExceptionHandler({ApplicationException.class, RuntimeException.class})
//    public String handleException() {
//        logger.info("in exception handler of login controller");
//        return "error";
//    }
}
