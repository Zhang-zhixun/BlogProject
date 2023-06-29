package com.backend.mapper;

import com.backend.entity.Chapter;
import com.backend.entity.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("select * from blog.comment order by publish_time")
    @Results({
            @Result(property = "commentId", column = "comment_id"),
            @Result(property = "user", column = "user_id", one = @One(select = "com.backend.mapper.UserUserMapper.SelectOnefindByUserId")),
            @Result(property = "course", column = "course_id", one = @One(select = "com.backend.mapper.CourseMapper.findCourseById")),
            @Result(property = "content", column = "content"),
            @Result(property = "publishTime", column = "publish_time"),
            @Result(property = "likeCount", column = "like_count"),
            @Result(property = "isHidden", column = "is_hidden"),
    })
    List<Comment> findAllCommentInASC();

    @Select("select * from blog.comment order by publish_time desc")
    @Results({
            @Result(property = "commentId", column = "comment_id"),
            @Result(property = "user", column = "user_id", one = @One(select = "com.backend.mapper.UserUserMapper.SelectOnefindByUserId")),
            @Result(property = "course", column = "course_id", one = @One(select = "com.backend.mapper.CourseMapper.findCourseById")),
            @Result(property = "content", column = "content"),
            @Result(property = "publishTime", column = "publish_time"),
            @Result(property = "likeCount", column = "like_count"),
            @Result(property = "isHidden", column = "is_hidden"),
    })
    List<Comment> findAllCommentInDESC();

    @Select("select * from blog.comment where course_id = #{id} order by publish_time")
    @Results({
            @Result(property = "commentId", column = "comment_id"),
            @Result(property = "user", column = "user_id", one = @One(select = "com.backend.mapper.UserUserMapper.SelectOnefindByUserId")),
            @Result(property = "course", column = "course_id", one = @One(select = "com.backend.mapper.CourseMapper.findCourseById")),
            @Result(property = "content", column = "content"),
            @Result(property = "publishTime", column = "publish_time"),
            @Result(property = "likeCount", column = "like_count"),
            @Result(property = "isHidden", column = "is_hidden"),
    })
    List<Comment> findCommentByCourseId(int id);

    @Select("select * from blog.comment where user_id = #{id} order by publish_time")
    @Results({
            @Result(property = "commentId", column = "comment_id"),
            @Result(property = "user", column = "user_id", one = @One(select = "com.backend.mapper.UserUserMapper.SelectOnefindByUserId")),
            @Result(property = "course", column = "course_id", one = @One(select = "com.backend.mapper.CourseMapper.findCourseById")),
            @Result(property = "content", column = "content"),
            @Result(property = "publishTime", column = "publish_time"),
            @Result(property = "likeCount", column = "like_count"),
            @Result(property = "isHidden", column = "is_hidden"),
    })
    List<Comment> findCommentByUserId(int id);

    @Select("""
            select * from blog.comment where user_id in
            (
            select user_id from user where name like concat('%',#{name},'%')
            )
            order by publish_time
            """)
    @Results({
            @Result(property = "commentId", column = "comment_id"),
            @Result(property = "user", column = "user_id", one = @One(select = "com.backend.mapper.UserUserMapper.SelectOnefindByUserId")),
            @Result(property = "course", column = "course_id", one = @One(select = "com.backend.mapper.CourseMapper.findCourseById")),
            @Result(property = "content", column = "content"),
            @Result(property = "publishTime", column = "publish_time"),
            @Result(property = "likeCount", column = "like_count"),
            @Result(property = "isHidden", column = "is_hidden"),
    })
    List<Comment> findCommentByUserName(String name);

    @Select("call getByCourseNameComment(#{name})")
    @Results({
            @Result(property = "commentId", column = "comment_id"),
            @Result(property = "user", column = "user_id", one = @One(select = "com.backend.mapper.UserUserMapper.SelectOnefindByUserId")),
            @Result(property = "course", column = "course_id", one = @One(select = "com.backend.mapper.CourseMapper.findCourseById")),
            @Result(property = "content", column = "content"),
            @Result(property = "publishTime", column = "publish_time"),
            @Result(property = "likeCount", column = "like_count"),
            @Result(property = "isHidden", column = "is_hidden"),
    })
    List<Comment> findCommentByCourseName(String name);

    @Select("select * from blog.comment where content like concat('%',#{content},'%')")
    @Results({
            @Result(property = "commentId", column = "comment_id"),
            @Result(property = "user", column = "user_id", one = @One(select = "com.backend.mapper.UserUserMapper.SelectOnefindByUserId")),
            @Result(property = "course", column = "course_id", one = @One(select = "com.backend.mapper.CourseMapper.findCourseById")),
            @Result(property = "content", column = "content"),
            @Result(property = "publishTime", column = "publish_time"),
            @Result(property = "likeCount", column = "like_count"),
            @Result(property = "isHidden", column = "is_hidden"),
    })
    List<Comment> findCommentByContent(String content);

    @Select("select * from blog.comment where DATE(publish_time) = #{day}")
    @Results({
            @Result(property = "commentId", column = "comment_id"),
            @Result(property = "user", column = "user_id", one = @One(select = "com.backend.mapper.UserUserMapper.SelectOnefindByUserId")),
            @Result(property = "course", column = "course_id", one = @One(select = "com.backend.mapper.CourseMapper.findCourseById")),
            @Result(property = "content", column = "content"),
            @Result(property = "publishTime", column = "publish_time"),
            @Result(property = "likeCount", column = "like_count"),
            @Result(property = "isHidden", column = "is_hidden"),
    })
    List<Comment> findCommentByDay(String day);

    @Select("select * from blog.comment where is_hidden = #{info}")
    @Results({
            @Result(property = "commentId", column = "comment_id"),
            @Result(property = "user", column = "user_id", one = @One(select = "com.backend.mapper.UserUserMapper.SelectOnefindByUserId")),
            @Result(property = "course", column = "course_id", one = @One(select = "com.backend.mapper.CourseMapper.findCourseById")),
            @Result(property = "content", column = "content"),
            @Result(property = "publishTime", column = "publish_time"),
            @Result(property = "likeCount", column = "like_count"),
            @Result(property = "isHidden", column = "is_hidden"),
    })
    List<Comment> findCommentByIsHidden(int info);

    @Select("select * from blog.comment where comment_id = #{id}")
    @Results({
            @Result(property = "commentId", column = "comment_id"),
            @Result(property = "user", column = "user_id", one = @One(select = "com.backend.mapper.UserUserMapper.SelectOnefindByUserId")),
            @Result(property = "course", column = "course_id", one = @One(select = "com.backend.mapper.CourseMapper.findCourseById")),
            @Result(property = "content", column = "content"),
            @Result(property = "publishTime", column = "publish_time"),
            @Result(property = "likeCount", column = "like_count"),
            @Result(property = "isHidden", column = "is_hidden"),
    })
    Comment findCommentByCommentId(int id);

    @Delete("delete from blog.comment where comment_id = #{id}")
    boolean deleteCommentByCommentId(int id);

    @Delete("delete from blog.comment where user_id = #{id}")
    boolean deleteCommentByUserId(int id);

    @Delete("delete from blog.comment where course_id = #{id}")
    boolean deleteCommentByCourseId(int id);

    @Update("""
            update blog.comment set is_hidden=#{isHidden}
            where comment_id = #{id}
            """)
    boolean updateCommentIsHidden(int id, int isHidden);

}
