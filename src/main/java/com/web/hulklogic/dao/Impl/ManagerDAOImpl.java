package com.web.hulklogic.dao.Impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.web.hulklogic.dao.ManagerDAO;
import com.web.hulklogic.entity.JobsDocuments;
import com.web.hulklogic.entity.Target;
@Repository
public class ManagerDAOImpl implements ManagerDAO{
	private static Logger logger = Logger.getLogger(ManagerDAOImpl.class);
	
	 private static final String COUNT_JOBS_OPEN_CLOSED_PER_RECRUITER="select a.jobs_open,b.total  from"
			  + "(select count(1) as jobs_open from  job j inner join jobassign ja on j.jobid=ja.jobid  WHERE ja.created_at  between  DATE_SUB(CURDATE(), INTERVAL :day DAY) and CURDATE() and status='OPEN' and ja.recuiteremail=:recruiteremail) a,"
			   +"(select count(1) as  total  from  job j inner join jobassign ja on j.jobid=ja.jobid  WHERE ja.created_at  between  DATE_SUB(CURDATE(), INTERVAL :day DAY) and CURDATE()  and ja.recuiteremail=:recruiteremail) b";
		   
		   private static final String COUNT_JOBS_OPEN_CLOSED_ALL_RECRUITER="select a.jobs_open,b.total  from"
					  + "(select count(1) as jobs_open from  job j inner join jobassign ja on j.jobid=ja.jobid  WHERE ja.created_at  between  DATE_SUB(CURDATE(), INTERVAL :day DAY) and CURDATE()  and status='OPEN' and teamname=:teamname) a,"
					   +"(select count(1) as  total  from  job   WHERE created_at  between  DATE_SUB(CURDATE(), INTERVAL :day DAY) and CURDATE()  and teamname=:teamname) b";
		  
		   private static final String COUNT_JOBS_OPEN_CLOSED_TILLNOW_PER_RECRUITER="select a.jobs_open,b.total  from"
			   + "(select count(1) as jobs_open from  job j inner join jobassign ja on j.jobid=ja.jobid  WHERE  status='OPEN' and ja.recuiteremail=:recruiteremail) a,"
			   +"(select count(1) as  total  from  job j inner join jobassign ja on j.jobid=ja.jobid  WHERE ja.recuiteremail=:recruiteremail) b";
		   
		   private static final String COUNT_JOBS_OPEN_CLOSED_TILLNOW_ALL_RECRUITERS="select a.jobs_open,b.total  from"
					  + "(select count(1) as jobs_open from  job where status='OPEN' and teamname=:teamname) a,"
					   +"(select count(1) as  total  from  job  where  teamname=:teamname) b";
			    
		   private static final String COUNT_SOURCE_ANALYTICS_PER_RECRUITER="SELECT source,count(source) AS stat  FROM applicationstart a inner join jobassign "
		   		+ "ja on a.jobid=ja.jobid WHERE a.created_at  between  DATE_SUB(CURDATE(), INTERVAL :day DAY) and CURDATE()  and ja.recuiteremail=:recruiteremail GROUP BY source";
		   
		   private static final String COUNT_SOURCE_ANALYTICS_ALL_RECRUITERS="SELECT source,count(source) AS stat  FROM applicationstart a "
		   		+ " WHERE a.created_at between  DATE_SUB(CURDATE(), INTERVAL :day DAY) and CURDATE() and a.teamname=:teamname GROUP BY source";
		   
		   private static final String COUNT_SOURCE_ANALYTICS_TILL_NOW_ALL_RECRUITERS="select source,count(source) AS stat  FROM applicationstart where teamname=:teamname GROUP BY source" ;
			
		   private static final String COUNT_SOURCE_ANALYTICS_TILL_NOW_PER_RECRUITERS="select source,count(source) AS stat  FROM applicationstart a inner join jobassign ja on a.jobid=ja.jobid  where ja.recuiteremail=:recruiteremail GROUP BY source" ;
			
			
		   private static final String COUNT_STATUS_ANALYTICS_PER_RECRUITER="SELECT status,count(status) AS stat  FROM applicationstart a inner join jobassign ja on a.jobid=ja.jobid  WHERE a.created_at between  DATE_SUB(CURDATE(), INTERVAL :day DAY) and CURDATE() and ja.recuiteremail=:recruiteremail GROUP BY status";
		   
