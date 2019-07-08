package com.web.hulklogic.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.hulklogic.dao.RecruiterDashBordDAO;
import com.web.hulklogic.entity.Applicants;
import com.web.hulklogic.entity.CreateJob;
import com.web.hulklogic.entity.User;
import com.web.hulklogic.service.RecruiterDashBordService;


@Service
public class RecruiterDashBordServiceImpl implements RecruiterDashBordService {

	@Autowired
	private RecruiterDashBordDAO recruiterDashBordDao;

	@Override
	public List<Map<String, Object>> sourceAnalytics(int day, String recruiteremail) {

		return recruiterDashBordDao.sourceAnalytics(day, recruiteremail);
	}

	
	@Override
	public List<Map<String, Object>> sourceAnalyticsTillPerRecruiter(String recruiteremail) {

		return recruiterDashBordDao.sourceAnalyticsTillPerRecruiter(recruiteremail);
	}

	@Override
	public List<Map<String, Object>> statusAnalytics(int day, String recruiteremail) {

		return recruiterDashBordDao.statusAnalytics(day, recruiteremail);
	}

	@Override
	public List<Map<String, Object>> statusAnalyticsTillPerRecruiter(String recruiteremail) {

		return recruiterDashBordDao.statusAnalyticsTillPerRecruiter(recruiteremail);
	}

	@Override
	public List<Map<String, Object>> getCountJobOpenClosed(int day, String recruiteremail) {
		return recruiterDashBordDao.getCountJobOpenClosed(day, recruiteremail);
	}

	@Override
	public List<Map<String, Object>> getCountJobOpenClosedTillNow(String recruiteremail) {
		return recruiterDashBordDao.getCountJobOpenClosedTillNow(recruiteremail);
	}

	@Override
	public List<Map<String, Object>> applicationInProcessNew(int day, String recruiteremail) {
		return recruiterDashBordDao.applicationInProcessNew(day, recruiteremail);
	}

	@Override
	public List<Map<String, Object>> applicationInProcessNewTillPerRecruiter(String recruiteremail) {
		return recruiterDashBordDao.applicationInProcessNewTillPerRecruiter(recruiteremail);
	}

	@Override
	public List<Map<String, Object>> countApplicantHiredAndTarget(int day, String recruiteremail, String teamname) {
		return recruiterDashBordDao.countApplicantHiredAndTarget(day, recruiteremail, teamname);
	}

	@Override
	public List<Map<String, Object>> countApplicantHiredAndTargetTillPer(String recruiteremail, String teamname) {
		return recruiterDashBordDao.countApplicantHiredAndTargetTillPer(recruiteremail, teamname);
	}

	@Override
	public List<User> getTeamMemeberDetails(String teamname, int userid) {

		return recruiterDashBordDao.getTeamMemeberDetails(teamname, userid);
	}

	@Override
	public List<CreateJob> getAllRecruiterJobs(String email) {
		return recruiterDashBordDao.getAllRecruiterJobs(email);
	}

	@Override
	public List<CreateJob> getJobTitleByRecruiter(String email) {
		return recruiterDashBordDao.getJobTitleByRecruiter(email);
	}

	@Override
	public List<Applicants> getApplicationByRecruiter(int jobId, String email) {

		return recruiterDashBordDao.getApplicationByRecruiter(jobId, email);
	}

}
