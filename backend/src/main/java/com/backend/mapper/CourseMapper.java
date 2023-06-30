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
            @Result(property = "teachers", column = "course_id", many = @Many(select = "com.backend.mapper.TeacherMapper.findTeacherByCourseId")),
            @Result(property = "onlineStatus", column = "online_status")
    })
    List<Course> findAllCourse();

    @Select("""
            select * from course where course_id in
            (
            	select course_id from teach where teacher_id =
            	(
            		select teacher_id from teacher where user_account_username = #{username}
            	)
            )
            """)
    @Results({
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "courseDescription", column = "course_description"),
            @Result(property = "coursePrice", column = "course_price"),
            @Result(property = "teachers", column = "course_id", many = @Many(select = "com.backend.mapper.TeacherMapper.findTeacherByCourseId")),
            @Result(property = "onlineStatus", column = "online_status")
    })
    List<Course> findAllCourseByUsername(String username);

    @Select("select * from blog.course where course_id = #{id}")
    @Results({
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "courseDescription", column = "course_description"),
            @Result(property = "coursePrice", column = "course_price"),
            @Result(property = "teachers", column = "course_id", many = @Many(select = "com.backend.mapper.TeacherMapper.findTeacherByCourseId")),
            @Result(property = "onlineStatus", column = "online_status")
    })
    Course findCourseById(int id);

    @Select("call getByNameCourse(#{name})")
    @Results({
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "courseDescription", column = "course_description"),
            @Result(property = "coursePrice", column = "course_price"),
            @Result(property = "teachers", column = "course_id", many = @Many(select = "com.backend.mapper.TeacherMapper.findTeacherByCourseId")),
            @Result(property = "onlineStatus", column = "online_status")
    })
    List<Course> findCourseByName(String name);

    @Select("call getByUNameCourse(#{name},#{uName})")
    @Results({
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "courseDescription", column = "course_description"),
            @Result(property = "coursePrice", column = "course_price"),
            @Result(property = "teachers", column = "course_id", many = @Many(select = "com.backend.mapper.TeacherMapper.findTeacherByCourseId")),
            @Result(property = "onlineStatus", column = "online_status")
    })
    List<Course> findCourseByUName(String name,String uName);

    @Select("""
            select * from course
            where course_id in (
            select course_id from teach
            where teacher_id in(
            select teacher_id from teacher
            where name like concat('%',#{name},'%')
            )
            );
            """)
    @Results({
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "courseDescription", column = "course_description"),
            @Result(property = "coursePrice", column = "course_price"),
            @Result(property = "teachers", column = "course_id", many = @Many(select = "com.backend.mapper.TeacherMapper.findTeacherByCourseId")),
            @Result(property = "onlineStatus", column = "online_status")
    })
    List<Course> findCourseByTName(String name);

    @Select("select * from blog.course where online_status = #{status}")
    @Results({
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "courseDescription", column = "course_description"),
            @Result(property = "coursePrice", column = "course_price"),
            @Result(property = "teachers", column = "course_id", many = @Many(select = "com.backend.mapper.TeacherMapper.findTeacherByCourseId")),
            @Result(property = "onlineStatus", column = "online_status")
    })
    List<Course> findCourseByIsOnline(int status);

    @Select("""
            select * from blog.course where online_status = #{status}
            and course_id in
                (
                	select course_id from teach where teacher_id =
                	(
                		select teacher_id from teacher where user_account_username = #{uname}
                	)
                );
            """)
    @Results({
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "courseDescription", column = "course_description"),
            @Result(property = "coursePrice", column = "course_price"),
            @Result(property = "teachers", column = "course_id", many = @Many(select = "com.backend.mapper.TeacherMapper.findTeacherByCourseId")),
            @Result(property = "onlineStatus", column = "online_status")
    })
    List<Course> findCourseByUIsOnline(int status, String uname);

    @Insert("""
            insert into blog.course (course_name,course_description,course_price,online_status) VALUES
            (#{courseName},#{courseDescription},#{coursePrice},#{onlineStatus})
            """)
    boolean insertCourse(Course course);

    @Delete("delete from blog.course where course_id = #{id}")
    boolean deleteCourse(int id);

    @Update("""
            update blog.course set course_name=#{courseName},course_description=#{courseDescription},
            course_price=#{coursePrice},online_status=#{onlineStatus}
            where course_id = #{courseId}
            """)
    boolean updateCourse(Course course);

    @Update("""
            update blog.course set online_status=#{status}
            where course_id = #{id}
            """)
    boolean updateCourseStatus(int id, int status);
}
