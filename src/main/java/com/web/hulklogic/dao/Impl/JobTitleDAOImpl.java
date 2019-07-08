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

import com.web.hulklogic.dao.JobTitleDAO;
import com.web.hulklogic.entity.ApplicantInfo;
import com.web.hulklogic.entity.AutoSignature;
import com.web.hulklogic.entity.CreateTemplate;
import com.web.hulklogic.entity.JobTitle;
import com.web.hulklogic.entity.JobsDocuments;
import com.web.hulklogic.entity.Target;

@Repository
public class JobTitleDAOImpl implements JobTitleDAO {
	private static Logger logger = Logger.getLogger(JobTitleDAOImpl.class);

	private static final String GET_LIST_OF_JOB_TITLE = "select jobid,concat(jobtitle,'  [', jobid,  ']', ', ' ,location) as Jobtitle from job where teamname=?";
	private static final String INSERT_INTO_NEW_APPLICANT = "insert into applicationstart(jobid,applicantid,status,created_by,teamname) SELECT * FROM (SELECT :jobid,:applicantid,:status,:created_by,:teamname) AS tmp WHERE NOT EXISTS ("
			+ "SELECT jobid,applicantid FROM applicationstart WHERE jobid=:jobid and applicantid=:applicantid) LIMIT 1";

	private static final String INSERT_INTO_NEW_APPLICANT1 = "insert into applicationhold(jobid,applicantid,status,created_by,teamname,source) SELECT * FROM (SELECT :jobid as jobid,:applicantid as applicantid ,:status as status,:created_by as created_by,:teamname as teamname,:source as source) AS tmp WHERE NOT EXISTS ("
			+ "SELECT jobid,applicantid FROM applicationstart WHERE jobid=:jobid and applicantid=:applicantid) LIMIT 1";

	private static final String INSERT_INTO_ADD_COMMENT = "insert into applicationhistory (comment,app_id,createdemail,particulars) values (?,?,?,?)";
	private static final String INSERT_ADD_COMMENT_APPLICANT = "update applicant set addcomments=? where applicantid=?";
	private static final String UPDATE_STATUS_APPLICATION = "update application set status=1 where applicationid=?";
	private static final String INSERT_APPLICATIONSTART = "insert into applicationstart (applicationid,appemail,status) values (:applicationid,:appemail'Awaiting Review')";
	private static final String INSERT_APPLICANT_COMMENT_HISTORY = "insert into applicantcomment (comments,applicantid,createdemail) values (:addComments,:applicantid,:email)";
	private static final String UPDATE_STATUS_OF_APPLICANT = "update application set status=1 where applicationid=? ";
	private static final String GET_ALL_DOCUMENTS = "select  at.jobid,at.applicantid,j.uploaddocument1, j.uploaddocument2 ,a.uplaodcv,a.otheruploads from applicationstart at  inner join job j on "
			+ " j.jobid = at.jobid inner join applicant a on a.applicantid = at.applicantid where at.app_id=?";
	private static final String GET_COMMENT_DETAILS_BY_APP_ID = "select concat(u.firstname, ' ' ,u.lastname )as name,ac.comment,ac.created_at,ac.email from appcomment ac inner join user u where u.email = ac.email and app_id=?";

	private static final String INSERT_TARGETS_MONTH = "insert into target(teamtarget,startdate,rectarget,periodic,teamname,email,userid,enddate)"
			+ "values(:teamtarget,:startdate,:rectarget,:periodic,:teamname,:email,:userid,DATE_ADD(:startdate, INTERVAL 1 MONTH ))";

	private static final String INSERT_TARGETS_QUARTER = "insert into target(teamtarget,startdate,rectarget,periodic,teamname,email,userid,enddate)"
			+ "values(:teamtarget,:startdate,:rectarget,:periodic,:teamname,:email,:userid,DATE_ADD(:startdate, INTERVAL 1 QUARTER ))";

	private static final String INSERT_TARGETS_YEAR = "insert into target(teamtarget,startdate,rectarget,periodic,teamname,email,userid,enddate)"
			+ "values(:teamtarget,:startdate,:rectarget,:periodic,:teamname,:email,:userid,DATE_ADD(:startdate, INTERVAL 1 YEAR))";

