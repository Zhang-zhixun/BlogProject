package com.backend.service.impl;

import com.backend.entity.CourseResource;
import com.backend.mapper.CourseResourceMapper;
import com.backend.service.CourseResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseResourceServiceImpl implements CourseResourceService {

    @Autowired
    CourseResourceMapper courseResourceMapper;

    @Override
    public List<CourseResource> findCourseResourceByChapterId(int id) {
        return courseResourceMapper.findCourseResourceByChapterId(id);
    }

    @Override
    public List<CourseResource> findCourseResourceByResourceType(String type,int id) {
        return courseResourceMapper.findCourseResourceByResourceType(type,id);
    }

    @Override
    public CourseResource findCourseResourceByResourceId(int id) {
        return courseResourceMapper.findCourseResourceByResourceId(id);
    }

    @Override
    public boolean insertCourseResource(CourseResource courseResource) {
        return courseResourceMapper.insertCourseResource(courseResource);
    }

    @Override
    public boolean deleteCourseResource(int id) {
        return courseResourceMapper.deleteCourseResource(id);
    }

    @Override
    public boolean updateCourseResource(CourseResource courseResource) {
        return courseResourceMapper.updateCourseResource(courseResource);
    }
}
