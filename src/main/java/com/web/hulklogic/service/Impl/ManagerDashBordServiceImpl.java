package com.web.hulklogic.service.Impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.hulklogic.dao.ManagerDashBordDAO;
import com.web.hulklogic.dao.UserDAO;
import com.web.hulklogic.entity.User;
import com.web.hulklogic.service.ManagerDashBordService;

@Service
public class ManagerDashBordServiceImpl implements ManagerDashBordService {

	@Autowired
	private ManagerDashBordDAO managerDashBordDao;
	@Autowired
	private UserDAO userdao;

	@Override
	public List<Map<String, Object>> getCountValues() {
		return managerDashBordDao.getCountValues();
	}

	@Override
	public List<Map<String, Object>> getCountApplicantDays() {
		return managerDashBordDao.getCountApplicantDays();
	}

	@Override
	public List<Map<String, Object>> getCountStatus() {
		return managerDashBordDao.getCountStatus();
	}

	@Override
	public List<Map<String, Object>> getCountSource() {
		return managerDashBordDao.getCountSource();
	}

	@Override
	public List<Map<String, Object>> getCountHired() {
		return managerDashBordDao.getCountHired();
	}

	@Override
	public List<Map<String, Object>> getTopRecruiterApp() {
		return managerDashBordDao.getTopRecruiterApp();
	}

	@Override
	public List<User> getRecruitereNamesByAutoComplate(String recruitername) {
		return managerDashBordDao.getRecruitereNamesByAutoComplate(recruitername);
	}

	@Override
	public List<Map<String, Object>> getRecruiterPerformance(String email, String month, String year) {
		return managerDashBordDao.getRecruiterPerformance(email, month, year);
	}

	@Override
	public List<User> getManagerNamesByAutoComplete(String managername) {

		return managerDashBordDao.getManagerNamesByAutoComplete(managername);
	}

}
