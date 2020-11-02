package com.example.myboot2.controller;

import com.example.myboot2.aop.FileType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author YUANCHENGMAN
 * @date 2020-10-29
 */
@RestController
public class UploadController {
    @GetMapping("/upload/file")
    @FileType({"xlsx", "zip"})
    public void uploadFile(MultipartFile file) {
        System.out.println(file.getName());
    }
}
