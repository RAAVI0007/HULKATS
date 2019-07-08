package com.web.hulklogic.service.Impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.web.hulklogic.dao.EditDetailsDAO;
import com.web.hulklogic.dao.JobDAO;
import com.web.hulklogic.dao.SupportDAO;
import com.web.hulklogic.dao.UserDAO;
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
import com.web.hulklogic.service.UserService;
import com.web.hulklogic.utility.EmailUtility;

@Service
public class UserServiceImpl implements UserService {
	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	@Autowired
	private UserDAO userdao;
	@Autowired
	private JobDAO jobdao;
	@Autowired
	private EditDetailsDAO editDetailsDao;
	@Autowired
	private SupportDAO supportDAO;

	@Override
	public int saveUser(User user) {
		return userdao.saveUser(user);
	}

	@Override
	public int saveQuote(QuoteMessages quote) {
		return userdao.saveQuote(quote);
	}

	@Override
	public int demo(QuoteMessages quote) {
		return userdao.demo(quote);
	}

	@Override
	public String createJob(CreateJob createjob) {
		int count = jobdao.createJob(createjob);
		if (count > 0) {
			int lastJobId = editDetailsDao.getLastJobId();
			logger.info("lastJobId :" + lastJobId);
			return "success " + lastJobId;
		}
		return "fail";
	}

	@Override
	public Role getRole(String rolename) {
		return userdao.getRole(rolename);
	}

	@Override
	public Applicants getApplicantByEmail(String email) {
		return userdao.getapplicantByEmail(email);
	}

	@Override
	public User getUserByEmail(String email) {
		return userdao.getUserByEmail(email);
	}