	private static final String GET_APPLICANT_INFO_BY_APP_ID = "select j.jobtitle,concat(ap.firstname,' ',ap.lastname)as name, ap.contactno,ap.email,a.applicantdetails_id,a.profilejson from applicationstart a "
			+ "inner join job j on j.jobid = a.jobid inner join applicant ap on a.applicantid= ap.applicantid where a.app_id=?";
	private static final String GET_PROFILE_INFO_BY_APP_ID ="select a.applicantdetails_id,at.profilename,a.profilejson from applicationstart a "
			+ "inner join job j on j.jobid = a.jobid inner join applicantdetails at on a.applicantid= at.applicantid where a.app_id=? and a.applicantdetails_id = at.applicantdetails_id";
	private static final String GET_TEMPLATE_BY_TEMPLATE_NAME = "select attachement from templates where attachement=?";

	private static final String GET_ALL_TEMPLATE_NAMES = "select * from templates where organization =?";

	private static final String GET_LAST_APP_ID = "SELECT app_id FROM applicationstart ORDER BY app_id DESC LIMIT 1";

	private static final String INSERT_CREATE_APPLICATION_IN_APPLICATION_HISTORY = "insert into applicationhistory (app_id,particulars,createdemail)values(?,?,?)";
	private static final String INSERT_AUTO_SIGNATURE = "update user set autosin_name=:autosin_name,autosin_companyname=:autosin_companyname,autosin_designation=:autosin_designation,"
			+ "autosin_companyurl=:autosin_companyurl,autosin_contact=:autosin_contact,address=:address,sightml=:sightml where email=:email";

	private static final String INSERT_JOBID_APPLICANTID_IN_APPLICATION = "insert into application(jobid,applicantid,status,appemail,teamname)values(:jobid,:applicantid,:status,:appemail,:teamname)";

	private static final String GET_TEAMTARGETS_BY_TEAMNAME = "select *from target where teamname=?";

	private static final String UPDATE_TEAM_TARGETS_MONTHLY = "update target set teamtarget=:teamtarget,startdate=:startdate,rectarget=:rectarget,periodic=:periodic,"
			+ "teamname=:teamname,enddate=DATE_ADD(:startdate, INTERVAL 1 MONTH) where teamname=:teamname";

	private static final String UPDATE_TEAM_TARGETS_QUARTERLY = "update target set teamtarget=:teamtarget,startdate=:startdate,rectarget=:rectarget,periodic=:periodic,"
			+ "teamname=:teamname,enddate=DATE_ADD(:startdate, INTERVAL 1 QUARTER) where teamname=:teamname";

	private static final String UPDATE_TEAM_TARGETS_YEARLY = "update target set teamtarget=:teamtarget,startdate=:startdate,rectarget=:rectarget,periodic=:periodic,"
			+ "teamname=:teamname,enddate=DATE_ADD(:startdate,INTERVAL 1 YEAR) where teamname=:teamname";

	@Autowired
	private JdbcTemplate getJdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public List<JobTitle> getJobTitle(String teamname) {
		logger.info("teamname:" + teamname);
		return getJdbcTemplate.query(GET_LIST_OF_JOB_TITLE, new BeanPropertyRowMapper(JobTitle.class), teamname);

	}

	@Override
	public int createApplicantDirectly(JobTitle jobtitle) {
		return namedParameterJdbcTemplate.update(INSERT_INTO_NEW_APPLICANT, getSqlParameterByApplicant(jobtitle));
	}

