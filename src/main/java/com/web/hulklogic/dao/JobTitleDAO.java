package com.web.hulklogic.dao;

import java.util.List;

import com.web.hulklogic.entity.ApplicantInfo;
import com.web.hulklogic.entity.AutoSignature;
import com.web.hulklogic.entity.CreateTemplate;
import com.web.hulklogic.entity.JobTitle;
import com.web.hulklogic.entity.JobsDocuments;
import com.web.hulklogic.entity.Target;

public interface JobTitleDAO {
	public List<JobTitle> getJobTitle(String teamname);

	public int createApplicantDirectly(JobTitle jobtitle);

	public int createApplicantDirectly1(JobTitle jobtitle);

	public int saveAddComment(String addComment, int app_id, String email);

	public int addComment(String addcomments, int applicantid);

	public int createapp(int applicationid, String email);

	public int updateStatus(int applicationid);

	public int addCommentHistory(String addComments, int applicantid, String email);

	public int updateStatusApplicant(int applicationid);

	public JobsDocuments getJobAndApplicantDocuments(int app_id);

	public List<JobTitle> getAppCommnetByAppId(int appId);

	public ApplicantInfo getApplicantInfoByAppId(int appId);

	public ApplicantInfo getProfileInfoByAppId(int appId);

	public int saveAutoSignature(AutoSignature autoSignature);

	public CreateTemplate getTemplateName(String templateName);

	public List<CreateTemplate> getAllTemplateNames(String organization);

	public int getLastApp_Id();

	public int insertAppIdInAppHistory(int appId, String email);

	public int saveJobApplicantIdInApplication(JobTitle jobtitle);

	public int saveTargetMonthly(Target target);

	public int saveTargetQuarterly(Target target);

	public int saveTargetYearly(Target target);

	public Target getTargetsDetails(String teamname);

	public int updateTargetsMonthly(Target target);

	public int updateTargetsQuarterly(Target target);

	public int updateTargetsYearly(Target target);

}
