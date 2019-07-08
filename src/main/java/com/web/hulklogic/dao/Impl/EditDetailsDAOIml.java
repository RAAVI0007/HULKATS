package com.web.hulklogic.dao.Impl;

import java.util.ArrayList;
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

import com.web.hulklogic.dao.EditDetailsDAO;
import com.web.hulklogic.entity.Applicants;
import com.web.hulklogic.entity.CreateJob;
import com.web.hulklogic.entity.JobsDocuments;
import com.web.hulklogic.entity.User;

@Repository
public class EditDetailsDAOIml implements EditDetailsDAO {

	private static Logger logger = Logger.getLogger(EditDetailsDAOIml.class);

	private static final String UPDATE_USER_DETAILS = "update user set firstname=?,lastname=?,contactno=?,qualification=?,addrline1=?,city=?,pincode=? where userid=?";
	private static final String DELETE_RECORD_USER = "delete from user where userid=?";
	private static final String DELETE_RECORD_JOB = "delete from user where jobid=?";
	private static final String GET_TEAMID_MGID = "select userid,teamid from user where email=?";
	private static final String GET_ALL_MANAGERS = "select *from user where roleid=:roleid and status=1 and organization=:organization";
	private static final String GET_ALL_RECRUITERS_UNDER_MANAGERS = "select DISTINCT u.firstname,u.lastname,u.userid,u.email,u.contactno,u.city ,l.status1 as active1,l.status2 as active2 from user u left outer join live_user l on u.userid = l.userid where u.roleid=:roleid and u.teamname=:teamname and u.status=1 and u.organization=:organization";
	private static final String GET_ALL_RECRUITERS_NON_ASSIGN_TEAM = "select *from user where roleid=:roleid and teamname='N/A' and status=1 and  organization=:organization ";
	private static final String GET_CHANGE_JOB_STATUS = "update job set status=? where jobid=?";
	private static final String GET_JOB_LAST_ID = "SELECT jobid FROM job ORDER BY jobid DESC LIMIT 1";

	private static final String UPDATE_JOB_DETAILS_FILE = "update job set company=:company,location=:location,jobtype=:jobtype,salary=:salary,salarycriteria=:salarycriteria,"
			+ "jobtitle=:jobtitle,jobkeywords=:jobkeywords,uploaddocument1=:uploaddocument1,uploaddocument2=:uploaddocument2,videourl=:videourl,videoname=:videoname,jobdescription=:jobdescription,jobrequirement=:jobrequirement,jobtags=:jobtags,department=:department where jobid=:jobid";

	private static final String UPDATE_APPLICANT_DETAILS = "update applicant set firstname=:firstname,lastname=:lastname,location=:location,"
			+ "contactno=:contactno,email=:email,lastjobtitle=:lastjobtitle,highesteducation=:highesteducation,uplaodcv=:uplaodcv,otheruploads=:otheruploads,"
			+ "parsertext1=:parsertext1,parsertext2=:parsertext2,cvdocument1_tokens=:cvdocument1_tokens,cvdocument2_tokens=:cvdocument2_tokens,applicanttags=:applicanttags where applicantid=:applicantid";

	private static final String GET_LAST_APPLICANT_ID = "SELECT applicantid FROM applicant ORDER BY applicantid DESC LIMIT 1";

	private static final String GET_ALL_DOCUMENTS_NAME = "select a.uploaddocname1,a.uploaddocname2,b.uploadcvname,b.otheruploadname from (select uploaddocument1 "
			+ "as uploaddocname1,uploaddocument2 as uploaddocname2 from job where jobid = :jobid) a, (select uplaodcv as uploadcvname, otheruploads as otheruploadname  from  applicant where applicantid=:applicantid) b";

	private static final String GET_APPLICANT_DOC_NAME = "select uplaodcv as uploadcvname, otheruploads as otheruploadname  from  applicant where applicantid=:applicantid";

