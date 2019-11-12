package pl.example.spring.punkty;

import io.vavr.collection.List;

public class StudentService {
    public List<Student> students = List.empty();

    List<Student> getStudents() {
        return students;
    }

    Student addStudent(NewStudent student) {
        Student created = new Student(students.size() + 1, student.name, student.number, student.grupa);
        students = students.prepend(created);

        return created;
    }

}
