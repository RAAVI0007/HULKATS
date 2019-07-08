package com.web.hulklogic.dao;

import java.util.List;
import java.util.Map;

import com.web.hulklogic.entity.Target;
import com.web.hulklogic.entity.User;

public interface DashBordDAO {

	public List<Map<String, Object>> countApplicantHiredAndTarget();

	public List<Map<String, Object>> countJobsOpenAndTotal(String teamname);

	public List<Map<String, Object>> countApplicationInprocessAndNew();

	public List<Map<String, Object>> recruiterCountHiredThirty();

	public List<Map<String, Object>> recruiterCountHiredSeven();

	public List<Map<String, Object>> recruiterCountHiredTillnow();

	public List<Map<String, Object>> sourceAnalyticsThirty();

	public List<Map<String, Object>> sourceAnalyticsSeven();

	public List<Map<String, Object>> sourceAnalyticsTillNow();

	public List<Map<String, Object>> recruiterHiredBacklogsThirty();

	public List<Map<String, Object>> recruiterHiredBacklogsSeven();

	public List<Map<String, Object>> recruiterHiredBacklogsTillNow();

	public List<Map<String, Object>> statusAnalyticsThirty();

	public List<Map<String, Object>> statusAnalyticsSeven();

	public List<Map<String, Object>> statusAnalyticsTillNow();

	public int targetcreation(Target target);

	public int AssignManager(int userid, String teamname);

	public List<Map<String, Object>> getLiveUser(int userid, String teamname);

	public List<User> getRecruitereNamesByAutoComplate(String teamname);

	public List<User> email(int userid, String teamname);

}
