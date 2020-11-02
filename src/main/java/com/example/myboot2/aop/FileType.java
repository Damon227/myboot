package com.example.myboot2.aop;

import java.lang.annotation.*;

/**
 * 校验文件类型
 *
 * @author YUANCHENGMAN
 * @date 2020-10-30
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FileType {
    String[] value();
}
