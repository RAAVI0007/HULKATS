package com.web.hulklogic.dao.Impl;

import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.web.hulklogic.dao.RecruiterDashBordDAO;
import com.web.hulklogic.entity.Applicants;
import com.web.hulklogic.entity.CreateJob;
import com.web.hulklogic.entity.User;

@Repository
public class RecruiterDashBordDAOImpl implements RecruiterDashBordDAO {

	private static Logger logger = Logger.getLogger(RecruiterDashBordDAOImpl.class);

	@Autowired
	private JdbcTemplate getJdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static final String COUNT_SOURCE_ANALYTICS_PER_RECRUITER = "SELECT source,count(source) AS stat  FROM applicationstart a inner join jobassign "
			+ "ja on a.jobid=ja.jobid WHERE a.created_at  between  DATE_SUB(CURDATE(), INTERVAL :day DAY) and CURDATE()  and ja.recuiteremail=:recruiteremail GROUP BY source";

	private static final String COUNT_SOURCE_ANALYTICS_TILL_NOW_PER_RECRUITERS = "select source,count(source) AS stat  FROM applicationstart a inner join jobassign ja on a.jobid=ja.jobid  where ja.recuiteremail=:recruiteremail GROUP BY source";

	private static final String COUNT_STATUS_ANALYTICS_PER_RECRUITER = "SELECT status,count(status) AS stat  FROM applicationstart a inner join jobassign ja on a.jobid=ja.jobid  WHERE a.created_at between  DATE_SUB(CURDATE(), INTERVAL :day DAY) and CURDATE() and ja.recuiteremail=:recruiteremail GROUP BY status";

	private static final String COUNT_STATUS_ANALYTICS_TILL_NOW_PER_RECRUITERS = "select status,count(status) AS stat  FROM applicationstart a inner join jobassign ja on a.jobid=ja.jobid  where ja.recuiteremail=:recruiteremail GROUP BY status";

	private static final String COUNT_JOBS_OPEN_CLOSED_TILLNOW_PER_RECRUITER = "select a.jobs_open,b.total  from"
			+ "(select count(1) as jobs_open from  job j inner join jobassign ja on j.jobid=ja.jobid  WHERE  status='OPEN' and ja.recuiteremail=:recruiteremail) a,"
			+ "(select count(1) as  total  from  job j inner join jobassign ja on j.jobid=ja.jobid  WHERE ja.recuiteremail=:recruiteremail) b";

	private static final String COUNT_JOBS_OPEN_CLOSED_PER_RECRUITER = "select a.jobs_open,b.total  from"
			+ "(select count(1) as jobs_open from  job j inner join jobassign ja on j.jobid=ja.jobid  WHERE ja.created_at  between  DATE_SUB(CURDATE(), INTERVAL :day DAY) and CURDATE() and status='OPEN' and ja.recuiteremail=:recruiteremail) a,"
			+ "(select count(1) as  total  from  job j inner join jobassign ja on j.jobid=ja.jobid  WHERE ja.created_at  between  DATE_SUB(CURDATE(), INTERVAL :day DAY) and CURDATE()  and ja.recuiteremail=:recruiteremail) b";

	private static final String COUNT_APPLICATION_IN_PROCESS_NEW_PER_RECRUITER = "select b.progress, a.total from"
			+ "(select count(status) as total from applicationstart  a inner join jobassign ja on a.jobid=ja.jobid WHERE a.created_at between  DATE_SUB(CURDATE(), INTERVAL :day DAY) and CURDATE()"
			+ " and ja.recuiteremail=:recruiteremail and a.status = 'Awaiting Review' ) a,"
			+ "(select count(status) as progress from applicationstart  a inner join jobassign ja on a.jobid=ja.jobid WHERE a.created_at between  DATE_SUB(CURDATE(), INTERVAL :day DAY) and CURDATE()"
			+ "and ja.recuiteremail=:recruiteremail and a.status not in('Awaiting Review','Hired','Rejected')) b";

	private static final String COUNT_APPLICATION_IN_PROCESS_NEW_TILL_NOW_PER_RECRUITERS = "select b.progress, a.total from"
			+ "(select count(status) as total from applicationstart a inner join jobassign ja on a.jobid=ja.jobid  where  ja.recuiteremail=:recruiteremail and a.status = 'Awaiting Review' ) a,"
			+ "(select count(status) as progress from applicationstart a inner join jobassign ja on a.jobid=ja.jobid  where  ja.recuiteremail=:recruiteremail and status not in('Awaiting Review','Hired','Rejected')) b";

	private static final String COUNT_HIRED_TARGET_PER = "select b.targetvalue,b.periodic, a.hired from"
			+ "(select count(status) as hired from applicationstart at inner join jobassign ja on at.jobid=ja.jobid WHERE at.created_at between  DATE_SUB(CURDATE(), INTERVAL :day DAY) and CURDATE() and ja.recuiteremail=:recruiteremail and status = 'Hired' ) a,"
			+ "(select rectarget as targetvalue,periodic  from target where teamname=:teamname) b";

