package com.example.myboot2.service;

import com.example.myboot2.dao.iface.Teacher;

import java.util.List;

public interface SomeService {
    String doSome();

    void addTeacher(Teacher teacher) throws Exception;

    List<Teacher> listTeachers();
}
