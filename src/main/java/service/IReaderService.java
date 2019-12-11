package service;

import vo.Reader;
import vo.Tag;

import java.util.List;

public interface IReaderService {
    boolean checkTagforAll(List<Reader> readerlist, Tag tag);

    boolean checkTagforOne(Reader reader,Tag tag);



}
