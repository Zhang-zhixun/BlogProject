package com.backend.mapper;

import com.backend.entity.CertificateTemplate;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CertificateTemplateMapper {

    @Select("select * from blog.certificate_template where template_id = #{id}")
    CertificateTemplate findCertificateTemplateById(int id);

    @Select("call getByNameCertificateTemplate(#{name})")
    List<CertificateTemplate> findCertificateTemplateByName(String name);

    @Insert("""
            insert into blog.certificate_template (template_name,template_content) VALUES
            (#{templateName},#{templateContent})
            """)
    boolean insertCertificateTemplate(CertificateTemplate certificateTemplate);

    @Delete("delete from blog.certificate_template where template_id = #{id}")
    boolean deleteCertificateTemplate(int id);

    @Update("""
            update blog.certificate_template set template_name=#{templateName},template_content=#{templateContent},
            where template_id=#{templateId}
            """)
    boolean updateCertificateTemplate(CertificateTemplate certificateTemplate);

}
