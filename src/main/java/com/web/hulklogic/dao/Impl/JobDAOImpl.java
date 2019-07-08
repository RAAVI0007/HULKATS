package com.web.hulklogic.dao.Impl;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import com.web.hulklogic.dao.JobDAO;
import com.web.hulklogic.entity.Applicants;
import com.web.hulklogic.entity.CreateJob;
import com.web.hulklogic.entity.JobsDocuments;
@Repository
public class JobDAOImpl implements JobDAO{
	private static Logger logger = Logger.getLogger(JobDAOImpl.class);
	
	private static final String INSERT_JOB_CREATION="insert into job(company,jobtype,location,jobkeywords,salary,salarycriteria,uploaddocument1,"
			+ "uploaddocument2,jobtitle,jobcreatedemail,"
			+ "teamname,status,videourl,videoname,jobdescription,jobrequirement,jobtags,department)values(:company,:jobtype,:location,:jobkeywords ,:salary,:salarycriteria,:uploaddocument1,"
			+ ":uploaddocument2,:jobtitle,:jobcreatedemail,"
			+ ":teamname,:status,:videourl,:videoname,:jobdescription,:jobrequirement,:jobtags,:department)";
	private static final String GET_ALL_JOBS_CURRENT_LOGIN="select *from job j  where j.teamname=?";
	private static final String GET_ALL_JOBS="select * from jobassign ja inner join job j where ja.jobid = j.jobid";
	private static final String GET_ALL_RECENT_JOBS="select * from jobassign ja inner join job j where ja.jobid = j.jobid and company =?  order by j.jobid desc";
	private static final String INSERT_APPLICANTS_CREATIONS="insert into applicant(firstname,lastname,location,contactno,email,lastjobtitle,highesteducation,status,"
			+ "uplaodcv,otheruploads,applicantcreatedemail,teamname,source,publicprofile,active_status,roleid,applicanttags,organization)values(:firstname,:lastname,:location,"
			+ ":contactno,:email,:lastjobtitle,:highesteducation,:status,:uplaodcv,:otheruploads,:applicantcreatedemail,:teamname,:source,:publicprofile,:active_status,:roleid,:applicanttags,:organization)";
	
	private static final String GET_ALL_APPLICANTS_CURRENT_LOGIN="select *from applicant where organization = ?";
	private static final String GET_DOCUMENTS_JOBS_BY_ID="select uploaddocument1,uploaddocument2 from job where jobid=?";
	private static final String SELECT_PASSWORD="select password from user where password=? and email=?";
	private static final String UPADATE_PASSWORD="update user set password=? where email=?";
	private static final String GET_KEYWORDS_BASED_ON_JOBID="select jobkeywords from job where jobid=?";
	private static final String INSERT_JOBASSIGN_RECRUITER="insert into jobassign(recuiteremail,jobid)values(?,?)";
	private static final String GET_JOB_DETAILS = "select * from job where jobid=?";
	private static final String GET_APPLICANT_DETAILS = "select * from applicant where applicantid=?";
 
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private JdbcTemplate getJdbcTemplate;

	@Override
	public int createJob(CreateJob createjob) {
		return namedParameterJdbcTemplate.update(INSERT_JOB_CREATION, getSqlParameterByJob(createjob));
	}
	
	private SqlParameterSource getSqlParameterByJob(CreateJob createjob){
		MapSqlParameterSource paramSource=new MapSqlParameterSource();
		paramSource.addValue("jobtype",createjob.getJobtype());
		paramSource.addValue("company",createjob.getCompany());
		paramSource.addValue("location",createjob.getLocation());
		paramSource.addValue("jobkeywords",createjob.getJobkeywords());
		paramSource.addValue("salary",createjob.getSalary());
		paramSource.addValue("salarycriteria",createjob.getSalarycriteria());
		paramSource.addValue("jobtitle",createjob.getJobtitle());
		
		paramSource.addValue("jobcreatedemail",createjob.getCurrentLogin());
		paramSource.addValue("uploaddocument1",createjob.getDocument1());
		paramSource.addValue("uploaddocument2",createjob.getDocument2());
	
		paramSource.addValue("teamname",createjob.getTeamname());
		paramSource.addValue("status", "OPEN");
		paramSource.addValue("videoname",createjob.getVideoname());
		paramSource.addValue("videourl",createjob.getVideourl());
		paramSource.addValue("jobdescription",createjob.getJobdescription());
		paramSource.addValue("jobrequirement",createjob.getJobrequirement());
		paramSource.addValue("jobtags",createjob.getJobtags());
		paramSource.addValue("department",createjob.getDepartment());
		return paramSource;
}
	
	@Override
	public List<CreateJob> getAllJobs(String teamname) {
		logger.info("teamname :"+teamname);
		List<CreateJob>   jobs= getJdbcTemplate.query(GET_ALL_JOBS_CURRENT_LOGIN,new BeanPropertyRowMapper(CreateJob.class),teamname);
		return jobs;
	}
	
