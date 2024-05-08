package org.example.resource.payload;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.example.model.Gender;

import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@XmlRootElement
public class ReaderPayload {
    private String name;
    private String middleName;
    private String surname;
    private int genderId;
    public ReaderPayload() {
    }

    public ReaderPayload(String name, String middleName, String surname, int genderId) {
        this.name = name;
        this.middleName = middleName;
        this.surname = surname;
        this.genderId = genderId;
    }
}