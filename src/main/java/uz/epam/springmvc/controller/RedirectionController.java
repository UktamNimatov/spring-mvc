package uz.epam.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectionController {
    private static final Logger logger = LoggerFactory.getLogger(RedirectionController.class);

    @GetMapping("/redirectToLinkedIn")
    public String redirectToLinkedIn() {
        logger.info("in redirection controller" );
        return "redirect:https://www.youtube.com/watch?v=asVvLHxjzd8";
    }
}
