package com.web.hulklogic.service;

import java.util.List;

import com.web.hulklogic.entity.ApplicantInfo;
import com.web.hulklogic.entity.AutoSignature;
import com.web.hulklogic.entity.CreateTemplate;
import com.web.hulklogic.entity.JobTitle;
import com.web.hulklogic.entity.JobsDocuments;
import com.web.hulklogic.entity.Target;

public interface JobTitleService {
	public List<JobTitle> getJobTitle(String teamname);

	public String createApplicantDirectly(JobTitle jobtitle);
	
	public String createApplicantDirectly1(JobTitle jobtitle);
	
	public String saveAddComment(String addComment, int app_id, String email);

	public String addCommenthistory(String addComment, int applicantid, String email);

	public String updateAndSaveApplicant(JobTitle jobtitle);

	public JobsDocuments getJobAndApplicantDocuments(int app_id);

	public List<JobTitle> getAppCommnetByAppId(int appId);

	public String saveTarget(Target target);

	public ApplicantInfo getApplicantInfoByAppId(int appId);
	
	public ApplicantInfo getProfileInfoByAppId(int appId);

	public String saveAutoSignature(AutoSignature autoSignature);

	public CreateTemplate getTemplateName(String templateName);

	public List<CreateTemplate> getAllTemplateNames(String organization);

	public Target getTargetsDetails(String teamname);

	public String updateTargetsDetails(Target target);

}
