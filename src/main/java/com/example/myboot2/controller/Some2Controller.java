package com.example.myboot2.controller;

import com.example.myboot2.dao.iface.Teacher;
import com.example.myboot2.service.SomeService;
import com.ycm.springbootstarterwrap3.service.WrapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
public class Some2Controller {

    @Autowired
    private SomeService service;

    @Autowired
    private WrapService wrapService;

    @RequestMapping("/register")
    public String registerHandler(String name, int age, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);

        // 要返回视图，类必须用 @Controller，而不是@RestController
        return "jsp/welcome";
    }

    @PostMapping("/register2")
    public ResponseEntity<Teacher> registerHandler(@RequestBody Teacher teacher) throws Exception {
        service.addTeacher(teacher);

        return ResponseEntity.ok(teacher);
    }

    @GetMapping("/first")
    public @ResponseBody String firstHandler(){
        return "first handler";
    }

    @GetMapping("/second")
    public @ResponseBody String secondHandler(){
        return "second handler";
    }

    @GetMapping("wrap")
    public @ResponseBody String wrapHandler(@RequestParam String word){
        return wrapService.wrap(word);
    }
}