		   private static final String COUNT_STATUS_ANALYTICS_ALL_RECRUITERS="SELECT status,count(status) AS stat  FROM applicationstart a "
		   		+ " WHERE a.created_at between  DATE_SUB(CURDATE(), INTERVAL :day DAY) and CURDATE() and a.teamname=:teamname GROUP BY status";
		   
		   private static final String COUNT_STATUS_ANALYTICS_TILL_NOW_ALL_RECRUITERS="select status,count(status) AS stat  FROM applicationstart where teamname=:teamname GROUP BY status" ;

		   private static final String COUNT_STATUS_ANALYTICS_TILL_NOW_PER_RECRUITERS="select status,count(status) AS stat  FROM applicationstart a inner join jobassign ja on a.jobid=ja.jobid  where ja.recuiteremail=:recruiteremail GROUP BY status" ;
		   
		   
		   private static final String COUNT_APPLICATION_IN_PROCESS_NEW_PER_RECRUITER="select b.progress, a.total from" + 
				"(select count(status) as total from applicationstart  a inner join jobassign ja on a.jobid=ja.jobid WHERE a.created_at between  DATE_SUB(CURDATE(), INTERVAL :day DAY) and CURDATE()"
				+ " and ja.recuiteremail=:recruiteremail and a.status = 'Awaiting Review' ) a," + 
			    "(select count(status) as progress from applicationstart  a inner join jobassign ja on a.jobid=ja.jobid WHERE a.created_at between  DATE_SUB(CURDATE(), INTERVAL :day DAY) and CURDATE()"
			    +"and ja.recuiteremail=:recruiteremail and a.status not in('Awaiting Review','Hired','Rejected')) b";
		   
		   
		  private static final String COUNT_APPLICATION_IN_PROCESS_NEW_ALL_RECRUITER="select b.progress, a.total from" + 
			   "(select count(status) as total from applicationstart  WHERE created_at between  DATE_SUB(CURDATE(), INTERVAL :day DAY) and CURDATE() and teamname=:teamname and status = 'Awaiting Review' ) a," + 
			   "(select count(status) as progress from applicationstart  WHERE created_at between  DATE_SUB(CURDATE(), INTERVAL :day DAY) and CURDATE() and teamname=:teamname and status not in('Awaiting Review','Hired','Rejected')) b";
		  
	  private static final String COUNT_APPLICATION_IN_PROCESS_NEW_TILL_NOW_PER_RECRUITERS="select b.progress, a.total from" + 
				"(select count(status) as total from applicationstart a inner join jobassign ja on a.jobid=ja.jobid  where  ja.recuiteremail=:recruiteremail and a.status = 'Awaiting Review' ) a," + 
				"(select count(status) as progress from applicationstart a inner join jobassign ja on a.jobid=ja.jobid  where  ja.recuiteremail=:recruiteremail and status not in('Awaiting Review','Hired','Rejected')) b";
		  
	   private static final String COUNT_APPLICATION_IN_PROCESS_NEW_TILL_NOW_ALL_RECRUITER="select b.progress, a.total from" + 
					"(select count(status) as total from applicationstart where  teamname=:teamname and status = 'Awaiting Review' ) a," + 
					"(select count(status) as progress from applicationstart where teamname=:teamname and status not in('Awaiting Review','Hired','Rejected')) b";
		  
	   private static final String COUNT_HIRED_TARGET_TILL_NOW_ALL="select b.targetvalue, a.hired from" + 
				   "(select count(status) as hired from applicationstart where  teamname=:teamname and status = 'Hired' ) a," + 
				   "(select teamtarget as targetvalue from target where teamname=:teamname) b";
				  
        private static final String COUNT_HIRED_TARGET_TILL_NOW_PER="select b.targetvalue, a.hired from" + 
				   "(select count(status) as hired from applicationstart at inner join jobassign ja on at.jobid=ja.jobid  where  ja.recuiteremail=:recruiteremail and at.status = 'Hired' ) a," + 
				   "(select rectarget as targetvalue from target where teamname=:teamname) b";
				 
	    private static final String COUNT_HIRED_TARGET_PER="select b.targetvalue,b.periodic, a.hired from" + 
				    "(select count(status) as hired from applicationstart at inner join jobassign ja on at.jobid=ja.jobid WHERE at.created_at between  DATE_SUB(CURDATE(), INTERVAL :day DAY) and CURDATE() and ja.recuiteremail=:recruiteremail and status = 'Hired' ) a," + 
				    "(select rectarget as targetvalue,periodic  from target where teamname=:teamname) b";
				 
