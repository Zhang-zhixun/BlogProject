package com.backend.mapper;

import com.backend.entity.Teach;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TeachMapper {
    
    @Select("select * from blog.teach where teacher_id = #{id}")
    @Results({
            @Result(property = "teacher",column = "teacher_id", one=@One(select = "com.backend.mapper.TeacherMapper.findTeacherById")),
            @Result(property = "course",column = "course_id", many=@Many(select = "com.backend.mapper.CourseMapper.findCourseById"))
    })
    List<Teach> findTeachByTeacherId(int id);

    @Select("select * from blog.teach where course_id = #{id}")
    @Results({
            @Result(property = "teacher",column = "teacher_id", one=@One(select = "com.backend.mapper.TeacherMapper.findTeacherById")),
            @Result(property = "course",column = "course_id", many=@Many(select = "com.backend.mapper.CourseMapper.findCourseById"))
    })
    List<Teach> findTeachByCourseId(int id);

    @Select("select * from blog.teach")
    @Results({
            @Result(property = "teacher",column = "teacher_id", one=@One(select = "com.backend.mapper.TeacherMapper.findTeacherById")),
            @Result(property = "course",column = "course_id", many=@Many(select = "com.backend.mapper.CourseMapper.findCourseById"))
    })
    List<Teach> findAllTeach();

    @Insert("""
            insert into blog.teach (teacher_id,course_id) VALUES
            (#{teacher.teacherId},#{course.courseId})
            """)
    boolean insertTeach(Teach teach);

    @Delete("delete from blog.teach where teacher_id = #{tid} course_id = #{cid}")
    boolean deleteteach(int tid,int cid);

    @Update("""
            update blog.teach set teacher_id=#{teacher.teacherId},course_id=#{course.courseId}
            where teacher_id = #{teacher.teacherId}
            """)
    boolean updateTeach(Teach teach);
}
