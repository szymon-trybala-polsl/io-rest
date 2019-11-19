package pl.example.spring.punkty;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/points")
public class PointsController {

    private final StudentService service;
    public PointsController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/students")
    private List<Student> getUsers() {
        return this.service.getStudents().asJava();
    }

    @PostMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    private Student addUser(@RequestBody NewStudent student) {
        return this.service.addStudent(student);
    }

}
