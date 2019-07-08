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

import com.web.hulklogic.dao.DashBordDAO;
import com.web.hulklogic.entity.Target;
import com.web.hulklogic.entity.User;

@Repository
public class DashBordDAOImpl  implements DashBordDAO{
	private static Logger logger = Logger.getLogger(DashBordDAOImpl.class);
	
	
	private static final String COUNT_APPLICANT_HIRED_TARGET="select count(1) *from applicant;";
	
	private static final String COUNT_APPLICATION_INPROCESS_NEW="select b.progress, a.new from" + 
			"(select count(status) as new from applicationstart where status = 'Awaiting Review' ) a," + 
			"(select count(status) as progress from applicationstart where status not in('Awaiting Review','Hired','Rejected')) b";
	
	private static final String COUNT_JOB_OPEN_AND_TOTAL="select a.jobtotalcount,b.jobopencount from "
			   + " (select count(1) as  jobtotalcount from job where teamname=:teamname) a, "
			   +"  (select count(1) as  jobopencount   from job  where status='OPEN' and teamname=:teamname) b";
			
	
	private static final String TOP_RECRUITER_COUNT_HIRED_THIRTY="select concat(firstname,' ',lastname) as Name, "
			+ "count(a.status) as hired from application a  where a.status = 'Hired' WHERE updated_at = DATE_SUB(CURDATE(), INTERVAL 30 DAY)  group by status order by hired desc limit 3";
	
	private static final String TOP_RECRUITER_COUNT_HIRED_SEVEEN="select concat(firstname,' ',lastname) as Name, "
			+ "count(a.status) as hired from application a  where a.status = 'Hired' WHERE updated_at = DATE_SUB(CURDATE(), INTERVAL 7 DAY)  group by status order by hired desc limit 3";
	
	private static final String TOP_RECRUITER_COUNT_HIRED_TILLNOW="select concat(firstname,' ',lastname) as Name,target "
			+ "count(a.status) as hired from application a  where a.status = 'Hired'  group by status order by hired desc limit 3";
	
	private static final String COUNT_SOURCE_ANALYTICS_THIRTY="SELECT source,count(source) AS stat  FROM applicationstart WHERE updated_at >= DATE_SUB(CURDATE(), INTERVAL 30 DAY) GROUP BY source";
	
	private static final String COUNT_SOURCE_ANALYTICS_SEVEN="SELECT source,count(source) AS stat  FROM applicationstart WHERE updated_at >= DATE_SUB(CURDATE(), INTERVAL 7 DAY) GROUP BY source";
	
	private static final String COUNT_SOURCE_ANALYTICS_TILL_NOW="SELECT source,count(source) AS stat  FROM applicationstart  GROUP BY source";
	
	private static final String TOP_RECRUITER_COUNT_HIRED__BACKLOGS_THIRTY="select concat(firstname,' ',lastname) as Name "
			+ "count(a.status) as hired from application a  where a.status = 'Hired' WHERE updated_at = DATE_SUB(CURDATE(), INTERVAL 30 DAY)  group by status order by hired asc limit 3";
	
	private static final String TOP_RECRUITER_COUNT_HIRED__BACKLOGS_SEVEEN="select concat(firstname,' ',lastname) as Name, "
			+ "count(a.status) as hired from application a  where a.status = 'Hired' WHERE updated_at = DATE_SUB(CURDATE(), INTERVAL 7 DAY)  group by status order by hired asc limit 3";
	
	private static final String TOP_RECRUITER_COUNT_HIRED__BACKLOGS="select concat(firstname,' ',lastname) as Name, "
			+ "count(a.status) as hired from application a  where a.status = 'Hired'  group by status order by hired desc limit 3";
	
	private static final String STATYUS_ANALYTICS_COUNT_THIRTY="SELECT STATUS,count(STATUS) AS stat  FROM applicationstart WHERE created_at >= DATE_SUB(CURDATE(), INTERVAL 30 DAY) GROUP BY status";
	
