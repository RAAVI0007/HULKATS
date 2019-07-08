package com.web.hulklogic.dao.Impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.web.hulklogic.dao.UserDAO;
import com.web.hulklogic.entity.Applicants;
import com.web.hulklogic.entity.ApplicantsDocuments;
import com.web.hulklogic.entity.CardDetails;
import com.web.hulklogic.entity.CreateJob;
import com.web.hulklogic.entity.JobTitle;
import com.web.hulklogic.entity.QuoteMessages;
import com.web.hulklogic.entity.Role;
import com.web.hulklogic.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {
	private static Logger logger = Logger.getLogger(UserDAOImpl.class);

	private static final String INSERT_USER_DATA = "insert into user(firstname,lastname,addrline1,city,country,dob,email,contactno,qualification,pincode,roleid,status,password,referencedmail,veterans,teamname,teamname1,organization,logourl,templatedesignid)"
			+ "values(:firstname,:lastname,:addrline1,:city,:country,:dob,:email,:contactno,:qualification,:pincode,:roleid,:status,:password,:referencedmail,:veterans,:teamname,:teamname1,:organization,:logourl,:templatedesignid)";
	private static final String INSERT_QUOTE_DATA = "insert into quotemessage(name, contactno, email, location, organization, timeslot, message,subject,product,volume) values(:name, :contactno, :email, :location, :organization, :timeslot,:message,:subject,:product,:volume)";
	private static final String INSERT_CONTACT_DATA = "insert into contactmessage(name, contactno, email, location, organization, timeslot, message,subject) values(:name, :contactno, :email, :location, :organization, :timeslot,:message,:subject)";
	private static final String CHECK_USER = "select * from user where email = ? and password = ?";
	private static final String GET_ROLE_ID = "select roleid from role where rolename=?";
	private static final String GET_USER_INFO = "select *from user where email=?";
	private static final String GET_CARD_INFO = "select email from bussinesscarddetails where email=?";

	private static final String GET_APPLICANTS_INFO = "select *from applicant where email=?";
	private static final String GET_ALL_USERS = "select *from user where roleid =1";
	private static final String GET_ALL_APPLICANTS_BY_ID = "select uplaodcv,otheruploads from applicant where applicantid=?";
	private static final String GET_ALL_RECRUITER_EMAILS = "select email from user where roleid!=1 and status=1";
	private static final String INSERT_JOBS_KEYWORDS = "update  job set jobkeywords=?,experience=? where jobid=? ";
	private static final String INSERT_APPLICANTS_KEYWORDS = "update applicant set cvskeywords=? , experience=?  where applicantid=?";
	private static final String INSERT_ASSIGN_JOBS_RECRUITER = "insert into jobassign(recuiteremail,jobid)values(?,?)";
	private static final String GET_ALL_RECRUITER = "select *from user where roleid=:roleid and status=1";
	private static final String CHECK_EMAIL = "select email from user where email = ?";
	private static final String CHECK_CONTACTNO = "select contactno from user where contactno = ?";
	private static final String CHECK_CARD_CONTACTNO = "select contactno from bussinesscarddetails where contactno = ?";
	private static final String CHECK_JOB_ASSIGN = "select jobid from jobassign where jobid=?";
	private static final String UPDATE_JOB_ASSIGN = "update jobassign set recuiteremail=? where jobid=?";
	private static final String GET_CITY_AND_STATE_NAME = "select c.sortname, s.statename,ac.citiesname from countries c inner join allstates s on c.id= s.country_id inner join allcities ac on s.id = ac.state_id where phonecode=:phonecode";
	private static final String GET_COUNTRY_ID_NAME = "select id,name from countries";
	private static final String GET_CITIES_STATES_BY_COUNTRY_ID = "select c.sortname, s.statename,ac.citiesname from countries c inner join allstates s on c.id= s.country_id inner join allcities ac on s.id = ac.state_id where c.id=:countryId";

	private static final String GET_ROLENAME_BY_ROLEID = "select rolename from role where roleid=?";
	private static final String GET_JOBLIST = "SELECT jobid,company,location,jobtype,salary,salarycriteria,uploaddocument1,uploaddocument2,jobtitle,created_at,status,videoname,videourl,jobdescription,jobrequirement FROM job where jobid=?  order by jobid desc limit 1";
	private static final String SAVE_CARD = "insert into bussinesscard(cardname) values(?)";
	private static final String GET_ALL_CARDS = "select * from bussinesscard where status = 0";
	private static final String GET_CARDS = "select * from bussinesscard where card_id = ?";
	private static final String DELETE_RECORD_APPLICANT = "delete from applicant where applicantid=?";
	private static final String GET_COUNT_TEAM = "select count(*) from user where teamname =?";
	private static final String GET_RECRUITER_EMAIL = "select email from user where roleid = 2 and teamname =?";
	private static final String GET_COUNT_MANAGER = "select count(*) from user where organization =? and roleid=1";
	private static final String GET_LIST_OF_COMPANIES = "select organization,roleid from customer";
	private static final String UPDATE_TEMPALTE_ID = "update user set templatedesignid=? where teamname=?";
	private static final String GET_TEMPLATEDESIGN_ID = "select templatedesignid from user where email=?";
	private static final String GET_SEARCH_JOBS = "select * from jobassign ja inner join job j where ja.jobid = j.jobid and company = :company and (j.jobtitle LIKE :jobtitle OR j.location LIKE :location OR j.department LIKE :department) ";
	private static final String INSERT_DEMO_DATA = "insert into demo(name,email,organization,timeslot,contactno)values(:name,:email,:organization,:timeslot,:contactno)";
	private static final String GET_SEARCH_JOBS_BY_TAGS = "select * from jobassign ja inner join job j where ja.jobid = j.jobid and company = :company and (j.jobtags LIKE :jobtags) ";
	private static final String GET_SEARCH_JOBS_BY_DEPARTMENT = "select * from jobassign ja inner join job j where ja.jobid = j.jobid and company = :company and (j.department LIKE :department)";
	private static final String GET_SEARCH_JOBS_BY_LOCATION = "select * from jobassign ja inner join job j where ja.jobid = j.jobid and company = :company and ( j.location LIKE :location)";
	private static final String GET_SEARCH_JOBS_BY_JOBTITLE = "select * from jobassign ja inner join job j where ja.jobid = j.jobid and company = :company and (j.jobtitle LIKE :jobtitle )";
	private static final String GET_SEARCH_JOBS_BY_ALL = "select * from jobassign ja inner join job j where ja.jobid = j.jobid and company = :company and (j.jobtitle LIKE :jobtitle AND j.location LIKE :location AND j.department LIKE :department AND j.jobtags LIKE :jobtags)";
	private static final String GET_SEARCH_JOBS_BY_TITLE_LOCATION = "select * from jobassign ja inner join job j where ja.jobid = j.jobid and company = :company and (j.jobtitle LIKE :jobtitle AND j.location LIKE :location)";
	private static final String GET_SEARCH_JOBS_BY_DEPT_TAGS = "select * from jobassign ja inner join job j where ja.jobid = j.jobid and company = :company and ( j.department LIKE :department AND j.jobtags LIKE :jobtags)";
	private static final String GET_SEARCH_JOBS_BY_TITLE_DEPT = "select * from jobassign ja inner join job j where ja.jobid = j.jobid and company = :company and (j.jobtitle LIKE :jobtitle AND j.department LIKE :department)";
	private static final String GET_SEARCH_JOBS_BY_TITLE_TAGS = "select * from jobassign ja inner join job j where ja.jobid = j.jobid and company = :company and (j.jobtitle LIKE :jobtitle  AND j.jobtags LIKE :jobtags)";
	private static final String GET_SEARCH_JOBS_BY_LOC_TAGS = "select * from jobassign ja inner join job j where ja.jobid = j.jobid and company = :company and ( j.location LIKE :location AND j.jobtags LIKE :jobtags)";
	private static final String GET_SEARCH_JOBS_BY_LOC_DEPT = "select * from jobassign ja inner join job j where ja.jobid = j.jobid and company = :company and ( j.location LIKE :location AND j.department LIKE :department)";

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private JdbcTemplate getJdbcTemplate;

	@Override
	public int saveUser(User user) {
		return namedParameterJdbcTemplate.update(INSERT_USER_DATA, getSqlParameterByMode(user));
	}

	private SqlParameterSource getSqlParameterByMode(User user) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("firstname", user.getFirstname());
		paramSource.addValue("lastname", user.getLastname());
		paramSource.addValue("addrline1", user.getAddrline1());
		paramSource.addValue("city", user.getCity());
		paramSource.addValue("country", "USA");
		paramSource.addValue("dob", user.getDob());
		paramSource.addValue("email", user.getEmail());
		paramSource.addValue("contactno", user.getContactno());
		paramSource.addValue("qualification", user.getQualification());
		paramSource.addValue("pincode", user.getPincode());
		paramSource.addValue("roleid", user.getRoleid());
		paramSource.addValue("status", user.getStatus());
		paramSource.addValue("referencedmail", user.getReferencedmail());
		paramSource.addValue("veterans", user.getVeterans());
		paramSource.addValue("password", user.getPassword());
		paramSource.addValue("teamname", user.getTeamname());
		paramSource.addValue("teamname1", user.getTeamname());
		paramSource.addValue("organization", user.getOrganization());
		paramSource.addValue("logourl", user.getLogourl());
		paramSource.addValue("templatedesignid", user.getTemplatedesignid());
		logger.info(user.getTeamname());
		return paramSource;
	}

	@Override
	public Role getRole(String rolename) {
		logger.info("rolename :" + rolename);
		Role role = null;
		try {
			role = (Role) getJdbcTemplate.queryForObject(GET_ROLE_ID, new Object[] { rolename },
					new BeanPropertyRowMapper<Role>(Role.class));
		} catch (EmptyResultDataAccessException e) {
			Role rolenull = new Role();
			return rolenull;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return role;
	}

	@Override
	public User getUserByEmail(String email) {
		logger.info("email :" + email);
		User user = null;
		try { // CHECK_USER
			user = (User) getJdbcTemplate.queryForObject(GET_USER_INFO, new Object[] { email },
					new BeanPropertyRowMapper<User>(User.class));
		} catch (EmptyResultDataAccessException e) {

			return null;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return user;
	}

	@Override
	public int getCardByEmail(String email) {

		logger.info("email :" + email);
		List<Map<String, Object>> data = getJdbcTemplate.queryForList(GET_CARD_INFO, email);
		int count = data.size();
		return count;
	}

	//

	@Override
	public Applicants getapplicantByEmail(String email) {
		logger.info("email :" + email);
		Applicants applicants = null;
		try {
			applicants = (Applicants) getJdbcTemplate.queryForObject(GET_APPLICANTS_INFO, new Object[] { email },
					new BeanPropertyRowMapper<Applicants>(Applicants.class));
		} catch (EmptyResultDataAccessException e) {

			return null;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return applicants;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = getJdbcTemplate.query(GET_ALL_USERS, new BeanPropertyRowMapper<User>(User.class));
		return users;

	}

	@Override
	public ApplicantsDocuments getAllApplicantsDocuments(int applicantid) {
		logger.info("applicantid :" + applicantid);
		ApplicantsDocuments applicantsDocumnets = null;
		try {
			applicantsDocumnets = (ApplicantsDocuments) getJdbcTemplate.queryForObject(GET_ALL_APPLICANTS_BY_ID,
					new Object[] { applicantid },
					new BeanPropertyRowMapper<ApplicantsDocuments>(ApplicantsDocuments.class));

		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return applicantsDocumnets;
	}

	@Override
	public List<String> getAllRecruiterEmails() {
		List<String> getAllRecruiterEmails = getJdbcTemplate.queryForList(GET_ALL_RECRUITER_EMAILS, String.class);
		return getAllRecruiterEmails;
	}

	@Override
	public int saveJobsKeyWords(String jobsKeyWords, int experience, int jobid) {
		logger.info("jobsKeyWords :" + jobsKeyWords);
		logger.info("experience :" + experience);
		logger.info("jobid :" + jobid);
		return getJdbcTemplate.update(INSERT_JOBS_KEYWORDS, jobsKeyWords, experience, jobid);
	}

	@Override
	public int saveApplicantsKeyWords(String applicantsKeyWords, int experience, int applicantid) {
		logger.info("applicantsKeyWords :" + applicantsKeyWords);
		logger.info("experience :" + experience);
		logger.info("applicantid :" + applicantid);
		return getJdbcTemplate.update(INSERT_APPLICANTS_KEYWORDS, applicantsKeyWords, experience, applicantid);
	}

	@Override
	public int saveAssignJobs(String recuiteremail, int jobid) {
		logger.info("recuiteremail :" + recuiteremail);
		logger.info("jobid :" + jobid);
		return getJdbcTemplate.update(INSERT_ASSIGN_JOBS_RECRUITER, recuiteremail, jobid);
	}

	@Override
	public List<User> getAllRecruiters(int roleid) {
		logger.info("roleid :" + roleid);
		MapSqlParameterSource pss = new MapSqlParameterSource();
		pss.addValue("roleid", roleid);
		List<User> users = namedParameterJdbcTemplate.query(GET_ALL_RECRUITER, pss,
				new BeanPropertyRowMapper<User>(User.class));
		return users;
	}

	public int getRoleId(String roleName) {
		logger.info("roleName :" + roleName);
		Integer id = (Integer) getJdbcTemplate.queryForObject(GET_ROLE_ID, new Object[] { roleName }, Integer.class);
		return id;
	}

	@Override
	public int getUsernameCheck(String email) {
		logger.info("email :" + email);
		List<Map<String, Object>> data = getJdbcTemplate.queryForList(CHECK_EMAIL, email);
		int count = data.size();
		return count;
	}

	@Override
	public int getContactnoCheck(String contactno) {
		logger.info("contactno :" + contactno);
		List<Map<String, Object>> data = getJdbcTemplate.queryForList(CHECK_CONTACTNO, contactno);
		int count = data.size();
		return count;
	}

	@Override
	public int getCardContactnoCheck(String contactno) {
		logger.info("contactno :" + contactno);
		List<Map<String, Object>> data = getJdbcTemplate.queryForList(CHECK_CARD_CONTACTNO, contactno);
		int count = data.size();
		return count;
	}

	@Override
	public int checkJobAssign(int jobid) {
		logger.info("jobid :" + jobid);
		int count = 0;
		List<Map<String, Object>> checkjob = getJdbcTemplate.queryForList(CHECK_JOB_ASSIGN, jobid);
		count = checkjob.size();
		return count;
	}

	@Override
	public int updateJobAssign(int jobid, String email) {
		logger.info("jobid :" + jobid);
		logger.info("email :" + email);
		return getJdbcTemplate.update(UPDATE_JOB_ASSIGN, email, jobid);
	}

	@Override
	public List<User> getCityAndStateNames(int phonecode) {
		logger.info("phonecode :" + phonecode);
		MapSqlParameterSource pss = new MapSqlParameterSource();
		pss.addValue("phonecode", phonecode);
		List<User> users = namedParameterJdbcTemplate.query(GET_CITY_AND_STATE_NAME, pss,
				new BeanPropertyRowMapper<User>(User.class));
		return users;

	}

	@Override
	public List<CreateJob> getCountryNameAndId() {
		List<CreateJob> getIdAndNames = getJdbcTemplate.query(GET_COUNTRY_ID_NAME,
				new BeanPropertyRowMapper<CreateJob>(CreateJob.class));
		return getIdAndNames;
	}

	@Override
	public List<CreateJob> getStateAndCitiesByCountryId(int countryId) {
		logger.info("countryId :" + countryId);
		MapSqlParameterSource pss = new MapSqlParameterSource();
		pss.addValue("countryId", countryId);
		List<CreateJob> citiesStates = namedParameterJdbcTemplate.query(GET_CITIES_STATES_BY_COUNTRY_ID, pss,
				new BeanPropertyRowMapper<CreateJob>(CreateJob.class));
		return citiesStates;
	}

	@Override
	public Role getRoleName(int roleid) {
		logger.info("roleid :" + roleid);
		Role role = null;
		try {
			role = (Role) getJdbcTemplate.queryForObject(GET_ROLENAME_BY_ROLEID, new Object[] { roleid },
					new BeanPropertyRowMapper<Role>(Role.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return role;
	}

	@Override
	public User checkLogin(String email, String password) {
		logger.info("email :" + email);
		User user = null;
		try { // CHECK_USER
			user = (User) getJdbcTemplate.queryForObject(CHECK_USER, new Object[] { email, password },
					new BeanPropertyRowMapper<User>(User.class));
		} catch (EmptyResultDataAccessException e) {

			return null;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return user;
	}

	@Override
	public CreateJob getVideo(int jobid) {
		logger.info(jobid);
		CreateJob createjob = null;
		try { // CHECK_USER
			createjob = (CreateJob) getJdbcTemplate.queryForObject(GET_JOBLIST, new Object[] { jobid },
					new BeanPropertyRowMapper<CreateJob>(CreateJob.class));
		} catch (EmptyResultDataAccessException e) {

			return null;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		logger.info(createjob.getCompany());
		return createjob;
	}

	@Override
	public int saveQuote(QuoteMessages quote) {
		return namedParameterJdbcTemplate.update(INSERT_QUOTE_DATA, getSqlParameterByQuote(quote));
	}

	private SqlParameterSource getSqlParameterByQuote(QuoteMessages quote) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("name", quote.getName());
		paramSource.addValue("contactno", quote.getContactno());
		paramSource.addValue("organization", quote.getOrganization());
		paramSource.addValue("email", quote.getEmail());
		paramSource.addValue("timeslot", quote.getTimeslot());
		paramSource.addValue("message", quote.getMessage());
		paramSource.addValue("location", quote.getLocation());
		paramSource.addValue("subject", quote.getSubject());
		paramSource.addValue("product", quote.getProduct());
		paramSource.addValue("volume", quote.getVolume());
		return paramSource;
	}

	@Override
	public int demo(QuoteMessages quote) {
		return namedParameterJdbcTemplate.update(INSERT_DEMO_DATA, getSqlParameterByDemo(quote));
	}

	private SqlParameterSource getSqlParameterByDemo(QuoteMessages quote) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("name", quote.getName());
		paramSource.addValue("organization", quote.getOrganization());
		paramSource.addValue("email", quote.getEmail());
		paramSource.addValue("timeslot", quote.getTimeslot());
		paramSource.addValue("contactno", quote.getContactno());

		return paramSource;
	}

	@Override
	public int saveContact(QuoteMessages quote) {
		return namedParameterJdbcTemplate.update(INSERT_CONTACT_DATA, getSqlParameterByContact(quote));
	}

	private SqlParameterSource getSqlParameterByContact(QuoteMessages quote) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("name", quote.getName());
		paramSource.addValue("contactno", quote.getContactno());
		paramSource.addValue("organization", quote.getOrganization());
		paramSource.addValue("email", quote.getEmail());
		paramSource.addValue("timeslot", quote.getTimeslot());
		paramSource.addValue("message", quote.getMessage());
		paramSource.addValue("location", quote.getLocation());
		paramSource.addValue("subject", quote.getSubject());
		return paramSource;
	}

	@Override
	public int savecard(String cardname) {
		return getJdbcTemplate.update(SAVE_CARD, cardname);
	}

	@Override
	public List<CardDetails> getallcards() {
		List<CardDetails> getcarddetails = getJdbcTemplate.query(GET_ALL_CARDS,
				new BeanPropertyRowMapper<CardDetails>(CardDetails.class));
		return getcarddetails;
	}

	@Override
	public CardDetails getcarddetails(int card_id) {
		CardDetails getcarddetails = (CardDetails) getJdbcTemplate.queryForObject(GET_CARDS, new Object[] { card_id },
				new BeanPropertyRowMapper<CardDetails>(CardDetails.class));
		return getcarddetails;
	}

	@Override
	public int deleteApplicant(int applicantid) {
		return getJdbcTemplate.update(DELETE_RECORD_APPLICANT, applicantid);
	}

	@Override
	public int checkTeamCount(String teamname) {
		logger.info("teamname :" + teamname);
		int count = getJdbcTemplate.queryForObject(GET_COUNT_TEAM, new Object[] { teamname }, Integer.class);

		return count;
	}

	@Override
	public String getRecruiterEmail(String teamname) {
		String name = null;
		try {
			name = (String) getJdbcTemplate.queryForObject(GET_RECRUITER_EMAIL, new Object[] { teamname },
					String.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return name;
	}

	@Override
	public int checkmanager(String companyname) {
		logger.info("companyname :" + companyname);
		int count = getJdbcTemplate.queryForObject(GET_COUNT_MANAGER, new Object[] { companyname }, Integer.class);

		return count;
	}

	@Override
	public List<User> getCompanyList() {
		return getJdbcTemplate.query(GET_LIST_OF_COMPANIES, new BeanPropertyRowMapper(User.class));

	}

	@Override
	public int updateTemplate(int templatedesignid, String teamname) {
		logger.info("templatedesignid :" + templatedesignid);
		logger.info("teamname :" + teamname);
		int result = getJdbcTemplate.update(UPDATE_TEMPALTE_ID, templatedesignid, teamname);
		if (result >= 1) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public String gettemplateid(String email) {

		String name = null;
		try {
			name = (String) getJdbcTemplate.queryForObject(GET_TEMPLATEDESIGN_ID, new Object[] { email }, String.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return name;
	}

	@Override
	public List<CreateJob> searchjobs(String joborloc, String company) {
		logger.info("joborloc :" + joborloc);
		MapSqlParameterSource pss = new MapSqlParameterSource();
		String finalName = "%" + joborloc + "%";
		pss.addValue("jobtitle", finalName);
		pss.addValue("location", finalName);
		pss.addValue("department", finalName);
		pss.addValue("company", company);
		List<CreateJob> jobs = namedParameterJdbcTemplate.query(GET_SEARCH_JOBS, pss,
				new BeanPropertyRowMapper<CreateJob>(CreateJob.class));
		return jobs;
	}

	@Override
	public List<CreateJob> searchjobsbytags(CreateJob createjob) {
		MapSqlParameterSource pss = new MapSqlParameterSource();
		String finalName = "%" + createjob.getJobtags() + "%";
		pss.addValue("jobtags", finalName);
		pss.addValue("company", createjob.getCompany());
		List<CreateJob> jobs = namedParameterJdbcTemplate.query(GET_SEARCH_JOBS_BY_TAGS, pss,
				new BeanPropertyRowMapper<CreateJob>(CreateJob.class));
		return jobs;
	}

	@Override
	public List<CreateJob> searchjobsbydepartment(CreateJob createjob) {
		MapSqlParameterSource pss = new MapSqlParameterSource();
		String finalName = "%" + createjob.getDepartment() + "%";
		pss.addValue("department", finalName);
		pss.addValue("company", createjob.getCompany());
		List<CreateJob> jobs = namedParameterJdbcTemplate.query(GET_SEARCH_JOBS_BY_DEPARTMENT, pss,
				new BeanPropertyRowMapper<CreateJob>(CreateJob.class));
		return jobs;
	}

	@Override
	public List<CreateJob> searchjobsbylocation(CreateJob createjob) {
		MapSqlParameterSource pss = new MapSqlParameterSource();
		String finalName = "%" + createjob.getLocation() + "%";
		pss.addValue("location", finalName);
		pss.addValue("company", createjob.getCompany());
		List<CreateJob> jobs = namedParameterJdbcTemplate.query(GET_SEARCH_JOBS_BY_LOCATION, pss,
				new BeanPropertyRowMapper<CreateJob>(CreateJob.class));
		return jobs;
	}

	@Override
	public List<CreateJob> searchjobsbytitle(CreateJob createjob) {
		MapSqlParameterSource pss = new MapSqlParameterSource();
		String finalName = "%" + createjob.getJobtitle() + "%";
		pss.addValue("jobtitle", finalName);
		pss.addValue("company", createjob.getCompany());
		List<CreateJob> jobs = namedParameterJdbcTemplate.query(GET_SEARCH_JOBS_BY_JOBTITLE, pss,
				new BeanPropertyRowMapper<CreateJob>(CreateJob.class));
		return jobs;
	}

	@Override
	public List<CreateJob> searchjobsbyall(CreateJob createjob) {
		MapSqlParameterSource pss = new MapSqlParameterSource();
		String finalName = "%" + createjob.getJobtitle() + "%";
		String finalName1 = "%" + createjob.getLocation() + "%";
		String finalName2 = "%" + createjob.getDepartment() + "%";
		String finalName3 = "%" + createjob.getJobtags() + "%";
		pss.addValue("jobtitle", finalName);
		pss.addValue("location", finalName1);
		pss.addValue("department", finalName2);
		pss.addValue("jobtags", finalName3);
		pss.addValue("company", createjob.getCompany());
		List<CreateJob> jobs = namedParameterJdbcTemplate.query(GET_SEARCH_JOBS_BY_ALL, pss,
				new BeanPropertyRowMapper<CreateJob>(CreateJob.class));
		return jobs;
	}

	@Override
	public List<CreateJob> searchjobsbytitleloc(CreateJob createjob) {
		MapSqlParameterSource pss = new MapSqlParameterSource();
		String finalName = "%" + createjob.getJobtitle() + "%";
		String finalName1 = "%" + createjob.getLocation() + "%";
		pss.addValue("jobtitle", finalName);
		pss.addValue("location", finalName1);
		pss.addValue("company", createjob.getCompany());
		List<CreateJob> jobs = namedParameterJdbcTemplate.query(GET_SEARCH_JOBS_BY_TITLE_LOCATION, pss,
				new BeanPropertyRowMapper<CreateJob>(CreateJob.class));
		return jobs;
	}

	@Override
	public List<CreateJob> searchjobsbydepttags(CreateJob createjob) {
		MapSqlParameterSource pss = new MapSqlParameterSource();

		String finalName2 = "%" + createjob.getDepartment() + "%";
		String finalName3 = "%" + createjob.getJobtags() + "%";

		pss.addValue("department", finalName2);
		pss.addValue("jobtags", finalName3);
		pss.addValue("company", createjob.getCompany());
		List<CreateJob> jobs = namedParameterJdbcTemplate.query(GET_SEARCH_JOBS_BY_DEPT_TAGS, pss,
				new BeanPropertyRowMapper<CreateJob>(CreateJob.class));
		return jobs;
	}

	@Override
	public List<CreateJob> searchjobsbytitledept(CreateJob createjob) {
		MapSqlParameterSource pss = new MapSqlParameterSource();
		String finalName = "%" + createjob.getJobtitle() + "%";
		String finalName2 = "%" + createjob.getDepartment() + "%";
		pss.addValue("jobtitle", finalName);
		pss.addValue("department", finalName2);
		pss.addValue("company", createjob.getCompany());
		List<CreateJob> jobs = namedParameterJdbcTemplate.query(GET_SEARCH_JOBS_BY_TITLE_DEPT, pss,
				new BeanPropertyRowMapper<CreateJob>(CreateJob.class));
		return jobs;
	}

	@Override
	public List<CreateJob> searchjobsbyloctags(CreateJob createjob) {
		MapSqlParameterSource pss = new MapSqlParameterSource();

		String finalName1 = "%" + createjob.getLocation() + "%";

		String finalName3 = "%" + createjob.getJobtags() + "%";

		pss.addValue("location", finalName1);

		pss.addValue("jobtags", finalName3);
		pss.addValue("company", createjob.getCompany());
		List<CreateJob> jobs = namedParameterJdbcTemplate.query(GET_SEARCH_JOBS_BY_LOC_TAGS, pss,
				new BeanPropertyRowMapper<CreateJob>(CreateJob.class));
		return jobs;
	}

	@Override
	public List<CreateJob> searchjobsbytitletags(CreateJob createjob) {
		MapSqlParameterSource pss = new MapSqlParameterSource();
		String finalName = "%" + createjob.getJobtitle() + "%";

		String finalName3 = "%" + createjob.getJobtags() + "%";
		pss.addValue("jobtitle", finalName);

		pss.addValue("jobtags", finalName3);
		pss.addValue("company", createjob.getCompany());
		List<CreateJob> jobs = namedParameterJdbcTemplate.query(GET_SEARCH_JOBS_BY_TITLE_TAGS, pss,
				new BeanPropertyRowMapper<CreateJob>(CreateJob.class));
		return jobs;
	}

	@Override
	public List<CreateJob> searchjobsbydeptloc(CreateJob createjob) {
		MapSqlParameterSource pss = new MapSqlParameterSource();

		String finalName1 = "%" + createjob.getLocation() + "%";
		String finalName2 = "%" + createjob.getDepartment() + "%";
		pss.addValue("location", finalName1);
		pss.addValue("department", finalName2);
		pss.addValue("company", createjob.getCompany());
		List<CreateJob> jobs = namedParameterJdbcTemplate.query(GET_SEARCH_JOBS_BY_LOC_DEPT, pss,
				new BeanPropertyRowMapper<CreateJob>(CreateJob.class));
		return jobs;
	}

}
