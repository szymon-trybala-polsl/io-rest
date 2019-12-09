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

    @RequestMapping(value = "/students", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    private List<Student> getUsers() {
        return this.service.getStudents().asJava();
    }

    @RequestMapping(value = "/students", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    private Student addUser(@RequestBody NewStudent student) {
        return this.service.addStudent(student);
    }

    @RequestMapping(value = "/students/{id}/number/{number}", method = RequestMethod.POST)
    private Student setNumber(@PathVariable("id") long id, @PathVariable("number") String number) {
        return this.service.changeNumber(id, number).orElseThrow(() -> new NoStudentException(id));
    }

    @RequestMapping(value = "/students/{id}/scores", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public int addScore(@PathVariable("id") long id, @RequestBody Score score) {
        return this.service.addScore(id, score)
                .orElseThrow(() ->new NoStudentException(id));
    }
}
