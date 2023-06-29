package com.backend.service;

import com.backend.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findAllCommentInASC();

    List<Comment> findAllCommentInDESC();

    List<Comment> findCommentByCourseId(int id);

    List<Comment> findCommentByUserId(int id);

    List<Comment> findCommentByContent(String content);

    List<Comment> findCommentByDay(String day);

    List<Comment> findCommentByIsHidden(int info);

    List<Comment> findCommentByUserName(String name);

    List<Comment> findCommentByCourseName(String name);

    Comment findCommentByCommentId(int id);

    boolean deleteCommentByCommentId(int id);

    boolean deleteCommentByUserId(int id);

    boolean deleteCommentByCourseId(int id);

    boolean updateCommentIsHidden(int id,int isHidden);
}
