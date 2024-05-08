package org.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "issuance")
public class Issuance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "readers_id", referencedColumnName = "id")
    private Reader reader;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "receipt_date")
    private Date receiptDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "return_date")
    private Date returnDate;

    @OneToOne(mappedBy = "issuance",targetEntity = BookReturn.class,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private BookReturn bookReturn;

    public Issuance() {

    }

    public Issuance(Date receiptDate, Date returnDate) {

        this.receiptDate = receiptDate;
        this.returnDate = returnDate;
    }
}
