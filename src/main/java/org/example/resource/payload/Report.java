package org.example.resource.payload;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@XmlRootElement
public class Report {
    private int readerId;
    private Date startDate;
    private Date endDate;

    public Report() {
    }

    public Report(int readerId, Date startDate, Date endDate) {
        this.readerId = readerId;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
