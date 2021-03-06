package com.example.myboot2.dao;

import com.example.myboot2.dao.iface.Teacher;
import com.example.myboot2.dao.iface.TeacherDao;
import com.example.myboot2.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherDalImpl implements TeacherDao {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public void insert(Teacher teacher) {
        teacherMapper.insert(teacher);
    }

    @Override
    public List<Teacher> listTeachers() {
        return teacherMapper.listTeachers();
    }
}
