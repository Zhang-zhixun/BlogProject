package com.backend.controller;

import com.backend.entity.Comment;
import com.backend.entity.tools.RestBean;
import com.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/index")
public class CommentController {

    @Autowired
    CommentService commentService;


    @GetMapping("/CommentsInASC")
    public RestBean<List<Comment>> findAllCommentInASC() {
        return RestBean.success(commentService.findAllCommentInASC());
    }

    @GetMapping("/CommentsInDESC")
    public RestBean<List<Comment>> findAllCommentInDESC() {
        return RestBean.success(commentService.findAllCommentInDESC());
    }

    @GetMapping("/getCommentByCourseName/{id}")
    public RestBean<List<Comment>> findCommentByCourseName(@PathVariable int id) {
        return RestBean.success(commentService.findCommentByCourseId(id));
    }

    @GetMapping("/getCommentByUserId/{id}")
    public RestBean<List<Comment>> findCommentByUserId(@PathVariable int id) {
        return RestBean.success(commentService.findCommentByUserId(id));
    }

    @PostMapping("/getCommentByContent")
    public RestBean<List<Comment>> findCommentByContent(@RequestParam("content") String content) {
        return RestBean.success(commentService.findCommentByContent(content));
    }

    @PostMapping("/getCommentByDay")
    public RestBean<List<Comment>> findCommentByDay(@RequestParam("day") String day) {
        return RestBean.success(commentService.findCommentByDay(day));
    }

    @GetMapping("/getCommentByIsHidden/{info}")
    public RestBean<List<Comment>> findCommentByIsHidden(@PathVariable int info) {
        return RestBean.success(commentService.findCommentByIsHidden(info));
    }

    @PostMapping("/getCommentByUserName")
    public RestBean<List<Comment>> findCommentByUserName(@RequestParam("name") String name) {
        return RestBean.success(commentService.findCommentByUserName(name));
    }

    @PostMapping("/getCommentByCourseName")
    public RestBean<List<Comment>> findCommentByCourseName(@RequestParam("name") String name) {
        return RestBean.success(commentService.findCommentByCourseName(name));
    }


    @GetMapping("/getCommentByCommentId/{id}")
    public RestBean<Comment> findCommentByCommentId(@PathVariable int id) {
        return RestBean.success(commentService.findCommentByCommentId(id));
    }


    @GetMapping("/deleteCommentByCommentId/{id}")
    public RestBean<String> deleteCommentByCommentId(@PathVariable int id) {
        if (commentService.deleteCommentByCommentId(id)) {
            return RestBean.success("删除成功");
        } else {
            return RestBean.failure(404, "删除失败");
        }
    }

    @GetMapping("/deleteCommentByUserId/{id}")
    public RestBean<String> deleteCommentByUserId(@PathVariable int id) {
        if (commentService.deleteCommentByUserId(id)) {
            return RestBean.success("删除成功");
        } else {
            return RestBean.failure(404, "删除失败");
        }
    }

    @GetMapping("/deleteCommentByCourseId/{id}")
    public RestBean<String> deleteCommentByCourseId(@PathVariable int id) {
        if (commentService.deleteCommentByCourseId(id)) {
            return RestBean.success("删除成功");
        } else {
            return RestBean.failure(404, "删除失败");
        }
    }

    @PostMapping("/updateCommentIsHidden")
    public boolean updateCommentIsHidden(@RequestParam("id") int id,@RequestParam("isHidden") int isHidden) {
        return commentService.updateCommentIsHidden(id,isHidden);
    }

}
