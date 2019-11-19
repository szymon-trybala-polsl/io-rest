package pl.example.spring.punkty;

public class Student {
    public final long id;
    public final String name;
    public final String number;
    public final String grupa;

    public Student(long id, String name, String number, String grupa) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.grupa = grupa;
    }
}
