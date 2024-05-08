package org.example.resource.payload;

import jakarta.persistence.Column;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@ToString
@XmlRootElement
public class BookPayload {
    private String title;

    private int publicationYear;

    private int count;

    private List<AuthorPayload> authorPayloads;

    public BookPayload() {
    }

    public BookPayload(String title, int publicationYear, int count, List<AuthorPayload> authorPayloads) {
        this.title = title;
        this.publicationYear = publicationYear;
        this.count = count;
        this.authorPayloads = authorPayloads;
    }
}
