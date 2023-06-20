package com.backend.mapper;

import com.backend.entity.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseMapper {

    @Select("select * from blog.course")
    @Results({
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "courseDescription", column = "course_description"),
            @Result(property = "coursePrice", column = "course_price"),
            @Result(property = "teacher", column = "teacher_id", many = @Many(select = "com.backend.mapper.TeacherMapper.findTeacherById")),
            @Result(property = "onlineStatus", column = "online_status")
    })
    List<Course> findAllCourse();

    @Select("select * from blog.course where course_id = #{id}")
    @Results({
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "courseDescription", column = "course_description"),
            @Result(property = "coursePrice", column = "course_price"),
            @Result(property = "teacher", column = "teacher_id", one = @One(select = "com.backend.mapper.TeacherMapper.findTeacherById")),
            @Result(property = "onlineStatus", column = "online_status")
    })
    Course findCourseById(int id);

    @Select("select * from blog.course where course_name = #{name}")
    @Results({
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "courseDescription", column = "course_description"),
            @Result(property = "coursePrice", column = "course_price"),
            @Result(property = "teacher", column = "teacher_id", one = @One(select = "com.backend.mapper.TeacherMapper.findTeacherById")),
            @Result(property = "onlineStatus", column = "online_status")
    })
    Course findCourseByName(String name);

    @Insert("""
            insert into blog.course (course_name,course_description,course_price,teacher_id,online_status) VALUES
            (#{courseName},#{courseDescription},#{coursePrice},#{teacher.teacherId},#{onlineStatus})
            """)
    boolean insertCourse(Course course);

    @Delete("delete from blog.course where course_id = #{id}")
    boolean deleteCourse(int id);

    @Update("""
            update blog.course set course_name=#{courseName},course_description=#{courseDescription},
            course_price=#{coursePrice},teacher_id=#{teacher.teacherId},online_status=#{onlineStatus}
            where course_id = #{courseId}
            """)
    boolean updateCourse(Course course);

    @Update("""
            update blog.course set online_status=#{status}
            where course_id = #{id}
            """)
    boolean updateCourseStatus(int id, int status);
}
