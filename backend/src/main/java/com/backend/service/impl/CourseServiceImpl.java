package com.backend.service.impl;

import com.backend.entity.Course;
import com.backend.mapper.CourseMapper;
import com.backend.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseMapper courseMapper;

    @Override
    public List<Course> findAllCourse() {
        return courseMapper.findAllCourse();
    }

    @Override
    public Course findCourseById(int id) {
        return courseMapper.findCourseById(id);
    }

    @Override
    public List<Course> findCourseByName(String name) {
        return courseMapper.findCourseByName(name);
    }

    @Override
    public List<Course> findCourseByTName(String name) {
        return courseMapper.findCourseByTName(name);
    }

    @Override
    public List<Course> findCourseByIsOnline(int status) {
        return courseMapper.findCourseByIsOnline(status);
    }

    @Override
    public boolean insertCourse(Course course) {
        System.out.println(course+"=============");
        return courseMapper.insertCourse(course);
    }

    @Override
    public boolean deleteCourse(int id) {
        return courseMapper.deleteCourse(id);
    }

    @Override
    public boolean updateCourse(Course course) {
        return courseMapper.updateCourse(course);
    }

    @Override
    public boolean updateCourseStatus(int id, int status) {
        return courseMapper.updateCourseStatus(id,status);
    }
}