	private SqlParameterSource getSqlParameterByApplicant(JobTitle jobtitle) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("jobid", jobtitle.getJobid());
		paramSource.addValue("applicantid", jobtitle.getApplicantid());
		paramSource.addValue("status", "Awaiting Review");
		paramSource.addValue("recuiteremail", jobtitle.getReferncedemail());
		paramSource.addValue("created_by", jobtitle.getReferncedemail());
		paramSource.addValue("teamname", jobtitle.getTeamname());
		paramSource.addValue("source", jobtitle.getSource());
		return paramSource;
	}

	@Override
	public int createApplicantDirectly1(JobTitle jobtitle) {
		return namedParameterJdbcTemplate.update(INSERT_INTO_NEW_APPLICANT1, getSqlParameterByApplicant(jobtitle));
	}

	@Override
	public int saveAddComment(String addComment, int app_id, String email) {
		logger.info("addCommnet:" + addComment);
		logger.info("app_id:" + app_id);
		return getJdbcTemplate.update(INSERT_INTO_ADD_COMMENT, addComment, app_id, email, "Comment");
	}

	@Override
	public int addComment(String addcomments, int applicantid) {
		logger.info("addCommnet:" + addcomments);
		logger.info("applicantid:" + applicantid);
		return getJdbcTemplate.update(INSERT_ADD_COMMENT_APPLICANT, addcomments, applicantid);
	}

	@Override
	public int updateStatus(int applicationid) {
		logger.info("applicationid:" + applicationid);
		return getJdbcTemplate.update(UPDATE_STATUS_APPLICATION, applicationid);
	}

	@Override
	public int createapp(int applicationid, String email) {
		MapSqlParameterSource app = new MapSqlParameterSource();
		app.addValue("applicationid", applicationid);
		app.addValue("appemail", email);

		return namedParameterJdbcTemplate.update(INSERT_APPLICATIONSTART, app);
	}

	@Override
	public int addCommentHistory(String addComments, int applicantid, String email) {
		MapSqlParameterSource apphistory = new MapSqlParameterSource();
		apphistory.addValue("addComments", addComments);
		apphistory.addValue("applicantid", applicantid);
		apphistory.addValue("email", email);
		return namedParameterJdbcTemplate.update(INSERT_APPLICANT_COMMENT_HISTORY, apphistory);
	}

	@Override
	public int updateStatusApplicant(int applicationid) {
		logger.info("applicationid:" + applicationid);
		return getJdbcTemplate.update(UPDATE_STATUS_OF_APPLICANT, applicationid);
	}

	@Override
	public JobsDocuments getJobAndApplicantDocuments(int app_id) {
		logger.info("app_id :" + app_id);
		JobsDocuments getDocuments = null;
		try {
			getDocuments = (JobsDocuments) getJdbcTemplate.queryForObject(GET_ALL_DOCUMENTS, new Object[] { app_id },
					new BeanPropertyRowMapper(JobsDocuments.class));
		} catch (EmptyResultDataAccessException e) {
			JobsDocuments getDocumentsnull = new JobsDocuments();
			return getDocumentsnull;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return getDocuments;
	}

	public List<JobTitle> getAppCommnetByAppId(int appId) {
		logger.info("app_id :" + appId);
		return getJdbcTemplate.query(GET_COMMENT_DETAILS_BY_APP_ID, new BeanPropertyRowMapper(JobTitle.class), appId);

	}

	@Override
	public int saveTargetMonthly(Target target) {
		logger.info("Periodic :" + target.getPeriodic());
		return namedParameterJdbcTemplate.update(INSERT_TARGETS_MONTH, getSqlParameterTargets(target));
	}

	@Override
	public int saveTargetQuarterly(Target target) {
		logger.info("Periodic :" + target.getPeriodic());
		return namedParameterJdbcTemplate.update(INSERT_TARGETS_QUARTER, getSqlParameterTargets(target));
	}

	@Override
	public int saveTargetYearly(Target target) {
		logger.info("Periodic :" + target.getPeriodic());
		return namedParameterJdbcTemplate.update(INSERT_TARGETS_YEAR, getSqlParameterTargets(target));
	}

	@Override
	public int updateTargetsMonthly(Target target) {
		logger.info(" Update Periodic :" + target.getPeriodic());
		return namedParameterJdbcTemplate.update(UPDATE_TEAM_TARGETS_MONTHLY, getSqlParameterTargets(target));
	}

	@Override
	public int updateTargetsQuarterly(Target target) {
		logger.info(" Update Periodic :" + target.getPeriodic());
		return namedParameterJdbcTemplate.update(UPDATE_TEAM_TARGETS_QUARTERLY, getSqlParameterTargets(target));
	}

	@Override
	public int updateTargetsYearly(Target target) {
		logger.info(" Update Periodic :" + target.getPeriodic());
		return namedParameterJdbcTemplate.update(UPDATE_TEAM_TARGETS_YEARLY, getSqlParameterTargets(target));
	}

	private SqlParameterSource getSqlParameterTargets(Target target) {
		MapSqlParameterSource paramTargets = new MapSqlParameterSource();
		paramTargets.addValue("startdate", target.getStartdate());
		paramTargets.addValue("teamtarget", target.getTeamtarget());
		paramTargets.addValue("rectarget", target.getRectarget());
		paramTargets.addValue("periodic", target.getPeriodic());
		paramTargets.addValue("email", target.getEmail());
		paramTargets.addValue("userid", target.getUserid());
		paramTargets.addValue("teamname", target.getTeamname());
		return paramTargets;
	}

	@Override
	public ApplicantInfo getApplicantInfoByAppId(int appId) {
		logger.info("app_id :" + appId);
		ApplicantInfo getApplicantDetailsByAppId = null;
		try {
			getApplicantDetailsByAppId = (ApplicantInfo) getJdbcTemplate.queryForObject(GET_APPLICANT_INFO_BY_APP_ID,
					new Object[] { appId }, new BeanPropertyRowMapper(ApplicantInfo.class));
		} catch (EmptyResultDataAccessException e) {
			ApplicantInfo getCommnetByAppIdNull = new ApplicantInfo();
			return getCommnetByAppIdNull;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return getApplicantDetailsByAppId;
	}
	
	@Override
	public ApplicantInfo getProfileInfoByAppId(int appId) {
		logger.info("app_id :" + appId);
		ApplicantInfo getApplicantDetailsByAppId = null;
		try {
			getApplicantDetailsByAppId = (ApplicantInfo) getJdbcTemplate.queryForObject(GET_PROFILE_INFO_BY_APP_ID,
					new Object[] { appId }, new BeanPropertyRowMapper(ApplicantInfo.class));
		} catch (EmptyResultDataAccessException e) {
			ApplicantInfo getCommnetByAppIdNull = new ApplicantInfo();
			return getCommnetByAppIdNull;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return getApplicantDetailsByAppId;
	}

	@Override
	public int saveAutoSignature(AutoSignature autoSignature) {
		logger.info("style :" + autoSignature.getStyle());
		return namedParameterJdbcTemplate.update(INSERT_AUTO_SIGNATURE, getSqlParameterByAutoSignature(autoSignature));
	}

	private SqlParameterSource getSqlParameterByAutoSignature(AutoSignature autoSignature) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("autosin_name", autoSignature.getAutosin_name());
		paramSource.addValue("autosin_companyname", autoSignature.getAutosin_companyname());
		paramSource.addValue("autosin_designation", autoSignature.getAutosin_designation());
		paramSource.addValue("autosin_companyurl", autoSignature.getAutosin_companyurl());
		paramSource.addValue("autosin_contact", autoSignature.getAutosin_contact());
		paramSource.addValue("email", autoSignature.getCurrentEmail());
		paramSource.addValue("address", autoSignature.getAddress());
		paramSource.addValue("sightml", autoSignature.getSightml());
		return paramSource;
	}

	@Override
	public CreateTemplate getTemplateName(String templateName) {
		logger.info("templateName :" + templateName);
		CreateTemplate getTempalteName = null;
		try {
			getTempalteName = (CreateTemplate) getJdbcTemplate.queryForObject(GET_APPLICANT_INFO_BY_APP_ID,
					new Object[] { templateName }, new BeanPropertyRowMapper(CreateTemplate.class));
		} catch (EmptyResultDataAccessException e) {
			CreateTemplate getTempalteNameNull = new CreateTemplate();
			return getTempalteNameNull;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return getTempalteName;
	}

	@Override
	public List<CreateTemplate> getAllTemplateNames(String organization) {
		logger.info(organization);
		return getJdbcTemplate.query(GET_ALL_TEMPLATE_NAMES, new BeanPropertyRowMapper(CreateTemplate.class),organization);
	}

	@Override
	public int getLastApp_Id() {
		Integer getJobId = null;
		try {
			getJobId = (Integer) getJdbcTemplate.queryForObject(GET_LAST_APP_ID, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
		return getJobId;
	}

	@Override
	public int insertAppIdInAppHistory(int appId, String email) {
		logger.info("appId :" + appId);
		logger.info("email :" + email);
		return getJdbcTemplate.update(INSERT_CREATE_APPLICATION_IN_APPLICATION_HISTORY, appId, "ApplicationCreation",
				email);
	}

	@Override
	public int saveJobApplicantIdInApplication(JobTitle jobtitle) {
		return namedParameterJdbcTemplate.update(INSERT_JOBID_APPLICANTID_IN_APPLICATION,
				getSqlParameterByApplicantion(jobtitle));
	}

	private SqlParameterSource getSqlParameterByApplicantion(JobTitle jobtitle) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		logger.info("lastappId :" + jobtitle.getApplicantid());
		logger.info("recuiteremail :" + jobtitle.getReferncedemail());
		logger.info("teamname :" + jobtitle.getTeamname());
		paramSource.addValue("jobid", jobtitle.getJobid());
		paramSource.addValue("applicantid", jobtitle.getApplicantid());
		paramSource.addValue("status", "1");
		paramSource.addValue("appemail", jobtitle.getReferncedemail());
		paramSource.addValue("teamname", jobtitle.getTeamname());
		return paramSource;
	}

	@Override
	public Target getTargetsDetails(String teamname) {
		logger.info("teamname :" + teamname);
		Target getTargets = null;
		try {
			getTargets = (Target) getJdbcTemplate.queryForObject(GET_TEAMTARGETS_BY_TEAMNAME, new Object[] { teamname },
					new BeanPropertyRowMapper(Target.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return getTargets;
	}

}
