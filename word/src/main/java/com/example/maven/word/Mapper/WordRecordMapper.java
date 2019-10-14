package com.example.maven.word.Mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.example.maven.word.Entities.WordEntities.Record;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;

@Mapper
public interface WordRecordMapper {

    @Insert("insert into record (gradle, classs, week, allNumber, section, lession, teacher, absence, person) values(#{gradle}, #{classs}, #{week}, #{allNumber}, #{section}, #{lession}, #{teacher}, #{absence}, #{person})")
    public void save(Record record);

    @Insert("replace into record (id, gradle, classs, week, allNumber, section, lession, teacher, absence, person) values(#{id}, #{gradle}, #{classs}, #{week}, #{allNumber}, #{section}, #{lession}, #{teacher}, #{absence}, #{person})")
    public void updata(Record record);

    @Delete("delete from record where id = #{id}")
    public void delete(int id);

    @Select("select * from record ORDER BY gradle DESC, classs DESC")
    public Collection<Record> getAllRecords();

    @Select("select * from record ORDER BY gradle ASC, classs ASC")
    public Collection<Record> getAllRecords1();
}