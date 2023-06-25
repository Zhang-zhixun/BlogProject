package com.backend.mapper;

import com.backend.entity.CourseResource;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseResourceMapper {

    @Select("select * from blog.course_resource where chapter_id = #{id} order by sort_order")
    @Results({
            @Result(property = "resourceId", column = "resource_id"),
            @Result(property = "chapter", column = "chapter_id", one = @One(select = "com.backend.mapper.ChapterMapper.findChapterByChapterId")),
            @Result(property = "resourceType", column = "resource_type"),
            @Result(property = "resourceUrl", column = "resource_url"),
            @Result(property = "sortOrder", column = "sort_order"),
    })
    List<CourseResource> findCourseResourceByChapterId(int id);

    @Select("select * from blog.course_resource where resource_type = #{type} and chapter_id = #{id} order by sort_order")
    @Results({
            @Result(property = "resourceId", column = "resource_id"),
            @Result(property = "chapter", column = "chapter_id", one = @One(select = "com.backend.mapper.ChapterMapper.findChapterByChapterId")),
            @Result(property = "resourceType", column = "resource_type"),
            @Result(property = "resourceUrl", column = "resource_url"),
            @Result(property = "sortOrder", column = "sort_order"),
    })
    List<CourseResource> findCourseResourceByResourceType(String type,int id);


    @Select("select * from blog.course_resource where resource_id = #{id}")
    @Results({
            @Result(property = "resourceId", column = "resource_id"),
            @Result(property = "chapter", column = "chapter_id", one = @One(select = "com.backend.mapper.ChapterMapper.findChapterByChapterId")),
            @Result(property = "resourceType", column = "resource_type"),
            @Result(property = "resourceUrl", column = "resource_url"),
            @Result(property = "sortOrder", column = "sort_order"),
    })
    CourseResource findCourseResourceByResourceId(int id);

    @Insert("""
            insert into blog.course_resource (chapter_id,resource_type,resource_url,sort_order) VALUES
            (#{chapter.chapterId},#{resourceType},#{resourceUrl},#{sortOrder})
            """)
    boolean insertCourseResource(CourseResource courseResource);

    @Delete("delete from blog.course_resource where resource_id = #{id}")
    boolean deleteCourseResource(int id);

    @Update("""
            update blog.course_resource set chapter_id=#{chapter.chapterId},resource_type=#{resourceType},resource_url=#{resourceUrl},sort_order=#{sortOrder}
            where resource_id = #{resourceId}
            """)
    boolean updateCourseResource(CourseResource courseResource);

}
