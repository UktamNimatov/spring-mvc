package uz.epam.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import uz.epam.springmvc.bean.Login;
import uz.epam.springmvc.bean.User;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/home")
    public String goHome() {
        logger.info("this is home controller");

        return "index";
    }

    @GetMapping("/goToSearch")
    public String search() {
        logger.info("going to search page");
        return "search";
    }

    @GetMapping("/goToLogin")
    public String goToLogin() {
        logger.info("going to login page");
        return "login";
    }

    @GetMapping("/goToRegistration")
    public String goToRegistration() {
        logger.info("going to registration page");
        return "register";
    }

//    @ModelAttribute("newUser")
//    public User getDefaultUser() {
//        return new User();
//    }
//
//    @ModelAttribute("genderItems")
//    public List<String> getGenderItems() {
//        return Arrays.asList("Male", "Female", "Other");
//    }
//
//    @ModelAttribute("login")
//    public Login getLogin() {
//        return new Login();
//    }
}
