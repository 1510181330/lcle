package com.example.maven.word.Service;


import com.example.maven.word.Entities.Person.FreeClass;
import com.example.maven.word.Mapper.FreeClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FreeClassService {

    @Autowired
    public FreeClassMapper freeClassMapper;

    public void saveFreeClass(FreeClass freeclass)
    {
        freeClassMapper.saveFreeClass(freeclass);
    }

    public FreeClass getFreeClassByID(int id)
    {
        return freeClassMapper.getFreeClassByID(id);
    }

    public Collection<FreeClass> getAllFreeClass()
    {
        return freeClassMapper.getAllFreeClass();
    }

    public void DelFreeClass(int id)
    {
        freeClassMapper.DelFreeClass(id);
    }
}