	private static final String STATUS_ANALYTICS_COUNT_SEVEN="SELECT STATUS,count(STATUS) AS stat  FROM applicationstart WHERE updated_at >= DATE_SUB(CURDATE(), INTERVAL 7 DAY) GROUP BY status";
	private static final String STATUS_ANALYTICS_COUNT="SELECT STATUS,count(STATUS) AS stat  FROM applicationstart  GROUP BY status";
	private static final String INSERT_INTO_TARGET="insert into application (target,level,report) values(?,?,?)";
	private static final String ASSIGN_TO_ANOTHER_TEANM="update user set teamname=? where userid=?";
	private static final String COUNT_ACTIVE_USER="select a.count_user_live,b.totalteam from "
		      + " (select count(distinct(lu.userid)) as count_user_live from live_user lu  where lu.teamname=:teamname and userid!=:userid and lu.status1=1 or lu.status2=1) a, "
		      +"  (select count(userid) as  totalteam  from user  where roleid=2 and teamname=:teamname) b";
	 
	 private static final String AUTO_COMPLETE_RECRUITER_NAMES="select CONCAT(u.firstname, ' ', u.lastname,'[',u.email,']') as name, u.userid, u.email,l.status1 as active1 from user u left outer join live_user l on u.userid = l.userid  where u.roleid=2 and u.teamname=:teamname and u.status=1";
	 private static final String RECRUITER_NAMES="select CONCAT(firstname, ' ', lastname,'[',email,']') as name, userid,email from user where userid != :userid and teamname=:teamname";  
	 
	@Autowired
	private JdbcTemplate getJdbcTemplate;
	
	
	@Autowired
	private NamedParameterJdbcTemplate namedparameterjdbctemplate;
	
	
	@Override
	public List<Map<String, Object>> countApplicantHiredAndTarget() {
	 List<Map<String, Object>> countApplicantHired=getJdbcTemplate.queryForList(COUNT_APPLICANT_HIRED_TARGET);
	 return countApplicantHired;
	}

	

	@Override
	public List<Map<String, Object>> countApplicationInprocessAndNew() {
		List<Map<String, Object>> applicationInprocessAndNew=getJdbcTemplate.queryForList(COUNT_APPLICATION_INPROCESS_NEW);
		return applicationInprocessAndNew;
	}

	
	@Override
	public List<Map<String, Object>> recruiterCountHiredThirty() {
		List<Map<String, Object>> counttophired=getJdbcTemplate.queryForList(TOP_RECRUITER_COUNT_HIRED_THIRTY);
		return counttophired;
	}

	@Override
	public List<Map<String, Object>> recruiterCountHiredSeven() {
		List<Map<String, Object>> countHiredSeven=getJdbcTemplate.queryForList(TOP_RECRUITER_COUNT_HIRED_SEVEEN);
		return countHiredSeven;
	}

	@Override
	public List<Map<String, Object>> recruiterCountHiredTillnow() {
		List<Map<String, Object>> countHiredTillNow=getJdbcTemplate.queryForList(TOP_RECRUITER_COUNT_HIRED_TILLNOW);
		return countHiredTillNow;
	}

	@Override
	public List<Map<String, Object>>  sourceAnalyticsThirty() {
		List<Map<String, Object>> countSourceAnalytics=getJdbcTemplate.queryForList(COUNT_SOURCE_ANALYTICS_THIRTY);
		return countSourceAnalytics;
	}

	@Override
	public List<Map<String, Object>> sourceAnalyticsSeven() {
		List<Map<String, Object>> countSourceAnalytics=getJdbcTemplate.queryForList(COUNT_SOURCE_ANALYTICS_SEVEN);
		return countSourceAnalytics;

	}

	@Override
	public List<Map<String, Object>> sourceAnalyticsTillNow() {
		List<Map<String, Object>> countSourceAnalytics=getJdbcTemplate.queryForList(COUNT_SOURCE_ANALYTICS_SEVEN);
		return countSourceAnalytics;

	}

