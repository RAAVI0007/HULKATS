package com.web.hulklogic.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;
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
import com.web.hulklogic.entity.Applicants;
import com.web.hulklogic.entity.ApplicantsDocuments;
import com.web.hulklogic.entity.AutoSignature;
import com.web.hulklogic.entity.CreateJob;
import com.web.hulklogic.entity.User;
import com.web.hulklogic.service.ApplicantService;
import com.web.hulklogic.service.JobTitleService;
import com.web.hulklogic.service.SupportService;
import com.web.hulklogic.service.TemplateService;
import com.web.hulklogic.service.UserService;


@Controller
public class JobController {
	private static Logger logger = Logger.getLogger(JobController.class);
	@Autowired
	UserService userService;
	@Autowired
	private ServletContext context;
	@Autowired
	ApplicantService appservice;
	@Autowired
	private TemplateService templateService;
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

	@RequestMapping(value = "/manager/getAllJobs", method = RequestMethod.POST)
	public @ResponseBody String getAllJobs(HttpSession session) {
		String teamname = (String) session.getAttribute("teamname");
		logger.info("teamname:" + teamname);
		List<CreateJob> jobs = userService.getAllJobs(teamname);
		logger.info("jobs:" + jobs.size());
		return new Gson().toJson(jobs);
	}

	@RequestMapping(value = "/user/getAllApplicants", method = RequestMethod.POST)
	public @ResponseBody String getAllApplicants(HttpSession session) {
		String teamname = (String) session.getAttribute("teamname");
		String organization = (String)session.getAttribute("organization");
		logger.info("teamname:" + teamname);
		List<Applicants> jobs = userService.getAllAplicants(organization);
		return new Gson().toJson(jobs);
	}

