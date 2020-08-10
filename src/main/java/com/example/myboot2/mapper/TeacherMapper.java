package com.example.myboot2.mapper;

import com.example.myboot2.dao.iface.Teacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TeacherMapper {
    @Insert("insert into teachers(id,name,age) values(#{id},#{name},#{age})")
    int Insert(Teacher teacher);
}
