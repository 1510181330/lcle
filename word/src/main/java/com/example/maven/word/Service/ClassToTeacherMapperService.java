package com.example.maven.word.Service;


import com.example.maven.word.Entities.classToTeacherMapper;
import com.example.maven.word.Mapper.WrodToTeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ClassToTeacherMapperService {

    @Autowired
    private WrodToTeacherMapper mapperDao;

    public classToTeacherMapper getOneMapperById(int id)
    {
        return mapperDao.getOneMapperById(id);
    }

    public Collection<classToTeacherMapper> getAllMappers()
    {
        return mapperDao.getAllMappers();
    }

    public Collection<String> GetAllClassesName(){
        return mapperDao.getAllClassesName();
    }

    public Collection<String> getClassesNameByGradle(int gradle){
        return mapperDao.getClassesNameByGradle(gradle);
    }

    public Integer getNumberByClassName(String classes){
        return mapperDao.getNumberByClassName(classes);
    }

    public String getTeacherbyClassName(String classes)
    {
        return mapperDao.getTeacherbyClassName(classes);
    }

    public Collection<Integer> getAllGradles()
    {
        return mapperDao.getAllGradles();
    }

}
