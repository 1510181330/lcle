package com.example.maven.word.Mapper;

import com.example.maven.word.Entities.Person.Person;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Mapper
public interface PersonLikeMapper {

    //获取所有成员
    @Select("select * from personinformation")
    public Collection<Person> getAllPersons();

    //根据名字获取密码，登录验证会用到
    @Select("select password from personinformation where name = #{name}")
    public String getPasswordByName(String name);

    //根据名字获取QQ号码，邮箱发送附件用到
    @Select("select QQ from personinformation where name = #{name}")
    public String getQQnumberByName(String name);

    //根据ID获取成员
    @Select("select * from personinformation where id = #{id}")
    public Person getPersonById(int id);

    //根据名字获取成员
    @Select("select * from personinformation where name = #{name}")
    public Person getPersonByName(String name);

    @Delete("delete from personinformation where id = #{id}")
    public void delPersonById(int id);

    @Insert("insert into personinformation (name, password, sex, QQ, phone, depname, priority) values(#{name}, #{password}, #{sex}, #{QQ}, #{phone}, #{depname}, #{priority})")
    public void addPerson(Person person);

    @Select("select priority from personinformation where name=#{name}")
    public Integer GetPriorityByName(String name);

    @Select("select priority from personinformation where id=#{id}")
    public Integer GetPriorityById(int id);

    @Update("update personinformation set name=#{name}, password=#{password}, sex=#{sex}, QQ=#{QQ}, phone=#{phone}, depname=#{depname}, priority=#{priority} where id=#{id}")
    public void UpdatePerson(Person person);


    @Select("select * from personinformation where depname=#{depname}")
    public Collection<Person> getRow1(String depname);

    @Select("select techer from personinformation join classtoteachermapper on personinformation.classes=classtoteachermapper.classes where name=#{name}")
    public String getMore1(String name);

    @Select("select tphone from personinformation join classtoteachermapper on personinformation.classes=classtoteachermapper.classes where name=#{name}")
    public String getMore2(String name);
}
