package com.web.hulklogic.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.hulklogic.conversion.WordConvertPDF;
import com.web.hulklogic.entity.Applicants;
import com.web.hulklogic.entity.CreateJob;
import com.web.hulklogic.entity.Customer;
import com.web.hulklogic.entity.JobTitle;
import com.web.hulklogic.entity.JobsDocuments;
import com.web.hulklogic.entity.QuoteMessages;
import com.web.hulklogic.entity.Sharejobs;
import com.web.hulklogic.entity.User;
import com.web.hulklogic.service.ApplicantService;
import com.web.hulklogic.service.ApplicationService;
import com.web.hulklogic.service.ApplicationSettingsService;
import com.web.hulklogic.service.CustomerService;
import com.web.hulklogic.service.JobTitleService;
import com.web.hulklogic.service.SupportService;
import com.web.hulklogic.service.UserService;
import com.web.hulklogic.utility.DocumentDeleteUtility;
import com.web.hulklogic.utility.ResponseObj;
import com.web.hulklogic.utility.StatusEnum;



/*@CrossOrigin(origins = "*", maxAge = 3600)*/
@RestController
public class RestControllerTest {
	private static Logger logger = Logger.getLogger(RestControllerTest.class);
	@Autowired
	private UserService userService;
	@Autowired
	private ApplicationSettingsService applicationSettingsService;
	@Autowired
	private ServletContext context;
	@Autowired
	ApplicantService appservice;
	@Autowired
	private SupportService supportService;
	@Autowired
	private JobTitleService jobtitleService;
	@Autowired
	private ApplicationService applicationService;
	@Autowired
	private ApplicantService applicantService;
	@Autowired
	private CustomerService customerservice;

	static Gson gson = new Gson();
	static ResponseObj responseObj = new ResponseObj();
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

	@GetMapping("/rest/test")
	public String testRest() {
		return "Rest working !!";
	}
	
	 
	@RequestMapping(value = "/rest/quotemessage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String quotemessage(@RequestBody QuoteMessages quote, HttpServletRequest request) {
		ResponseObj responseObj = new ResponseObj();
		int result = userService.saveQuote(quote);

		if (result > 0) {
			boolean linkStatus = userService.sendcontactdetails(quote);
			logger.info("linkStatus:" + linkStatus);
			if (!linkStatus) {
				responseObj.setEvent("Fail");
				responseObj.setStatus(StatusEnum.FAIL.name());
				return new Gson().toJson(responseObj);
			} else {

				responseObj.setEvent("Success");
				responseObj.setStatus(StatusEnum.SUCCESS.name());
				return new Gson().toJson(responseObj);
			}

		}
		responseObj.setEvent("Fail");
		responseObj.setStatus(StatusEnum.FAIL.name());
		return new Gson().toJson(responseObj);
	}
	
	@RequestMapping(value = "/rest/demomessage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String demomessage(@RequestBody QuoteMessages quote, HttpServletRequest request) {
		ResponseObj responseObj = new ResponseObj();
		int result = userService.demo(quote);

		if (result > 0) {
			boolean linkStatus = userService.demomessage(quote);
			logger.info("linkStatus:" + linkStatus);
			if (!linkStatus) {
				responseObj.setEvent("Fail");
				responseObj.setStatus(StatusEnum.FAIL.name());
				return new Gson().toJson(responseObj);
			} else {
				responseObj.setEvent("Success");
				responseObj.setStatus(StatusEnum.SUCCESS.name());
				return new Gson().toJson(responseObj);
			}

		}
		responseObj.setEvent("Fail");
		responseObj.setStatus(StatusEnum.FAIL.name());
		return new Gson().toJson(responseObj);
	}