	@RequestMapping(value = "/user/alertjobdetails", method = RequestMethod.POST)
	public @ResponseBody String getalertjobs(@RequestParam("jobid") Integer jobid) {
		CreateJob jobs = userService.jobdetails(jobid);
		File destinationFile = new File(videosURL + jobs.getVideoname());
		System.out.println(jobs.getVideoname());
		FileInputStream fileInputStreamReader = null;
		try {
			fileInputStreamReader = new FileInputStream(destinationFile);
			byte[] bytes = new byte[(int) destinationFile.length()];
			fileInputStreamReader.read(bytes);
			String encodedupload1 = new String(Base64.encodeBase64(bytes), "UTF-8");
			jobs.setVideoencoded(encodedupload1);
			String uploaddocument1 = jobs.getUploaddocument1();
			int index = uploaddocument1.lastIndexOf(".");
			int addvalue = index + 1;
			String lastDot = uploaddocument1.substring(addvalue);
			logger.info("lastDot:" + lastDot);
			if (fileInputStreamReader != null)
				fileInputStreamReader.close();
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return new Gson().toJson(jobs);
	}

	@RequestMapping(value = "/user/alertapplicantdetails", method = RequestMethod.POST)
	public @ResponseBody String getapplicantjobs(@RequestParam("applicantid") Integer applicantid) {
		Applicants applicant = userService.getapplicantdetails(applicantid);
		return new Gson().toJson(applicant);
	}

	@RequestMapping(value = "/user/applicantsCreations", method = RequestMethod.POST)
	public @ResponseBody String jobcreation(HttpSession session, @RequestParam("applicantsCreations") String jobStr,
			Model map, HttpServletRequest req) {

		if (null == jobStr || jobStr.trim().isEmpty()) {
			return "failure";
		}

		Applicants createApplicants = new Gson().fromJson(jobStr, Applicants.class);
		boolean status = appservice.getappemailCheck(createApplicants.getEmail());

		if (status) {
			return "Email is already registered! Please choose another one";
		}

		boolean statusconcat = appservice.getappmobileCheck(createApplicants.getContactno());

		if (statusconcat) {
			return "Mobile Number is registered! Please choose another one";
		}

		String currentLoginEmail = (String) session.getAttribute("userName");
		String teamname = (String) session.getAttribute("teamname");
		String organization = (String) session.getAttribute("organization");
		logger.info("currenLoginEmail:" + currentLoginEmail);
		logger.info("teamname:" + teamname);
		Integer userid = (Integer) session.getAttribute("userid");
		logger.info("userid:" + userid);
		String roleName = (String) session.getAttribute("roleName");
		logger.info("roleName:" + roleName);
		createApplicants.setUserid(userid);
		createApplicants.setTeamname(teamname);
		createApplicants.setApplicantcreatedemail(currentLoginEmail);
		createApplicants.setOrganization(organization);
		String result = "fail";
		String document1 = null;
		String document2 = null;
		//Parser parser = null;
		
		String sendAlertEmail = null;
		
		try {
			java.util.Date todayDate = new java.util.Date();
			FileOutputStream fois1 = null;

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
				// File destinationFile1 = new
				// File(context.getRealPath("/WEB-INF/cvs")+ File.separator
				// +document1);
				File destinationFile1 = new File("/opt/hulkats/cvs/" + document1);
				fois1 = new FileOutputStream(destinationFile1);
				fois1.write(documentdecode1);
				try {
					if (fois1 != null)
						fois1.close();
				} catch (Exception e) {
					logger.error("Exception is: " + e);
				}
				// String filename =
				// context.getRealPath("/WEB-INF/cvs/")+document1;
				String filename = "/opt/hulkats/cvs/" + document1;
				if (null != filename && !filename.trim().isEmpty()) {
				/*	parser = new Parser();
					countNouns = parser.countNouns(filename, createApplicants.getFiletype1());
					if (null != countNouns && !countNouns.trim().isEmpty()) {
						logger.info("count nouns1:" + countNouns);
						createApplicants.setCountNouns1((countNouns));
					}*/
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

				// File destination2 = new
				// File(context.getRealPath("/WEB-INF/cvs")+ File.separator
				// +document2);
				File destination2 = new File("/opt/hulkats/cvs/" + document2);
				FileOutputStream fois2 = new FileOutputStream(destination2);
				fois2.write(documentdecode2);
				try {
					if (fois1 != null)
						fois1.close();
				} catch (Exception e) {
					logger.error("Exception is: " + e);
				}

				// String filename1 =
				// context.getRealPath("/WEB-INF/cvs/")+document2;
				String filename1 = "/opt/hulkats/cvs/" + document2;

				if (null != filename1 && !filename1.trim().isEmpty()) {

				/*	parser = new Parser();

					countNouns = parser.countNouns(filename1, createApplicants.getFiletype2());

					if (null != countNouns && !countNouns.trim().isEmpty()) {

						createApplicants.setCountNouns2((countNouns));
					}*/
				}
				createApplicants.setOtheruploads(document2);

			} else {
				createApplicants.setOtheruploads(null);
			}

			if (roleName.equals("ROLE_RECRUITER")) {
				sendAlertEmail = supportService.managerEmailForSenderAlert(teamname);
				createApplicants.setSenderEmail(sendAlertEmail);
				result = userService.createApplicants(createApplicants);
				String[] arrOfStr = result.split(" ");
				int applicant_id = Integer.parseInt(arrOfStr[1]);
				System.out.println(arrOfStr[1]);
				createApplicants.setApplicantid(applicant_id);
				String results = arrOfStr[0];
				if (results.equals("success")) {
					logger.info("applicant creation");
					boolean linkStatus = userService.sendApplicantLink(createApplicants);
					logger.info("linkStatus:" + linkStatus);

				}
			} else {

				result = userService.createApplicantManager(createApplicants);
				String[] arrOfStr = result.split(" ");
				String results = arrOfStr[0];

				createApplicants.setApplicantid(Integer.parseInt(arrOfStr[1]));

				if (results.equals("success")) {
					logger.info("applicant creation");
					boolean linkStatus = userService.sendApplicantLink(createApplicants);
					logger.info("linkStatus:" + linkStatus);

				}

			}
		} catch (Exception e) {

			logger.error("Exception is: " + e);
		}
		return result;

	}

	@RequestMapping(value = "/manager/sendApplicantsDocuments", method = RequestMethod.POST)
	public @ResponseBody ApplicantsDocuments sendApplicantsDocuments(
			@RequestParam(value = "applicantid", required = false) int applicantid) {

		logger.info("applicantid:" + applicantid);

		ApplicantsDocuments applicantionsDocuments = null;
		try {
			applicantionsDocuments = userService.getAllApplicantsDocuments(applicantid);
			// File destinationFile = new
			// File(context.getRealPath("/WEB-INF/cvs")+ File.separator +
			// applicantionsDocuments.getUplaodcv());
			File destinationFile = new File("/opt/hulkats/cvs/" + applicantionsDocuments.getUplaodcv());
			FileInputStream fileInputStreamReader = null;

			try {
				fileInputStreamReader = new FileInputStream(destinationFile);
			} catch (FileNotFoundException e) {
				logger.error("Exception is: " + e);
			}

			byte[] bytes = new byte[(int) destinationFile.length()];
			try {
				fileInputStreamReader.read(bytes);
			} catch (IOException e) {
				logger.error("Exception is: " + e);
			}
			String encodeduploadcv = null;
			try {
				encodeduploadcv = new String(Base64.encodeBase64(bytes), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error("Exception is: " + e);
			}
			applicantionsDocuments.setUplaodcv(encodeduploadcv);
			try {
				if (fileInputStreamReader != null)
					fileInputStreamReader.close();
			} catch (Exception e) {
				logger.error("Exception is: " + e);
			}

			// File destinationFile1 = new
			// File(context.getRealPath("/WEB-INF/cvs")+ File.separator +
			// applicantionsDocuments.getOtheruploads());
			File destinationFile1 = new File("/opt/hulkats/cvs" + applicantionsDocuments.getOtheruploads());
			FileInputStream fileInputStreamReader1 = null;

			try {
				fileInputStreamReader1 = new FileInputStream(destinationFile1);
			} catch (FileNotFoundException e) {
				logger.error("Exception is: " + e);
			}

			byte[] bytes1 = new byte[(int) destinationFile.length()];
			try {
				fileInputStreamReader.read(bytes1);
			} catch (IOException e) {
				logger.error("Exception is: " + e);
			}
			String encodedOtheruploads = null;
			try {
				encodedOtheruploads = new String(Base64.encodeBase64(bytes1), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error("Exception is: " + e);
			}
			applicantionsDocuments.setOtheruploads(encodedOtheruploads);
			;
			try {
				if (fileInputStreamReader != null)
					fileInputStreamReader.close();
			} catch (Exception e) {
				logger.error("Exception is: " + e);
			}
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return applicantionsDocuments;

	}

	@RequestMapping(value = "/manager/allRecruiterEmails", method = RequestMethod.POST)
	public @ResponseBody String sendAllRecruiterEmails() {
		List<String> getAllRecriterEmails = userService.getAllRecruiterEmails();
		return new Gson().toJson(getAllRecriterEmails);
	}

	@RequestMapping(value = "/user/getAutoSignature", method = RequestMethod.POST)
	public @ResponseBody String getAutosignatureByEmail(HttpSession session) {
		String currentemail = (String) session.getAttribute("userName");
		logger.info("currentemail:" + currentemail);
		AutoSignature getautoSignature = null;
		try {
			getautoSignature = templateService.geAutoSignatureByEmail(currentemail);
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return new Gson().toJson(getautoSignature);
	}

	@RequestMapping(value = "/user/countriesIds", method = RequestMethod.POST)
	public @ResponseBody List<CreateJob> getCountriesIdsAndNames() {
		List<CreateJob> countriesIdsNames = null;
		try {
			countriesIdsNames = userService.getCountryNameAndId();
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return countriesIdsNames;
	}

	@RequestMapping(value = "/user/statesAndCities", method = RequestMethod.POST)
	public @ResponseBody List<CreateJob> getCitiesAndStates(@RequestParam("countryId") int countryId) {
		List<CreateJob> citiesAndStates = null;
		try {
			citiesAndStates = userService.getStateAndCitiesByCountryId(countryId);
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return citiesAndStates;
	}
	
	@RequestMapping(value = "/user/updatetemplateid", method = RequestMethod.POST)
	public @ResponseBody String updatetemplateid(HttpSession session,@RequestParam("templatedesignid") int templatedesignid) {
		String teamname = (String) session.getAttribute("teamname");
		logger.info("teamname:" + teamname);
		boolean result = false;
		try {
			result = userService.updatetemplateid(templatedesignid, teamname);
			if(result == true){
				return "success";
			}else{
				return "fail";
			}
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return "fail";
		
	}
	
	@RequestMapping(value = "/user/gettemplateid", method = RequestMethod.POST)
	public @ResponseBody Integer gettemplateid(HttpSession session) {
		String email = (String) session.getAttribute("userName");
		logger.info("email:" + email);
		int result = 0;
		try {
			User getmanager = userService.getUserByEmail(email);
			logger.info("email:" + getmanager.getTemplatedesignid());
			result = getmanager.getTemplatedesignid();
			logger.info("result:" + result);
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return result;
		
	}

}