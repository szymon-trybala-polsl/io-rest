package pl.example.spring.punkty;

import io.vavr.collection.List;
import org.springframework.stereotype.Service;
import pl.example.spring.punkty.db.StudentRepository;
import pl.example.spring.punkty.db.StudentRow;

import java.util.function.Function;

@Service
public class StudentService {
    public List<Student> students = List.empty();

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }


    Student addStudent(final NewStudent student) {
        StudentRow created = this.repository.save(new StudentRow(
                student.name,
                student.number,
                student.grupa
        ));

        return getStudentRowStudentFunction().apply(created);
    }

    List<Student> getStudents() {
        return List.ofAll(this.repository.findAll()).
                map(getStudentRowStudentFunction());
    }

    private Function<StudentRow, Student> getStudentRowStudentFunction() {
        return dbObj ->
                new Student(dbObj.getId(), dbObj.getName(), dbObj.getNumber(), dbObj.getGrupa());
    }

}
