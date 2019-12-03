package pl.example.spring.punkty;

import io.vavr.collection.List;
import org.springframework.stereotype.Service;
import pl.example.spring.punkty.db.StudentRepository;
import pl.example.spring.punkty.db.StudentRow;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }


    Student addStudent(final NewStudent student) {
       return this.repository.save(new StudentRow(
                student.name,
                student.number,
                student.grupa
        )).toStudent();
    }

    List<Student> getStudents() {
        return List.ofAll(this.repository.findAll()).
                map(StudentRow::toStudent);
    }

    @Transactional
    public Optional<Student> changeNumber(long studentId, String newNumber) {
        final Optional<StudentRow> student = this.repository.findById(studentId);
        return student.map(c -> {
            c.setNumber(newNumber);
            repository.save(c);
            return c.toStudent();
        });
    }
}