	private static final String GET_JOB_DOC_NAME = "select uploaddocument1 as uploaddocname1, uploaddocument2 as uploaddocname2 from job where jobid =:jobid";

	private static final String UPDATE_JOB_DETAILS_UPLOAD1 = "update job set company=:company,location=:location,jobtype=:jobtype,salary=:salary,salarycriteria=:salarycriteria,"
			+ "jobtitle=:jobtitle,jobkeywords=:jobkeywords,uploaddocument1=:uploaddocument1,videourl=:videourl,videoname=:videoname,jobdescription=:jobdescription,jobrequirement=:jobrequirement,jobtags=:jobtags,department=:department where jobid=:jobid";

	private static final String UPDATE_JOB_DETAILS_UPLOAD2 = "update job set company=:company,location=:location,jobtype=:jobtype,salary=:salary,salarycriteria=:salarycriteria,"
			+ "jobtitle=:jobtitle,jobkeywords=:jobkeywords,uploaddocument2=:uploaddocument2,videourl=:videourl,videoname=:videoname,jobdescription=:jobdescription,jobrequirement=:jobrequirement,jobtags=:jobtags,department=:department where jobid=:jobid";

	private static final String UPDATE_APPLICANT_DETAILS_UPLOAD = "update applicant set firstname=:firstname,lastname=:lastname,location=:location,"
			+ "contactno=:contactno,email=:email,lastjobtitle=:lastjobtitle,highesteducation=:highesteducation,uplaodcv=:uplaodcv,"
			+ "applicanttags=:applicanttags where applicantid=:applicantid";

	private static final String UPDATE_APPLICANT_DETAILS_OTHER = "update applicant set firstname=:firstname,lastname=:lastname,location=:location,"
			+ "contactno=:contactno,email=:email,lastjobtitle=:lastjobtitle,highesteducation=:highesteducation,otheruploads=:otheruploads,"
			+ "applicanttags=:applicanttags where applicantid=:applicantid";

	private static final String UPDATE_JOB_WITH_OUT_DOCS = "update job set company=:company,location=:location,jobtype=:jobtype,salary=:salary,salarycriteria=:salarycriteria,"
			+ "jobtitle=:jobtitle,jobkeywords=:jobkeywords,videourl=:videourl,videoname=:videoname,jobdescription=:jobdescription,jobrequirement=:jobrequirement,jobtags=:jobtags,department=:department where jobid=:jobid";

	private static final String UPDATE_APPLICANT_WITH_OUT_DOCS = "update applicant set firstname=:firstname,lastname=:lastname,location=:location,"
			+ "contactno=:contactno,email=:email,lastjobtitle=:lastjobtitle,highesteducation=:highesteducation,applicanttags=:applicanttags where applicantid=:applicantid";

	private static final String UPDATE_LOGOUT_IDLE_STATUS = "update live_user set status1=2 where userid=?";
	private static final String UPDATE_LOGUT_STATUS = "update live_user set status1=0  where userid=?";
	private static final String CHECK_LOGIN_USER = "select userid,session_id1 from live_user where userid=?";
	private static final String UPDATE_LOGIN_STATUS = "update live_user set status1=1  where userid=?";
	private static final String GET_SESSION_IDS = "select session_id1 from live_user where userid=?";
	private static final String INSERT_SESSION1_ID = "update live_user set session_id1=?,status1=1 where userid=?";
	private static final String INSERT_CURRENTDATE_INFO = "insert into live_user(userid,session_id1,teamname,status1)values(?,?,?,?)";
	private static final String INSERT_SESSION2_ID = "update live_user set session_id2=?,status2=1 where userid=?";
	private static final String LOGOUT_SESSION1 = "update live_user set session_id1=?,status1=? where userid=?";
	private static final String LOGOUT_SESSION2 = "update live_user set session_id2=?, status2=? where userid=?";
	private static final String IDLAE_STATE_SESSION1 = "update live_user set status1=2 where userid=?";
	private static final String IDLAE_STATE_SESSION2 = "update live_user set status2=2 where userid=?";
	private static final String DELETE_SESSIONS = "update live_user set session_id1=?,status1=? where userid=?";

