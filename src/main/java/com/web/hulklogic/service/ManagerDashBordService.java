package com.web.hulklogic.service;

import java.util.List;
import java.util.Map;
import com.web.hulklogic.entity.User;

public interface ManagerDashBordService {
	public List<Map<String, Object>> getCountValues();

	public List<Map<String, Object>> getCountApplicantDays();

	public List<Map<String, Object>> getCountStatus();

	public List<Map<String, Object>> getCountSource();

	public List<Map<String, Object>> getCountHired();

	public List<Map<String, Object>> getTopRecruiterApp();

	public List<User> getRecruitereNamesByAutoComplate(String recruitername);

	public List<Map<String, Object>> getRecruiterPerformance(String email, String month, String year);

	public List<User> getManagerNamesByAutoComplete(String managername);

}