	private static final String COUNT_HIRED_TARGET_TILL_NOW_PER = "select b.targetvalue,b.periodic, a.hired from"
			+ "(select count(status) as hired from applicationstart at inner join jobassign ja on at.jobid=ja.jobid  where  ja.recuiteremail=:recruiteremail and at.status = 'Hired' ) a,"
			+ "(select rectarget as targetvalue,periodic  from target where teamname=:teamname) b";
	private static final String GET_JOBS_BY_RECRUITEREMAIL = "select *from job j inner join jobassign ja on j.jobid=ja.jobid where ja.recuiteremail=:email";
	private static final String GET_JOBTITLE_BY_RECRUITER = "select  j.jobtitle,j.jobid from job j inner join jobassign ja on j.jobid=ja.jobid where ja.recuiteremail=:email";
	private static final String GET_APPLICATION_DETAILS_BY_RECRUITER = "select a.firstname, a.lastname, a.location, a.email,a.contactno,a.lastjobtitle,a.highesteducation,a.applicantid,ap.app_id, "
			+ "ap.status,ap.source from applicant a inner join applicationstart ap on ap.applicantid = a.applicantid inner join job j on ap.jobid = j.jobid  inner join jobassign ja on ja.jobid=j.jobid where ap.jobid=:jobId and ja.recuiteremail=:email";
	private static final String GET_TEAMDETAILS = "select DISTINCT u.firstname,u.lastname,u.userid,u.email,u.contactno,u.city,l.status1 as active1,l.status2 as active2  from user u left outer join live_user l on u.userid = l.userid where u.status=1 and u.teamname=:teamname and u.userid!=:userid";

	@Override
	public List<Map<String, Object>> sourceAnalytics(int day, String recruiteremail) {
		logger.info("day:" + day);
		logger.info("recruiteremail:" + recruiteremail);
		MapSqlParameterSource pss = new MapSqlParameterSource();
		pss.addValue("day", day);
		pss.addValue("recruiteremail", recruiteremail);
		List<Map<String, Object>> countSourceAnalytics = namedParameterJdbcTemplate
				.queryForList(COUNT_SOURCE_ANALYTICS_PER_RECRUITER, pss);
		return countSourceAnalytics;
	}

	@Override
	public List<Map<String, Object>> sourceAnalyticsTillPerRecruiter(String recruiteremail) {
		logger.info("recruiteremail:" + recruiteremail);
		MapSqlParameterSource pss = new MapSqlParameterSource();
		pss.addValue("recruiteremail", recruiteremail);
		List<Map<String, Object>> countSourceAnalytics = namedParameterJdbcTemplate
				.queryForList(COUNT_SOURCE_ANALYTICS_TILL_NOW_PER_RECRUITERS, pss);
		return countSourceAnalytics;
	}

	@Override
	public List<Map<String, Object>> statusAnalytics(int day, String recruiteremail) {
		logger.info("day:" + day);
		logger.info("recruiteremail:" + recruiteremail);
		MapSqlParameterSource pss = new MapSqlParameterSource();
		pss.addValue("day", day);
		pss.addValue("recruiteremail", recruiteremail);
		List<Map<String, Object>> countstatusAnalytics = namedParameterJdbcTemplate
				.queryForList(COUNT_STATUS_ANALYTICS_PER_RECRUITER, pss);
		return countstatusAnalytics;
	}

	@Override
	public List<Map<String, Object>> statusAnalyticsTillPerRecruiter(String recruiteremail) {
		logger.info("recruiteremail:" + recruiteremail);
		MapSqlParameterSource pss = new MapSqlParameterSource();
		pss.addValue("recruiteremail", recruiteremail);
		List<Map<String, Object>> countStatusAnalytics = namedParameterJdbcTemplate
				.queryForList(COUNT_STATUS_ANALYTICS_TILL_NOW_PER_RECRUITERS, pss);
		return countStatusAnalytics;
	}

	@Override
	public List<Map<String, Object>> getCountJobOpenClosed(int day, String recruiteremail) {
		logger.info("day:" + day);
		logger.info("recruiteremail:" + recruiteremail);
		MapSqlParameterSource pss = new MapSqlParameterSource();
		pss.addValue("recruiteremail", recruiteremail);
		pss.addValue("day", day);
		List<Map<String, Object>> jobsOpenAndClosed = namedParameterJdbcTemplate
				.queryForList(COUNT_JOBS_OPEN_CLOSED_PER_RECRUITER, pss);
		return jobsOpenAndClosed;
	}

	@Override
	public List<Map<String, Object>> getCountJobOpenClosedTillNow(String recruiteremail) {
		logger.info("recruiteremail:" + recruiteremail);
		MapSqlParameterSource pss = new MapSqlParameterSource();
		pss.addValue("recruiteremail", recruiteremail);
		List<Map<String, Object>> jobsOpenAndClosed = namedParameterJdbcTemplate
				.queryForList(COUNT_JOBS_OPEN_CLOSED_TILLNOW_PER_RECRUITER, pss);
		return jobsOpenAndClosed;
	}

