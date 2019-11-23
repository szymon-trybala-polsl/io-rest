package pl.example.spring.punkty;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HelloController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    private String hello() {
        return "Hello, it's " + LocalDateTime.now();
    }
}
