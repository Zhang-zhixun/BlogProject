package com.backend.controller;

import com.backend.entity.CertificateTemplate;
import com.backend.entity.tools.RestBean;
import com.backend.service.CertificateTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/index")
public class CertificateTemplateController {

    @Autowired
    CertificateTemplateService certificateTemplateService;

    @GetMapping("/getCertificateTemplateById/{id}")
    public CertificateTemplate findCertificateTemplateById(@PathVariable int id) {
        return certificateTemplateService.findCertificateTemplateById(id);
    }

    @PostMapping("/getCertificateTemplateByName")
    public List<CertificateTemplate> findCertificateTemplateByName(@RequestParam("name") String name) {
        return certificateTemplateService.findCertificateTemplateByName(name);
    }

    @PostMapping("/insertCertificateTemplate")
    public RestBean<String> insertCertificateTemplate(@RequestParam("templateName") String templateName,
                                                      @RequestParam("templateContent") String templateContent) {
        CertificateTemplate certificateTemplate = new CertificateTemplate();
        certificateTemplate.setTemplateName(templateName);
        certificateTemplate.setTemplateContent(templateContent);
        if(certificateTemplateService.insertCertificateTemplate(certificateTemplate)){
            return RestBean.success("添加成功！");
        }else{
            return RestBean.failure(404,"添加失败！");
        }
    }

    @GetMapping("/deleteCertificateTemplate/{id}")
    public RestBean<String> deleteCertificateTemplate(@PathVariable int id) {
        if(certificateTemplateService.deleteCertificateTemplate(id)){
            return RestBean.success("删除成功！");
        }else{
            return RestBean.failure(404,"删除失败！");
        }
    }

    @PostMapping("/updateCertificateTemplate")
    public RestBean<String> updateCertificateTemplate(@RequestParam("templateId") int templateId,
                                                      @RequestParam("templateName") String templateName,
                                                      @RequestParam("templateContent") String templateContent) {
        CertificateTemplate certificateTemplate = new CertificateTemplate();
        certificateTemplate.setTemplateId(templateId);
        certificateTemplate.setTemplateName(templateName);
        certificateTemplate.setTemplateContent(templateContent);
        if(certificateTemplateService.insertCertificateTemplate(certificateTemplate)){
            return RestBean.success("修改成功！");
        }else{
            return RestBean.failure(404,"修改失败！");
        }
    }

}
