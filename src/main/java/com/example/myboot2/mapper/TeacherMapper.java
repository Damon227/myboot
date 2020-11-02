package com.example.myboot2.mapper;

import com.example.myboot2.dao.iface.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TeacherMapper {
    /**
     * 插入教师信息
     *
     * @param teacher 要保存的教师信息
     * @return
     */
    int insert(Teacher teacher);

    /**
     * 查询教师列表
     *
     * @return 教师列表
     */
    List<Teacher> listTeachers();
}
