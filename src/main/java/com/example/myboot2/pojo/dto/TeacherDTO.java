package com.example.myboot2.pojo.dto;

import com.example.myboot2.pojo.validator.Job;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author damon
 * @date 2020/08/10
 */
@Data
public class TeacherDTO {

    /**
     * 教师id
     */
    @NotNull(groups = Update.class)
    @Min(value = 1, groups = Update.class)
    private Integer id;

    /**
     * 教师姓名
     */
    @NotNull(groups = {Save.class, Update.class})
    @Length(min = 2, max = 20, groups = {Save.class, Update.class})
    private String name;

    /**
     * 教师年龄
     */
    @NotNull(groups = {Save.class, Update.class})
    @Min(value = 18, groups = {Save.class, Update.class})
    @Max(value = 60, groups = {Save.class, Update.class})
    private Integer age;

    /**
     * 教师的课程
     */
    @NotNull(groups = {Save.class, Update.class})
    @Valid
    private Course course;

    /**
     * 教师副业
     */
    @NotNull(groups = {Save.class, Update.class})
    @Job(groups = {Save.class, Update.class})
    private String job;

    /**
     * 课程
     */
    @Data
    public static class Course {

        /**
         * 课程id
         */
        @Min(value = 1, groups = Update.class)
        private Long id;

        /**
         * 课程名称
         */
        @NotNull(groups = {Save.class, Update.class})
        @Length(min = 2, max = 20, groups = {Save.class, Update.class})
        private String name;
    }

    public interface Save {
    }

    public interface Update {
    }
}
