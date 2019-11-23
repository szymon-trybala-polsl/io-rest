package pl.example.spring.punkty;

import io.vavr.collection.List;
import org.springframework.stereotype.Service;
import pl.example.spring.punkty.db.StudentRepository;
import pl.example.spring.punkty.db.StudentRow;

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
}