		 private static final String COUNT_HIRED_TARGET_ALL="select b.targetvalue,b.periodic, a.hired from" + 
				    "(select count(status) as hired from applicationstart  WHERE created_at between  DATE_SUB(CURDATE(), INTERVAL :day DAY) and CURDATE() and teamname=:teamname and status = 'Hired' ) a," + 
				    "(select teamtarget as targetvalue,periodic from target where teamname=:teamname) b";
		 
		 private static final String GET_DETAILS_BY_TEAMNAME="select * from target where teamname=? LIMIT 1";
		 
		 private static final String GET_TOP_RECRUITERS_TILL="select concat(u.firstname,' ',u.lastname) as name,u.email,time_to_sec(timediff(a.updated_at,concat(t.startdate,' 00:00:00'))) as duration,sum(case when(a.status='Hired') then 1 else 0 end )"
                 +" as performance ,t.rectarget,t.periodic from applicationstart a  inner join target t on a.teamname = t.teamname and DATE(a.updated_at)<= t.enddate"
                 +" inner join user u on a.recruiteremail = u.email where (t.startdate between :startDate and CURDATE()) and u.roleid=2 and a.teamname=:teamname"
                 +" group by a.recruiteremail order by performance desc,duration asc limit 3";
		 
		 private static final String GET_TOP_RECRUITERS_DAYS="select concat(u.firstname,' ',u.lastname) as name,u.email,time_to_sec(timediff(a.updated_at,concat(t.startdate,' 00:00:00'))) as duration,sum(case when(a.status='Hired') then 1 else 0 end )"
                 +" as performance ,t.rectarget,t.periodic from applicationstart a  inner join target t on a.teamname = t.teamname and DATE(a.updated_at)<= t.enddate"
                 +" inner join user u on a.recruiteremail = u.email where (t.startdate between DATE_SUB(CURDATE(), INTERVAL :day DAY) and  CURDATE()) and a.teamname=:teamname"
                 +" group by a.recruiteremail order by performance desc,duration asc limit 3";


		 private static final String GET_LESS_RECRUITERS="select concat(u.firstname,' ',u.lastname) as name,u.email,time_to_sec(timediff(a.updated_at,concat(t.startdate,' 00:00:00'))) as duration,sum(case when(a.status='Hired') then 1 else 0 end )"
                 +" as performance ,t.rectarget,t.periodic from applicationstart a  inner join target t on a.teamname = t.teamname and DATE(a.updated_at)<= t.enddate"
                 +" inner join user u on a.recruiteremail = u.email where (t.startdate between DATE_SUB(CURDATE(), INTERVAL :day DAY) and  CURDATE()) and a.teamname=:teamname"
                 +" group by a.recruiteremail order by performance asc,duration desc limit 3";
		 
		 private static final String GET_LESS_RECRUITERS_TILL="select concat(u.firstname,' ',u.lastname) as name,u.email,time_to_sec(timediff(a.updated_at,concat(t.startdate,' 00:00:00'))) as duration,sum(case when(a.status='Hired') then 1 else 0 end )"
                 +" as performance ,t.rectarget,t.periodic from applicationstart a  inner join target t on a.teamname = t.teamname and DATE(a.updated_at)<= t.enddate"
                 +" inner join user u on a.recruiteremail = u.email where (t.startdate between :startDate and CURDATE()) and u.roleid=2 and a.teamname=:teamname"
                 +" group by a.recruiteremail order by performance asc,duration desc limit 3";
		
		  
		  @Autowired
		  private JdbcTemplate getJdbcTemplate;
		  
		  @Autowired
		  private NamedParameterJdbcTemplate namedparameterjdbctemplate;
	
	
	@Override
	public List<Map<String,Object>> sourceAnalytics(int day,String recruiteremail) {
		logger.info("day:"+day);
		logger.info("recruiteremail:"+recruiteremail);
		MapSqlParameterSource pss = new MapSqlParameterSource();
	    pss.addValue("day",day );
	    pss.addValue("recruiteremail",recruiteremail );
		List<Map<String, Object>> countSourceAnalytics=namedparameterjdbctemplate.queryForList(COUNT_SOURCE_ANALYTICS_PER_RECRUITER,pss);
		return countSourceAnalytics;

	}

	@Override
	public List<Map<String, Object>> sourceAnalyticsAllRecruiters(int day,String teamname) {
		logger.info("day:"+day);
		logger.info("teamname:"+teamname);
		MapSqlParameterSource pss = new MapSqlParameterSource();
	    pss.addValue("day",day );
	    pss.addValue("teamname",teamname );
		List<Map<String, Object>> countSourceAnalytics=namedparameterjdbctemplate.queryForList(COUNT_SOURCE_ANALYTICS_ALL_RECRUITERS,pss);
		
		return countSourceAnalytics;
	}



	


