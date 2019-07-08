package com.web.hulklogic.dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.web.hulklogic.dao.GetDetailsDAO;
import com.web.hulklogic.entity.Applicants;
import com.web.hulklogic.entity.CreateJob;
import com.web.hulklogic.entity.GetEmployee;
import com.web.hulklogic.entity.GetTokens;
import com.web.hulklogic.entity.JobTitle;
import com.web.hulklogic.entity.ParserText;
import com.web.hulklogic.entity.User;

@Repository
public class GetDetailsDAOImpl implements GetDetailsDAO{
	private static Logger logger = Logger.getLogger(GetDetailsDAOImpl.class);
	
	private static final String GET_JOBS_INFO_BY_JOBID="select *from job where jobid=?";
	private static final String GET_APPLICANTS_BY_APPLICANTID="select *from applicant where applicantid=?";
	private static final String ENABLE_USER="update user set status=1 where userid=?";
	private static final String ENABLE_APPLICANT="update applicant set active_status=1 where applicantid=?";
	private static final String GET_USER_INFO_BY_ID="select *from user where userid=?";
    private static final String INSERT_INTO_OTP = "update user set otp =? where email =?";
    private static final String GET_PARSER_TEXT_BY_JOB_ID="select  parsertext1,parsertext2 from job where jobid=?";
    private static final String GET_PARSER_TEXT_BY_APPLICANT_ID="select parsertext1,parsertext2 from applicant where applicantid=? ";
    private static final String GET_ALL_JOBTITLE="select jobid, jobtitle from job where jobtitle like :pname";
    private static final String GET_ALL_LOCATIONS="select  jobid,location from job where location like :pname";
    private static final String GET_ALL_JOB_APPLICANT_TOKENS="select * from " + 
       "(select jobid,jobtags,jobtitle from job where jobid =?) a,"+
       "(select applicantid,"
   	      + "languages0,languages1,languages2,languages3,languages4,languages5,"
   	      + "languages6,languages7,languages8,languages9  "
          + " from applicantdetails) b";
    private static final String CHECK_JOB_ASSIGN_RECRUITER_EMAIL="select  recuiteremail from jobassign where jobid=?";
    
    private static final String CHECK_EMPLOYEE = "select email,firstname,lastname,organization,designation,employeeid from employee where designation =? and organization = ?";
    
    @Autowired
	private JdbcTemplate getJdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public CreateJob getJobDetailsByJobid(int jobid) {
		 logger.info("jobid :"+jobid);
		CreateJob createjob=null;
		 try{
			 createjob=(CreateJob) getJdbcTemplate.queryForObject(GET_JOBS_INFO_BY_JOBID,  new Object[] {jobid },new BeanPropertyRowMapper(CreateJob.class));
		
		 }catch(EmptyResultDataAccessException e){
			 return null;
		 }catch(Exception e){
			    logger.error("Exception is: "+e);
		  }
		 return createjob;
	}

	
	
	@Override
	public Applicants getApplicantsByApplicatId(int applicantid) {
		 logger.info("applicantid :"+applicantid);
		Applicants applicants=null;
		 try{
			 applicants=(Applicants) getJdbcTemplate.queryForObject(GET_APPLICANTS_BY_APPLICANTID,  new Object[] {applicantid },new BeanPropertyRowMapper(Applicants.class));
		
		 }catch(EmptyResultDataAccessException e){
			 return null;
		 }catch(Exception e){
			    logger.error("Exception is: "+e);
		  }
		 return applicants;
	}

	@Override
	public int enableUser(int userid) {
		logger.info("userid :"+userid);
		int count=getJdbcTemplate.update(ENABLE_USER,userid);
		return count;
	}
	
	@Override
	public int enableApplicant(int applicantid){
		logger.info("applicantid :"+applicantid);
		int count=getJdbcTemplate.update(ENABLE_APPLICANT,applicantid);
		return count;
		
	}

	
	@Override
	public User getUserByUserId(int userid) {
		logger.info("userid :"+userid);
		User user=null;
		try{
	    user=(User) getJdbcTemplate.queryForObject(GET_USER_INFO_BY_ID, new Object[] { userid },new BeanPropertyRowMapper(User.class));
	   
		}catch(EmptyResultDataAccessException e){
		return null;
		}catch(Exception e){
		    logger.error("Exception is: "+e);
	  }
		 return user;
}

	@Override
	public int saveOtp(User user) {
		return getJdbcTemplate.update(INSERT_INTO_OTP, user.getOtp(),user.getEmail());
	}
	
	
	@Override
	  public List<ParserText> getParserTextByApplicantId(int applicantid) {
		logger.info("applicantid :"+applicantid);
	   List<ParserText>  parsertext=getJdbcTemplate.query(GET_PARSER_TEXT_BY_APPLICANT_ID, new Object[] { applicantid },new BeanPropertyRowMapper(ParserText.class));
	   return parsertext;
	 }

	 
	 @Override
	  public List<ParserText> getParserTextByJobId(int jobid) {
		 logger.info("jobid :"+jobid);
	   List<ParserText>  parsertext=getJdbcTemplate.query(GET_PARSER_TEXT_BY_JOB_ID, new Object[] { jobid },new BeanPropertyRowMapper(ParserText.class));
	   return parsertext;
	 }
	
	
	
