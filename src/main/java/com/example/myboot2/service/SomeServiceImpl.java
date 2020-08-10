package com.example.myboot2.service;

import com.example.myboot2.dao.iface.Teacher;
import com.example.myboot2.dao.iface.TeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

//  使用生产配置
@Service
@Profile("prod")
public class SomeServiceImpl implements SomeService {

    @Autowired
    private TeacherDao dao;
    @Override
    public String doSome() {
        return "生产环境的接口实现类";
    }

    @Override
    public void addTeacher(Teacher teacher) {
        dao.insert(teacher);
    }
}
