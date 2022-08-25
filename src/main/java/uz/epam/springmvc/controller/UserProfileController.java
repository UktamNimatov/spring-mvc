package uz.epam.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import uz.epam.springmvc.bean.Login;

import javax.servlet.http.HttpSession;

@Controller
public class UserProfileController {
    private static final Logger logger = LoggerFactory.getLogger(UserProfileController.class);

    @PostMapping("/userprofile")
    public String getUserProfile(@SessionAttribute("login")Login login, Model model, HttpSession session) {
        logger.info("in user profile controller class");
        logger.info("username is " + login.getUsername());
        logger.info("password is " + login.getPassword());
        model.addAttribute("username", login.getUsername());
        session.setAttribute("item", session.getServletContext().getServerInfo());
        return "profile";
    }
}
