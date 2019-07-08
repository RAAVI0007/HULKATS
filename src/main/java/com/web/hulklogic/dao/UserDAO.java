package com.web.hulklogic.dao;

import java.util.List;

import com.web.hulklogic.entity.Applicants;
import com.web.hulklogic.entity.ApplicantsDocuments;
import com.web.hulklogic.entity.CardDetails;
import com.web.hulklogic.entity.CreateJob;
import com.web.hulklogic.entity.QuoteMessages;
import com.web.hulklogic.entity.Role;
import com.web.hulklogic.entity.User;

public interface UserDAO {

	public int saveUser(User user);

	public int saveQuote(QuoteMessages quote);
	
	public int demo(QuoteMessages quote);

	public int saveContact(QuoteMessages quote);

	public Role getRole(String rolename);

	public User getUserByEmail(String email);

	public int getCardByEmail(String email);

	public User checkLogin(String email, String password);

	public Applicants getapplicantByEmail(String email);

	public List<User> getAllUsers();

	public ApplicantsDocuments getAllApplicantsDocuments(int applicantid);

	public List<String> getAllRecruiterEmails();

	public int saveJobsKeyWords(String jobsKeyWords, int experience, int jobid);

	public int saveApplicantsKeyWords(String applicantsKeyWords, int experience, int applicantid);

	public int saveAssignJobs(String email, int jobid);

	public int getRoleId(String roleName);

	public List<User> getAllRecruiters(int roleid);

	public int getUsernameCheck(String email);

	public int getContactnoCheck(String contactno);

	public int getCardContactnoCheck(String contactno);

	public int checkJobAssign(int jobid);

	public int updateJobAssign(int jobid, String email);

	public List<User> getCityAndStateNames(int phonecode);

	public List<CreateJob> getCountryNameAndId();

	public List<CreateJob> getStateAndCitiesByCountryId(int countryId);

	public Role getRoleName(int roleid);

	public CreateJob getVideo(int jobid);

	public int savecard(String cardname);

	public List<CardDetails> getallcards();

	public CardDetails getcarddetails(int card_id);

	public int deleteApplicant(int applicantid);
	
	public int checkTeamCount(String teamname);
	
	public String getRecruiterEmail(String teamname);
	
	public int checkmanager(String companyname);
	
	public List<User> getCompanyList();
	
	public int updateTemplate(int templatedesignid, String teamname);
	
	public String gettemplateid(String email);
	
	public List<CreateJob> searchjobs(String joborloc,String company);
	public List<CreateJob> searchjobsbytags(CreateJob createjob);
	public List<CreateJob>  searchjobsbydepartment(CreateJob createjob);
	public List<CreateJob>  searchjobsbylocation(CreateJob createjob);
	public List<CreateJob>  searchjobsbytitle(CreateJob createjob);
	public List<CreateJob>  searchjobsbyall(CreateJob createjob);
	public List<CreateJob>  searchjobsbytitleloc(CreateJob createjob);
	public List<CreateJob>  searchjobsbydepttags(CreateJob createjob);
	public List<CreateJob>  searchjobsbytitledept(CreateJob createjob);
	public List<CreateJob>  searchjobsbyloctags(CreateJob createjob);
	public List<CreateJob>  searchjobsbytitletags(CreateJob createjob);
	public List<CreateJob> searchjobsbydeptloc(CreateJob createjob);

}
