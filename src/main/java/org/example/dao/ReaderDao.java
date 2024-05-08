package org.example.dao;

import org.example.model.Reader;
import org.example.resource.payload.ReaderPayload;
import org.example.resource.payload.Report;
import org.example.resource.payload.ReportReader;

import java.util.List;

public interface ReaderDao {

    Reader findReader(ReaderPayload readerPayload);
    Reader findReaderId(int id);
    List<Reader> getReaderList();

    Reader addReader(ReaderPayload reader);

    void readerRemove(int id);

    ReportReader repostBookCount(Report report);
}