	@Autowired
	private JdbcTemplate getJdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public int updateUserDetails(User user) {
		logger.info("user data dao:");

		int result = 0;

		try {
			result = getJdbcTemplate.update(UPDATE_USER_DETAILS, user.getFirstname(), user.getLastname(),
					user.getContactno(), user.getQualification(), user.getAddrline1(), user.getCity(),
					user.getPincode(), user.getUserid());

		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return result;
	}

	@Override
	public int deleteUser(int userid) {

		return getJdbcTemplate.update(DELETE_RECORD_USER, userid);
	}

	@Override
	public int deleteJob(int jobid) {

		return getJdbcTemplate.update(DELETE_RECORD_JOB, jobid);
	}

	@Override
	public User getTeamIdAndMgId(String email) {
		logger.info("email :" + email);
		User user = null;
		try {
			user = (User) getJdbcTemplate.queryForObject(GET_TEAMID_MGID, new Object[] { email },
					new BeanPropertyRowMapper(User.class));

		} catch (EmptyResultDataAccessException e) {
			User usernull = new User();
			return usernull;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}

		return user;
	}

	@Override
	public List<User> getAllManagers(int roleid, String organization) {
		MapSqlParameterSource pss = new MapSqlParameterSource();
		pss.addValue("roleid", roleid);
		pss.addValue("organization", organization);
		List<User> users = namedParameterJdbcTemplate.query(GET_ALL_MANAGERS, pss,
				new BeanPropertyRowMapper(User.class));
		return users;
	}

	@Override
	public List<User> getAllRecruiters(int roleid, String teamname, String organization) {
		logger.info("roleid :" + roleid);
		logger.info("teamname :" + teamname);
		MapSqlParameterSource pss = new MapSqlParameterSource();
		pss.addValue("roleid", roleid);
		pss.addValue("teamname", teamname);
		pss.addValue("organization", organization);
		List<User> users = namedParameterJdbcTemplate.query(GET_ALL_RECRUITERS_UNDER_MANAGERS, pss,
				new BeanPropertyRowMapper(User.class));
		return users;
	}

	@Override
	public List<User> getAllNARecruiters(int roleid, String organization) {
		MapSqlParameterSource pss = new MapSqlParameterSource();
		pss.addValue("roleid", roleid);
		pss.addValue("organization", organization);
		List<User> users = namedParameterJdbcTemplate.query(GET_ALL_RECRUITERS_NON_ASSIGN_TEAM, pss,
				new BeanPropertyRowMapper(User.class));
		return users;
	}

	@Override
	public int changeJobStatus(int jobid, String jobstatus) {
		return getJdbcTemplate.update(GET_CHANGE_JOB_STATUS, jobstatus, jobid);

	}

	@Override
	public int getLastJobId() {
		Integer getJobId = null;
		try {
			getJobId = (Integer) getJdbcTemplate.queryForObject(GET_JOB_LAST_ID, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
		return getJobId;
	}

	@Override
	public int updateJob(CreateJob createJob) {
		logger.info("lastjobidvalue :" + createJob.getJobid());
		return namedParameterJdbcTemplate.update(UPDATE_JOB_DETAILS_FILE, getSqlParameterByJob(createJob));
	}

	private SqlParameterSource getSqlParameterByJob(CreateJob createjob) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("jobtype", createjob.getJobtype());
		paramSource.addValue("company", createjob.getCompany());
		paramSource.addValue("location", createjob.getLocation());
		paramSource.addValue("jobkeywords", createjob.getJobkeywords());
		paramSource.addValue("salary", createjob.getSalary());
		paramSource.addValue("salarycriteria", createjob.getSalarycriteria());
		paramSource.addValue("jobtitle", createjob.getJobtitle());
		
		paramSource.addValue("jobcreatedemail", createjob.getCurrentLogin());
		paramSource.addValue("uploaddocument1", createjob.getDocument1());
		paramSource.addValue("uploaddocument2", createjob.getDocument2());
		
		paramSource.addValue("videoname", createjob.getVideoname());
		paramSource.addValue("videourl", createjob.getVideourl());
		paramSource.addValue("jobid", createjob.getJobid());
		paramSource.addValue("jobdescription", createjob.getJobdescription());
		paramSource.addValue("jobrequirement", createjob.getJobrequirement());
		paramSource.addValue("jobtags", createjob.getJobtags());
		paramSource.addValue("department", createjob.getDepartment());
		return paramSource;
	}

	@Override
	public int updateApplicant(Applicants applicant) {
		logger.info("applicantId :" + applicant.getApplicantid());
		return namedParameterJdbcTemplate.update(UPDATE_APPLICANT_DETAILS, getSqlParameterByApplicant(applicant));
	}

	private SqlParameterSource getSqlParameterByApplicant(Applicants applicants) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("firstname", applicants.getFirstname());
		paramSource.addValue("lastname", applicants.getLastname());
		paramSource.addValue("location", applicants.getLocation());
		paramSource.addValue("contactno", applicants.getContactno());
		paramSource.addValue("email", applicants.getEmail());
		paramSource.addValue("lastjobtitle", applicants.getLastjobtitle());
		paramSource.addValue("highesteducation", applicants.getHighesteducation());
		paramSource.addValue("uplaodcv", applicants.getUplaodcv());
		paramSource.addValue("otheruploads", applicants.getOtheruploads());
		paramSource.addValue("parsertext1", applicants.getParserData1());
		paramSource.addValue("parsertext2", applicants.getParserData2());
		paramSource.addValue("cvdocument1_tokens", applicants.getCountNouns1());
		paramSource.addValue("cvdocument2_tokens", applicants.getCountNouns2());
		paramSource.addValue("applicantid", applicants.getApplicantid());
		paramSource.addValue("applicanttags", applicants.getApplicanttags());
		return paramSource;
	}

	@Override
	public int getLastApplicantId() {
		Integer getApplicantId = null;
		try {
			getApplicantId = (Integer) getJdbcTemplate.queryForObject(GET_LAST_APPLICANT_ID, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
		return getApplicantId;
	}

	@Override
	public JobsDocuments getAllDocument(int jobid, int applicantid) {
		logger.info("jobid :" + jobid);
		logger.info("applicantid : " + applicantid);
		MapSqlParameterSource jbsrc = new MapSqlParameterSource();
		jbsrc.addValue("jobid", jobid);
		jbsrc.addValue("applicantid", applicantid);
		JobsDocuments jobsdocuments = null;
		try {
			jobsdocuments = (JobsDocuments) namedParameterJdbcTemplate.queryForObject(GET_ALL_DOCUMENTS_NAME, jbsrc,
					new BeanPropertyRowMapper(JobsDocuments.class));

		} catch (EmptyResultDataAccessException e) {

			JobsDocuments jobtitlenull = new JobsDocuments();
			return jobtitlenull;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return jobsdocuments;
	}

	@Override
	public JobsDocuments getApplicantDoc(int applicantid) {

		logger.info("applicantid : " + applicantid);
		MapSqlParameterSource appdoc = new MapSqlParameterSource();

		appdoc.addValue("applicantid", applicantid);
		JobsDocuments jobsdocuments = null;
		try {
			jobsdocuments = (JobsDocuments) namedParameterJdbcTemplate.queryForObject(GET_APPLICANT_DOC_NAME, appdoc,
					new BeanPropertyRowMapper(JobsDocuments.class));

		} catch (EmptyResultDataAccessException e) {

			JobsDocuments jobtitlenull = new JobsDocuments();
			return jobtitlenull;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return jobsdocuments;
	}

	@Override
	public JobsDocuments getJobDoc(int jobid) {
		logger.info("jobid : " + jobid);
		MapSqlParameterSource jobdoc = new MapSqlParameterSource();

		jobdoc.addValue("jobid", jobid);
		JobsDocuments jobsdocuments = null;
		try {
			jobsdocuments = (JobsDocuments) namedParameterJdbcTemplate.queryForObject(GET_JOB_DOC_NAME, jobdoc,
					new BeanPropertyRowMapper(JobsDocuments.class));

		} catch (EmptyResultDataAccessException e) {

			JobsDocuments jobtitlenull = new JobsDocuments();
			return jobtitlenull;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return jobsdocuments;
	}

	@Override
	public int updateJobUpload1(CreateJob createJob) {
		logger.info("lastjobidvalue :" + createJob.getJobid());
		return namedParameterJdbcTemplate.update(UPDATE_JOB_DETAILS_UPLOAD1, getSqlParameterByJobUpload1(createJob));
	}

	private SqlParameterSource getSqlParameterByJobUpload1(CreateJob createjob) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("jobtype", createjob.getJobtype());
		paramSource.addValue("company", createjob.getCompany());
		paramSource.addValue("location", createjob.getLocation());
		paramSource.addValue("jobkeywords", createjob.getJobkeywords());
		paramSource.addValue("salary", createjob.getSalary());
		paramSource.addValue("salarycriteria", createjob.getSalarycriteria());
		paramSource.addValue("jobtitle", createjob.getJobtitle());
		paramSource.addValue("jobcreatedemail", createjob.getCurrentLogin());
		
		paramSource.addValue("uploaddocument1", createjob.getDocument1());
		
		paramSource.addValue("videoname", createjob.getVideoname());
		paramSource.addValue("videourl", createjob.getVideourl());
		paramSource.addValue("jobid", createjob.getJobid());
		paramSource.addValue("jobdescription", createjob.getJobdescription());
		paramSource.addValue("jobrequirement", createjob.getJobrequirement());
		paramSource.addValue("jobtags", createjob.getJobtags());
		paramSource.addValue("department", createjob.getDepartment());
		return paramSource;
	}

	@Override
	public int updateJobUpload2(CreateJob createJob) {
		logger.info("lastjobidvalue :" + createJob.getJobid());
		return namedParameterJdbcTemplate.update(UPDATE_JOB_DETAILS_UPLOAD2, getSqlParameterByJobUpload2(createJob));
	}

	private SqlParameterSource getSqlParameterByJobUpload2(CreateJob createjob) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("jobtype", createjob.getJobtype());
		paramSource.addValue("company", createjob.getCompany());
		paramSource.addValue("location", createjob.getLocation());
		paramSource.addValue("jobkeywords", createjob.getJobkeywords());
		paramSource.addValue("salary", createjob.getSalary());
		paramSource.addValue("salarycriteria", createjob.getSalarycriteria());
		paramSource.addValue("jobtitle", createjob.getJobtitle());
		paramSource.addValue("jobcreatedemail", createjob.getCurrentLogin());
		paramSource.addValue("jobid", createjob.getJobid());
	
		paramSource.addValue("uploaddocument2", createjob.getDocument2());
		
		paramSource.addValue("videoname", createjob.getVideoname());
		paramSource.addValue("videourl", createjob.getVideourl());
		paramSource.addValue("jobdescription", createjob.getJobdescription());
		paramSource.addValue("jobrequirement", createjob.getJobrequirement());
		paramSource.addValue("jobtags", createjob.getJobtags());
		paramSource.addValue("department", createjob.getDepartment());
		return paramSource;
	}

	@Override
	public int updateApplicantUplaodcv(Applicants applicant) {
		logger.info("applicantId :" + applicant.getApplicantid());
		return namedParameterJdbcTemplate.update(UPDATE_APPLICANT_DETAILS_UPLOAD,
				getSqlParameterByApplicantUpload(applicant));
	}

	private SqlParameterSource getSqlParameterByApplicantUpload(Applicants applicants) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("firstname", applicants.getFirstname());
		paramSource.addValue("lastname", applicants.getLastname());
		paramSource.addValue("location", applicants.getLocation());
		paramSource.addValue("contactno", applicants.getContactno());
		paramSource.addValue("email", applicants.getEmail());
		paramSource.addValue("lastjobtitle", applicants.getLastjobtitle());
		paramSource.addValue("highesteducation", applicants.getHighesteducation());
		paramSource.addValue("uplaodcv", applicants.getUplaodcv());
		paramSource.addValue("parsertext1", applicants.getParserData1());
		paramSource.addValue("cvdocument1_tokens", applicants.getCountNouns1());
		paramSource.addValue("applicanttags", applicants.getApplicanttags());
		paramSource.addValue("applicantid", applicants.getApplicantid());
		return paramSource;

	}

	@Override
	public int updateApplicantotherUpload(Applicants applicant) {
		logger.info("applicantId :" + applicant.getApplicantid());
		return namedParameterJdbcTemplate.update(UPDATE_APPLICANT_DETAILS_OTHER,
				getSqlParameterByApplicantOther(applicant));
	}

	private SqlParameterSource getSqlParameterByApplicantOther(Applicants applicants) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("firstname", applicants.getFirstname());
		paramSource.addValue("lastname", applicants.getLastname());
		paramSource.addValue("location", applicants.getLocation());
		paramSource.addValue("contactno", applicants.getContactno());
		paramSource.addValue("email", applicants.getEmail());
		paramSource.addValue("lastjobtitle", applicants.getLastjobtitle());
		paramSource.addValue("highesteducation", applicants.getHighesteducation());
		paramSource.addValue("otheruploads", applicants.getOtheruploads());
		paramSource.addValue("parsertext2", applicants.getParserData2());
		paramSource.addValue("cvdocument2_tokens", applicants.getCountNouns1());
		paramSource.addValue("applicantid", applicants.getApplicantid());
		paramSource.addValue("applicanttags", applicants.getApplicanttags());
		return paramSource;

	}

	@Override
	public int updateJobWithoutDocs(CreateJob createJob) {
		logger.info("lastjobidvalue :" + createJob.getJobid());
		return namedParameterJdbcTemplate.update(UPDATE_JOB_WITH_OUT_DOCS, getSqlParameterByJobWithOutDocs(createJob));
	}

	private SqlParameterSource getSqlParameterByJobWithOutDocs(CreateJob createjob) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("jobtype", createjob.getJobtype());
		paramSource.addValue("company", createjob.getCompany());
		paramSource.addValue("location", createjob.getLocation());
		paramSource.addValue("jobkeywords", createjob.getJobkeywords());
		paramSource.addValue("salary", createjob.getSalary());
		paramSource.addValue("salarycriteria", createjob.getSalarycriteria());
		paramSource.addValue("jobtitle", createjob.getJobtitle());
		paramSource.addValue("jobcreatedemail", createjob.getCurrentLogin());
		paramSource.addValue("videoname", createjob.getVideoname());
		paramSource.addValue("videourl", createjob.getVideourl());
		paramSource.addValue("jobid", createjob.getJobid());
		paramSource.addValue("jobdescription", createjob.getJobdescription());
		paramSource.addValue("jobrequirement", createjob.getJobrequirement());
		paramSource.addValue("jobtags", createjob.getJobtags());
		paramSource.addValue("department", createjob.getDepartment());
		return paramSource;
	}

	@Override
	public int updateApplicantWithoutDocs(Applicants applicant) {
		logger.info("applicantId :" + applicant.getApplicantid());
		return namedParameterJdbcTemplate.update(UPDATE_APPLICANT_WITH_OUT_DOCS,
				getSqlParameterByApplicantWithOutDocs(applicant));
	}

	private SqlParameterSource getSqlParameterByApplicantWithOutDocs(Applicants applicants) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("firstname", applicants.getFirstname());
		paramSource.addValue("lastname", applicants.getLastname());
		paramSource.addValue("location", applicants.getLocation());
		paramSource.addValue("contactno", applicants.getContactno());
		paramSource.addValue("email", applicants.getEmail());
		paramSource.addValue("lastjobtitle", applicants.getLastjobtitle());
		paramSource.addValue("highesteducation", applicants.getHighesteducation());
		paramSource.addValue("applicanttags", applicants.getApplicanttags());
		paramSource.addValue("applicantid", applicants.getApplicantid());
		return paramSource;

	}

