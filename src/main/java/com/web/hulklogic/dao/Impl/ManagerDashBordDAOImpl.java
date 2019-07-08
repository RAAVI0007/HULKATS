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
import com.web.hulklogic.dao.ManagerDashBordDAO;
import com.web.hulklogic.entity.User;

@Repository

public class ManagerDashBordDAOImpl  implements  ManagerDashBordDAO{
	
	private static Logger logger = Logger.getLogger(ManagerDashBordDAOImpl.class);
	
	private static final String COUNT_TABLE_VALUES="select a.jobs_count,b.applicant_count,c.applicantion_count,d.recuriter_count from (select count(1)"
		+ " as jobs_count from job ) a, (select count(1) as  applicant_count from  applicant ) b,"
		+ " (select count(1) as applicantion_count from application ) c,(select count(1)"
		+ " as recuriter_count from user t  where roleid!=1 and status=1) d ";
	private static final String COUNT_APPLICANT_DAYS="select a.applicant_countthree,b.applicant_countseven,c. applicant_countthirty,d.applicant_countthree_status,e.applicant_countseven_status,f.applicant_countthirty_status  from"
			  + " (select count(1) as  applicant_countthree  from  applicant   WHERE created_at >= DATE_SUB(CURDATE(), INTERVAL 3 DAY) ORDER BY created_at DESC ) a, "
			  + " (select count(1) as  applicant_countseven from  applicant   WHERE created_at >= DATE_SUB(CURDATE(), INTERVAL 7 DAY) ORDER BY created_at DESC) b,"
			  + " (select count(1) as  applicant_countthirty  from  applicant   WHERE created_at >= DATE_SUB(CURDATE(), INTERVAL 30 DAY) ORDER BY created_at DESC) c ,"
			  +"  (select count(1) as  applicant_countthree_status  from  application WHERE updated_at >= DATE_SUB(CURDATE(), INTERVAL 3 DAY) and status='Hired' ORDER BY updated_at DESC) d,"
			  +"  (select count(1) as  applicant_countseven_status  from  application WHERE updated_at >= DATE_SUB(CURDATE(), INTERVAL 7 DAY) and status='Hired' ORDER BY updated_at DESC) e,"
			  +"  (select count(1) as  applicant_countthirty_status from  application WHERE updated_at >= DATE_SUB(CURDATE(), INTERVAL 30 DAY) and status='Hired' ORDER BY updated_at DESC) f ";
	 private static final String COUNT_STATUS= "SELECT STATUS,count(STATUS) AS stat" + 
	    		" FROM hulkats.application" + 
	    		" GROUP BY status" ;
	    		
	private static final String COUNT_SOURCE="select a.facebook,b.indeed ,c.monster from "
			   + " (select count(1) as  facebook from application  where source='facebook' ) a, "
			   +"  (select count(1) as  indeed   from application  where source='indeed') b,"
			   + " (select count(1) as  monster   from application  where source='monster' ) c";
			 
    private static final String COUNT_HIRED="select a.facebook_Hired,b.indeed_Hired,c.monster_Hired from "
			   +"  (select count(1) as facebook_Hired    from application  where status='Hired' and source='facebook') a ,"
			   +"  (select count(1) as indeed_Hired      from application  where status='Hired' and source='indeed') b,"
			   +"  (select count(1) as monster_Hired    from application  where status='Hired' and source='monster') c";
			 
    private static final String GET_TOP_RECRUITER="select concat(firstname,' ',lastname) as Name, count(a.status) as hired from application a inner join user on email = recuiteremail  where a.status = 'Hired' group by recuiteremail order by hired desc limit 3";
    private static final String AUTO_COMPLETE_RECRUITER_NAMES="select CONCAT(firstname, ' ', lastname,'[',email,']') as name, userid,email from user where roleid=2 and firstname like:pname  limit 10";
    
    private static final String RECRUITER_PERFORMANCE = "SELECT STATUS,count(STATUS) AS Awaiting_Review" + 
    		" FROM hulkats.application" + 
    		" WHERE recuiteremail = :email" + 
    		"  AND year(updated_at) = :year" + 
    		"  AND month(updated_at) = :month" + 
    		" GROUP BY status,year(updated_at)" + 
    		"  ,month(updated_at)"; 
    private static final String AUTO_COMPLETE_MANAGER_NAMES="select CONCAT(firstname, ' ', lastname,' [',email,']') as name, userid,email from user where roleid=1 and firstname like:pname  limit 10";
   
    
	@Autowired
	private JdbcTemplate getJdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedparameterjdbctemplate;

	@Override
	public List<Map<String, Object>> getCountValues() {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		List<Map<String, Object>> data=getJdbcTemplate.queryForList(COUNT_TABLE_VALUES);
		return data;
	}
	@Override
	 public List<Map<String, Object>> getCountApplicantDays() {
	  List<Map<String, Object>> countjobsdays=getJdbcTemplate.queryForList(COUNT_APPLICANT_DAYS);
	  return countjobsdays;
	 }
	@Override
	 public List<Map<String, Object>> getCountStatus() {
	  List<Map<String, Object>> countstatus=getJdbcTemplate.queryForList(COUNT_STATUS);
	  return countstatus;
	 }
	@Override
	 public List<Map<String, Object>> getCountSource() {
	  List<Map<String, Object>> countsource=getJdbcTemplate.queryForList(COUNT_SOURCE);
	  return countsource;
	 }

	 @Override
	 public List<Map<String, Object>> getCountHired() {
	  List<Map<String, Object>> counthired=getJdbcTemplate.queryForList(COUNT_HIRED);
	  return counthired;
	 }
	 @Override
	 public List<Map<String, Object>> getTopRecruiterApp() {
	  List<Map<String, Object>> getTopRecruiter=getJdbcTemplate.queryForList(GET_TOP_RECRUITER);
	  return getTopRecruiter;
	 }
	 
	 @Override
	 public List<User> getRecruitereNamesByAutoComplate(String recruitername) {
	      logger.info("recruitername:"+recruitername);
	      String name=recruitername + "%";
	      MapSqlParameterSource pss = new MapSqlParameterSource();
	      pss.addValue("pname",name );
	      List<User> getRecruitername  = namedparameterjdbctemplate.query(AUTO_COMPLETE_RECRUITER_NAMES, pss,new BeanPropertyRowMapper(User.class));
	     
	      return getRecruitername;
	 }
	
	 
	 @Override
	 public List<Map<String, Object>> getRecruiterPerformance(String email,String month,String year) {
	  logger.info(" email:"+email);
	  logger.info("month:"+month); 
	  logger.info("year:"+year); 
	  MapSqlParameterSource pss = new MapSqlParameterSource();
	  pss.addValue("email",email );
	  pss.addValue("month",month );
	  pss.addValue("year",year );
	  List<Map<String, Object>> getRecruiterPerformance=namedparameterjdbctemplate.queryForList(RECRUITER_PERFORMANCE,pss);
	  return getRecruiterPerformance;
}
	 
	 @Override
	 public List<User> getManagerNamesByAutoComplete(String managername) {
	    logger.info("managername:"+managername);
	       String name=managername + "%";
	       MapSqlParameterSource pss = new MapSqlParameterSource();
	       pss.addValue("pname",name );
	       List<User> getRecruitername  = namedparameterjdbctemplate.query(AUTO_COMPLETE_MANAGER_NAMES, pss,new BeanPropertyRowMapper(User.class));
	       return getRecruitername;
	 }
	  
	 
	 
	}
	
