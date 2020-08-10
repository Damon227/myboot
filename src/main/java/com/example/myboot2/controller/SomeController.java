package com.example.myboot2.controller;

import com.example.myboot2.config.Company;
import com.example.myboot2.config.Country;
import com.example.myboot2.config.Student;
import com.example.myboot2.service.SomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SomeController {
    @Autowired
    private SomeService service;

    @Value("${server.port}")
    private int port;

    @Value("${info.company.name}")
    private String companyName;

    @Autowired
    private Student student;

    @Autowired
    private Country country;

    @Autowired
    private Company company;

    @RequestMapping("/some")
    public @ResponseBody
    String someHandle() {
        return service.doSome();
    }

    @GetMapping("/port")
    public String portHandler() {
        return "port = " + port;
    }

    @GetMapping("/companyName")
    public String infoHandle() {
        return "info = " + companyName;
    }

    @GetMapping("/student")
    public String studentHandler() {
        return "student = " + student;
    }

    @GetMapping("/country")
    public String countryHandler() {
        return "country = " + country.getCities();
    }

    /**
     * 解析配置文件中的对象列表
     *
     * @return
     */
    @GetMapping("/company")
    public String companyHandler() {
        return "company = " + company.getDeparts();
    }
}
