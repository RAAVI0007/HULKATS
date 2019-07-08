package com.web.hulklogic.dao;

import java.util.List;
import java.util.Map;

import com.web.hulklogic.entity.Applicants;
import com.web.hulklogic.entity.CreateJob;
import com.web.hulklogic.entity.User;

public interface RecruiterDashBordDAO {
	public List<Map<String,Object>>  sourceAnalytics(int day,String recruiteremail);
	public List<Map<String,Object>>  sourceAnalyticsTillPerRecruiter(String recruiteremail);
	public List<Map<String,Object>>  statusAnalytics(int day,String recruiteremail);
    public List<Map<String,Object>>  statusAnalyticsTillPerRecruiter(String recruiteremail);
    public List<Map<String,Object>> getCountJobOpenClosed(int day,String recruiteremail);
    public List<Map<String,Object>> getCountJobOpenClosedTillNow(String recruiteremail);
    public List<Map<String,Object>>  applicationInProcessNew(int day,String recruiteremail);
    public List<Map<String,Object>>  applicationInProcessNewTillPerRecruiter(String recruiteremail);
    public List<Map<String,Object>> countApplicantHiredAndTarget(int day,String recruiteremail,String teamname);
    public List<Map<String,Object>> countApplicantHiredAndTargetTillPer(String recruiteremail,String teamname);
    public List<User> getTeamMemeberDetails(String teamname,int userid);
    public List<CreateJob> getAllRecruiterJobs(String email);
    public List<CreateJob> getJobTitleByRecruiter(String email);
    public List<Applicants> getApplicationByRecruiter(int jobId,String email);
}
