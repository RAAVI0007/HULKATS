package com.web.hulklogic.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.web.hulklogic.entity.AlertDetails;
import com.web.hulklogic.entity.Applicants;
import com.web.hulklogic.entity.CreateJob;
import com.web.hulklogic.entity.JobsDocuments;
import com.web.hulklogic.entity.User;
import com.web.hulklogic.service.EditDetailsService;
import com.web.hulklogic.service.SupportService;
import com.web.hulklogic.service.UserService;

@Controller
public class EditDetailsController {
	private static Logger logger = Logger.getLogger(EditDetailsController.class);
	@Autowired
	private EditDetailsService editDetailsService;
	@Autowired
	private UserService userService;
	@Autowired
	private ServletContext context;
	@Autowired
	private SupportService supportService;

	private static Properties prop = null;
	private static String templateURL = null;
	private static String cvsURL = null;
	private static String uploadedURL = null;
	private static String videosURL = null;
	static {
		if (null == prop) {
			try {
				prop = PropertiesLoaderUtils.loadAllProperties("mail.properties");
				templateURL = prop.getProperty("templateURL");
				cvsURL = prop.getProperty("cvsURL");
				uploadedURL = prop.getProperty("uploadedURL");
				videosURL = prop.getProperty("videosURL");
			} catch (IOException e) {
				logger.error("Exception is: " + e);
			}
		}
	}

