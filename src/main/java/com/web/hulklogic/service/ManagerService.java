package com.web.hulklogic.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.web.hulklogic.entity.Target;

public interface ManagerService {
	public List<Map<String, Object>> sourceAnalytics(int day, String recruiteremail);

	public List<Map<String, Object>> sourceAnalyticsAllRecruiters(int day, String teamname);

	public List<Map<String, Object>> sourceAnalyticsTillAllRecruiter(String teamname);

	public List<Map<String, Object>> sourceAnalyticsTillPerRecruiter(String recruiteremail);

	public List<Map<String, Object>> statusAnalytics(int day, String recruiteremail);

	public List<Map<String, Object>> statusAnalyticsAllRecruiters(int day, String teamname);

	public List<Map<String, Object>> statusAnalyticsTillPerRecruiter(String recruiteremail);

	public List<Map<String, Object>> statusAnalyticsTillAllRecruiters(String teamname);

	public List<Map<String, Object>> applicationInProcessNew(int day, String recruiteremail);

	public List<Map<String, Object>> applicationInProcessNewAllRecruiters(int day, String teamname);

	public List<Map<String, Object>> applicationInProcessNewTillPerRecruiter(String recruiteremail);

	public List<Map<String, Object>> applicationInProcessNewTillAllRecruiters(String teamname);

	public List<Map<String, Object>> countApplicantHiredAndTargetTillAll(String teamname);

	public List<Map<String, Object>> countApplicantHiredAndTargetTillPer(String recruiteremail, String teamname);

	public List<Map<String, Object>> countApplicantHiredAndTarget(int day, String recruiteremail, String teamname);

	public List<Map<String, Object>> countApplicantHiredAndTargetAll(int day, String teamname);

	public Target getDetailsByTeam(String teamname);

	public List<Map<String, Object>> topRecruiters(String teamname, int day);

	public List<Map<String, Object>> topRecruitersTillNow(Date startDate, String teamname);

	public List<Map<String, Object>> lessRecruiters(String teamname, int day);

	public List<Map<String, Object>> lessRecruitersTillNow(Date startDate, String teamname);

	public List<Map<String, Object>> countJobOpenClosed(int day, String recruiteremail);

	public List<Map<String, Object>> countJobOpenClosedAllRecruiters(int day, String teamname);

	public List<Map<String, Object>> countJobOpenClosedTillPerRecruiters(String recruiteremail);

	public List<Map<String, Object>> countJobOpenClosedTillAllRecruiters(String teamname);

}
