package training.mavendemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import training.Calculator;

@RestController
public class HelloController {

    @GetMapping("/api/calculator")
    public String calculate() {
        //return "Hello Spring Boot!";
        return Integer.toString(new Calculator().add(5 , 7));
    }
}
