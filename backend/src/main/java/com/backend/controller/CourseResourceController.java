package com.backend.controller;

import com.backend.entity.Chapter;
import com.backend.entity.CourseResource;
import com.backend.entity.tools.RestBean;
import com.backend.service.CourseResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/index")
public class CourseResourceController {

    @Autowired
    CourseResourceService courseResourceService;

    @GetMapping("/getCourseResourceByChapterId/{id}")
    RestBean<List<CourseResource>> findCourseResourceByChapterId(@PathVariable("id") int id) {
        List<CourseResource> list = courseResourceService.findCourseResourceByChapterId(id);
        return RestBean.success(list);
    }

    @PostMapping("/getCourseResourceByResourceType")
    RestBean<List<CourseResource>> findCourseResourceByResourceType(@RequestParam("type") String type,
                                                                    @RequestParam("id") int id) {
        List<CourseResource> list = courseResourceService.findCourseResourceByResourceType(type,id);
        return RestBean.success(list);
    }

    @GetMapping("/getCourseResourceByResourceId/{id}")
    RestBean<CourseResource> findCourseResourceByResourceId(@PathVariable("id") int id) {
        return RestBean.success(courseResourceService.findCourseResourceByResourceId(id));
    }

    @PostMapping("/insertCourseResource")
    RestBean<String> insertCourseResource(
            @RequestParam("chapterId") int chapterId,
            @RequestParam("resourceType") String resourceType,
            @RequestParam("resourceUrl") String resourceUrl,
            @RequestParam("sortOrder") int sortOrder) {
        Chapter chapter = new Chapter();
        chapter.setChapterId(chapterId);
        CourseResource courseResource = new CourseResource();
        courseResource.setChapter(chapter);
        courseResource.setResourceType(resourceType);
        courseResource.setResourceUrl(resourceUrl);
        courseResource.setSortOrder(sortOrder);
        if (courseResourceService.insertCourseResource(courseResource)) {
            return RestBean.success("添加成功");
        } else {
            return RestBean.failure(404, "添加失败");
        }
    }

    @GetMapping("/deleteCourseResource/{id}")
    RestBean<String> deleteCourseResource(@PathVariable("id") int id) {
        if (courseResourceService.deleteCourseResource(id)) {
            return RestBean.success("删除成功");
        } else {
            return RestBean.failure(404, "删除失败");
        }
    }

    @PostMapping("/updateCourseResource")
    RestBean<String> updateCourseResource(
            @RequestParam("resourceId") int resourceId,
            @RequestParam("chapterId") int chapterId,
            @RequestParam("resourceType") String resourceType,
            @RequestParam("resourceUrl") String resourceUrl,
            @RequestParam("sortOrder") int sortOrder) {
        Chapter chapter = new Chapter();
        chapter.setChapterId(chapterId);
        CourseResource courseResource = new CourseResource();
        courseResource.setResourceId(resourceId);
        courseResource.setChapter(chapter);
        courseResource.setResourceType(resourceType);
        courseResource.setResourceUrl(resourceUrl);
        courseResource.setSortOrder(sortOrder);
        if (courseResourceService.updateCourseResource(courseResource)) {
            return RestBean.success("修改成功");
        } else {
            return RestBean.failure(404, "修改失败");
        }
    }

}
