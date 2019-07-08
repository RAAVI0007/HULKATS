package com.web.hulklogic.service;

import java.util.List;

import com.web.hulklogic.entity.ApplicantDetails;
import com.web.hulklogic.entity.ApplicantInfo;
import com.web.hulklogic.entity.AutoSignature;
import com.web.hulklogic.entity.BulkEmailSend;
import com.web.hulklogic.entity.CreateTemplate;
import com.web.hulklogic.entity.JobTitle;
import com.web.hulklogic.entity.Sharejobs;

public interface ApplicantService {
	public List<CreateTemplate> autoCompleteTemplate(String templatename);

	public List<CreateTemplate> getSubjectBody(String templatename);

	public boolean sendBulkEmailToApplicant(BulkEmailSend bulkemailsend, AutoSignature autoSignature);

	public int updatepassword(int userid, String password);

	public int createpassword(int userid, String password);

	public boolean updateAndInsertStatus(ApplicantInfo applicant);

	public List<JobTitle> getStatusByApplicationid(int applicationid);

	public int updateCvsDocument(String documentname, int applicantid);

	public int updateCvsDocument1(String documentname, int applicantid);

	public int updateDocument1(String documentname, int applicantid);

	public int updateDocument2(String documentname, int applicantid);

	public int updateDocument3(String documentname, int applicantid);

	public int updateDocuemnt4(String documentname, int applicantid);

	public int updateDocument5(String documentname, int applicantid);

	public boolean getappemailCheck(String email);

	public boolean getappmobileCheck(String contactno);

	public int getapplicantid(String email);

	public boolean getappemailpassCheck(String email, String password);

	public boolean appchecking(int jobid, int applicantid);
	
	public boolean appcheckinghold(int jobid, int applicantid);

	public ApplicantDetails profile(int applicantdetails_id);
	
	public boolean shareemail(Sharejobs sharejob);
}