	@Override
	public List<Map<String, Object>> recruiterHiredBacklogsThirty() {
		List<Map<String, Object>> countHiredBackLogs=getJdbcTemplate.queryForList(TOP_RECRUITER_COUNT_HIRED__BACKLOGS_THIRTY);
		return countHiredBackLogs;
	}

	@Override
	public List<Map<String, Object>> recruiterHiredBacklogsSeven() {
		List<Map<String, Object>> countHiredBackLogs=getJdbcTemplate.queryForList(TOP_RECRUITER_COUNT_HIRED__BACKLOGS_SEVEEN);
		return countHiredBackLogs;
	}

	@Override
	public List<Map<String, Object>> recruiterHiredBacklogsTillNow() {
		List<Map<String, Object>> countHiredBackLogs=getJdbcTemplate.queryForList(TOP_RECRUITER_COUNT_HIRED__BACKLOGS);
		return countHiredBackLogs;
	}

	@Override
	public List<Map<String, Object>> statusAnalyticsThirty() {
		List<Map<String, Object>> countHiredBackLogs=getJdbcTemplate.queryForList(STATYUS_ANALYTICS_COUNT_THIRTY);
		return countHiredBackLogs;
	}

	@Override
	public List<Map<String, Object>> statusAnalyticsSeven() {
		System.out.println("hrrllrlr");
		List<Map<String, Object>> countHiredBackLogs=getJdbcTemplate.queryForList(STATUS_ANALYTICS_COUNT_SEVEN);
		logger.debug("countHiredBackLogs"+countHiredBackLogs);
		return countHiredBackLogs;
	}
	@Override
	 public List<Map<String, Object>> statusAnalyticsTillNow() {
	  List<Map<String, Object>> countStatusAnalytics=getJdbcTemplate.queryForList(STATUS_ANALYTICS_COUNT);
	  return countStatusAnalytics;
	 }
	

	@Override
	public int AssignManager(int userid, String teamname) {
		return getJdbcTemplate.update(ASSIGN_TO_ANOTHER_TEANM, teamname,userid);
	}
	@Override
	 public List<Map<String, Object>> getLiveUser(int userid,String teamname) {
	  MapSqlParameterSource pss = new MapSqlParameterSource();
	     pss.addValue("userid",userid );
	  pss.addValue("teamname",teamname );
	  List<Map<String, Object>> getActiveUser=namedparameterjdbctemplate.queryForList(COUNT_ACTIVE_USER,pss);
	     return getActiveUser;
	 }
	
	
	@Override
	public List<Map<String, Object>> countJobsOpenAndTotal(String teamname) {
		 MapSqlParameterSource jobcc = new MapSqlParameterSource();
		 jobcc.addValue("teamname", teamname);
		 System.out.println("daoimpl"+teamname);
		List<Map<String, Object>> jobsOpenAndTotal=namedparameterjdbctemplate.queryForList(COUNT_JOB_OPEN_AND_TOTAL,jobcc);
		  
		return jobsOpenAndTotal;
	}



	@Override
	public List<User> getRecruitereNamesByAutoComplate(String teamname) {
		 logger.info("teamname:"+teamname);
	      MapSqlParameterSource pss = new MapSqlParameterSource();
	      pss.addValue("teamname",teamname );
	      List<User> getRecruitername  = namedparameterjdbctemplate.query(AUTO_COMPLETE_RECRUITER_NAMES, pss,new BeanPropertyRowMapper(User.class));
	     
	      return getRecruitername;
	}



	@Override
	public int targetcreation(Target target) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public  List<User> email(int userid, String teamname) {
		 logger.info("teamname:"+teamname);
	      MapSqlParameterSource pss = new MapSqlParameterSource();
	      pss.addValue("teamname",teamname );
	      pss.addValue("userid", userid);
	      List<User> getRecruitername  = namedparameterjdbctemplate.query(RECRUITER_NAMES, pss,new BeanPropertyRowMapper(User.class));
	      return getRecruitername;
	
	}
}
