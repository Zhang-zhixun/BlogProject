package com.backend.controller;

import com.backend.entity.Chapter;
import com.backend.entity.Course;
import com.backend.entity.tools.RestBean;
import com.backend.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/index")
public class ChapterController {

    @Autowired
    ChapterService chapterService;

    @GetMapping("/getChapterByCourseId/{id}")
    RestBean<List<Chapter>> findCharpterByCourseId(@PathVariable("id") int id) {
        List<Chapter> list = chapterService.findChapterByCourseId(id);
        return RestBean.success(list);
    }

    @GetMapping("/getChapterByChapterId/{id}")
    RestBean<Chapter> findCharpterByChapterId(@PathVariable("id") int id) {
        return RestBean.success(chapterService.findChapterByChapterId(id));
    }
    @PostMapping("/insertChapter")
    RestBean<String> insertChapter(@RequestParam("courseId") int courseId,
                                   @RequestParam("chapterTitle") String chapterTitle,
                                   @RequestParam("sortOrder") int sortOrder) {
        Course course = new Course();
        course.setCourseId(courseId);
        Chapter chapter = new Chapter();
        chapter.setCourse(course);
        chapter.setChapterTitle(chapterTitle);
        chapter.setSortOrder(sortOrder);
        if (chapterService.insertChapter(chapter)) {
            return RestBean.success("添加成功");
        } else {
            return RestBean.failure(404, "添加失败");
        }
    }

    @GetMapping("/deleteChapter/{id}")
    RestBean<String> deleteChapter(@PathVariable("id") int id) {
        if (chapterService.deleteChapter(id)) {
            return RestBean.success("删除成功");
        } else {
            return RestBean.failure(404, "删除失败");
        }
    }

    @PostMapping("/updateChapter")
    RestBean<String> updateChapter(@RequestParam("chapterId") int chapterId,
                                   @RequestParam("courseId") int courseId,
                                   @RequestParam("chapterTitle") String chapterTitle,
                                   @RequestParam("sortOrder") int sortOrder) {
        Course course = new Course();
        course.setCourseId(courseId);
        Chapter chapter = new Chapter();
        chapter.setChapterId(chapterId);
        chapter.setCourse(course);
        chapter.setChapterTitle(chapterTitle);
        chapter.setSortOrder(sortOrder);
        if (chapterService.updateChapter(chapter)) {
            return RestBean.success("修改成功");
        } else {
            return RestBean.failure(404, "修改失败");
        }
    }
}
