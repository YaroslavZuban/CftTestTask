package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_sequence")
    @SequenceGenerator(name = "book_sequence", sequenceName = "books_seq", allocationSize = 1)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "publication_year")
    private int publicationYear;

    @Column(name = "count")
    private int count;

    @JsonIgnore
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Issuance> issuanceList;

    @JsonIgnoreProperties("books")
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;

    public Book() {

    }

    public Book(String title, int publicationYear, int count) {
        this.title = title;
        this.publicationYear = publicationYear;
        this.count = count;
    }

    public void addAuthors(Author author) {
        if (authors == null) {
            authors = new ArrayList<>();
        }

        boolean authorExists = false;

        for (Author temp : authors) {
            if (temp.getId() == author.getId()) {
                authorExists = true;
                break;
            }
        }

        if (!authorExists) {
            authors.add(author);
        }

        authors.add(author);
    }
}