package com.web.hulklogic.service;

import java.util.List;

import com.web.hulklogic.entity.ApplicantInfo;
import com.web.hulklogic.entity.Applicants;
import com.web.hulklogic.entity.AutoSignature;
import com.web.hulklogic.entity.CreateTemplate;
import com.web.hulklogic.entity.GetEmployee;
import com.web.hulklogic.entity.GetTokens;
import com.web.hulklogic.entity.JobTitle;
import com.web.hulklogic.entity.SenderEmail;
import com.web.hulklogic.entity.User;

public interface ApplicationService {
	public String saveJobidApplicantId(GetTokens gettokens);
	
	public String saveemployeereferraljobs(GetEmployee getemployee);

	public List<User> autoCompleteCities(int phonecode, String statenames);

	public List<Applicants> getApplicantDetails(int jobid, String teamname);

	public String saveTemplate(CreateTemplate createtemplate);

	public List<CreateTemplate> getAllTemplates(int template_id);

	public List<CreateTemplate> getTemplateNameId();

	public SenderEmail getAppllicantDetailsById(int applicantid);

	public boolean sendEmailToApplicant(SenderEmail senderemail, AutoSignature autoSignature);

	public Applicants getUploadDocuments(int applicantid);

	public Applicants getOtherUploadDocuments(int applicantid);

	public List<Applicants> getApplicantsNameEmail();

	public int getLastTemplateId();

	public boolean getLastTemplatename(String template_name);

	public String updateTemplate(CreateTemplate createtemplate);

	public JobTitle getjobtitle(int jobid);

	public List<Applicants> getApplicationDetails(int jobid, String teamname);

	public String updateSourceAndInsertSource(ApplicantInfo applicant);

	public String getApplicationStatusByApp_Id(int app_id);

	public String getAssignEmail(int jobid);

}
