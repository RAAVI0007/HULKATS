package com.web.hulklogic.service.Impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.hulklogic.dao.DashBordDAO;
import com.web.hulklogic.entity.User;
import com.web.hulklogic.service.DashBordService;

@Service
public class DashBordServiceImpl implements DashBordService{

	@Autowired
	private DashBordDAO dashBordDao;

	@Override
	public List<Map<String, Object>> countApplicantHired() {
		return dashBordDao.countApplicantHiredAndTarget();
	}

	

	@Override
	public List<Map<String, Object>> countApplicationInprocessAndNew() {
		
		return dashBordDao.countApplicationInprocessAndNew();
	}

	@Override
	public List<Map<String, Object>> recruiterCountHiredThirty() {
		return dashBordDao.recruiterCountHiredThirty();
	}

	@Override
	public List<Map<String, Object>> recruiterCountHiredSeven() {
		return dashBordDao.recruiterCountHiredSeven();
	}

	@Override
	public List<Map<String, Object>> recruiterCountHiredTillnow() {
	return dashBordDao.recruiterCountHiredTillnow();
	}

	@Override
	public List<Map<String, Object>> sourceAnalyticsThirty() {
		return dashBordDao.sourceAnalyticsThirty();
	}

	@Override
	public List<Map<String, Object>> sourceAnalyticsSeven() {
	
		return dashBordDao.sourceAnalyticsSeven();
	}

	@Override
	public List<Map<String, Object>> sourceAnalyticsTillNow() {
		
		return dashBordDao.sourceAnalyticsTillNow();
	}

	@Override
	public List<Map<String, Object>> recruiterHiredBacklogsThirty() {
		
		return dashBordDao.recruiterHiredBacklogsThirty();
	}

	@Override
	public List<Map<String, Object>> recruiterHiredBacklogsSeven() {
		
		return dashBordDao.recruiterHiredBacklogsSeven();
	}

	@Override
	public List<Map<String, Object>> recruiterHiredBacklogsTillNow() {
		
		return dashBordDao.recruiterHiredBacklogsTillNow();
	}

	@Override
	public List<Map<String, Object>> statusAnalyticsThirty() {
		return dashBordDao.statusAnalyticsThirty();
	}

	@Override
	public List<Map<String, Object>> statusAnalyticsSeven() {
		return dashBordDao.statusAnalyticsSeven();
	}
	@Override
	 public List<Map<String, Object>> statusAnalyticsTillNow() {
	  
	  return dashBordDao.statusAnalyticsTillNow();
	 }
	
	

	@Override
	public boolean AssignManager(int userid, String teamname) {
	    int result=dashBordDao.AssignManager(userid, teamname);
	    if(result>0) {
	    	return true;
	    }
		return false;
	}
	
	@Override
	 public List<Map<String, Object>> getLiveUser(int userid, String teamname) {
	       return dashBordDao.getLiveUser(userid, teamname);
	 }
	
	@Override
	public List<Map<String, Object>> countJobsOpenAndTotal(String teamname) {
		// TODO Auto-generated method stub
		return dashBordDao.countJobsOpenAndTotal(teamname);
	}



	@Override
	public List<User> getRecruitereNamesByAutoComplate(String teamname) {
		
		return dashBordDao.getRecruitereNamesByAutoComplate(teamname);
	}



	@Override
	public List<User> email(int userid, String teamname) {
		// TODO Auto-generated method stub
		return dashBordDao.email(userid, teamname);
	}
	
	
}
