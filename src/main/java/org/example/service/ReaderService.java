package org.example.service;

import org.example.model.Book;
import org.example.model.Reader;
import org.example.resource.payload.BookPayload;
import org.example.resource.payload.ReaderPayload;
import org.example.resource.payload.Report;
import org.example.resource.payload.ReportReader;

import java.util.List;

public interface ReaderService {
    Reader findReaderId(int id);

    List<Reader> getReaderList();

    void save(ReaderPayload readerPayload);

    void remove(int id);

    ReportReader repostBook(Report report);
}
