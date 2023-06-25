package com.backend.service;

import com.backend.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> findAllCourse();
    Course findCourseById(int id);
    List<Course> findCourseByName(String name);
    List<Course> findCourseByTName(String name);
    List<Course> findCourseByIsOnline(int status);
    boolean insertCourse(Course course);
    boolean deleteCourse(int id);
    boolean updateCourse(Course course);
    boolean updateCourseStatus(int id, int status);
}
