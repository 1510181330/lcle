package com.example.maven.word.Service;

import com.example.maven.word.Entities.WordEntities.Record;
import com.example.maven.word.Mapper.WordRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class WordRecordSerivce {

    @Autowired
    private WordRecordMapper recordMapper;

    public void save(Record record)
    {
        recordMapper.save(record);
    }

    public void updata(Record record)
    {
        recordMapper.updata(record);
    }

    public void delete(int id)
    {
        recordMapper.delete(id);
    }

    public Collection<Record> getAllRecords()
    {
        return recordMapper.getAllRecords();
    }

    public Collection<Record> getAllRecords1()
    {
        return recordMapper.getAllRecords1();
    }
}
