package pl.example.spring.punkty.db;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StudentRow {
    @Id
    private long id;
    private String name;
    private String number;
    private String grupa;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getGrupa() {
        return grupa;
    }

    public void setGrupa(String grupa) {
        this.grupa = grupa;
    }
}