	@RequestMapping(value = "/rest/contactmessage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String contactmessage(@RequestBody QuoteMessages quote) {
		ResponseObj responseObj = new ResponseObj();
		int result = userService.saveContact(quote);

		if (result > 0) {
			boolean linkStatus = userService.sendcontdetails(quote);
			logger.info("linkStatus:" + linkStatus);
			if (!linkStatus) {
				responseObj.setEvent("Fail");
				responseObj.setStatus(StatusEnum.FAIL.name());
				return new Gson().toJson(responseObj);
			} else {

				responseObj.setEvent("Success");
				responseObj.setStatus(StatusEnum.SUCCESS.name());
				return new Gson().toJson(responseObj);
			}

		}
		responseObj.setEvent("Fail");
		responseObj.setStatus(StatusEnum.FAIL.name());
		return new Gson().toJson(responseObj);
	}
	/*@CrossOrigin(origins = "https://hulklogic.com")*/
	@RequestMapping(value = "/rest/getCompanies", method = RequestMethod.POST)
	public @ResponseBody String getAllCompanies() {
		List<CreateJob> listcompanies = null;
		try {

			listcompanies = applicationSettingsService.getCompanies();

			Gson gson = new Gson();
			String listcompanyStr = gson.toJson(listcompanies);
			responseObj.setEvent("Success");
			responseObj.setStatus(listcompanyStr);
			return new Gson().toJson(responseObj);

		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}

		responseObj.setEvent("Fail");
		responseObj.setStatus(StatusEnum.FAIL.name());
		return new Gson().toJson(responseObj);

	}
	
