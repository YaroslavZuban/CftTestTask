package org.example.service;

import org.example.config.HibernateConfig;
import org.example.dao.ReaderDao;
import org.example.dao.ReaderDaoImpl;
import org.example.model.Reader;
import org.example.resource.payload.ReaderPayload;
import org.example.resource.payload.Report;
import org.example.resource.payload.ReportReader;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ReaderServiceImpl implements ReaderService {
    private final ReaderDao readerDao;

    public ReaderServiceImpl() {
        readerDao = new ReaderDaoImpl(HibernateConfig.getNewSessionFactory());
    }

    @Override
    public Reader findReaderId(int id) {
        return readerDao.findReaderId(id);
    }

    @Override
    public List<Reader> getReaderList() {
        return readerDao.getReaderList();
    }

    @Override
    public void save(ReaderPayload readerPayload) {
        readerDao.addReader(readerPayload);
    }

    @Override
    public void remove(int id) {
        readerDao.readerRemove(id);
    }

    @Override
    public ReportReader repostBook(Report report) {
        return readerDao.repostBookCount(report);
    }
}