	@Override
	 public List<JobTitle> getJobTitleOfAutoComplete(String jobtitle) {
	  logger.info("jobtitle:"+jobtitle);
	  String finalName=jobtitle + "%";
	  MapSqlParameterSource pss = new MapSqlParameterSource();
	  pss.addValue("pname",finalName );
	  List<JobTitle> getAutoCompleteJobtitle  = namedParameterJdbcTemplate.query(GET_ALL_JOBTITLE, pss,new BeanPropertyRowMapper(JobTitle.class));
	  return getAutoCompleteJobtitle;
	  }
		 
	 @Override
	 public List<JobTitle> getLocationleOfAutoComplete(String location) {
		  logger.info("locationDao:"+location);
		  String finalName=location + "%";
		  MapSqlParameterSource pss = new MapSqlParameterSource();
		  pss.addValue("pname",finalName );
		  List<JobTitle> getAutoCompleteLocation = namedParameterJdbcTemplate.query(GET_ALL_LOCATIONS, pss,new BeanPropertyRowMapper(JobTitle.class));
		  return getAutoCompleteLocation;
		  
}
	
	 
  @Override
	 public List<GetTokens> getJobAndApplicantTokens(int jobid) {
	  logger.info("jobid :"+jobid);
	  List<GetTokens> getListTokens=null;
	  try{
	   getListTokens=getJdbcTemplate.query(GET_ALL_JOB_APPLICANT_TOKENS, new GetTokensResultSetExtractor(),jobid);
	  }catch(Exception e){
		  logger.error("Exception is: "+e);
	  }
	  return getListTokens;
	  
	 }
	 private static class GetTokensResultSetExtractor implements ResultSetExtractor<List<GetTokens>>{

	  public  List<GetTokens> extractData(ResultSet rs)throws SQLException, DataAccessException {
	   List<GetTokens> listbo=new  ArrayList<GetTokens>();
	   GetTokens getTokens=null;
	   while(rs.next()){
	    getTokens=new GetTokens();
	    getTokens.setJobtags(rs.getString("jobtags"));
	    getTokens.setLanguages0(rs.getString("languages0"));
	    getTokens.setLanguages1(rs.getString("languages1"));
	    getTokens.setLanguages2(rs.getString("languages2"));
	    getTokens.setLanguages3(rs.getString("languages3"));
	    getTokens.setLanguages4(rs.getString("languages4"));
	    getTokens.setLanguages5(rs.getString("languages5"));
	    getTokens.setLanguages6(rs.getString("languages6"));
	    getTokens.setLanguages7(rs.getString("languages7"));
	    getTokens.setLanguages8(rs.getString("languages8"));
	    getTokens.setLanguages9(rs.getString("languages9"));
	    getTokens.setApplicantid(rs.getInt("applicantid"));
	    getTokens.setJobid(rs.getInt("jobid"));
	  
	       listbo.add(getTokens);
	   }
	   
	   return listbo;
	  }
	 }
	 @Override
	 public int checkJobAssignToRecruiter(int jobid) {
		 logger.info("jobid :"+jobid);
	   List<Map<String, Object>> data = getJdbcTemplate.queryForList(CHECK_JOB_ASSIGN_RECRUITER_EMAIL,jobid);
	    int count=data.size();
	    return count;
	 }





	@Override
	public List<GetEmployee> getEmployeedetails(String designation, String organization) {
		 logger.info("designation :"+designation);
		 logger.info("organization :"+organization);
		  List<GetEmployee> getListTokens=null;
		  try{
		   getListTokens=(List<GetEmployee>) getJdbcTemplate.query(CHECK_EMPLOYEE, new GetTokensResultSetExtractors(),designation,organization);
		  }catch(Exception e){
			  logger.error("Exception is: "+e);
		  }
		  return getListTokens;
		  
		 }
		 private static class GetTokensResultSetExtractors implements ResultSetExtractor<List<GetEmployee>>{

		  public  List<GetEmployee> extractData(ResultSet rs)throws SQLException, DataAccessException {
		   List<GetEmployee> listbo=new  ArrayList<GetEmployee>();
		   GetEmployee getTokens=null;
		   while(rs.next()){
		    getTokens=new GetEmployee();
		    getTokens.setDesignation(rs.getString("designation"));
		    getTokens.setEmail(rs.getString("email"));
		    getTokens.setFirstname(rs.getString("firstname"));
		    getTokens.setLastname(rs.getString("lastname"));
		    getTokens.setOrganization(rs.getString("organization"));
		    getTokens.setEmployeeid(rs.getString("employeeid"));
		  
		       listbo.add(getTokens);
		   }
		   
		   return listbo;
		  }
		 }
	 
}
	
