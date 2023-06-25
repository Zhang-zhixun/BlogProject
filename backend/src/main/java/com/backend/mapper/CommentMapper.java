package com.backend.mapper;

import com.backend.entity.Chapter;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("select * from blog.chapter where course_id = #{id} order by sort_order")
    @Results({
            @Result(property = "chapterId", column = "chapter_id"),
            @Result(property = "course", column = "course_id", one = @One(select = "com.backend.mapper.CourseMapper.findCourseById")),
            @Result(property = "chapterTitle", column = "chapter_title"),
            @Result(property = "sortOrder", column = "sort_order"),
    })
    List<Chapter> findChapterByCourseId(int id);

    @Select("select * from blog.chapter where chapter_id = #{id}")
    @Results({
            @Result(property = "chapterId", column = "chapter_id"),
            @Result(property = "course", column = "course_id", one = @One(select = "com.backend.mapper.CourseMapper.findCourseById")),
            @Result(property = "chapterTitle", column = "chapter_title"),
            @Result(property = "sortOrder", column = "sort_order"),
    })
    Chapter findChapterByChapterId(int id);

    @Insert("""
            insert into blog.chapter (course_id,chapter_title,sort_order) VALUES
            (#{course.courseId},#{chapterTitle},#{sortOrder})
            """)
    boolean insertChapter(Chapter chapter);

    @Delete("delete from blog.chapter where chapter_id = #{id}")
    boolean deleteChapter(int id);

    @Update("""
            update blog.chapter set course_id=#{course.courseId},chapter_title=#{chapterTitle},sort_order=#{sortOrder}
            where chapter_id = #{chapterId}
            """)
    boolean updateChapter(Chapter chapter);

}
