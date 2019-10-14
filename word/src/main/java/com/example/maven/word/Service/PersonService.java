package com.example.maven.word.Service;

import com.example.maven.word.Entities.Person.Person;
import com.example.maven.word.Mapper.PersonLikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;

@Service
public class PersonService {

    @Autowired
    private PersonLikeMapper personDao;

    private static HashMap<Integer, Person> allEmps = null;

    //用作密码验证
    private static HashMap<String, String> pass = null;

    public Collection<Person> getAllPerson()
    {
        return personDao.getAllPersons();
    }


    public String getPasswordByName(String name)
    {
        return personDao.getPasswordByName(name);
    }

    public String getQQnumberByName(String name)
    {
        return personDao.getQQnumberByName(name);
    }

    public Person getPersonById(int id)
    {
        return personDao.getPersonById(id);
    }

    public void delPersonById(int id)
    {
        personDao.delPersonById(id);
    }

    public void addPerson(Person person)
    {
        personDao.addPerson(person);
    }

    public Integer GetPriorityByName(String name)
    {
        return personDao.GetPriorityByName(name);
    }

    public Integer GetPriorityById(int id)
    {
        return personDao.GetPriorityById(id);
    }

    public Person getPersonByName(String name)
    {
        return personDao.getPersonByName(name);
    }

    public void UpdatePerson(Person person)
    {
        personDao.UpdatePerson(person);
    }

    public Collection<Person> getRow1(String depname)
    {
        return personDao.getRow1(depname);
    }


}