	@Override
	public List<CreateJob> getalljobs() {
		List<CreateJob> jobs= getJdbcTemplate.query(GET_ALL_JOBS,new BeanPropertyRowMapper(CreateJob.class));
		return jobs;
	}
	
	@Override
	public List<CreateJob> getallrecentjobs(String company) {
		List<CreateJob> jobs= getJdbcTemplate.query(GET_ALL_RECENT_JOBS,new BeanPropertyRowMapper(CreateJob.class),company);
		return jobs;
	}
	
	
	@Override
	public int createApplicants(Applicants applicants) {
		return namedParameterJdbcTemplate.update(INSERT_APPLICANTS_CREATIONS,getSqlParameterByApplicant(applicants));
	}
	
	private SqlParameterSource getSqlParameterByApplicant(Applicants applicants){
		 MapSqlParameterSource paramSource=new MapSqlParameterSource();
		 paramSource.addValue("firstname",applicants.getFirstname());
		 paramSource.addValue("lastname",applicants.getLastname());
		 paramSource.addValue("location",applicants.getLocation());
		 paramSource.addValue("contactno",applicants.getContactno());
		 paramSource.addValue("email",applicants.getEmail());
		 paramSource.addValue("lastjobtitle",applicants.getLastjobtitle());
		 paramSource.addValue("highesteducation",applicants.getHighesteducation());
		 paramSource.addValue("status","no");
		 paramSource.addValue("uplaodcv",applicants.getUplaodcv());
		 paramSource.addValue("otheruploads",applicants.getOtheruploads());
		 paramSource.addValue("applicantcreatedemail",applicants.getApplicantcreatedemail());
		 paramSource.addValue("teamname",applicants.getTeamname());
		 paramSource.addValue("source",applicants.getSource());
		 paramSource.addValue("publicprofile",applicants.getPublicprofile());
		 paramSource.addValue("active_status",0);
		 paramSource.addValue("applicanttags",applicants.getApplicanttags());
		 paramSource.addValue("roleid",4);
		 paramSource.addValue("organization", applicants.getOrganization());
		 return paramSource;	
	}
	

	@Override
	public List<Applicants> getAllApplicants(String organization) {
		
		List<Applicants> getapplicants= getJdbcTemplate.query(GET_ALL_APPLICANTS_CURRENT_LOGIN,new BeanPropertyRowMapper(Applicants.class),organization);
		return getapplicants;
	}
	@Override
	 public JobsDocuments getDocumentsByJobId(int jobid) {
		logger.info("jobid:"+jobid);
		JobsDocuments getjobsDocuments=null;
	  try{
	      getjobsDocuments = (JobsDocuments)getJdbcTemplate.queryForObject(GET_DOCUMENTS_JOBS_BY_ID, new Object[] { jobid },new BeanPropertyRowMapper(JobsDocuments.class));
	  }catch(EmptyResultDataAccessException e){
	   return null;
	  }catch(Exception e){
		    logger.error("Exception is: "+e);
	  }
	  return getjobsDocuments;        
	 }
	@Override
	 public int updatePassword(String currentEmail,String newPassword) {
		logger.info("currentEmail :"+currentEmail);
		logger.info("newPassword :"+newPassword);
	  int count=getJdbcTemplate.update(UPADATE_PASSWORD, newPassword,currentEmail);
	  return count;
	 }

	 @Override
	 public int selectPassword(String currentEmail, String oldPassword) {
		 logger.info("currentEmail :"+currentEmail);
		 logger.info("oldPassword :"+oldPassword);
	     List data=getJdbcTemplate.queryForList(SELECT_PASSWORD, oldPassword,currentEmail);
	     int count=data.size();
	      return count;
	 }

	 @Override
	 public List getJobKeywords(int jobid) {
	  return getJdbcTemplate.queryForList(GET_KEYWORDS_BASED_ON_JOBID, jobid);
	 }

	 @Override
	 public int jobassignRecruiter(String email, int jobid) {
	   logger.info("currentEmail :"+email);
	   logger.info("jobid :"+jobid);
	   int count=0;
	   count=getJdbcTemplate.update(INSERT_JOBASSIGN_RECRUITER, email,jobid);
	   return count;
	 }

	@Override
	public CreateJob jobdetails(int jobid) {
	
		return (CreateJob) getJdbcTemplate.queryForObject(GET_JOB_DETAILS,new Object[] { jobid },new BeanPropertyRowMapper(CreateJob.class));
	}

	@Override
	public Applicants getapplicantdetails(int applicantid) {
		
		return (Applicants) getJdbcTemplate.queryForObject(GET_APPLICANT_DETAILS,new Object[] { applicantid },new BeanPropertyRowMapper(Applicants.class));
	}
	
	 
}