	@Override
	public int updateLogout(int userId) {
		logger.info("update logout :" + userId);
		return getJdbcTemplate.update(UPDATE_LOGOUT_IDLE_STATUS, userId);
	}

	@Override
	public int saveLogoutInfo(int userid) {
		logger.info("userid:" + userid);
		return getJdbcTemplate.update(UPDATE_LOGUT_STATUS, userid);

	}

	@Override
	public int saveCurrentLoginInfo(int userid, String teamname) {
		logger.info("userid:" + userid);
		logger.info("teamname:" + teamname);
		return getJdbcTemplate.update(INSERT_CURRENTDATE_INFO, userid, teamname, 1);

	}

	@Override
	public int checkLoginUser(int userId) {
		logger.info("userId:" + userId);
		int count = 0;
		List<Map<String, Object>> checkuserid = new ArrayList<Map<String, Object>>();
		try {
			checkuserid = getJdbcTemplate.queryForList(CHECK_LOGIN_USER, userId);
			count = checkuserid.size();
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}

		return count;
	}

	@Override
	public int insertLoginUser(int userId, String sessionId1, String teamname) {
		logger.info("userId:" + userId);
		logger.info("sessionId1:" + sessionId1);
		logger.info("teamname:" + teamname);
		return getJdbcTemplate.update(INSERT_CURRENTDATE_INFO, userId, sessionId1, teamname, 1);
	}

