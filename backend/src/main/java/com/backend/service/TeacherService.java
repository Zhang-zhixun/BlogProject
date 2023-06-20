package com.backend.service;

import com.backend.entity.Teacher;

import java.util.List;

public interface TeacherService {

    Teacher findTeacherById(int id);

    List<Teacher> findAllTeacher();

    List<Teacher> findTeacherByName(String name);

    boolean insertTeacher(Teacher teacher);

    boolean deleteTeacher(int id);

    boolean updateTeacher(Teacher teacher);
}
