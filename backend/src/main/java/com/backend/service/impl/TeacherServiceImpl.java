package com.backend.service.impl;

import com.backend.entity.Teacher;
import com.backend.mapper.TeacherMapper;
import com.backend.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherMapper teacherMapper;
    @Override
    public Teacher findTeacherById(int id) {
        return teacherMapper.findTeacherById(id);
    }

    @Override
    public List<Teacher> findAllTeacher() {
        return teacherMapper.findAllTeacher();
    }

    @Override
    public List<Teacher> findTeacherByName(String name) {
        return teacherMapper.findTeacherByName(name);
    }

    @Override
    public boolean insertTeacher(Teacher teacher) {
        return teacherMapper.insertTeacher(teacher);
    }

    @Override
    public boolean deleteTeacher(int id) {
        return teacherMapper.deleteTeacher(id);
    }

    @Override
    public boolean updateTeacher(Teacher teacher) {
        return teacherMapper.updateTeacher(teacher);
    }
}
