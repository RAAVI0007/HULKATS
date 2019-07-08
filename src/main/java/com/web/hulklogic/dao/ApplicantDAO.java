package com.web.hulklogic.dao;

import java.util.List;

import com.web.hulklogic.entity.ApplicantDetails;
import com.web.hulklogic.entity.ApplicantInfo;
import com.web.hulklogic.entity.CreateTemplate;
import com.web.hulklogic.entity.JobTitle;
import com.web.hulklogic.entity.Sharejobs;

public interface ApplicantDAO {

	public List<CreateTemplate> autoCompleteTemplate(String templatename);

	public List<CreateTemplate> getSubjectBody(String templatename);

	public int updatepassword(int userid, String password);
	
	public int createpassword(int userid, String password);

	public int insertStatus(ApplicantInfo applicant);

	public int updateStatus(int applicationid, String status, String email);

	public List<JobTitle> getStatusByApplicationid(int app_id);

	public int updateCvsDocument(String documentname, int applicantid);

	public int updateCvsDocument1(String documentname, int applicantid);

	public int updateDocument1(String documentname, int applicantid);

	public int updateDocument2(String documentname, int applicantid);

	public int updateDocument3(String documentname, int applicantid);

	public int updateDocuemnt4(String documentname, int applicantid);

	public int updateDocument5(String documentname, int applicantid);

	public int getappemailCheck(String email);

	public int getappmobileCheck(String contactno);

	public int getapplicantid(String email);
	
	public int getappemailpassCheck(String email, String password);
	
	public int appchecking(int jobid, int applicantid);
	
	public int appcheckinghold(int jobid, int applicantid);
	
	public ApplicantDetails profile(int applicantdetails_id);
	
	public int insertshareemail(Sharejobs sharejob);
	
	
}