	@Override
	public List<Map<String, Object>> sourceAnalyticsTillAllRecruiter(String teamname) {
		 logger.info("teamname:"+teamname);
		 MapSqlParameterSource pss = new MapSqlParameterSource();
	     pss.addValue("teamname",teamname );
	     List<Map<String, Object>> countSourceAnalytics=namedparameterjdbctemplate.queryForList(COUNT_SOURCE_ANALYTICS_TILL_NOW_ALL_RECRUITERS,pss);
	      return countSourceAnalytics;

	}



	@Override
	public List<Map<String, Object>> sourceAnalyticsTillPerRecruiter(String recruiteremail) {
		 logger.info("recruiteremail:"+recruiteremail);
		 MapSqlParameterSource pss = new MapSqlParameterSource();
	     pss.addValue("recruiteremail",recruiteremail );
	     List<Map<String, Object>> countSourceAnalytics=namedparameterjdbctemplate.queryForList(COUNT_SOURCE_ANALYTICS_TILL_NOW_PER_RECRUITERS,pss);
	      return countSourceAnalytics;
	}



	@Override
	public List<Map<String, Object>> statusAnalytics(int day,String recruiteremail) {
		logger.info("day:"+day);
		logger.info("recruiteremail:"+recruiteremail);
		MapSqlParameterSource pss = new MapSqlParameterSource();
	    pss.addValue("day",day );
	    pss.addValue("recruiteremail",recruiteremail );
		List<Map<String, Object>> countstatusAnalytics=namedparameterjdbctemplate.queryForList(COUNT_STATUS_ANALYTICS_PER_RECRUITER,pss);
		return countstatusAnalytics;
	}



	@Override
	public List<Map<String, Object>> statusAnalyticsAllRecruiters(int day,String teamname) {
		logger.info("day:"+day);
		logger.info("teamname:"+teamname);
		MapSqlParameterSource pss = new MapSqlParameterSource();
	    pss.addValue("day",day );
	    pss.addValue("teamname",teamname );
		List<Map<String, Object>> countStatusAnalytics=namedparameterjdbctemplate.queryForList(COUNT_STATUS_ANALYTICS_ALL_RECRUITERS,pss);
		return countStatusAnalytics;
	}



	@Override
	public List<Map<String, Object>> statusAnalyticsTillPerRecruiter(String recruiteremail) {
		 logger.info("recruiteremail:"+recruiteremail);
		 MapSqlParameterSource pss = new MapSqlParameterSource();
	     pss.addValue("recruiteremail",recruiteremail );
	     List<Map<String, Object>> countStatusAnalytics=namedparameterjdbctemplate.queryForList(COUNT_STATUS_ANALYTICS_TILL_NOW_PER_RECRUITERS,pss);
	     logger.info("statusTillPer:"+countStatusAnalytics.get(0));
	     return countStatusAnalytics;
	}



	@Override
	public List<Map<String, Object>> statusAnalyticsTillAllRecruiters(String teamname) {
		 logger.info("teamname:"+teamname);
		 MapSqlParameterSource pss = new MapSqlParameterSource();
	     pss.addValue("teamname",teamname );
	     List<Map<String, Object>> countStatusAnalytics=namedparameterjdbctemplate.queryForList(COUNT_STATUS_ANALYTICS_TILL_NOW_ALL_RECRUITERS,pss);
	     logger.info("statusTillAll:"+countStatusAnalytics.get(0));
	     return countStatusAnalytics;
}



	@Override
	public List<Map<String, Object>> applicationInProcessNew(int day,String recruiteremail) {
		logger.info("day:"+day);
		logger.info("recruiteremail:"+recruiteremail);
		MapSqlParameterSource pss = new MapSqlParameterSource();
	    pss.addValue("day",day );
	    pss.addValue("recruiteremail",recruiteremail );
		List<Map<String, Object>> countapplicationInProcessNew=namedparameterjdbctemplate.queryForList(COUNT_APPLICATION_IN_PROCESS_NEW_PER_RECRUITER,pss);
		return countapplicationInProcessNew;
	}



