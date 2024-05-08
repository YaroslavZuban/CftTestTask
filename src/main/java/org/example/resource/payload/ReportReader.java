package org.example.resource.payload;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@XmlRootElement
public class ReportReader {
    private int readerId;
    private int count;

    public ReportReader() {
    }

    public ReportReader(int readerId) {
        this.readerId = readerId;
    }

    public ReportReader(int readerId, int count) {
        this.readerId = readerId;
        this.count = count;
    }
}