	@RequestMapping(value = "/manager/getManagers", method = RequestMethod.POST)
	public @ResponseBody List<User> getManagers(@RequestParam("rolename") String rolename, HttpSession session) {
		logger.info("rolename:" + rolename);
		String organization = (String) session.getAttribute("organization");
		int roleid = 0;
		List<User> users = null;

		if (rolename.equals("ROLE_MANAGER")) {
			roleid = userService.getRoleId(rolename);
		}
		try {
			logger.info("roleid:" + roleid);
			users = editDetailsService.getAllManagers(roleid, organization);
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return users;
	}

	@RequestMapping(value = "/user/getAllDocuments", method = RequestMethod.POST)
	public @ResponseBody JobsDocuments getAllDocuments(@RequestParam("jobid") int jobid,
			@RequestParam("applicantid") int applicantid) {
		logger.info("jobid:" + jobid);

		JobsDocuments jobdocuments = null;

		try {
			jobdocuments = editDetailsService.getAllDocument(jobid, applicantid);
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return jobdocuments;
	}

	@RequestMapping(value = "/user/getAppDocuments", method = RequestMethod.POST)
	public @ResponseBody JobsDocuments getAppDocuments(@RequestParam("applicantid") int applicantid) {

		JobsDocuments jobdocuments = null;

		try {
			jobdocuments = editDetailsService.getApplicantDoc(applicantid);
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return jobdocuments;
	}

	@RequestMapping(value = "/user/getJobDocuments", method = RequestMethod.POST)
	public @ResponseBody JobsDocuments getJobDocuments(@RequestParam("jobid") int jobid) {

		JobsDocuments jobdocuments = null;

		try {
			jobdocuments = editDetailsService.getJobDoc(jobid);
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return jobdocuments;
	}

	@RequestMapping(value = "/manager/getRecruiters", method = RequestMethod.POST)
	public @ResponseBody List<User> getRecruitersUnderManager(HttpSession session,
			@RequestParam("rolename") String rolename) {
		String teamname = (String) session.getAttribute("teamname");
		Integer userId = (Integer) session.getAttribute("userid");
		String organization = (String) session.getAttribute("organization");
		logger.info("teamname:" + teamname);

		logger.info("rolename:" + rolename);
		int roleid = 0;
		List<User> users = null;

		if (rolename.equals("ROLE_RECRUITER")) {
			roleid = userService.getRoleId(rolename);
		}
		try {
			logger.info("roleid:" + roleid);
			users = editDetailsService.getAllRecruiters(roleid, teamname, organization);
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return users;
	}

	@RequestMapping(value = "/manager/getRecruitersBench", method = RequestMethod.POST)
	public @ResponseBody List<User> getRecruitersNotAssign(@RequestParam("rolename") String rolename,
			HttpSession session) {
		logger.info("rolename:" + rolename);
		String organization = (String) session.getAttribute("organization");
		int roleid = 0;
		List<User> users = null;
		roleid = userService.getRoleId("ROLE_RECRUITER");
		try {
			logger.info("roleid:" + roleid);
			users = editDetailsService.getAllNARecruiters(roleid, organization);
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return users;
	}

	@RequestMapping(value = "/user/jobChanageStatus", method = RequestMethod.POST)
	public @ResponseBody String changeJobStatus(HttpSession session, @RequestParam("jobid") int jobid,
			@RequestParam("status") String jobstatus) {

		logger.info("jobid:" + jobid);
		logger.info("status:" + jobstatus);
		Integer userid = (Integer) session.getAttribute("userid");
		logger.info("userid:" + userid);
		String currenLoginEmail = (String) session.getAttribute("userName");
		logger.info("currenLoginEmail:" + currenLoginEmail);
		String roleName = (String) session.getAttribute("roleName");
		logger.info("roleName:" + roleName);
		String teamname = (String) session.getAttribute("teamname");
		logger.info("teamname:" + teamname);
		String result = "fails";
		String sendAlertEmail = null;
		if ("ROLE_RECRUITER".equalsIgnoreCase(roleName)) {
			sendAlertEmail = supportService.managerEmailForSenderAlert(teamname);
			logger.info("sendAlertEmail:" + sendAlertEmail);
		} else {
			sendAlertEmail = supportService.recruiterEmailForSenderAlert(jobid);
			logger.info("sendAlertEmail:" + sendAlertEmail);
		}
		try {
			AlertDetails alertDetails = new AlertDetails();
			alertDetails.setCreated_alert(currenLoginEmail);
			alertDetails.setUserid(userid);
			alertDetails.setRef_jobid(jobid);
			alertDetails.setAlert_type("Job Alert");
			alertDetails.setJobstatus(jobstatus);
			alertDetails.setSender_email(sendAlertEmail);
			result = editDetailsService.changeJobStatus(alertDetails);
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return result;
	}

	@RequestMapping(value = "/user/updateJobDetails", method = RequestMethod.POST)
	public @ResponseBody String jobcreation(HttpSession session, @RequestParam("jobcreation") String jobStr, Model map,
			HttpServletRequest req) {

		if (null == jobStr || jobStr.trim().isEmpty()) {
			return "failure";
		}

		CreateJob createjob = new Gson().fromJson(jobStr, CreateJob.class);
		logger.info("update Job id:" + createjob.getJobid());
		String jobtitle = createjob.getJobtitle();
		String upperCaseJobtitle = jobtitle.toUpperCase();
		createjob.setJobtitle(upperCaseJobtitle);
		String location = createjob.getLocation();
		String upperCaseLocation = location.toUpperCase();
		createjob.setLocation(upperCaseLocation);
		String countNouns = null;
		String getparserData = null;
		String result = "fail";
		// Parser parser=null;
		// ParserData parserdata=null;
		String document1 = null;
		String document2 = null;
		String videoname = null;

		try {
			// java.util.Date todayDate = new java.util.Date();
			Date todayDate = new Date();
			if (createjob.getFiletype3() != null) {

				byte[] videodecode = DatatypeConverter.parseBase64Binary(createjob.getVideoupload());
				videoname = createjob.getVideoname() + "_" + todayDate.getYear() + "_" + todayDate.getMonth() + "_"
						+ todayDate.getDay() + "_" + todayDate.getHours() + "_" + todayDate.getSeconds() + "." + "mp4";
				// File destinationVideo = new File(this.context.getRealPath("/WEB-INF/videos")
				// + File.separator + videoname);
				File destinationVideo = new File(videosURL + videoname);
				FileOutputStream foisvideo = new FileOutputStream(destinationVideo);

				foisvideo.write(videodecode);
				createjob.setVideoname(videoname);
			} else {
				createjob.setVideoname("NA");

			}
			if (createjob.getVideourl() == "" || createjob.getVideourl() == null) {
				createjob.setVideourl("NA");
			}

			if (createjob.getFiletype1() != null) {

				byte[] documentdecode1 = DatatypeConverter.parseBase64Binary(createjob.getDocument1());

				if (createjob.getFiletype1().equals("vnd.openxmlformats-officedocument.wordprocessingml.document")) {
					document1 = createjob.getDocname1() + "_" + todayDate.getYear() + "_" + todayDate.getMonth() + "_"
							+ todayDate.getDay() + "_" + todayDate.getHours() + "_" + todayDate.getSeconds() + "."
							+ "docx";

				} else {

					document1 = createjob.getDocname1() + "_" + todayDate.getYear() + "_" + todayDate.getMonth() + "_"
							+ todayDate.getDay() + "_" + todayDate.getHours() + "_" + todayDate.getSeconds() + "."
							+ "pdf";
				}

				// File destinationFile1 = new File(context.getRealPath("/WEB-INF/uploaded")+
				// File.separator +document1);
				File destinationFile1 = new File(uploadedURL + document1);
				FileOutputStream fois1 = new FileOutputStream(destinationFile1);
				fois1.write(documentdecode1);

				try {
					if (fois1 != null)
						fois1.close();
				} catch (Exception e) {
					logger.error("Exception is: " + e);
				}

				// String filename = context.getRealPath("/WEB-INF/uploaded/")+document1;
				String filename = uploadedURL + document1;
				if (null != filename && !filename.trim().isEmpty()) {
					/*
					 * parser=new Parser(); countNouns
					 * =parser.countNouns(filename,createjob.getFiletype1());
					 * logger.info("countNouns:"+countNouns); if(null != countNouns &&
					 * !countNouns.trim().isEmpty()){ createjob.setCountNouns(countNouns); }
					 */
				}
				createjob.setDocument1(document1);
			} else {
				createjob.setDocument1(null);
			}
			if (createjob.getFiletype2() != null) {

				byte[] documentdecode2 = DatatypeConverter.parseBase64Binary(createjob.getDocument2());

				if (createjob.getFiletype2().equals("vnd.openxmlformats-officedocument.wordprocessingml.document")) {

					document2 = createjob.getDocname2() + "_" + todayDate.getYear() + "_" + todayDate.getMonth() + "_"
							+ todayDate.getDay() + "_" + todayDate.getHours() + "_" + todayDate.getSeconds() + ".docx";

				} else {

					document2 = createjob.getDocname2() + "_" + todayDate.getYear() + "_" + todayDate.getMonth() + "_"
							+ todayDate.getDay() + "_" + todayDate.getHours() + "_" + todayDate.getSeconds() + ".pdf";
				}

				// File destination2 = new File(context.getRealPath("/WEB-INF/uploaded")+
				// File.separator +document2);
				File destination2 = new File(uploadedURL + document2);
				FileOutputStream fois2 = new FileOutputStream(destination2);
				fois2.write(documentdecode2);

				try {
					if (fois2 != null)
						fois2.close();
				} catch (Exception e) {
					logger.error("Exception is: " + e);
				}

				// String filename1 = context.getRealPath("/WEB-INF/uploaded/")+document2;
				String filename1 = uploadedURL + document2;
				if (null != filename1 && !filename1.trim().isEmpty()) {
					/*
					 * parser=new Parser(); countNouns
					 * =parser.countNouns(filename1,createjob.getFiletype2()); if(countNouns!=null){
					 * createjob.setCountNouns1(countNouns); }
					 */
				}
				createjob.setDocument2(document2);

			} else {
				createjob.setDocument2(null);
			}
			result = editDetailsService.updateJob(createjob);

		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return result;
	}

	@RequestMapping(value = "/user/updateApplicantDetails", method = RequestMethod.POST)
	public @ResponseBody String updateApplicantDetails(HttpSession session,
			@RequestParam("applicantsCreations") String jobStr, Model map) {

		if (null == jobStr || jobStr.trim().isEmpty()) {
			return "failure";
		}

		Applicants createApplicants = new Gson().fromJson(jobStr, Applicants.class);
		logger.info("update Applicant id:" + createApplicants.getApplicantid());
		String currentLoginEmail = (String) session.getAttribute("userName");
		logger.info("currenLoginEmail:" + currentLoginEmail);
		createApplicants.setApplicantcreatedemail(currentLoginEmail);
		String result = "fail";
		String document1 = null;
		String document2 = null;
		// Parser parser=null;
		// ParserData parserdata=null;
		String countNouns = null;
		String getparserData = null;

		try {

			java.util.Date todayDate = new java.util.Date();
			FileOutputStream fois1 = null;
			String firstname = createApplicants.getFirstname();
			String fullname = firstname.concat(createApplicants.getLastname());

			if (createApplicants.getFiletype1() != null) {

				byte[] documentdecode1 = DatatypeConverter.parseBase64Binary(createApplicants.getUplaodcv());

				if (createApplicants.getFiletype1()
						.equals("vnd.openxmlformats-officedocument.wordprocessingml.document")) {

					document1 = createApplicants.getDocname1() + "_" + todayDate.getYear() + "_" + todayDate.getMonth()
							+ "_" + todayDate.getDay() + "_" + todayDate.getHours() + "_" + todayDate.getSeconds()
							+ ".docx";

				} else {

					document1 = createApplicants.getDocname1() + "_" + todayDate.getYear() + "_" + todayDate.getMonth()
							+ "_" + todayDate.getDay() + "_" + todayDate.getHours() + "_" + todayDate.getSeconds()
							+ ".pdf";

				}
				// File destinationFile1 = new File(context.getRealPath("/WEB-INF/cvs")+
				// File.separator +document1);
				File destinationFile1 = new File("/opt/hulkats/cvs/" + document1);
				fois1 = new FileOutputStream(destinationFile1);
				fois1.write(documentdecode1);
				try {
					if (fois1 != null)
						fois1.close();
				} catch (Exception e) {

					logger.error("Exception is: " + e);
				}

				// String filename = context.getRealPath("/WEB-INF/cvs/")+document1;
				String filename = "/opt/hulkats/cvs/" + document1;
				if (null != filename && !filename.trim().isEmpty()) {
					// parser=new Parser();
					// countNouns =parser.countNouns(filename,createApplicants.getFiletype1());
					if (null != countNouns && !countNouns.trim().isEmpty()) {
						logger.info("count nouns1:" + countNouns);
						createApplicants.setCountNouns1((countNouns));
					} else {
						return "fail";
					}
				}
				createApplicants.setUplaodcv(document1);
			} else {
				createApplicants.setUplaodcv(null);
			}

			if (createApplicants.getFiletype2() != null) {
				byte[] documentdecode2 = DatatypeConverter.parseBase64Binary(createApplicants.getOtheruploads());
				if (createApplicants.getFiletype2()
						.equals("vnd.openxmlformats-officedocument.wordprocessingml.document")) {
					document2 = createApplicants.getDocname2() + "_" + todayDate.getYear() + "_" + todayDate.getMonth()
							+ "_" + todayDate.getDay() + "_" + todayDate.getHours() + "_" + todayDate.getSeconds()
							+ ".docx";
				} else {
					document2 = createApplicants.getDocname2() + "_" + todayDate.getYear() + "_" + todayDate.getMonth()
							+ "_" + todayDate.getDay() + "_" + todayDate.getHours() + "_" + todayDate.getSeconds()
							+ ".pdf";
				}
				// File destination2 = new File(context.getRealPath("/WEB-INF/cvs")+
				// File.separator +document2);
				File destination2 = new File("/opt/hulkats/cvs/" + document2);
				FileOutputStream fois2 = new FileOutputStream(destination2);
				fois2.write(documentdecode2);
				try {
					if (fois1 != null)
						fois1.close();
				} catch (Exception e) {
					logger.error("Exception is: " + e);
				}
				// String filename1 = context.getRealPath("/WEB-INF/cvs/")+document2;
				String filename1 = "/opt/hulkats/cvs/" + document2;
				if (null != filename1 && !filename1.trim().isEmpty()) {
					/*
					 * parser=new Parser(); // countNouns
					 * =parser.countNouns(filename1,createApplicants.getFiletype2()); if(null !=
					 * countNouns && !countNouns.trim().isEmpty()){
					 * createApplicants.setCountNouns2((countNouns)); }
					 */

				}
				createApplicants.setOtheruploads(document2);
			} else {
				createApplicants.setOtheruploads(null);
			}
			result = editDetailsService.updateApplicant(createApplicants);
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return result;

	}

	@RequestMapping(value = "/user/getUserInfo", method = RequestMethod.POST)
	public @ResponseBody User getUserDetails(HttpSession session) {
		String currentemail = (String) session.getAttribute("userName");
		logger.info("currentemail:" + currentemail);

		User user = null;
		try {
			user = userService.getUserByEmail(currentemail);
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return user;
	}
}
