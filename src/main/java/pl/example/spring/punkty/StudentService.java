package pl.example.spring.punkty;

import io.vavr.collection.List;
import org.springframework.stereotype.Service;
import pl.example.spring.punkty.db.ScoreRepository;
import pl.example.spring.punkty.db.ScoreRow;
import pl.example.spring.punkty.db.StudentRepository;
import pl.example.spring.punkty.db.StudentRow;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final ScoreRepository scoreRepository;

    public StudentService(StudentRepository repository, ScoreRepository scoreRepository) {
        this.studentRepository = repository;
        this.scoreRepository = scoreRepository;
    }


    Student addStudent(final NewStudent student) {
       return this.studentRepository.save(new StudentRow(
                student.name,
                student.number,
                student.grupa
        )).toStudent();
    }

    List<Student> getStudents() {
        return List.ofAll(this.studentRepository.findAll()).
                map(StudentRow::toStudent);
    }

    @Transactional
    public Optional<Student> changeNumber(long studentId, String newNumber) {
        final Optional<StudentRow> student = this.studentRepository.findById(studentId);
        return student.map(c -> {
            c.setNumber(newNumber);
            studentRepository.save(c);
            return c.toStudent();
        });
    }

    @Transactional
    public Optional<Integer> addScore(long id, Score score) {
        final Optional<StudentRow> student = this.studentRepository.findById(id);
        return student.map(c -> {
            int existingScore = List.ofAll(c.getScores()).foldLeft(0, (p,s)->p+s.getScore());
            final ScoreRow newScore = new ScoreRow(score.Score, score.comment, c);
            this.scoreRepository.save(newScore);
            return existingScore+score.Score;
        });
    }
}
