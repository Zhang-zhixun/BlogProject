package com.backend.service;

import com.backend.entity.CourseResource;

import java.util.List;

public interface CourseResourceService {
    List<CourseResource> findCourseResourceByChapterId(int id);
    List<CourseResource> findCourseResourceByResourceType(String type,int id);
    CourseResource findCourseResourceByResourceId(int id);
    boolean insertCourseResource(CourseResource courseResource);
    boolean deleteCourseResource(int id);
    boolean updateCourseResource(CourseResource courseResource);
}