	@Override
	public List<Map<String, Object>> applicationInProcessNew(int day, String recruiteremail) {
		logger.info("day:" + day);
		logger.info("recruiteremail:" + recruiteremail);
		MapSqlParameterSource pss = new MapSqlParameterSource();
		pss.addValue("day", day);
		pss.addValue("recruiteremail", recruiteremail);
		List<Map<String, Object>> countapplicationInProcessNew = namedParameterJdbcTemplate
				.queryForList(COUNT_APPLICATION_IN_PROCESS_NEW_PER_RECRUITER, pss);
		return countapplicationInProcessNew;
	}

	@Override
	public List<Map<String, Object>> applicationInProcessNewTillPerRecruiter(String recruiteremail) {
		logger.info("recruiteremail:" + recruiteremail);
		MapSqlParameterSource pss = new MapSqlParameterSource();
		pss.addValue("recruiteremail", recruiteremail);
		List<Map<String, Object>> countapplicationInProcessNew = namedParameterJdbcTemplate
				.queryForList(COUNT_APPLICATION_IN_PROCESS_NEW_TILL_NOW_PER_RECRUITERS, pss);
		return countapplicationInProcessNew;
	}

	@Override
	public List<Map<String, Object>> countApplicantHiredAndTarget(int day, String recruiteremail, String teamname) {
		logger.info("day:" + day);
		logger.info("recruiteremail:" + recruiteremail);
		logger.info("teamname:" + teamname);
		MapSqlParameterSource pss = new MapSqlParameterSource();
		pss.addValue("day", day);
		pss.addValue("recruiteremail", recruiteremail);
		pss.addValue("teamname", teamname);
		List<Map<String, Object>> countapplicationHiredAndNew = namedParameterJdbcTemplate
				.queryForList(COUNT_HIRED_TARGET_PER, pss);
		return countapplicationHiredAndNew;
	}

	@Override
	public List<Map<String, Object>> countApplicantHiredAndTargetTillPer(String recruiteremail, String teamname) {
		logger.info("recruiteremail:" + recruiteremail);
		logger.info("teamname:" + teamname);
		MapSqlParameterSource pss = new MapSqlParameterSource();
		pss.addValue("recruiteremail", recruiteremail);
		pss.addValue("teamname", teamname);
		List<Map<String, Object>> countHiredAndTarget = namedParameterJdbcTemplate
				.queryForList(COUNT_HIRED_TARGET_TILL_NOW_PER, pss);
		return countHiredAndTarget;
	}

	@Override
	public List<User> getTeamMemeberDetails(String teamname, int userid) {
		logger.info("teamname:" + teamname);
		logger.info("userid:" + userid);
		MapSqlParameterSource pss = new MapSqlParameterSource();
		pss.addValue("teamname", teamname);
		pss.addValue("userid", userid);
		List<User> users = namedParameterJdbcTemplate.query(GET_TEAMDETAILS, pss,
				new BeanPropertyRowMapper(User.class));
		return users;
	}

	@Override
	public List<CreateJob> getAllRecruiterJobs(String email) {
		logger.info("email:getAllRecruiterJobs:" + email);
		MapSqlParameterSource pss = new MapSqlParameterSource();
		pss.addValue("email", email);
		List<CreateJob> getAllJobs = namedParameterJdbcTemplate.query(GET_JOBS_BY_RECRUITEREMAIL, pss,
				new BeanPropertyRowMapper(CreateJob.class));
		return getAllJobs;
	}

	@Override
	public List<CreateJob> getJobTitleByRecruiter(String email) {
		logger.info("email:getJobTitleByRecruiter:" + email + ">>>>" + GET_JOBTITLE_BY_RECRUITER);
		MapSqlParameterSource pss = new MapSqlParameterSource();
		pss.addValue("email", email);
		List<CreateJob> getAllJobs = namedParameterJdbcTemplate.query(GET_JOBTITLE_BY_RECRUITER, pss,
				new BeanPropertyRowMapper(CreateJob.class));
		return getAllJobs;
	}

	@Override
	public List<Applicants> getApplicationByRecruiter(int jobId, String email) {
		logger.info("jobId:" + jobId + ">>>" + GET_APPLICATION_DETAILS_BY_RECRUITER);
		logger.info("email:" + email);
		MapSqlParameterSource pss = new MapSqlParameterSource();
		pss.addValue("jobId", jobId);
		pss.addValue("email", email);
		List<Applicants> getAllApplication = namedParameterJdbcTemplate.query(GET_APPLICATION_DETAILS_BY_RECRUITER, pss,
				new BeanPropertyRowMapper(Applicants.class));
		return getAllApplication;
	}
}
