package com.backend.service.impl;

import com.backend.entity.CertificateTemplate;
import com.backend.mapper.CertificateTemplateMapper;
import com.backend.service.CertificateTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateTemplateServiceImpl implements CertificateTemplateService {

    @Autowired
    CertificateTemplateMapper certificateTemplateMapper;

    @Override
    public CertificateTemplate findCertificateTemplateById(int id) {
        return certificateTemplateMapper.findCertificateTemplateById(id);
    }

    @Override
    public List<CertificateTemplate> findCertificateTemplateByName(String name) {
        return certificateTemplateMapper.findCertificateTemplateByName(name);
    }

    @Override
    public boolean insertCertificateTemplate(CertificateTemplate certificateTemplate) {
        return certificateTemplateMapper.insertCertificateTemplate(certificateTemplate);
    }

    @Override
    public boolean deleteCertificateTemplate(int id) {
        return certificateTemplateMapper.deleteCertificateTemplate(id);
    }

    @Override
    public boolean updateCertificateTemplate(CertificateTemplate certificateTemplate) {
        return certificateTemplateMapper.updateCertificateTemplate(certificateTemplate);
    }
}
