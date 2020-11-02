package com.example.myboot2.dao.iface;

import java.util.List;

public interface TeacherDao {
    void insert(Teacher teacher);

    List<Teacher> listTeachers();
}
