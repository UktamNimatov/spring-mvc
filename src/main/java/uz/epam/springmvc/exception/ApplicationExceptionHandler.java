package uz.epam.springmvc.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

    @ExceptionHandler({ApplicationException.class, RuntimeException.class})
    public String handleException() {
        logger.info("in global exception handler class");
        return "error";
    }

    @ExceptionHandler(LoginFailureException.class)
    public ResponseEntity handleLoginFailure(LoginFailureException exception) {
        return new ResponseEntity("Invalid data given", HttpStatus.FORBIDDEN);
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
    }
}