	@Override
	public List<Map<String, Object>> applicationInProcessNewAllRecruiters(int day, String teamname) {
		
		logger.info("day:"+day);
		logger.info("teamname:"+teamname);
		MapSqlParameterSource pss = new MapSqlParameterSource();
	    pss.addValue("day",day );
	    pss.addValue("teamname",teamname );
		List<Map<String, Object>> countapplicationInProcessNew=namedparameterjdbctemplate.queryForList(COUNT_APPLICATION_IN_PROCESS_NEW_ALL_RECRUITER,pss);
		return countapplicationInProcessNew;
	}



	@Override
	public List<Map<String, Object>> applicationInProcessNewTillPerRecruiter(String recruiteremail) {
		logger.info("recruiteremail:"+recruiteremail);
		 MapSqlParameterSource pss = new MapSqlParameterSource();
	     pss.addValue("recruiteremail",recruiteremail );
	     List<Map<String, Object>> countapplicationInProcessNew=namedparameterjdbctemplate.queryForList(COUNT_APPLICATION_IN_PROCESS_NEW_TILL_NOW_PER_RECRUITERS,pss);
	     return countapplicationInProcessNew;
	}



	@Override
	public List<Map<String, Object>> applicationInProcessNewTillAllRecruiters(String teamname) {
		logger.info("teamname:"+teamname);
		 MapSqlParameterSource pss = new MapSqlParameterSource();
	     pss.addValue("teamname",teamname );
	     List<Map<String, Object>> countStatusAnalytics=namedparameterjdbctemplate.queryForList(COUNT_APPLICATION_IN_PROCESS_NEW_TILL_NOW_ALL_RECRUITER,pss);
	     return countStatusAnalytics;
	}
	
	
	@Override
	 public List<Map<String, Object>> countApplicantHiredAndTargetTillAll(String teamname) {
	   logger.info("teamname:"+teamname);
	   MapSqlParameterSource pss = new MapSqlParameterSource();
	      pss.addValue("teamname",teamname );
	      List<Map<String, Object>> countHiredAndTarget=namedparameterjdbctemplate.queryForList(COUNT_HIRED_TARGET_TILL_NOW_ALL,pss);
	      return countHiredAndTarget;
	 }


	 @Override
	 public List<Map<String, Object>> countApplicantHiredAndTargetTillPer(String recruiteremail,String teamname) {
	      logger.info("recruiteremail:"+recruiteremail);
	      logger.info("teamname:"+teamname);
	      MapSqlParameterSource pss = new MapSqlParameterSource();
	      pss.addValue("recruiteremail",recruiteremail );
	      pss.addValue("teamname",teamname );
	      List<Map<String, Object>> countHiredAndTarget=namedparameterjdbctemplate.queryForList(COUNT_HIRED_TARGET_TILL_NOW_PER,pss);
	      return countHiredAndTarget;
	 }

	 @Override
	 public List<Map<String, Object>> countApplicantHiredAndTarget(int day,String recruiteremail,String teamname) {
	      logger.info("day:"+day);
	      logger.info("recruiteremail:"+recruiteremail);
	      logger.info("teamname:"+teamname);
	      MapSqlParameterSource pss = new MapSqlParameterSource();
	      pss.addValue("day",day );
	      pss.addValue("recruiteremail",recruiteremail );
	      pss.addValue("teamname",teamname );
	     List<Map<String, Object>> countapplicationHiredAndNew=namedparameterjdbctemplate.queryForList(COUNT_HIRED_TARGET_PER,pss);
	     return countapplicationHiredAndNew;
	 }

	 @Override
	 public List<Map<String, Object>> countApplicantHiredAndTargetAll(int day,String teamname) {
	  logger.info("day:"+day);
	  logger.info("teamname:"+teamname);
	  MapSqlParameterSource pss = new MapSqlParameterSource();
	     pss.addValue("day",day );
	     pss.addValue("teamname",teamname );
	  List<Map<String, Object>> countapplicationHiredAndNew=namedparameterjdbctemplate.queryForList(COUNT_HIRED_TARGET_ALL,pss);
	  return countapplicationHiredAndNew;
	 }



	@Override
	public  Target getDetailsByTeam(String teamname) {
		  logger.info("teamname :"+teamname);
		  Target target=null;
		  try{
			  target = (Target)getJdbcTemplate.queryForObject(GET_DETAILS_BY_TEAMNAME, new Object[] { teamname },new BeanPropertyRowMapper(Target.class));
		  }catch(EmptyResultDataAccessException e){
		   return null;
		  }catch(Exception e){
			    logger.error("Exception is: "+e);
		  }
		  return target;        
}



