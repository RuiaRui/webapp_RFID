package service.Impl;

import service.IReaderService;
import utils.Method;
import utils.Parameter;
import vo.Reader;
import vo.Tag;

import java.awt.*;
import java.util.List;

public class IReaderServiceImpl implements IReaderService {


    @Override
    public boolean checkTagforAll(List<Reader> readers, Tag tag) {
        for(Reader reader: readers){
            if(Method.locWithinReader(tag.location.x,tag.location.y,
                    reader.loc.x,reader.loc.y,Parameter.ri)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkTagforOne(Reader reader, Tag tag) {
        if(Method.locWithinReader(tag.location.x,tag.location.y,
                reader.loc.x,reader.loc.y,Parameter.ri)) {
            return true;
        }else{
            return false;
        }
    }
}
