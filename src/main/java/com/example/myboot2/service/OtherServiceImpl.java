package com.example.myboot2.service;

import com.example.myboot2.dao.iface.Teacher;
import com.example.myboot2.dao.iface.TeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//  使用开发配置
@Service
@Profile("dev")
public class OtherServiceImpl implements SomeService {

    @Autowired
    private TeacherDao teacherDao;

    @Override
    public String doSome() {
        return "开发环境的接口实现类";
    }

    @Transactional(rollbackFor = Exception.class) //事务，发生异常时回滚
    @Override
    public void addTeacher(Teacher teacher) throws Exception {
        teacherDao.insert(teacher);

        Teacher teacher2 = new Teacher();
        teacher2.setId(teacher.getId() + 1);
        teacher2.setName(teacher.getName());
        teacher2.setAge(teacher.getAge());

        if(teacher.getId() % 2 == 0){
            throw new Exception("发生异常");
        }

        teacherDao.insert(teacher2);
    }

    @Override
    public List<Teacher> listTeachers() {
        return teacherDao.listTeachers();
    }
}