	@Override
	public List<Map<String, Object>> topRecruiters(String teamname, int day) {
		 logger.info("day:"+day);
		  logger.info("teamname:"+teamname);
		  MapSqlParameterSource pss = new MapSqlParameterSource();
		  pss.addValue("day",day );
		  pss.addValue("teamname",teamname );
		  List<Map<String, Object>> countapplicationHiredAndNew=namedparameterjdbctemplate.queryForList(GET_TOP_RECRUITERS_DAYS,pss);
		  return countapplicationHiredAndNew;
	}



	@Override
	public List<Map<String, Object>> topRecruitersTillNow(Date startDate,String teamname) {
	  logger.info("startDate"+startDate);
	  logger.info("teamname :"+teamname);
	  MapSqlParameterSource pss = new MapSqlParameterSource();
	  pss.addValue("startDate",startDate );
	  pss.addValue("teamname",teamname );
	  List<Map<String, Object>> countapplicationHiredAndNew=namedparameterjdbctemplate.queryForList(GET_TOP_RECRUITERS_TILL,pss);
	  return countapplicationHiredAndNew;
	}



	@Override
	public List<Map<String, Object>> lessRecruiters(String teamname, int day) {
		logger.info("day:"+day);
		  logger.info("teamname:"+teamname);
		  MapSqlParameterSource pss = new MapSqlParameterSource();
		  pss.addValue("day",day );
		  pss.addValue("teamname",teamname );
		  List<Map<String, Object>> countapplicationHiredAndNew=namedparameterjdbctemplate.queryForList(GET_LESS_RECRUITERS,pss);
		  return countapplicationHiredAndNew;
	}



	@Override
	public List<Map<String, Object>> lessRecruitersTillNow(Date startDate,String teamname) {
		  logger.info("startDate"+startDate);
		  logger.info("teamname"+teamname);
		  MapSqlParameterSource pss = new MapSqlParameterSource();
		  pss.addValue("startDate",startDate );
		  pss.addValue("teamname",teamname );
		  List<Map<String, Object>> countapplicationHiredAndNew=namedparameterjdbctemplate.queryForList(GET_LESS_RECRUITERS_TILL,pss);
		  return countapplicationHiredAndNew;
	}



	@Override
	public List<Map<String, Object>> countJobOpenClosed(int day, String recruiteremail) {
		logger.info("day:"+day);
		logger.info("recruiteremail:"+recruiteremail);
		MapSqlParameterSource pss = new MapSqlParameterSource();
	    pss.addValue("day",day );
	    pss.addValue("recruiteremail",recruiteremail );
		List<Map<String, Object>> countapplicationInProcessNew=namedparameterjdbctemplate.queryForList(COUNT_JOBS_OPEN_CLOSED_PER_RECRUITER,pss);
		return countapplicationInProcessNew;
	}



	@Override
	public List<Map<String, Object>> countJobOpenClosedAllRecruiters(int day, String teamname) {
		logger.info("day:"+day);
		logger.info("teamname:"+teamname);
		MapSqlParameterSource pss = new MapSqlParameterSource();
	    pss.addValue("day",day );
	    pss.addValue("teamname",teamname );
		List<Map<String, Object>> countapplicationInProcessNew=namedparameterjdbctemplate.queryForList(COUNT_JOBS_OPEN_CLOSED_ALL_RECRUITER,pss);
		return countapplicationInProcessNew;
	}



	@Override
	public List<Map<String, Object>> countJobOpenClosedTillPerRecruiters(String recruiteremail) {
		logger.info("recruiteremail:"+recruiteremail);
		 MapSqlParameterSource pss = new MapSqlParameterSource();
	     pss.addValue("recruiteremail",recruiteremail );
	     List<Map<String, Object>> countapplicationInProcessNew=namedparameterjdbctemplate.queryForList(COUNT_JOBS_OPEN_CLOSED_TILLNOW_PER_RECRUITER,pss);
	     return countapplicationInProcessNew;
	}



	@Override
	public List<Map<String, Object>> countJobOpenClosedTillAllRecruiters(String teamname) {
		logger.info("teamname:"+teamname);
		 MapSqlParameterSource pss = new MapSqlParameterSource();
	     pss.addValue("teamname",teamname );
	     List<Map<String, Object>> countStatusAnalytics=namedparameterjdbctemplate.queryForList(COUNT_JOBS_OPEN_CLOSED_TILLNOW_ALL_RECRUITERS,pss);
	     return countStatusAnalytics;
	}



	
}	
	