	@RequestMapping(value = "/rest/getalljobs", method = RequestMethod.POST)
	public @ResponseBody String getalljobs() {
		List<CreateJob> jobs = null;
		try {
			jobs = userService.getalljobs();
			String jobsStr = gson.toJson(jobs);
			responseObj.setEvent("Success");
			responseObj.setStatus(jobsStr);
			return new Gson().toJson(responseObj);
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}

		responseObj.setEvent("Fail");
		responseObj.setStatus(StatusEnum.FAIL.name());
		return new Gson().toJson(responseObj);
	}
	@RequestMapping(value = "/rest/getsearchedjobs", method = RequestMethod.POST)
	public @ResponseBody String getsearchedjobs(@RequestBody CreateJob createjob) {
		List<CreateJob> jobs = null;
		try {
			jobs = userService.searchjobs(createjob);
			String jobsStr = gson.toJson(jobs);
			responseObj.setEvent("Success");
			responseObj.setStatus(jobsStr);
			return new Gson().toJson(responseObj);
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		responseObj.setEvent("Fail");
		responseObj.setStatus(StatusEnum.FAIL.name());
		return new Gson().toJson(responseObj);
	}
	
	@RequestMapping(value = "/rest/getallrecent", method = RequestMethod.POST)
	public @ResponseBody String getallrecent(@RequestParam(value = "company", required = false) String company) {
		List<CreateJob> jobs = null;
		try {
			jobs = userService.getallrecentjobs(company);
			String jobsStr = gson.toJson(jobs);
			responseObj.setEvent("Success");
			responseObj.setStatus(jobsStr);
			return new Gson().toJson(responseObj);
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}

		responseObj.setEvent("Fail");
		responseObj.setStatus(StatusEnum.FAIL.name());
		return new Gson().toJson(responseObj);
	}
	
	
	
	@RequestMapping(value = "/rest/getperCompany", method = RequestMethod.POST)
	public @ResponseBody String getperCountries(@RequestParam(value = "company", required = false) String company) {
		List<CreateJob> listcoutries = null;
		try {

			listcoutries = applicationSettingsService.getperCompanies(company);

			Gson gson = new Gson();
			String listcoutriesStr = gson.toJson(listcoutries);
			responseObj.setEvent("Success");
			responseObj.setStatus(listcoutriesStr);
			return new Gson().toJson(responseObj);

		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}

		responseObj.setEvent("Fail");
		responseObj.setStatus(StatusEnum.FAIL.name());
		return new Gson().toJson(responseObj);

	}
	@RequestMapping(value = "/rest/checkapplicants", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String applicantcheck(HttpServletRequest request,@RequestParam(value = "email", required = false) String email,@RequestParam(value="jobid", required=false) int jobid) {
		String result = "fail";
		boolean status = appservice.getappemailCheck(email);
		if (status) {
			int applicantid = appservice.getapplicantid(email);
			logger.info(applicantid);
			boolean checkinghold = appservice.appcheckinghold(jobid,applicantid);
			boolean checking = appservice.appchecking(jobid,applicantid);
			if(checking) {
				responseObj.setEvent("Application already created");
				responseObj.setStatus(StatusEnum.FAIL.name());
				return new Gson().toJson(responseObj);
			}else {
				if(checkinghold) {
					responseObj.setEvent("Application already created");
					responseObj.setStatus(StatusEnum.FAIL.name());
					return new Gson().toJson(responseObj);
					
					
				}else {
					String sendAlertEmail = null;
					logger.info(jobid);
					String email1 = applicationService.getAssignEmail(jobid);
					logger.info(email1);
					User user = userService.getUserByEmail(email1);
					String teamname = user.getTeamname();
					logger.info("teamname:" + teamname);
					Integer userid = user.getUserid();
					logger.info("userid:" + userid);
					JobTitle jobtitle = new JobTitle();
					jobtitle.setJobid(jobid);
					
					jobtitle.setApplicantid(applicantid);
					jobtitle.setReferncedemail(email1);
					jobtitle.setTeamname(teamname);
					jobtitle.setUserid(userid);
					sendAlertEmail = supportService.managerEmailForSenderAlert(teamname);
					logger.info("sendAlertEmail:" + sendAlertEmail);
					jobtitle.setSender_email(sendAlertEmail);
					try {

						result = jobtitleService.createApplicantDirectly1(jobtitle);

					} catch (Exception e) {
						logger.error("Exception is: " + e);
					}
					if (result.equals("success")) {

						responseObj.setEvent("Application Created Successfully");
						responseObj.setStatus(StatusEnum.SUCCESS.name());
						return new Gson().toJson(responseObj);
					} else {
						responseObj.setEvent("Application creation Failed");
						responseObj.setStatus(StatusEnum.FAIL.name());
						return new Gson().toJson(responseObj);
					}	
				}
			
			}
			
			}else{
			String result2 = "NO_ACCOUNT";
			responseObj.setEvent("Success");
			responseObj.setStatus(result2);
			return new Gson().toJson(responseObj);
			
		}
		
		
	}
	@RequestMapping(value = "/rest/applicationcreatedbyjobid", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String applicationcreatedbyjobid(HttpServletRequest request,@RequestParam(value = "email", required = false) String email,
	 @RequestParam(value="password", required=false) String password,@RequestParam(value="jobid", required=false) int jobid){
		
		String result = "fail";
		boolean status = appservice.getappemailpassCheck(email,password);
		if(status){
			int applicantid = appservice.getapplicantid(email);
			logger.info(applicantid);
			boolean checking = appservice.appchecking(applicantid,jobid);
			if(!checking){
				String sendAlertEmail = null;
				logger.info(jobid);
				String email1 = applicationService.getAssignEmail(jobid);
				logger.info(email1);
				User user = userService.getUserByEmail(email1);
				String teamname = user.getTeamname();
				logger.info("teamname:" + teamname);
				Integer userid = user.getUserid();
				logger.info("userid:" + userid);
				JobTitle jobtitle = new JobTitle();
				jobtitle.setJobid(jobid);
				
				jobtitle.setApplicantid(applicantid);
				jobtitle.setReferncedemail(email1);
				jobtitle.setTeamname(teamname);
				jobtitle.setUserid(userid);
				sendAlertEmail = supportService.managerEmailForSenderAlert(teamname);
				logger.info("sendAlertEmail:" + sendAlertEmail);
				jobtitle.setSender_email(sendAlertEmail);
				try {

					result = jobtitleService.createApplicantDirectly1(jobtitle);

				} catch (Exception e) {
					logger.error("Exception is: " + e);
				}
				if (result.equals("success")) {

					responseObj.setEvent("Application Created Successfully");
					responseObj.setStatus(StatusEnum.SUCCESS.name());
					return new Gson().toJson(responseObj);
				} else {
					responseObj.setEvent("Application creation Failed");
					responseObj.setStatus(StatusEnum.FAIL.name());
					return new Gson().toJson(responseObj);
				}
			}else{
				responseObj.setEvent("Application already created");
				responseObj.setStatus(StatusEnum.FAIL.name());
				return new Gson().toJson(responseObj);
			}
		}else{
			responseObj.setEvent("Wrong Credentials");
			responseObj.setStatus(StatusEnum.FAIL.name());
			return new Gson().toJson(responseObj);
			
		}
		
	}
	
	@RequestMapping(value = "/rest/carrerapplicants", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String applicantcreations(HttpServletRequest request,
			@RequestBody Applicants createApplicants) {
		boolean status = appservice.getappemailCheck(createApplicants.getEmail());
		String result = "fail";
		String sendAlertEmail = null;
		logger.info(createApplicants.getJobid());
		String email = applicationService.getAssignEmail(createApplicants.getJobid());
		logger.info(email);
		User user = userService.getUserByEmail(email);
		String teamname = user.getTeamname();
		String organization = user.getOrganization();
		createApplicants.setOrganization(organization);
		logger.info("teamname:" + teamname);
		Integer userid = user.getUserid();
		logger.info("userid:" + userid);
		JobTitle jobtitle = new JobTitle();
		jobtitle.setJobid(createApplicants.getJobid());
		if (status) {
			int applicantid = appservice.getapplicantid(createApplicants.getEmail());
			jobtitle.setApplicantid(applicantid);
			jobtitle.setReferncedemail(email);
			jobtitle.setTeamname(teamname);
			jobtitle.setUserid(userid);
			jobtitle.setSource(createApplicants.getSource());
			sendAlertEmail = supportService.managerEmailForSenderAlert(teamname);
			logger.info("sendAlertEmail:" + sendAlertEmail);
			jobtitle.setSender_email(sendAlertEmail);
			try {

				result = jobtitleService.createApplicantDirectly1(jobtitle);

			} catch (Exception e) {
				logger.error("Exception is: " + e);
			}
			if (result.equals("success")) {

				responseObj.setEvent("Success");
				responseObj.setStatus(StatusEnum.SUCCESS.name());
				return new Gson().toJson(responseObj);
			} else {
				responseObj.setEvent("Fail");
				responseObj.setStatus(StatusEnum.FAIL.name());
				return new Gson().toJson(responseObj);
			}

		} else {

				try {
					
					result = userService.createApplicantManager(createApplicants);
					System.out.println(result);
					String[] arrOfStr = result.split(" ");

					int applicant_id1 = Integer.parseInt(arrOfStr[1]);
					System.out.println(arrOfStr[1]);

					createApplicants.setApplicantid(Integer.parseInt(arrOfStr[1]));
					String results1 = arrOfStr[0];
					if (results1.equals("success")) {
						logger.info("applicant creation");
						jobtitle.setApplicantid(applicant_id1);
						jobtitle.setReferncedemail(email);
						jobtitle.setTeamname(teamname);
						jobtitle.setUserid(userid);
						jobtitle.setSource(createApplicants.getSource());
						sendAlertEmail = supportService.managerEmailForSenderAlert(teamname);
						logger.info("sendAlertEmail:" + sendAlertEmail);
						jobtitle.setSender_email(sendAlertEmail);
						try {

							result = jobtitleService.createApplicantDirectly1(jobtitle);

						} catch (Exception e) {
							logger.error("Exception is: " + e);
						}
						if (result.equals("success")) {

							boolean linkStatus = userService.sendApplicantLink(createApplicants);
							logger.info("linkStatus:" + linkStatus);
							if (linkStatus) {
								responseObj.setEvent("Application Created Successfully");
								responseObj.setStatus(StatusEnum.SUCCESS.name());
								return new Gson().toJson(responseObj);
							} else {
								responseObj.setEvent("Fail");
								responseObj.setStatus(StatusEnum.FAIL.name());
								return new Gson().toJson(responseObj);

							}

						} else {
							responseObj.setEvent("Fail");
							responseObj.setStatus(StatusEnum.FAIL.name());
							return new Gson().toJson(responseObj);
						}
					}
				} catch (Exception e) {

					logger.error("Exception is: " + e);
				}
				responseObj.setEvent("Fail");
				responseObj.setStatus(StatusEnum.FAIL.name());
				return new Gson().toJson(responseObj);
			}
		}
		

	@RequestMapping(value = "/rest/getdatajob", method = RequestMethod.POST)
	public @ResponseBody String sendVideo(@RequestParam(value = "jobid", required = false) int jobid) {
		logger.info("jobid:" + jobid);

		CreateJob createjob = userService.getVideo(jobid);
		System.out.println(createjob.getVideoname());
		//File destinationFile = new File(
			//	context.getRealPath("/WEB-INF/videos") + File.separator + createjob.getVideoname());
				File destinationFile = new File(videosURL  + createjob.getVideoname());
		System.out.println(createjob.getVideoname());
		FileInputStream fileInputStreamReader = null;
		try {
			fileInputStreamReader = new FileInputStream(destinationFile);
			byte[] bytes = new byte[(int) destinationFile.length()];
			fileInputStreamReader.read(bytes);
			String encodedupload1 = new String(Base64.encodeBase64(bytes), "UTF-8");
			System.out.println(encodedupload1);
			createjob.setVideoencoded(encodedupload1);
			String uploaddocument1 = createjob.getUploaddocument1();
			int index = uploaddocument1.lastIndexOf(".");
			int addvalue = index + 1;
			String lastDot = uploaddocument1.substring(addvalue);
			logger.info("lastDot:" + lastDot);
			if (fileInputStreamReader != null)
				fileInputStreamReader.close();
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		String jobdata = gson.toJson(createjob);
		responseObj.setEvent("Success");
		responseObj.setStatus(jobdata);
		return new Gson().toJson(responseObj);

	}

	@RequestMapping(value = "/rest/getAllCountries", method = RequestMethod.POST)
	public @ResponseBody String getAllCountriesByPhoneCode(
			@RequestParam(value = "phoneCode", required = false) int phoneCode) {
		logger.info("phoneCode:" + phoneCode);
		List<CreateJob> listCountries = null;
		ResponseObj responseObj = null;
		try {
			responseObj = new ResponseObj();
			listCountries = applicationSettingsService.getCountriesByPhoneCode(phoneCode);
			Gson gson = new Gson();
			String countriesStr = gson.toJson(listCountries);
			responseObj.setEvent("Success");
			responseObj.setStatus(countriesStr);

		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return new Gson().toJson(responseObj);
	}

	@RequestMapping(value = "/rest/getStates", method = RequestMethod.POST)
	public @ResponseBody String getAllStates(@RequestParam(value = "countryId", required = false) int countryId) {
		logger.info("countryId:" + countryId);
		ResponseObj responseObj = null;
		List<CreateJob> liststates = null;
		try {
			responseObj = new ResponseObj();
			liststates = applicationSettingsService.getAllStates(countryId);
			Gson gson = new Gson();
			String statesStr = gson.toJson(liststates);
			responseObj.setEvent("Success");
			responseObj.setStatus(statesStr);

		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return new Gson().toJson(responseObj);
	}

	@RequestMapping(value = "/rest/getAllCities", method = RequestMethod.POST)
	public @ResponseBody String getAllCities(@RequestParam(value = "state_id", required = false) int state_id) {
		logger.info("state_id:" + state_id);
		ResponseObj responseObj = null;
		List<CreateJob> listcities = null;
		try {

			responseObj = new ResponseObj();
			listcities = applicationSettingsService.getAllCities(state_id);
			Gson gson = new Gson();
			String citiesStr = gson.toJson(listcities);
			responseObj.setEvent("Success");
			responseObj.setStatus(citiesStr);

		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return new Gson().toJson(responseObj);
	}
	
	
	@RequestMapping(value = "/rest/getvideourl", method = RequestMethod.POST)
	public @ResponseBody String getvideourl(@RequestParam(value = "company", required = false) String company) {
		logger.info("company:" + company);
		ResponseObj responseObj = null;
		Customer listcities = null;
		try {

			responseObj = new ResponseObj();
			listcities =customerservice.getvideourl(company) ;
			Gson gson = new Gson();
			String citiesStr = gson.toJson(listcities);
			responseObj.setEvent("Success");
			responseObj.setStatus(citiesStr);

		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return new Gson().toJson(responseObj);
	}
	
	@RequestMapping(value = "/rest/shareemail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String shareemail(@RequestBody Sharejobs sharejob) {
		ResponseObj responseObj = new ResponseObj();
		//int result = applicantService.
		boolean result = applicantService.shareemail(sharejob);
		if(result){
			boolean linkStatus = userService.sharejob(sharejob);
			if (!linkStatus) {
				responseObj.setEvent("Fail");
				responseObj.setStatus(StatusEnum.FAIL.name());
				return new Gson().toJson(responseObj);
			}
			responseObj.setEvent("Success");
			responseObj.setStatus(StatusEnum.SUCCESS.name());
			return new Gson().toJson(responseObj);
		}
			
		responseObj.setEvent("Fail");
		responseObj.setStatus(StatusEnum.FAIL.name());
		return new Gson().toJson(responseObj);
		
	}
	
	

	@RequestMapping(value = "/rest/sendJobsDocuments", method = RequestMethod.POST)
	public @ResponseBody String sendJobsDocuments(@RequestParam(value = "jobid", required = false) int jobid) {
		logger.info("jobid:" + jobid);
		JobsDocuments jobsDocuments = null;
		String pdffile = null;
		String pdffile1 = null;
		DocumentDeleteUtility documentDelete = null;
		String beforeDot = null;
		try {
			jobsDocuments = userService.getJobsDocuments(jobid);
			if (jobsDocuments.getUploaddocument1() != null) {
				String uploaddocument1 = jobsDocuments.getUploaddocument1();
				int index = uploaddocument1.lastIndexOf(".");
				int addvalue = index + 1;
				String lastDot = uploaddocument1.substring(addvalue);
				logger.info("lastDot:" + lastDot);
				if (lastDot.equals("docx")) {
			//		String filename = context.getRealPath("/WEB-INF/uploaded/") + jobsDocuments.getUploaddocument1();
					String filename =uploadedURL + jobsDocuments.getUploaddocument1();
					String getAfterDot = uploaddocument1;
					int lastIndxDot = getAfterDot.lastIndexOf('.');
					if (lastIndxDot != -1) {
						beforeDot = getAfterDot.substring(0, lastIndxDot);
						logger.info("afterDot:" + beforeDot);
					}
				//	pdffile = context.getRealPath("/WEB-INF/uploaded/") + beforeDot + ".pdf";
					pdffile = uploadedURL + beforeDot + ".pdf";
					pdffile1 = beforeDot + ".pdf";
					WordConvertPDF wordconvertpdf = new WordConvertPDF();
					wordconvertpdf.ConvertToPDF(filename, pdffile);
					File destinationFile = new File(pdffile);
					FileInputStream fileInputStreamReader = null;
					try {
						fileInputStreamReader = new FileInputStream(destinationFile);
						byte[] bytes = new byte[(int) destinationFile.length()];
						fileInputStreamReader.read(bytes);
						String encodedupload1 = new String(Base64.encodeBase64(bytes), "UTF-8");
						jobsDocuments.setUploaddocument1(encodedupload1);

					} catch (Exception e) {
						logger.error("Exception is: " + e);
					}
					try {

						if (fileInputStreamReader != null)
							fileInputStreamReader.close();
					} catch (Exception e) {
						logger.error("Exception is: " + e);
					}
				} else {

				//	File destinationFile = new File(context.getRealPath("/WEB-INF/uploaded") + File.separator
						//	+ jobsDocuments.getUploaddocument1());
					File destinationFile = new File(uploadedURL
									+ jobsDocuments.getUploaddocument1());
					pdffile1 = jobsDocuments.getUploaddocument1();
					FileInputStream fileInputStreamReader = null;
					try {

						fileInputStreamReader = new FileInputStream(destinationFile);
						byte[] bytes = new byte[(int) destinationFile.length()];
						fileInputStreamReader.read(bytes);
						String encodedupload1 = new String(Base64.encodeBase64(bytes), "UTF-8");
						jobsDocuments.setUploaddocument1(encodedupload1);
					} catch (Exception e) {
						logger.error("Exception is: " + e);
					}

					try {
						if (fileInputStreamReader != null)
							fileInputStreamReader.close();
					} catch (Exception e) {
						logger.error("Exception is: " + e);
					}
				}

				int result = applicantService.updateDocument1(pdffile1, jobid);
				//File destinationFile = new File(context.getRealPath("/WEB-INF/uploaded"));
				File destinationFile = new File(uploadedURL);
				String docXFileName = beforeDot + ".docx";
				String docFileName = beforeDot + ".doc";
				if (result > 0) {
					documentDelete = new DocumentDeleteUtility();
					documentDelete.deleteDocument(destinationFile, docXFileName);
				}
			}

			if (jobsDocuments.getUploaddocument2() != null) {

				String uploaddocument2 = jobsDocuments.getUploaddocument2();
				int index = uploaddocument2.lastIndexOf(".");
				int addvalue = index + 1;
				String lastDot = uploaddocument2.substring(addvalue);
				logger.info("lastDot:" + lastDot);

				if (lastDot.equals("docx"))

				{

				//	String filename = context.getRealPath("/WEB-INF/uploaded/") + jobsDocuments.getUploaddocument2();
					String filename = uploadedURL + jobsDocuments.getUploaddocument2();
					String getAfterDot = uploaddocument2;
					int lastIndxDot = getAfterDot.lastIndexOf('.');
					if (lastIndxDot != -1) {
						beforeDot = getAfterDot.substring(0, lastIndxDot);
						logger.info("afterDot:" + beforeDot);
					}
				//	pdffile = context.getRealPath("/WEB-INF/uploaded/") + beforeDot + ".pdf";
					pdffile = uploadedURL +beforeDot + ".pdf";
					pdffile1 = beforeDot + ".pdf";

					WordConvertPDF wordconvertpdf = new WordConvertPDF();
					wordconvertpdf.ConvertToPDF(filename, pdffile);
					File destinationFile = new File(pdffile);
					FileInputStream fileInputStreamReader = null;
					try {
						fileInputStreamReader = new FileInputStream(destinationFile);
						byte[] bytes = new byte[(int) destinationFile.length()];
						fileInputStreamReader.read(bytes);
						String encodedupload1 = new String(Base64.encodeBase64(bytes), "UTF-8");
						jobsDocuments.setUploaddocument2(encodedupload1);

					} catch (Exception e) {

						logger.error("Exception is: " + e);
					}
					try {

						if (fileInputStreamReader != null)
							fileInputStreamReader.close();
					} catch (Exception e) {
						logger.error("Exception is: " + e);
					}

				} else {
				//	File destinationFile1 = new File(context.getRealPath("/WEB-INF/uploaded") + File.separator
						//	+ jobsDocuments.getUploaddocument2());
				File destinationFile1 = new File(uploadedURL
									+ jobsDocuments.getUploaddocument2());
					pdffile1 = jobsDocuments.getUploaddocument2();
					FileInputStream fileInputStreamReader1 = null;

					try {

						fileInputStreamReader1 = new FileInputStream(destinationFile1);
						byte[] bytes = new byte[(int) destinationFile1.length()];
						fileInputStreamReader1.read(bytes);
						String encodeupload2 = new String(Base64.encodeBase64(bytes), "UTF-8");
						jobsDocuments.setUploaddocument2(encodeupload2);

					} catch (Exception e) {

						logger.error("Exception is: " + e);
					}
					try {
						if (fileInputStreamReader1 != null)
							fileInputStreamReader1.close();
					} catch (Exception e) {
						logger.error("Exception is: " + e);
					}
				}

				int result = applicantService.updateDocument2(pdffile1, jobid);
			//	File destinationFile = new File(context.getRealPath("/WEB-INF/uploaded"));
				File destinationFile = new File(uploadedURL);
				String docXFileName = beforeDot + ".docx";
				String docFileName = beforeDot + ".doc";
				if (result > 0) {

					documentDelete = new DocumentDeleteUtility();
					documentDelete.deleteDocument(destinationFile, docXFileName);
				}
			}
			String jobsDocumentsStr = gson.toJson(jobsDocuments);
			responseObj.setEvent("Success");
			responseObj.setStatus(jobsDocumentsStr);
			return new Gson().toJson(responseObj);

		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		responseObj.setEvent("Fail");
		responseObj.setStatus(StatusEnum.FAIL.name());
		return new Gson().toJson(responseObj);
	}

}
