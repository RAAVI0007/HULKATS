
package com.web.hulklogic.service;

import java.util.List;

import com.web.hulklogic.entity.AlertDetails;
import com.web.hulklogic.entity.Applicants;
import com.web.hulklogic.entity.ApplicantsDocuments;
import com.web.hulklogic.entity.CardDetails;
import com.web.hulklogic.entity.CreateJob;
import com.web.hulklogic.entity.GetEmployee;
import com.web.hulklogic.entity.JobTitle;
import com.web.hulklogic.entity.JobsDocuments;
import com.web.hulklogic.entity.QuoteMessages;
import com.web.hulklogic.entity.Role;
import com.web.hulklogic.entity.Sharejobs;
import com.web.hulklogic.entity.User;

public interface UserService {
	public int saveUser(User user);

	public int saveQuote(QuoteMessages quote);
	
	public int demo(QuoteMessages quote);

	public String createJob(CreateJob createjob);

	public Role getRole(String rolename);

	public User getUserByEmail(String email);

	public boolean getCardByEmail(String email);

	public User checkLogin(String email, String password);

	public Applicants getApplicantByEmail(String email);

	public List<User> getAllUsers();

	public List<CreateJob> getAllJobs(String currentMail);

	public List<CreateJob> getalljobs();
	
	public List<CreateJob> getallrecentjobs(String company);
	
	public List<CreateJob> searchjobs(CreateJob createjob);

	public CreateJob getVideo(int jobid);

	public int savecard(String cardname);

	// public Map<String,String> createApplicants(Applicants applicants);
	public String createApplicants(Applicants applicants);

	public List<Applicants> getAllAplicants(String organization);

	public JobsDocuments getJobsDocuments(int jobid);

	public ApplicantsDocuments getAllApplicantsDocuments(int applicantid);

	public List<String> getAllRecruiterEmails();

	public String saveJobsKeyWords(String jobsKeyWords, int experience, int jobid);

	public String saveApplicantsKeyWords(String applicantsKeyWords, int experience, int applicantid);

	public String saveAssignJobs(JobTitle jobtitle);

	public boolean sendActivationLink(User user);
	
	public boolean sendRefferalLink(GetEmployee getemployee);

	public boolean sendApplicantLink(Applicants createApplicants);

	public boolean sendbussinesscarddetails(CardDetails carddetails);

	public boolean sendForgetPwdLink(User user);
	
	public boolean sharejob(Sharejobs sharejob);

	public List<User> getAllRecruiters(int roleid);

	public int getRoleId(String roleName);

	public boolean getUsernameCheck(String email);

	public boolean getContactnoCheck(String contactno);

	public boolean getCardContactnoCheck(String contactno);

	public boolean jobassignRecruiter(AlertDetails alertDetails);
	
	public boolean jobassignManager(AlertDetails alertDetails);

	public CreateJob jobdetails(int jobid);

	public Applicants getapplicantdetails(int applicantid);

	public String createApplicantManager(Applicants applicants);

	public List<User> getCityAndStateNames(int phonecode);

	public List<CreateJob> getCountryNameAndId();

	public List<CreateJob> getStateAndCitiesByCountryId(int countryId);

	public Role getRoleName(int roleid);

	public boolean sendapplicantmail(Applicants applicant);

	public List<CardDetails> getallcards();

	public CardDetails getcarddetails(int card_id);

	public int deleteApplicant(int applicantid);

	public boolean sendApplicantActivationLink(Applicants applicants);

	public boolean sendcontactdetails(QuoteMessages quote);
	
	public boolean demomessage(QuoteMessages quote);

	public boolean sendcontdetails(QuoteMessages quote);

	public int saveContact(QuoteMessages quote);
	
	public int checkTeamCount(String teamname);
	
	public String getRecruiterEmail(String teamname);
	
	public int checkmanager(String companyname);
	
	public List<User> getCompanyList();
	
	public boolean updatetemplateid(int templatedesignid, String teamname);
	
	public String gettemplateid(String email);

}
