package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "gender")
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "gender",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Reader> readers;

    public Gender() {

    }

    @Override
    public String toString() {
        return "Gender{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Gender(String name) {
        this.name = name;
    }

    public void addReader(Reader reader) {
        if (readers == null) {
            readers = new ArrayList<>();
        }

        readers.add(reader);
    }
}