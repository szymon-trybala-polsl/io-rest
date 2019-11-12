package pl.example.spring.punkty;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/points")
public class PointsController {

    private StudentService service = new StudentService();

    @GetMapping("/students")
    private List<Student> getUsers() {
        return this.service.getStudents().asJava();
    }

    @PostMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    private Student addUser(@RequestBody NewStudent student) {
        return this.service.addStudent(student);
    }

}
