package com.backend.controller;

import com.backend.entity.Teacher;
import com.backend.entity.tools.RestBean;
import com.backend.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/index")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @GetMapping("/Teachers")
    RestBean<List<Teacher>> findAllTeacher(){
        List<Teacher> list = teacherService.findAllTeacher();
        return RestBean.success(list);
    }
}
