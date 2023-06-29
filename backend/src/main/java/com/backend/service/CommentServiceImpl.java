package com.backend.service;

import com.backend.entity.Comment;
import com.backend.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentMapper commentMapper;

    @Override
    public List<Comment> findAllCommentInASC() {
        return commentMapper.findAllCommentInASC();
    }

    @Override
    public List<Comment> findAllCommentInDESC() {
        return commentMapper.findAllCommentInDESC();
    }

    @Override
    public List<Comment> findCommentByCourseId(int id) {
        return commentMapper.findCommentByCourseId(id);
    }

    @Override
    public List<Comment> findCommentByUserId(int id) {
        return commentMapper.findCommentByUserId(id);
    }

    @Override
    public List<Comment> findCommentByContent(String content) {
        return commentMapper.findCommentByContent(content);
    }

    @Override
    public List<Comment> findCommentByDay(String day) {
        return commentMapper.findCommentByDay(day);
    }

    @Override
    public List<Comment> findCommentByIsHidden(int info) {
        return commentMapper.findCommentByIsHidden(info);
    }

    @Override
    public List<Comment> findCommentByUserName(String name) {
        return commentMapper.findCommentByUserName(name);
    }

    @Override
    public List<Comment> findCommentByCourseName(String name) {
        return commentMapper.findCommentByCourseName(name);
    }

    @Override
    public Comment findCommentByCommentId(int id) {
        return commentMapper.findCommentByCommentId(id);
    }

    @Override
    public boolean deleteCommentByCommentId(int id) {
        return commentMapper.deleteCommentByCommentId(id);
    }

    @Override
    public boolean deleteCommentByUserId(int id) {
        return commentMapper.deleteCommentByUserId(id);
    }

    @Override
    public boolean deleteCommentByCourseId(int id) {
        return commentMapper.deleteCommentByCourseId(id);
    }

    @Override
    public boolean updateCommentIsHidden(int id, int isHidden) {
        return commentMapper.updateCommentIsHidden(id,isHidden);
    }
}
