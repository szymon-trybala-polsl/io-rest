package pl.example.spring.punkty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewStudent {
    public final String name;
    public final String number;
    public final String grupa;

    public NewStudent(@JsonProperty("name") String name, @JsonProperty("number") String number, @JsonProperty("grupa") String grupa) {
        this.name = name;
        this.number = number;
        this.grupa = grupa;
    }
}
