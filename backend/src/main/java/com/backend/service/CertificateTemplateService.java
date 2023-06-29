package com.backend.service;

import com.backend.entity.CertificateTemplate;

import java.util.List;

public interface CertificateTemplateService {

    CertificateTemplate findCertificateTemplateById(int id);

    List<CertificateTemplate> findCertificateTemplateByName(String name);

    boolean insertCertificateTemplate(CertificateTemplate certificateTemplate);

    boolean deleteCertificateTemplate(int id);

    boolean updateCertificateTemplate(CertificateTemplate certificateTemplate);

}
