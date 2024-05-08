package org.example.resource.payload;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@XmlRootElement
public class AuthorPayload {
    private String name;
    private String middleName;
    private String surname;

    public AuthorPayload() {
    }

    public AuthorPayload(String name, String middleName, String surname) {
        this.name = name;
        this.middleName = middleName;
        this.surname = surname;
    }
}
