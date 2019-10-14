package com.example.maven.word.Mapper;


import com.example.maven.word.Entities.Person.FreeClass;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;

@Mapper
public interface FreeClassMapper {

    @Insert("replace into freeclass (id, C11, C12, C13, C14, C21, C22, C23, C24, C31, C32, C33, C34, C41, C42, C43, C44, C51, C52, C53, C54) values(#{id}, #{C11}, #{C12}, #{C13}, #{C14}, #{C21}, #{C22}, #{C23}, #{C24}, #{C31}, #{C32}, #{C33}, #{C34}, #{C41}, #{C42}, #{C43}, #{C44}, #{C51}, #{C52}, #{C53}, #{C54})")
    public void saveFreeClass(FreeClass freeclass);

    @Select("select * from freeclass where id = #{id}")
    public FreeClass getFreeClassByID(int id);

    @Select("select * from freeclass")
    public Collection<FreeClass> getAllFreeClass();

    @Delete("delete from freeclass where id = #{id}")
    public void DelFreeClass(int id);
}
