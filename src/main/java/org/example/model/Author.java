package org.example.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Setter
@Getter
@ToString
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "surname")
    private String surname;

    @JsonIgnoreProperties("authors")
    @ManyToMany(mappedBy = "authors",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Book> books;

    public Author() {

    }

    public Author(String name, String middleName, String surname) {
        this.name = name;
        this.middleName = middleName;
        this.surname = surname;
    }

    public void addBook(Book book) {
        if (books == null) {
            books = new ArrayList<>();
        }

        books.add(book);
    }
}