	@Override
	public boolean getCardByEmail(String email) {

		int count = userdao.getCardByEmail(email);
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<User> getAllUsers() {
		return userdao.getAllUsers();
	}

	@Override
	public List<CreateJob> getAllJobs(String teamname) {
		return jobdao.getAllJobs(teamname);
	}

	@Override
	public List<CreateJob> getalljobs() {
		return jobdao.getalljobs();
	}

	@Override
	public List<CreateJob> getallrecentjobs(String company) {
		return jobdao.getallrecentjobs(company);
	}

	/*
	 * @Override
	 * 
	 * @Transactional(propagation=Propagation.REQUIRED,readOnly=false,
	 * rollbackFor=SQLException.class) public Map<String, String>
	 * createApplicants(Applicants applicants) { Map<String, String> map = new
	 * HashMap<>(); String status ="fail"; AlertDetails alertDetails=new
	 * AlertDetails();
	 * alertDetails.setCreated_alert(applicants.getApplicantcreatedemail());
	 * alertDetails.setUserid(applicants.getUserid());
	 * alertDetails.setSender_email(applicants.getSenderEmail());
	 * alertDetails.setAlert_type("Applicant Alert"); int
	 * saveApplicant=jobdao.createApplicants(applicants); int
	 * lastApplicantId=editDetailsDao.getLastApplicantId();
	 * logger.info("lastApplicantId :"+lastApplicantId);
	 * alertDetails.setRef_applicantid(lastApplicantId); int
	 * saveAlterDetails=supportDAO.saveAlertDetails(alertDetails);
	 * if(saveApplicant==0 || saveAlterDetails==0){ status = "fail"; }else{
	 * status = "success"; } map.put("status", status);
	 * map.put("lastApplicantId", String.valueOf(lastApplicantId)); return map;
	 * }
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = SQLException.class)
	public String createApplicants(Applicants applicants) {
		AlertDetails alertDetails = new AlertDetails();
		alertDetails.setCreated_alert(applicants.getApplicantcreatedemail());
		alertDetails.setUserid(applicants.getUserid());
		alertDetails.setSender_email(applicants.getSenderEmail());
		alertDetails.setAlert_type("Applicant Alert");
		int saveApplicant = jobdao.createApplicants(applicants);
		int lastApplicantId = editDetailsDao.getLastApplicantId();
		logger.info("lastApplicantId :" + lastApplicantId);
		alertDetails.setRef_applicantid(lastApplicantId);
		int saveAlterDetails = supportDAO.saveAlertDetails(alertDetails);
		if (saveApplicant == 0 || saveAlterDetails == 0) {
			return "fail";
		}
		return "success " + lastApplicantId;
	}

	@Override
	public List<Applicants> getAllAplicants(String organization) {
		return jobdao.getAllApplicants(organization);
	}

	@Override
	public JobsDocuments getJobsDocuments(int jobid) {

		return jobdao.getDocumentsByJobId(jobid);
	}

	@Override
	public ApplicantsDocuments getAllApplicantsDocuments(int applicantid) {
		return userdao.getAllApplicantsDocuments(applicantid);
	}

	@Override
	public List<String> getAllRecruiterEmails() {
		return userdao.getAllRecruiterEmails();
	}

	@Override
	public String saveJobsKeyWords(String jobsKeyWords, int experience, int jobid) {
		int count = userdao.saveJobsKeyWords(jobsKeyWords, experience, jobid);
		if (count > 0) {
			return "success";
		}
		return "fail";
	}

	@Override
	public String saveApplicantsKeyWords(String applicantsKeyWords, int experience, int applicantid) {
		int count = userdao.saveApplicantsKeyWords(applicantsKeyWords, experience, applicantid);
		if (count > 0) {
			return "success";
		}
		return "fail";
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = SQLException.class)
	public String saveAssignJobs(JobTitle jobtitle) {
		int checkJobAssign = userdao.checkJobAssign(jobtitle.getJobid());
		logger.info("checkJobAssign:" + checkJobAssign);
		int saveJobAssign = 0;
		int saveAlterDetails = 0;
		if (checkJobAssign == 0) {
			AlertDetails alertDetails = new AlertDetails();
			alertDetails.setUserid(jobtitle.getUserid());
			alertDetails.setRef_jobid(jobtitle.getJobid());
			alertDetails.setSender_email(jobtitle.getReferncedemail());
			alertDetails.setAlert_type("Job Alert");
			saveJobAssign = userdao.saveAssignJobs(jobtitle.getReferncedemail(), jobtitle.getJobid());
			saveAlterDetails = supportDAO.saveAlertDetails(alertDetails);
			if (saveJobAssign == 0 || saveAlterDetails == 0) {
				return "fail";
			}
			return "success";
		}
		return "Job is assign already";
	}

	@Override
	public boolean sendActivationLink(User user) {
		logger.info("Sending activation mail to:" + user.getEmail());
		EmailUtility emailUtility = new EmailUtility();
		String[] details = new String[2];
		details[1] = "/login/createpassword" + "?" + "id:" + user.getUserid();
		String activate = "activated";
		boolean status = emailUtility.sendMail(user, details, activate);
		if (status) {
			return true;
		}
		logger.info("status Service to:" + status);
		return false;
	}

	@Override
	public boolean sendRefferalLink(GetEmployee getemployee) {
		logger.info("Sending activation mail to:" + getemployee.getEmail());
		EmailUtility emailUtility = new EmailUtility();
		boolean status = emailUtility.sendreferrallink(getemployee);
		if (status) {
			return true;
		}
		logger.info("status Service to:" + status);
		return false;
	}

	@Override
	public boolean sendApplicantLink(Applicants createApplicants) {
		logger.info("Sending activation mail to:" + createApplicants.getEmail());
		EmailUtility emailUtility = new EmailUtility();
		String[] details = new String[2];
		details[1] = "/login/createpassword" + "?" + "id:" + createApplicants.getApplicantid();
		String activate = "activated";
		boolean status = emailUtility.sendApplicantMail(createApplicants, details, activate);
		if (status) {
			return true;
		}
		logger.info("status Service to:" + status);
		return false;
	}

	@Override
	public boolean sendapplicantmail(Applicants applicant) {
		logger.info("Sending activation mail to:" + applicant.getEmail());
		EmailUtility emailUtility = new EmailUtility();
		String[] details = new String[2];
		details[1] = "/login/createpassword" + "?" + "id:" + applicant.getApplicantid();
		String activate = "activated";

		boolean status = emailUtility.sendapplicantMail(applicant, details, activate);
		if (status) {
			return true;
		}
		logger.info("status Service to:" + status);
		return false;

	}

	@Override
	public boolean sendForgetPwdLink(User user) {
		logger.info("Sending forgetpwd mail to:" + user.getEmail());
		EmailUtility emailUtility = new EmailUtility();
		String[] details = new String[2];
		details[1] = "/login/resetpassword" + "?" + "id:" + user.getUserid();
		String resetpassword = "resetpassword";
		emailUtility.sendMail(user, details, resetpassword);
		return false;
	}

	@Override
	public List<User> getAllRecruiters(int roleid) {

		return userdao.getAllRecruiters(roleid);
	}

	@Override
	public int getRoleId(String roleName) {
		logger.info("rolename:" + roleName);
		return userdao.getRoleId(roleName);
	}

	@Override
	public boolean getUsernameCheck(String email) {
		int count = userdao.getUsernameCheck(email);
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean getContactnoCheck(String contactno) {
		int count = userdao.getContactnoCheck(contactno);
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean getCardContactnoCheck(String contactno) {
		int count = userdao.getCardContactnoCheck(contactno);
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = SQLException.class)
	public boolean jobassignRecruiter(AlertDetails alertDetails) {
		int saveJobAssign = 0;
		int alertDetaills = 0;
		saveJobAssign = jobdao.jobassignRecruiter(alertDetails.getJobassignemail(), alertDetails.getRef_jobid());
		alertDetaills = supportDAO.saveAlertDetails(alertDetails);
		if (saveJobAssign == 0 || alertDetaills == 0) {
			return false;
		}
		return true;

	}

	public boolean jobassignManager(AlertDetails alertDetails) {
		int saveJobAssign = 0;

		saveJobAssign = jobdao.jobassignRecruiter(alertDetails.getJobassignemail(), alertDetails.getRef_jobid());

		if (saveJobAssign == 0) {
			return false;
		}
		return true;

	}

	@Override
	public CreateJob jobdetails(int jobid) {
		return jobdao.jobdetails(jobid);
	}

	@Override
	public Applicants getapplicantdetails(int applicantid) {
		return jobdao.getapplicantdetails(applicantid);
	}

	@Override
	public String createApplicantManager(Applicants applicants) {
		logger.info("Create Applicant Manager");
		int saveApplicant = jobdao.createApplicants(applicants);
		int lastApplicantId = editDetailsDao.getLastApplicantId();
		if (saveApplicant > 0) {
			return "success " + lastApplicantId;
		}
		return "fail";
	}

	@Override
	public List<User> getCityAndStateNames(int phonecode) {
		return userdao.getCityAndStateNames(phonecode);
	}

	@Override
	public List<CreateJob> getCountryNameAndId() {
		return userdao.getCountryNameAndId();
	}

	@Override
	public List<CreateJob> getStateAndCitiesByCountryId(int countryId) {
		return userdao.getStateAndCitiesByCountryId(countryId);
	}

	@Override
	public Role getRoleName(int roleid) {

		return userdao.getRoleName(roleid);
	}

	@Override
	public User checkLogin(String email, String password) {

		return userdao.checkLogin(email, password);
	}

	@Override
	public CreateJob getVideo(int jobid) {
		logger.info(jobid);

		return userdao.getVideo(jobid);
	}

	@Override
	public int savecard(String cardname) {

		return userdao.savecard(cardname);
	}

	@Override
	public List<CardDetails> getallcards() {
		// TODO Auto-generated method stub
		return userdao.getallcards();
	}

	@Override
	public CardDetails getcarddetails(int card_id) {

		return userdao.getcarddetails(card_id);
	}

	@Override
	public boolean sendbussinesscarddetails(CardDetails carddetails) {
		logger.info("Sending activation mail to:" + carddetails.getEmail());
		EmailUtility emailUtility = new EmailUtility();

		boolean status = emailUtility.sendbussinesscard(carddetails);
		if (status) {
			return true;
		}
		logger.info("status Service to:" + status);
		return false;
	}

	@Override
	public int deleteApplicant(int applicantid) {
		return userdao.deleteApplicant(applicantid);
	}

	@Override
	public boolean sendApplicantActivationLink(Applicants applicants) {
		logger.info("Sending activation mail to:" + applicants.getEmail());
		EmailUtility emailUtility = new EmailUtility();
		String[] details = new String[2];
		details[1] = "/login/activate/" + applicants.getApplicantid();
		String activate = "activated";
		boolean status = emailUtility.sendapplicantMail(applicants, details, activate);
		if (status) {
			return true;
		}
		logger.info("status Service to:" + status);
		return false;
	}

	public boolean sendcontactdetails(QuoteMessages quote) {
		logger.info("Sending activation mail to:" + quote.getEmail());
		EmailUtility emailUtility = new EmailUtility();

		boolean status = emailUtility.sendcontactMail(quote);
		if (status) {
			return true;
		}
		logger.info("status Service to:" + status);
		return false;
	}

	public boolean demomessage(QuoteMessages quote) {
		logger.info("Sending activation mail to:" + quote.getEmail());
		EmailUtility emailUtility = new EmailUtility();

		boolean status = emailUtility.senddemomessage(quote);
		if (status) {
			boolean status1 = emailUtility.senddemomessagetocustomer(quote);
			if (status1) {
			return true;
			}else{
				return false;
			}
		}
		logger.info("status Service to:" + status);
		return false;
	}

	public boolean sendcontdetails(QuoteMessages quote) {
		logger.info("Sending activation mail to:" + quote.getEmail());
		EmailUtility emailUtility = new EmailUtility();

		boolean status = emailUtility.sendcontMail(quote);
		if (status) {
			return true;
		}
		logger.info("status Service to:" + status);
		return false;
	}

	public int saveContact(QuoteMessages quote) {
		return this.userdao.saveContact(quote);
	}

	@Override
	public int checkTeamCount(String teamname) {
		return userdao.checkTeamCount(teamname);
	}

	@Override
	public String getRecruiterEmail(String teamname) {
		return userdao.getRecruiterEmail(teamname);
	}

	@Override
	public int checkmanager(String companyname) {
		return userdao.checkmanager(companyname);
	}

	@Override
	public boolean sharejob(Sharejobs sharejob) {
		logger.info("Sending activation mail to:" + sharejob.getEmail());
		EmailUtility emailUtility = new EmailUtility();

		boolean status = emailUtility.sharejob(sharejob);
		if (status) {
			return true;
		}
		logger.info("status Service to:" + status);
		return false;
	}

	@Override
	public List<User> getCompanyList() {
		return userdao.getCompanyList();
	}

	@Override
	public boolean updatetemplateid(int templatedesignid, String teamname) {
		int result = userdao.updateTemplate(templatedesignid, teamname);
		if (result >= 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String gettemplateid(String email) {
		return userdao.gettemplateid(email);
	}

	@Override
	public List<CreateJob> searchjobs(CreateJob createjob) {
		List<CreateJob> search = null;
		logger.info(createjob.getJobtitle());
		logger.info(createjob.getLocation());
		logger.info(createjob.getDepartment());
		logger.info(createjob.getJobtags());
		
		if (createjob.getJobtitle().equals("NA")  && createjob.getLocation().equals("NA") && createjob.getDepartment().equals("NA")
				&& !createjob.getJobtags().equals("NA")) {
			logger.info("1");
			search =  userdao.searchjobsbytags(createjob);

		} else if (createjob.getJobtitle().equals("NA") && createjob.getLocation().equals("NA")
				&& !createjob.getDepartment().equals("NA") && createjob.getJobtags().equals("NA")) {
			logger.info("2");
			search =  userdao.searchjobsbydepartment(createjob);

		} else if (createjob.getJobtitle().equals("NA") && !createjob.getLocation().equals("NA")
				&& createjob.getDepartment().equals("NA") && createjob.getJobtags().equals("NA")) {
			logger.info("3");
			search = userdao.searchjobsbylocation(createjob);

		}

		else if (!createjob.getJobtitle().equals("NA") && createjob.getLocation().equals("NA") 
				&& createjob.getDepartment().equals("NA") && createjob.getJobtags().equals("NA")) {
			logger.info("4");
			search = userdao.searchjobsbytitle(createjob);
		}
		else if (!createjob.getJobtitle().equals("NA") && !createjob.getLocation().equals("NA") 
				&& !createjob.getDepartment().equals("NA") && !createjob.getJobtags().equals("NA")) {
			logger.info("5");
			search = userdao.searchjobsbyall(createjob);
		}
		else if (!createjob.getJobtitle().equals("NA") && !createjob.getLocation().equals("NA") 
				&& createjob.getDepartment().equals("NA") && createjob.getJobtags().equals("NA")) {
			logger.info("6");
			search = userdao.searchjobsbytitleloc(createjob);
		}
		else if (createjob.getJobtitle().equals("NA") && createjob.getLocation().equals("NA") 
				&& !createjob.getDepartment().equals("NA") && !createjob.getJobtags().equals("NA")) {
			logger.info("7");
			search = userdao.searchjobsbydepttags(createjob);
		}
		else if (!createjob.getJobtitle().equals("NA") && createjob.getLocation().equals("NA") 
				&& !createjob.getDepartment().equals("NA") && createjob.getJobtags().equals("NA")) {
			logger.info("8");
			search = userdao.searchjobsbytitledept(createjob);
		}
		else if (createjob.getJobtitle().equals("NA") && !createjob.getLocation().equals("NA") 
				&& createjob.getDepartment().equals("NA") && !createjob.getJobtags().equals("NA")) {
			logger.info("9");
			search = userdao.searchjobsbyloctags(createjob);
		}
		else if (!createjob.getJobtitle().equals("NA") && createjob.getLocation().equals("NA") 
				&& createjob.getDepartment().equals("NA") && !createjob.getJobtags() .equals("NA")) {
			logger.info("10");
			search = userdao.searchjobsbytitletags(createjob);
		}
		else if (createjob.getJobtitle().equals("NA") && !createjob.getLocation().equals("NA") 
				&& !createjob.getDepartment().equals("NA") && createjob.getJobtags().equals("NA")) {
			logger.info("11");
			search = userdao.searchjobsbydeptloc(createjob);
		}
		return search;
	}
}
