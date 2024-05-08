package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "readers")
public class Reader {
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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "gender_id", referencedColumnName = "id")
    private Gender gender;

    @JsonIgnoreProperties("readers")
    @OneToMany(mappedBy = "reader", targetEntity = Issuance.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Issuance> issuance;

    public Reader() {

    }

    public Reader(String name, String middleName, String surname, Gender gender) {
        this.name = name;
        this.middleName = middleName;
        this.surname = surname;
        this.gender = gender;
    }
}