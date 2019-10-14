package com.example.maven.word.Mapper;

import com.example.maven.word.Entities.classToTeacherMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.Collection;

@Mapper
public interface WrodToTeacherMapper {

    @Select("select * from classtoteachermapper where id = #{id}")
    public classToTeacherMapper getOneMapperById(int id);

    @Select("select * from classtoteachermapper")
    public Collection<classToTeacherMapper> getAllMappers();

    @Select("select classes from classtoteachermapper")
    public Collection<String> getAllClassesName();

    @Select("select classes from classtoteachermapper where gradle=#{gradle}")
    public Collection<String> getClassesNameByGradle(int gradle);

    @Select("select sum from classtoteachermapper where classes=#{classes}")
    public Integer getNumberByClassName(String classes);

    @Select("select techer from classtoteachermapper where classes=#{classes}")
    public String getTeacherbyClassName(String classes);

    @Select("select DISTINCT gradle from classtoteachermapper")
    public Collection<Integer> getAllGradles();
}
