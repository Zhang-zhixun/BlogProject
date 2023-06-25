package com.backend.mapper;

import com.backend.entity.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TeacherMapper {

    @Select("select * from blog.teacher where teacher_id = #{id}")
    Teacher findTeacherById(int id);

    @Select("select * from blog.teacher")
    List<Teacher> findAllTeacher();

    @Select("""
            SELECT t.*
            FROM teacher t
            JOIN teach tc ON t.teacher_id = tc.teacher_id
            WHERE tc.course_id = #{id};
            """)
    List<Teacher> findTeacherByCourseId(int id);

    @Select("select * from blog.teacher where name = #{name}")
    List<Teacher> findTeacherByName(String name);

    @Insert("""
            insert into blog.teacher (name,gender,age,user_account_username) VALUES
            (#{name},#{gender},#{age},#{userAccount.userAccountUsername})
            """)
    boolean insertTeacher(Teacher teacher);

    @Delete("delete from blog.teacher where teacher_id = #{id}")
    boolean deleteTeacher(int id);

    @Update("""
            update blog.teacher set name=#{name},gender=#{gender},
            age=#{age},user_account_username=#{userAccount.userAccountUsername}
            where teacher_id=#{teacherId}
            """)
    boolean updateTeacher(Teacher teacher);
}
