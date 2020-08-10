package com.example.myboot2.service;

import com.example.myboot2.dao.iface.Teacher;

public interface SomeService {
    String doSome();

    void addTeacher(Teacher teacher) throws Exception;
}
