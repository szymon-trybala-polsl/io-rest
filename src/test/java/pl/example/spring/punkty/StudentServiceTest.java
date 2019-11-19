package pl.example.spring.punkty;

import io.vavr.collection.List;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.example.spring.punkty.db.StudentRepository;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {

    @Autowired
    private StudentRepository repository;

    @Test
    public void getEmptyList() {
        final StudentService service = new StudentService(repository);
        List<Student> students = service.getStudents();
        assertTrue(students.isEmpty());
    }

    @Test
    public void addStudent() {
        final StudentService service = new StudentService(repository);
        final Student created = service.addStudent(new NewStudent("Student 1", "1-2-3", "IP"));
        assertNotNull(created);
    }

    @Test
    public void addStudentIsReturned() {
        final StudentService service = new StudentService(repository);
        final Student created = service.addStudent(new NewStudent("Student 1", "1-2-3", "IP"));
        final List<Student> studentsAll = service.getStudents();

        assertEquals(created.name, studentsAll.get(0).name);
    }

    @Test
    public void addStudentHasNewId() {
        final StudentService service = new StudentService(repository);
        final Student st1 = service.addStudent(new NewStudent("Student 1", "1-2-3", "IP"));
        final Student st2 = service.addStudent(new NewStudent("Student 2", "2-1-3", "IP"));
        final List<Student> studentsAll = service.getStudents();

        assertEquals(2, studentsAll.size());
        assertNotEquals(studentsAll.get(0).id, studentsAll.get(1).id);
    }

    @After
    public void cleanAfterTest() {
        this.repository.deleteAll();
    }
}