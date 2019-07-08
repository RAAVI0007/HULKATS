package com.web.hulklogic.service.Impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.hulklogic.dao.ManagerDAO;
import com.web.hulklogic.entity.Target;
import com.web.hulklogic.service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService {

	private static Logger logger = Logger.getLogger(ManagerServiceImpl.class);

	@Autowired
	private ManagerDAO managerDao;

	@Override
	public List<Map<String, Object>> sourceAnalytics(int day, String recruiteremail) {
		return managerDao.sourceAnalytics(day, recruiteremail);
	}

	@Override
	public List<Map<String, Object>> sourceAnalyticsAllRecruiters(int day, String teamname) {
		return managerDao.sourceAnalyticsAllRecruiters(day, teamname);
	}

	@Override
	public List<Map<String, Object>> sourceAnalyticsTillAllRecruiter(String teamname) {
		return managerDao.sourceAnalyticsTillAllRecruiter(teamname);
	}

	@Override
	public List<Map<String, Object>> sourceAnalyticsTillPerRecruiter(String recruiteremail) {
		return managerDao.sourceAnalyticsTillPerRecruiter(recruiteremail);
	}

	@Override
	public List<Map<String, Object>> statusAnalytics(int day, String recruiteremail) {

		return managerDao.statusAnalytics(day, recruiteremail);
	}

	@Override
	public List<Map<String, Object>> statusAnalyticsAllRecruiters(int day, String teamname) {

		return managerDao.statusAnalyticsAllRecruiters(day, teamname);
	}

	@Override
	public List<Map<String, Object>> statusAnalyticsTillPerRecruiter(String recruiteremail) {
		return managerDao.statusAnalyticsTillPerRecruiter(recruiteremail);
	}

	@Override
	public List<Map<String, Object>> statusAnalyticsTillAllRecruiters(String teamname) {
		return managerDao.statusAnalyticsTillAllRecruiters(teamname);
	}

	@Override
	public List<Map<String, Object>> applicationInProcessNew(int day, String recruiteremail) {
		return managerDao.applicationInProcessNew(day, recruiteremail);
	}

	@Override
	public List<Map<String, Object>> applicationInProcessNewAllRecruiters(int day, String teamname) {
		System.out.println("so");
		return managerDao.applicationInProcessNewAllRecruiters(day, teamname);
	}

	@Override
	public List<Map<String, Object>> applicationInProcessNewTillPerRecruiter(String recruiteremail) {
		return managerDao.applicationInProcessNewTillPerRecruiter(recruiteremail);
	}

	@Override
	public List<Map<String, Object>> applicationInProcessNewTillAllRecruiters(String teamname) {
		return managerDao.applicationInProcessNewTillAllRecruiters(teamname);
	}

	@Override
	public List<Map<String, Object>> countApplicantHiredAndTargetTillAll(String teamname) {

		return managerDao.countApplicantHiredAndTargetTillAll(teamname);
	}

	@Override
	public List<Map<String, Object>> countApplicantHiredAndTargetTillPer(String recruiteremail, String teamname) {

		return managerDao.countApplicantHiredAndTargetTillPer(recruiteremail, teamname);
	}

	@Override
	public List<Map<String, Object>> countApplicantHiredAndTarget(int day, String recruiteremail, String teamname) {

		return managerDao.countApplicantHiredAndTarget(day, recruiteremail, teamname);
	}

	@Override
	public List<Map<String, Object>> countApplicantHiredAndTargetAll(int day, String teamname) {

		return managerDao.countApplicantHiredAndTargetAll(day, teamname);
	}

	@Override
	public Target getDetailsByTeam(String teamname) {
		return managerDao.getDetailsByTeam(teamname);
	}

	@Override
	public List<Map<String, Object>> topRecruiters(String teamname, int day) {
		// TODO Auto-generated method stub
		return managerDao.topRecruiters(teamname, day);
	}

	@Override
	public List<Map<String, Object>> topRecruitersTillNow(Date startDate, String teamname) {

		return managerDao.topRecruitersTillNow(startDate, teamname);
	}

	@Override
	public List<Map<String, Object>> lessRecruiters(String teamname, int day) {
		return managerDao.lessRecruiters(teamname, day);
	}

	@Override
	public List<Map<String, Object>> lessRecruitersTillNow(Date startDate, String teamname) {
		return managerDao.lessRecruitersTillNow(startDate, teamname);
	}

	@Override
	public List<Map<String, Object>> countJobOpenClosed(int day, String recruiteremail) {
		// TODO Auto-generated method stub
		return managerDao.countJobOpenClosed(day, recruiteremail);
	}

	@Override
	public List<Map<String, Object>> countJobOpenClosedAllRecruiters(int day, String teamname) {
		// TODO Auto-generated method stub
		return managerDao.countJobOpenClosedAllRecruiters(day, teamname);
	}

	@Override
	public List<Map<String, Object>> countJobOpenClosedTillPerRecruiters(String recruiteremail) {
		// TODO Auto-generated method stub
		return managerDao.countJobOpenClosedTillPerRecruiters(recruiteremail);
	}

	@Override
	public List<Map<String, Object>> countJobOpenClosedTillAllRecruiters(String teamname) {
		// TODO Auto-generated method stub
		return managerDao.countJobOpenClosedTillAllRecruiters(teamname);
	}
}
