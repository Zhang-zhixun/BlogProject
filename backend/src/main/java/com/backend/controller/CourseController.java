package com.backend.controller;

import com.backend.entity.Course;
import com.backend.entity.Teach;
import com.backend.entity.Teacher;
import com.backend.entity.tools.RestBean;
import com.backend.service.CourseService;
import com.backend.service.TeachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/index")
public class CourseController {

    @Autowired
    CourseService courseService;
    @Autowired
    TeachService teachService;

    @GetMapping("/Courses")
    RestBean<List<Course>> findAllCourse() {
        List<Course> list = courseService.findAllCourse();
        return RestBean.success(list);
    }

    @GetMapping("/getCoursesByUsername/{username}")
    RestBean<List<Course>> findCoursesByUsername(@PathVariable String username) {
        List<Course> list = courseService.findAllCourseByUsername(username);
        return RestBean.success(list);
    }

    @PostMapping("/getCourseByCourseName")
    RestBean<List<Course> > findCourseByName(@RequestParam("name") String name) {
        List<Course> list = courseService.findCourseByName(name);
        return RestBean.success(list);
    }

    @PostMapping("/getCourseByUName")
    RestBean<List<Course> > findCourseByUName(@RequestParam("name") String name,
                                              @RequestParam("uname") String uname) {
        List<Course> list = courseService.findCourseByUName(name,uname);
        return RestBean.success(list);
    }

    @PostMapping("/getCourseByTeacherName")
    RestBean<List<Course> > getCourseByTeacherName(@RequestParam("name") String name) {
        List<Course> list = courseService.findCourseByTName(name);
        return RestBean.success(list);
    }

    @GetMapping("/CourseById/{id}")
    RestBean<Course> findCourseById(@PathVariable("id") int id) {
        Course course = courseService.findCourseById(id);
        return RestBean.success(course);
    }

    @GetMapping("/CourseByStatus/{status}")
    RestBean<List<Course> > findCourseByStatus(@PathVariable("status") int status) {
        List<Course> list = courseService.findCourseByIsOnline(status);
        return RestBean.success(list);
    }

    @PostMapping("/CourseByUStatus")
    RestBean<List<Course> > findCourseByUIsOnline(@RequestParam("status") int status,
                                                  @RequestParam("uname") String uname) {
        List<Course> list = courseService.findCourseByUIsOnline(status,uname);
        return RestBean.success(list);
    }

    @PostMapping("/insertCourse")
    RestBean<String> insertCourse(@RequestParam("courseName") String courseName,
                                  @RequestParam("courseDescription") String courseDescription,
                                  @RequestParam("coursePrice") Double coursePrice,
                                  @RequestParam("teacherId") String teacherId,
                                  @RequestParam("onlineStatus") int onlineStatus) {
        Course course = new Course();
        course.setCourseName(courseName);
        course.setCourseDescription(courseDescription);
        course.setCoursePrice(coursePrice);
        course.setOnlineStatus(onlineStatus);
        boolean f1 = courseService.insertCourse(course);
        boolean f2 = false;
        long cId = courseService.findCourseByName(courseName).get(0).getCourseId();
        Teach teach = new Teach();
        Course course1 = new Course();
        course1.setCourseId(cId);
        teach.setCourse(course1);
        teacherId = teacherId.replace("[", "");
        teacherId = teacherId.replace("]", "");
        String[] strs = teacherId.split(",");
        for (int i = 0; i < strs.length; i++) {
            Teacher teacher = new Teacher();
            teacher.setTeacherId(Integer.parseInt(strs[i]));
            teach.setTeacher(teacher);
            f2 = teachService.insertTeach(teach);
        }
        if (f1 && f2) {
            return RestBean.success("添加成功");
        } else {
            return RestBean.failure(404, "添加失败");
        }
    }

    @GetMapping("/deleteCourse/{id}")
    RestBean<String> deleteCourse(@PathVariable("id") int id) {
        boolean f1 = teachService.deleteteachByCid(id);
        boolean f2 = courseService.deleteCourse(id);
        if (f1 && f2) {
            return RestBean.success("删除成功");
        } else {
            return RestBean.failure(404, "删除失败");
        }
    }

    @PostMapping("/updateCourse")
    RestBean<String> updateCourse(@RequestParam("courseId") int courseId,
                                  @RequestParam("courseName") String courseName,
                                  @RequestParam("courseDescription") String courseDescription,
                                  @RequestParam("coursePrice") Double coursePrice,
                                  @RequestParam("teacherId") String teacherId,
                                  @RequestParam("onlineStatus") int onlineStatus) {
        Course course = new Course();
        course.setCourseId(courseId);
        course.setCourseName(courseName);
        course.setCourseDescription(courseDescription);
        course.setCoursePrice(coursePrice);
        course.setOnlineStatus(onlineStatus);
        teachService.deleteteachByCid(courseId);
        teacherId = teacherId.replace("[", "");
        teacherId = teacherId.replace("]", "");
        if (teacherId.length() != 0) {
            Teach teach = new Teach();
            teach.setCourse(course);
            String[] strs = teacherId.split(",");
            for (int i = 0; i < strs.length; i++) {
                Teacher teacher = new Teacher();
                teacher.setTeacherId(Integer.parseInt(strs[i]));
                teach.setTeacher(teacher);
                teachService.insertTeach(teach);
            }
        }
        boolean f = courseService.updateCourse(course);
        if (f) {
            return RestBean.success("修改成功");
        } else {
            return RestBean.failure(404, "修改失败");
        }
    }

    @PostMapping("/updateCourseStatus/{id}")
    public void updateCourseStatus(@PathVariable("id") int id
            , @RequestParam("onlineStatus") int status) {
        courseService.updateCourseStatus(id, status);
    }
}
