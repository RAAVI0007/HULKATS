package com.web.hulklogic.service.Impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.web.hulklogic.dao.ApplicantionDAO;
import com.web.hulklogic.entity.ApplicantInfo;
import com.web.hulklogic.entity.Applicants;
import com.web.hulklogic.entity.AutoSignature;
import com.web.hulklogic.entity.CreateTemplate;
import com.web.hulklogic.entity.GetEmployee;
import com.web.hulklogic.entity.GetTokens;
import com.web.hulklogic.entity.JobTitle;
import com.web.hulklogic.entity.SenderEmail;
import com.web.hulklogic.entity.User;
import com.web.hulklogic.service.ApplicationService;
import com.web.hulklogic.utility.EmailTemplate;

@Service
public class ApplicationServiceImpl implements ApplicationService {
	private static Logger logger = Logger.getLogger(ApplicantServiceImpl.class);
	@Autowired
	private ApplicantionDAO applicantdao;

	@Override
	public String saveJobidApplicantId(GetTokens gettokens) {
		int result = applicantdao.saveJobIdApplicantId(gettokens);

		if (result == 1) {
			return "success";
		}

		return "fail";
	}
	
	@Override
	public String saveemployeereferraljobs(GetEmployee getemployee){
		int result = applicantdao.saveemployeereferraljobs(getemployee);
		if (result == 1) {
			return "success";
		}

		return "fail";
	}

	@Override
	public List<Applicants> getApplicantDetails(int jobid, String teamname) {
		return applicantdao.getApplicantDetails(jobid, teamname);
	}

	@Override
	public String saveTemplate(CreateTemplate createtemplate) {
		int result = applicantdao.saveTemplate(createtemplate);
		if (result > 0) {
			int lastTemplateId = applicantdao.getLastTemplateId();
			logger.info("lastTemplateId :" + lastTemplateId);
			return "success " + lastTemplateId;
		}
		return "fail";
	}

	@Override
	public List<CreateTemplate> getAllTemplates(int template_id) {
		return applicantdao.getAllTemplates(template_id);
	}

	@Override
	public List<CreateTemplate> getTemplateNameId() {
		return applicantdao.getTemplateNameId();
	}

	@Override
	public SenderEmail getAppllicantDetailsById(int applicantid) {
		return applicantdao.getApplicantDetailsById(applicantid);
	}

	@Override
	public boolean sendEmailToApplicant(SenderEmail senderemail, AutoSignature autoSignature) {
		EmailTemplate emailtemplate = new EmailTemplate();
		boolean status = emailtemplate.sendMail(senderemail, autoSignature);
		if (status) {
			return true;
		}
		return false;
	}

	@Override
	public Applicants getUploadDocuments(int applicantid) {

		return applicantdao.getUploadDocuments(applicantid);
	}

	@Override
	public List<Applicants> getApplicantsNameEmail() {

		return applicantdao.getApplicantsNameEmail();
	}

	@Override
	public Applicants getOtherUploadDocuments(int applicantid) {

		return applicantdao.getOtherUploadDocuments(applicantid);
	}

	@Override
	public int getLastTemplateId() {

		return applicantdao.getLastTemplateId();

	}

	@Override
	public boolean getLastTemplatename(String template_name) {
		int count = applicantdao.getLastTemplatename(template_name);
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public String updateTemplate(CreateTemplate createtemplate) {
		int result = 0;

		if (createtemplate.getFileType() != null && createtemplate.getFileType1() != null) {

			logger.info("file type :" + createtemplate.getFileType());
			logger.info("file type1 :" + createtemplate.getFileType1());

			result = applicantdao.updateTemplate(createtemplate);

		} else if (createtemplate.getFileType() == null && createtemplate.getFileType1() == null) {
			logger.info("file type :" + createtemplate.getFileType());
			logger.info("file type1 :" + createtemplate.getFileType1());

			result = applicantdao.updateTemplateWithOutAttachement(createtemplate);

		} else if (createtemplate.getFileType() != null) {
			logger.info("file type :" + createtemplate.getFileType());
			result = applicantdao.updateTemplateAttachement(createtemplate);

		} else if (createtemplate.getFileType1() != null) {
			logger.info("file type1 :" + createtemplate.getFileType1());

			result = applicantdao.updateTemplateAttachement1(createtemplate);
		}

		if (result > 0) {
			return "success";
		}
		return "fail";
	}

	@Override
	public JobTitle getjobtitle(int jobid) {

		return applicantdao.getjobtitle(jobid);
	}

	@Override
	public List<Applicants> getApplicationDetails(int jobid, String teamname) {
		return applicantdao.getApplicationDetails(jobid, teamname);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = SQLException.class)
	public String updateSourceAndInsertSource(ApplicantInfo applicant) {
		try {
			int status = applicantdao.updateSourceInAppStart(applicant.getApp_id(), applicant.getSource(),
					applicant.getEmail());
			int statushistorty = applicantdao.updateSourceInAppHistory(applicant);

			if (status != 0 && statushistorty != 0) {
				return "success";
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "fail";
	}

	@Override
	public List<User> autoCompleteCities(int phonecode, String statenames) {

		return applicantdao.autoCompleteCities(phonecode, statenames);
	}

	@Override
	public String getApplicationStatusByApp_Id(int app_id) {
		// TODO Auto-generated method stub
		return applicantdao.getApplicationStatusByApp_Id(app_id);
	}

	@Override
	public String getAssignEmail(int jobid) {
		return applicantdao.getAssignEmail(jobid);
	}

}
