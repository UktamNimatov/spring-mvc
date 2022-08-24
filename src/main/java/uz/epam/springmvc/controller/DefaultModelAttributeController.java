package uz.epam.springmvc.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import uz.epam.springmvc.bean.Gender;
import uz.epam.springmvc.bean.Login;
import uz.epam.springmvc.bean.User;

import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class DefaultModelAttributeController {

    @ModelAttribute("newUser")
    private User getDefaultUser() {
        return new User();
    }

    @ModelAttribute("login")
    private Login getDefaultLogin() {
        return new Login();
    }

    @ModelAttribute("genderItems")
    private List<String> getGenderList() {
        return Arrays.asList("MALE", "FEMALE", "OTHER");
    }
}