	@Override
	public User getSessions(int userId) {
		logger.info("userId :" + userId);
		User user = null;
		try {
			user = (User) getJdbcTemplate.queryForObject(GET_SESSION_IDS, new Object[] { userId },
					new BeanPropertyRowMapper(User.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return user;

	}

	@Override
	public int insertSession1(int userId, String sessionId1) {
		logger.info("userId:" + userId);
		logger.info("sessionId1:" + sessionId1);
		return getJdbcTemplate.update(INSERT_SESSION1_ID, sessionId1, userId);
	}

	@Override
	public int insertSession2(String sessionId2, int userId) {
		logger.info("userId:" + userId);
		logger.info("sessionId2:" + sessionId2);
		return getJdbcTemplate.update(INSERT_SESSION2_ID, sessionId2, userId);
	}

	@Override
	public int logoutSession1(int userId) {
		logger.info("userId:" + userId);
		String sessionId1 = null;
		String status1 = null;
		return getJdbcTemplate.update(LOGOUT_SESSION1, sessionId1, status1, userId);
	}

	@Override
	public int logoutSession2(int userId) {
		logger.info("userId:" + userId);
		String sessionId2 = null;
		String status2 = null;
		return getJdbcTemplate.update(LOGOUT_SESSION2, sessionId2, status2, userId);

	}

	@Override
	public int idleStateSession1(int userId) {
		logger.info("userId:" + userId);
		return getJdbcTemplate.update(IDLAE_STATE_SESSION1, userId);

	}

	@Override
	public int idleStateSessioon2(int userId) {
		logger.info("userId:" + userId);
		return getJdbcTemplate.update(IDLAE_STATE_SESSION2, userId);

	}

	@Override
	public int deleteSessions(int userId) {
		logger.info("userId:" + userId);
		String sessionId1 = null;
		String status1 = null;
		return getJdbcTemplate.update(DELETE_SESSIONS, sessionId1, status1, userId);

	}

}
