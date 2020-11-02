package com.example.myboot2.controller;

import com.example.myboot2.enums.StatusEnum;
import com.example.myboot2.pojo.dto.TeacherDTO;
import com.example.myboot2.pojo.dto.ValidationList;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author damon
 * @date 2020/08/10
 */
@RestController
@RequestMapping("valid")
public class ValidController {
    /**
     * 创建教师
     * @param teacherDTO 教师信息
     * @return String
     */
    @PostMapping("teacher")
    public @ResponseBody String saveTeacher(@RequestBody @Validated(TeacherDTO.Save.class) TeacherDTO teacherDTO){
        return "save teacher";
    }

    /**
     * 更新教师信息
     * @param teacherDTO 教师信息
     * @return String
     */
    @PutMapping("teacher")
    public @ResponseBody String updateTeacher(@RequestBody @Validated(TeacherDTO.Update.class) TeacherDTO teacherDTO){

        String desc = getDesc(null);
        System.out.printf(desc);

        return "update teacher";
    }

    /**
     * 批量更新教师信息
     * @param dtos 教师信息集合
     * @return String
     */
    @PutMapping("teachers")
    public @ResponseBody String updateTeachers(@RequestBody @Validated(TeacherDTO.Update.class) ValidationList<TeacherDTO> dtos){
        return "update teachers";
    }

    private String getDesc(StatusEnum statusEnum){
        if(statusEnum == null){
            return "null";
        }
        return statusEnum.getDesc();
    }
}
