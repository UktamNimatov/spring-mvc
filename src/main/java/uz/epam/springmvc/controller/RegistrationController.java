package uz.epam.springmvc.controller;

import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import uz.epam.springmvc.bean.User;
import uz.epam.springmvc.repository.UserRepository;

import javax.validation.Valid;
import java.beans.PropertyEditor;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class RegistrationController {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    private UserRepository userRepository;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, "dateOfBirth",
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    @Autowired
    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/registeruser")
    public String registerUser(@Valid @ModelAttribute("newUser") User user, BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            logger.error("error in data");
            return "register";
        }
        userRepository.save(user);
        logger.info("in registration controller");
        logger.info(user.getDateOfBirth().toString());
        model.addAttribute("newUser", user);
        return "login";
    }
}
