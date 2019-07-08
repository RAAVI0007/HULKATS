package com.web.hulklogic.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.web.hulklogic.entity.Applicants;
import com.web.hulklogic.entity.CreateJob;
import com.web.hulklogic.entity.User;
import com.web.hulklogic.service.RecruiterDashBordService;

@Controller
public class RecruiterDashBordController {
	private static Logger logger = Logger.getLogger(RecruiterDashBordController.class);

	@Autowired
	private RecruiterDashBordService recruiterService;

	@RequestMapping(value = "/recruiter/sourceAnalytics", method = RequestMethod.POST)
	public @ResponseBody String sourceAnalytics(@RequestParam(value = "days", required = false) int days,
			HttpSession session) {
		logger.info("days:" + days);
		String recruiterEmail = (String) session.getAttribute("userName");
		logger.info("recruiterEmail:" + recruiterEmail);
		List<Map<String, Object>> countSourceAnalytics = null;
		try {
			if (days == 3) {
				countSourceAnalytics = recruiterService.sourceAnalyticsTillPerRecruiter(recruiterEmail);
			} else {
				countSourceAnalytics = recruiterService.sourceAnalytics(days, recruiterEmail);
			}

		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return new Gson().toJson(countSourceAnalytics);
	}

	@RequestMapping(value = "/recruiter/statusAnalytics", method = RequestMethod.POST)
	public @ResponseBody String statusAnalytics(@RequestParam(value = "days", required = false) int days,
			HttpSession session) {
		logger.info("days:" + days);
		String recruiterEmail = (String) session.getAttribute("userName");
		logger.info("recruiterEmail:" + recruiterEmail);
		List<Map<String, Object>> countStatusAnalytics = null;
		try {
			if (days == 3) {
				countStatusAnalytics = recruiterService.statusAnalyticsTillPerRecruiter(recruiterEmail);

			} else {
				countStatusAnalytics = recruiterService.statusAnalytics(days, recruiterEmail);
			}

		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}

		return new Gson().toJson(countStatusAnalytics);
	}

	@RequestMapping(value = "/recruiter/applicationprocessNew", method = RequestMethod.POST)
	public @ResponseBody String applicationprocessNew(HttpSession session,
			@RequestParam(value = "days", required = false) int days) {
		logger.info("days:" + days);
		String recruiterEmail = (String) session.getAttribute("userName");
		logger.info("recruiterEmail:" + recruiterEmail);
		List<Map<String, Object>> countapplicationprocessNew = null;
		try {
			if (days == 3) {
				countapplicationprocessNew = recruiterService.applicationInProcessNewTillPerRecruiter(recruiterEmail);

			} else {
				countapplicationprocessNew = recruiterService.applicationInProcessNew(days, recruiterEmail);
			}

		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return new Gson().toJson(countapplicationprocessNew);
	}

	@RequestMapping(value = "/recruiter/applicantHiredAndTarget", method = RequestMethod.POST)
	public @ResponseBody String applicantHiredAndTarget(HttpSession session,
			@RequestParam(value = "days", required = false) int days) {
		logger.info("days:" + days);

		String teamname = (String) session.getAttribute("teamname");
		logger.info("teamname:" + teamname);
		String recruiterEmail = (String) session.getAttribute("userName");
		logger.info("recruiterEmail:" + recruiterEmail);
		List<Map<String, Object>> countApplicantHiredAndTarget = null;
		try {
			if (days == 3) {

				countApplicantHiredAndTarget = recruiterService.countApplicantHiredAndTargetTillPer(recruiterEmail,
						teamname);

			} else {
				countApplicantHiredAndTarget = recruiterService.countApplicantHiredAndTarget(days, recruiterEmail,
						teamname);
			}

		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return new Gson().toJson(countApplicantHiredAndTarget);
	}

	@RequestMapping(value = "/recruiter/jobsOpenAndClosed", method = RequestMethod.POST)
	public @ResponseBody String jobsOpenAndClosedSeven(HttpSession session,
			@RequestParam(value = "days", required = false) int days) {
		logger.info("days:" + days);
		List<Map<String, Object>> jobsOpenClosed = null;
		String recruiterEmail = (String) session.getAttribute("userName");
		logger.info("recruiterEmail:" + recruiterEmail);
		try {
			if (days == 3) {
				jobsOpenClosed = recruiterService.getCountJobOpenClosedTillNow(recruiterEmail);
			} else {

				jobsOpenClosed = recruiterService.getCountJobOpenClosed(days, recruiterEmail);

			}
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return new Gson().toJson(jobsOpenClosed);

	}

	@RequestMapping(value = "/recruiter/getTeam", method = RequestMethod.POST)
	public @ResponseBody List<User> getTeamDetails(HttpSession session) {
		String teamname = (String) session.getAttribute("teamname");
		logger.info("teamname:" + teamname);
		Integer userid = (Integer) session.getAttribute("userid");
		logger.info("userid:" + userid);
		List<User> getTeam = null;
		try {
			getTeam = recruiterService.getTeamMemeberDetails(teamname, userid);
			System.out.println(getTeam.size() + ":dssd");
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return getTeam;

	}

	@RequestMapping(value = "/recruiter/getJobs", method = RequestMethod.POST)
	public @ResponseBody List<CreateJob> getRecruiterJobs(HttpSession session) {
		String recruiterEmail = (String) session.getAttribute("userName");
		logger.info("recruiterEmail:getRecruiterJobs:" + recruiterEmail);
		List<CreateJob> getJobs = null;
		try {
			getJobs = recruiterService.getAllRecruiterJobs(recruiterEmail);
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return getJobs;
	}

	@RequestMapping(value = "/recruiter/getJobtitle", method = RequestMethod.POST)
	public @ResponseBody List<CreateJob> getRecruiterJobTitle(HttpSession session) {
		String recruiterEmail = (String) session.getAttribute("userName");
		logger.info("recruiterEmail:getRecruiterJobTitle:" + recruiterEmail);
		List<CreateJob> getJobs = null;
		try {
			getJobs = recruiterService.getJobTitleByRecruiter(recruiterEmail);
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return getJobs;
	}

	@RequestMapping(value = "/recruiter/getApplication", method = RequestMethod.POST)
	public @ResponseBody List<Applicants> getRecruiterApplications(HttpSession session,
			@RequestParam(value = "jobid", required = false) int jobId) {
		logger.info("jobId:" + jobId);
		String recruiterEmail = (String) session.getAttribute("userName");
		logger.info("recruiterEmail:getRecruiterApplications:" + recruiterEmail);
		List<Applicants> getApplicationDetails = null;
		try {
			getApplicationDetails = recruiterService.getApplicationByRecruiter(jobId, recruiterEmail);
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return getApplicationDetails;
	}

}
