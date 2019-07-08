package com.web.hulklogic.dao;

import java.util.List;

import com.web.hulklogic.entity.ApplicantInfo;
import com.web.hulklogic.entity.Applicants;
import com.web.hulklogic.entity.CreateTemplate;
import com.web.hulklogic.entity.GetEmployee;
import com.web.hulklogic.entity.GetTokens;
import com.web.hulklogic.entity.JobTitle;
import com.web.hulklogic.entity.SenderEmail;
import com.web.hulklogic.entity.User;

public interface ApplicantionDAO {

	public int saveJobIdApplicantId(GetTokens gettokens);
	
	public int saveemployeereferraljobs(GetEmployee getemployee);

	public List<User> autoCompleteCities(int phonecode, String statenames);

	public List<Applicants> getApplicantDetails(int jobid, String teamname);

	public int saveTemplate(CreateTemplate createtemplate);

	public List<CreateTemplate> getAllTemplates(int template_id);

	public List<CreateTemplate> getTemplateNameId();

	public SenderEmail getApplicantDetailsById(int applicantid);

	public Applicants getUploadDocuments(int applicantid);

	public Applicants getOtherUploadDocuments(int applicantid);

	public List<Applicants> getApplicantsNameEmail();

	public int getLastTemplateId();

	public int getLastTemplatename(String template_name);

	public int updateTemplate(CreateTemplate createtemplate);

	public JobTitle getjobtitle(int jobid);

	public List<Applicants> getApplicationDetails(int jobid, String teamname);

	public int updateTemplateWithOutAttachement(CreateTemplate createtemplate);

	public int updateTemplateAttachement(CreateTemplate createtemplate);

	public int updateTemplateAttachement1(CreateTemplate createtemplate);

	public int updateSourceInAppStart(int appId, String source, String createdEmail);

	public int updateSourceInAppHistory(ApplicantInfo applicant);

	public String getApplicationStatusByApp_Id(int app_id);

	public String getAssignEmail(int jobid);

}
