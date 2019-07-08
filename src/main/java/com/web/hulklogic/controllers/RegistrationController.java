package com.web.hulklogic.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.web.hulklogic.entity.AlertDetails;
import com.web.hulklogic.entity.CreateJob;
import com.web.hulklogic.entity.GetEmployee;
import com.web.hulklogic.entity.JobTitle;
import com.web.hulklogic.entity.User;
import com.web.hulklogic.service.ApplicationService;
import com.web.hulklogic.service.EditDetailsService;
import com.web.hulklogic.service.GetDetailsService;
import com.web.hulklogic.service.SupportService;
import com.web.hulklogic.service.UserService;

@Controller
public class RegistrationController {
	private static Logger logger = Logger.getLogger(RegistrationController.class);

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
	@Autowired
	UserService userService;

	@Autowired
	private EditDetailsService editDetailService;
	@Autowired
	private SupportService supportService;
	@Autowired
	private GetDetailsService getDetailsService;
	@Autowired
	private ApplicationService applicantionService;

	@RequestMapping(value = "/login/save", method = RequestMethod.POST)
	public @ResponseBody String saveUser(HttpSession session, @RequestParam("userInfo") String userStr, Model map) {
		if (null == userStr || userStr.trim().isEmpty()) {
			return "failure";
		}

		User user = new Gson().fromJson(userStr, User.class);
		boolean status = userService.getUsernameCheck(user.getEmail());

		if (status) {
			return "Email is already registered! Please choose another one";
		}

		boolean statusconcat = userService.getContactnoCheck(user.getContactno());

		if (statusconcat) {
			return "Mobile Number is registered! Please choose another one";
		}

		String currentemail = (String) session.getAttribute("userName");
		String organization = (String) session.getAttribute("organization");
		String teamnames = (String) session.getAttribute("teamname");
		String logourl = (String) session.getAttribute("logourl");
		logger.info("currentemail:" + currentemail);
		user.setReferencedmail(currentemail);
		user.setOrganization(organization);
		if (null != user.getEmail() && !user.getEmail().trim().isEmpty()) {
			User existingUser = userService.getUserByEmail(user.getEmail());
			try {

				if (existingUser == null) {
					if (user.getRoleid() == 1) {
						String numbers = "0123456789";
						String values = numbers;
						int len = 6;
						Random rndm_method = new Random();
						char[] password = new char[len];
						for (int i = 0; i < len; i++) {
							password[i] = values.charAt(rndm_method.nextInt(values.length()));
						}
						String text = String.valueOf(password);
						String team = "Team";
						String teamname = team.concat(text);
						user.setTeamname(teamname);
						user.setTemplatedesignid(1);
					} else if (user.getRoleid() == 2) {
						User getmanager = userService.getUserByEmail(currentemail);
						int managercount = userService.checkmanager(organization);
						user.setTemplatedesignid(getmanager.getTemplatedesignid());
						if (managercount == 1) {
							System.out.println(managercount);
							user.setTeamname(teamnames);
						} else {

							user.setTeamname("N/A");
						}
					}
					user.setStatus((byte) 0);
					user.setLogourl(logourl);
					int result = userService.saveUser(user);
					if (result > 0) {
						User getUser = userService.getUserByEmail(user.getEmail());
						if (getUser.getUserid() != null) {
							boolean linkStatus = userService.sendActivationLink(getUser);
							logger.info("linkStatus:" + linkStatus);
							if (!linkStatus) {
								int deleterecord = editDetailService.deleteUser(getUser.getUserid());
								logger.info("deleterecord:" + deleterecord);
								return "email";
							}
							return "success";

						}
						return "nouser";
					}
				} else {
					return "user exists";
				}
				return "fail";
			} catch (Exception e) {
				logger.error("Exception is: " + e);

			}
		}
		return "fail";
	}

	@RequestMapping(value = "/manager/getusers", method = RequestMethod.POST)
	public @ResponseBody String getUsers() {
		List<User> users = userService.getAllUsers();
		return new Gson().toJson(users);
	}

	/*
	 * Adding Job and searching the employees and sending alert message to
	 * manager if recruiter add the job,
	 * 
	 */

	@RequestMapping(value = "/user/jobcreation", method = RequestMethod.POST)
	public @ResponseBody String jobcreation(HttpSession session, @RequestParam("jobcreation") String jobStr) {

		if (null == jobStr || jobStr.trim().isEmpty()) {
			return "failure";
		}

		CreateJob createjob = new Gson().fromJson(jobStr, CreateJob.class);
		String jobtitle = createjob.getJobtitle();
		String upperCaseJobtitle = jobtitle.toUpperCase();
		createjob.setJobtitle(upperCaseJobtitle);
		String location = createjob.getLocation();
		String upperCaseLocation = location.toUpperCase();
		createjob.setLocation(upperCaseLocation);
		String organization = (String) session.getAttribute("organization");
		createjob.setCompany(organization);
		/*
		 * Getting the session user details
		 */
		String currenLoginEmail = (String) session.getAttribute("userName");
		Integer templatedesignid = (Integer) session.getAttribute("templatedesignid");
		String teamname = (String) session.getAttribute("teamname");
		String roleName = (String) session.getAttribute("roleName");
		Integer userId = (Integer) session.getAttribute("userid");

		logger.info("currenLoginEmail:" + currenLoginEmail);
		logger.info("teamname:" + teamname);
		logger.info("roleName:" + roleName);
		logger.info("userId:" + userId);

		createjob.setCurrentLogin(currenLoginEmail);
		createjob.setTeamname(teamname);

		String result = "fail";
		String document1 = null;
		String document2 = null;
		String sendAlertEmail = null;
		String directassign = null;
		String videoname = null;
		boolean data = false;

		try {
			/*
			 * Storing job videos and files with current timestamp
			 */
			Date todayDate = new Date();
			logger.info("Checking the videoname:" + createjob.getVideoname());
			if (createjob.getFiletype3() != null) {

				byte[] videodecode = DatatypeConverter.parseBase64Binary(createjob.getVideoupload());
				videoname = createjob.getVideoname() + "_" + todayDate.getYear() + "_" + todayDate.getMonth() + "_"
						+ todayDate.getDay() + "_" + todayDate.getHours() + "_" + todayDate.getSeconds() + "." + "mp4";

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

				File destinationFile1 = new File(uploadedURL + document1);
				FileOutputStream fois1 = new FileOutputStream(destinationFile1);

				fois1.write(documentdecode1);

				try {
					if (fois1 != null)
						fois1.close();
				} catch (Exception e) {
					logger.error("Exception is: " + e);
				}

				String filename = uploadedURL + document1;
				logger.info(" geting file from upload folder:" + filename);

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

				File destination2 = new File(uploadedURL + document2);
				FileOutputStream fois2 = new FileOutputStream(destination2);
				fois2.write(documentdecode2);

				try {
					if (fois2 != null)
						fois2.close();
				} catch (Exception e) {
					logger.error("Exception is: " + e);
				}

				String filename1 = uploadedURL + document2;
				logger.info(" geting file from upload folder:" + filename1);
				createjob.setDocument2(document2);

			} else {
				createjob.setDocument2(null);
			}

			/*
			 * Sending request to the service layer
			 */
			result = userService.createJob(createjob);

			if (roleName.equals("ROLE_RECRUITER")) {

				String[] jobid = result.split(" ");
				String lastjobid = jobid[1];
				logger.info("lastjobid:" + lastjobid);
				String number = lastjobid;
				int jobId = Integer.parseInt(number);
				logger.info("Convert String to Int Job Id:" + jobId);
				sendAlertEmail = supportService.managerEmailForSenderAlert(teamname);
				AlertDetails alertDetails = new AlertDetails();
				alertDetails.setUserid(userId);
				alertDetails.setRef_jobid(jobId);
				alertDetails.setJobassignemail(currenLoginEmail);
				alertDetails.setSender_email(sendAlertEmail);
				alertDetails.setAlert_type("Job Alert");
				boolean status = userService.jobassignRecruiter(alertDetails);
				logger.info(status);
				if (status) {
					List<GetEmployee> employeelist = getDetailsService.getemployeedetails(createjob.getJobtitle(),
							createjob.getCompany());
					logger.info(employeelist.size());
					if (employeelist.size() > 0) {
						for (int k = 0; k < employeelist.size(); k++) {

							if (employeelist.get(k).getDesignation() != null) {

								String tokens = employeelist.get(k).getDesignation();
								logger.info(employeelist.get(k).getDesignation());
								data = (tokens.toLowerCase()).equals(createjob.getJobtitle().toLowerCase());
								if (data) {
									GetEmployee getemployee = new GetEmployee();
									getemployee.setEmployeeid(employeelist.get(k).getEmployeeid());
									getemployee.setEmail(employeelist.get(k).getEmail());
									getemployee.setDesignation(employeelist.get(k).getDesignation());
									getemployee.setOrganization(employeelist.get(k).getOrganization());
									getemployee.setJobid(lastjobid);
									getemployee.setLocation(createjob.getLocation());
									getemployee.setTemplatedesignid(templatedesignid);
									String refferalresult = applicantionService.saveemployeereferraljobs(getemployee);
									if (refferalresult == "success") {
										boolean linkStatus = userService.sendRefferalLink(getemployee);
										logger.info("linkStatus:" + linkStatus);
										if (!linkStatus) {
											int deleterecord = editDetailService.deleteJob(jobId);
											logger.info("deleterecord:" + deleterecord);
											return "fail";
										}
										return result;
									} else {
										return "fail";
									}
								}
							} else {
								return result;
							}
						}
					} else {
						return result;
					}

				}

			} else if (roleName.equals("ROLE_BASICMANAGER")) {
				String[] jobid = result.split(" ");
				String lastjobid = jobid[1];
				logger.info("lastjobid:" + lastjobid);
				String number = lastjobid;
				int jobId = Integer.parseInt(number);
				AlertDetails alertDetails = new AlertDetails();
				alertDetails.setUserid(userId);
				alertDetails.setRef_jobid(jobId);
				alertDetails.setJobassignemail(currenLoginEmail);
				boolean status = userService.jobassignManager(alertDetails);
				logger.info(status);
				if (status) {
					List<GetEmployee> employeelist = getDetailsService.getemployeedetails(createjob.getJobtitle(),
							createjob.getCompany());
					if (employeelist.size() > 0) {
						for (int k = 0; k < employeelist.size(); k++) {

							if (employeelist.get(k).getDesignation() != null) {

								String tokens = employeelist.get(k).getDesignation();
								logger.info(employeelist.get(k).getDesignation());
								data = (tokens.toLowerCase()).equals(jobtitle.toLowerCase());
								if (data) {
									GetEmployee getemployee = new GetEmployee();
									getemployee.setEmployeeid(employeelist.get(k).getEmployeeid());
									getemployee.setEmail(employeelist.get(k).getEmail());
									getemployee.setDesignation(employeelist.get(k).getDesignation());
									getemployee.setOrganization(employeelist.get(k).getOrganization());
									getemployee.setJobid(lastjobid);
									getemployee.setLocation(createjob.getLocation());
									getemployee.setTemplatedesignid(templatedesignid);
									String refferalresult = applicantionService.saveemployeereferraljobs(getemployee);
									if (refferalresult == "success") {
										boolean linkStatus = userService.sendRefferalLink(getemployee);
										logger.info("linkStatus:" + linkStatus);
										if (!linkStatus) {
											int deleterecord = editDetailService.deleteJob(jobId);
											logger.info("deleterecord:" + deleterecord);
											return "fail";
										}
										return result;
									} else {
										return "fail";
									}
								}
							} else {
								return result;
							}
						}
					} else {

						return result;
					}

				}

			} else if (roleName.equals("ROLE_MANAGER")) {
				String[] jobid = result.split(" ");
				String lastjobid = jobid[1];
				logger.info("lastjobid:" + lastjobid);
				String number = lastjobid;
				int jobId = Integer.parseInt(number);
				int count = userService.checkTeamCount(teamname);
				logger.info("Team count " + count);
				if (count == 1) {
					AlertDetails alertDetails = new AlertDetails();
					alertDetails.setUserid(userId);
					alertDetails.setRef_jobid(jobId);
					alertDetails.setJobassignemail(currenLoginEmail);
					boolean status = userService.jobassignManager(alertDetails);
					logger.info(status);
					if (status) {
						List<GetEmployee> employeelist = getDetailsService.getemployeedetails(createjob.getJobtitle(),
								createjob.getCompany());
						if (employeelist.size() > 0) {
							for (int k = 0; k < employeelist.size(); k++) {

								if (employeelist.get(k).getDesignation() != null) {

									String tokens = employeelist.get(k).getDesignation();
									logger.info(employeelist.get(k).getDesignation());
									data = (tokens.toLowerCase()).equals(jobtitle.toLowerCase());
									if (data) {
										GetEmployee getemployee = new GetEmployee();
										getemployee.setEmployeeid(employeelist.get(k).getEmployeeid());
										getemployee.setEmail(employeelist.get(k).getEmail());
										getemployee.setDesignation(employeelist.get(k).getDesignation());
										getemployee.setOrganization(employeelist.get(k).getOrganization());
										getemployee.setJobid(lastjobid);
										getemployee.setLocation(createjob.getLocation());
										getemployee.setTemplatedesignid(templatedesignid);
										String refferalresult = applicantionService
												.saveemployeereferraljobs(getemployee);
										if (refferalresult == "success") {
											boolean linkStatus = userService.sendRefferalLink(getemployee);
											logger.info("linkStatus:" + linkStatus);
											if (!linkStatus) {
												int deleterecord = editDetailService.deleteJob(jobId);
												logger.info("deleterecord:" + deleterecord);
												return "fail";
											}

											return result;
										} else {
											return "fail";
										}
									}

								} else {

									return result;
								}

							}
						} else {

							return result;
						}

					}

				} else if (count == 2) {
					directassign = userService.getRecruiterEmail(teamname);
					sendAlertEmail = supportService.managerEmailForSenderAlert(teamname);
					AlertDetails alertDetails = new AlertDetails();
					alertDetails.setUserid(userId);
					alertDetails.setRef_jobid(jobId);
					alertDetails.setJobassignemail(directassign);
					alertDetails.setSender_email(sendAlertEmail);
					alertDetails.setAlert_type("Job Alert");
					boolean status = userService.jobassignRecruiter(alertDetails);
					logger.info(status);
					if (status) {

						List<GetEmployee> employeelist = getDetailsService.getemployeedetails(createjob.getJobtitle(),
								createjob.getCompany());
						if (employeelist.size() > 0) {
							for (int k = 0; k < employeelist.size(); k++) {

								if (employeelist.get(k).getDesignation() != null) {

									String tokens = employeelist.get(k).getDesignation();
									logger.info(employeelist.get(k).getDesignation());
									data = (tokens.toLowerCase()).equals(jobtitle.toLowerCase());
									if (data) {
										GetEmployee getemployee = new GetEmployee();
										getemployee.setEmployeeid(employeelist.get(k).getEmployeeid());
										getemployee.setEmail(employeelist.get(k).getEmail());
										getemployee.setDesignation(employeelist.get(k).getDesignation());
										getemployee.setOrganization(employeelist.get(k).getOrganization());
										getemployee.setFirstname(employeelist.get(k).getFirstname());
										getemployee.setLastname(employeelist.get(k).getLastname());
										getemployee.setJobid(lastjobid);
										getemployee.setLocation(createjob.getLocation());
										getemployee.setTemplatedesignid(templatedesignid);
										String refferalresult = applicantionService
												.saveemployeereferraljobs(getemployee);
										if (refferalresult == "success") {
											boolean linkStatus = userService.sendRefferalLink(getemployee);
											logger.info("linkStatus:" + linkStatus);
											if (!linkStatus) {
												int deleterecord = editDetailService.deleteJob(jobId);
												logger.info("deleterecord:" + deleterecord);
												return "fail";
											}

											return result;
										} else {
											return "fail";
										}
									}
								} else {
									return result;
								}

							}
						} else {
							return result;
						}

					}
				} else if (count >= 3) {

					List<GetEmployee> employeelist = getDetailsService.getemployeedetails(createjob.getJobtitle(),
							createjob.getCompany());
					if (employeelist.size() > 0) {
						for (int k = 0; k < employeelist.size(); k++) {

							if (employeelist.get(k).getDesignation() != null) {

								String tokens = employeelist.get(k).getDesignation();
								logger.info(employeelist.get(k).getDesignation());
								data = (tokens.toLowerCase()).equals(jobtitle.toLowerCase());
								if (data) {
									GetEmployee getemployee = new GetEmployee();
									getemployee.setEmployeeid(employeelist.get(k).getEmployeeid());
									getemployee.setEmail(employeelist.get(k).getEmail());
									getemployee.setDesignation(employeelist.get(k).getDesignation());
									getemployee.setOrganization(employeelist.get(k).getOrganization());
									getemployee.setJobid(lastjobid);
									getemployee.setLocation(createjob.getLocation());
									getemployee.setTemplatedesignid(templatedesignid);
									String refferalresult = applicantionService.saveemployeereferraljobs(getemployee);
									if (refferalresult == "success") {
										boolean linkStatus = userService.sendRefferalLink(getemployee);
										logger.info("linkStatus:" + linkStatus);
										if (!linkStatus) {
											int deleterecord = editDetailService.deleteJob(jobId);
											logger.info("deleterecord:" + deleterecord);
											return "fail";
										}

										return result;
									} else {
										return "fail";
									}
								}

							} else {

								return result;
							}

						}
					} else {
						return result;
					}

				}
			}
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return "fail";
	}

	@RequestMapping(value = "/manager/saveJobsKeyWord", method = RequestMethod.POST)
	public @ResponseBody String saveJobKeyWord(@RequestParam("jobskeyword") String jobskeywordStr,
			@RequestParam("jobid") int jobid, @RequestParam("experience") int experience) {
		logger.info("jobskeyword:" + jobskeywordStr);
		logger.info("jobid:" + jobid);
		logger.info("experience:" + experience);
		String result = null;
		try {
			result = userService.saveJobsKeyWords(jobskeywordStr, experience, jobid);
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return result;
	}

	@RequestMapping(value = "/manager/saveCvsKeyWord", method = RequestMethod.POST)
	public @ResponseBody String saveApplicantsKeyWord(@RequestParam("cvskeyword") String cvskeywordStr,
			@RequestParam("applicantid") int applicantid, @RequestParam("experience") int experience) {
		logger.info("cvskeywordStr:" + cvskeywordStr);
		logger.info("applicantid:" + applicantid);
		logger.info("experience:" + experience);
		String result = null;
		try {
			result = userService.saveApplicantsKeyWords(cvskeywordStr, experience, applicantid);
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return result;
	}

	@RequestMapping(value = "/user/JobAssigns", method = RequestMethod.POST)
	public @ResponseBody String assignJobsToRecuiter(HttpSession session,
			@RequestParam("recuiteremail") String recuiteremail, @RequestParam("jobid") int jobid) {
		logger.info("recuiteremail:" + recuiteremail);
		logger.info("jobid:" + jobid);
		Integer userid = (Integer) session.getAttribute("userid");
		logger.info("userid:" + userid);
		String roleName = (String) session.getAttribute("roleName");
		logger.info("roleName:" + roleName);
		String teamname = (String) session.getAttribute("teamname");
		logger.info("teamname:" + teamname);
		String sendAlertEmail = null;
		String result = "fail";
		if ("ROLE_RECRUITER".equalsIgnoreCase(roleName)) {
			sendAlertEmail = supportService.managerEmailForSenderAlert(teamname);
			logger.info("sendAlertEmail:" + sendAlertEmail);
		} else {
			sendAlertEmail = supportService.recruiterEmailForSenderAlert(jobid);
		}
		JobTitle jobtitle = new JobTitle();
		jobtitle.setJobid(jobid);
		jobtitle.setUserid(userid);
		jobtitle.setReferncedemail(recuiteremail);
		try {
			result = userService.saveAssignJobs(jobtitle);
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return result;
	}

	@RequestMapping(value = "/manager/getrecruiters", method = RequestMethod.POST)
	public @ResponseBody List<User> getrecruiters(@RequestParam("rolename") String rolename) {
		logger.info("rolename:" + rolename);
		int roleid = 0;
		List<User> users = null;

		if (rolename.equals("ROLE_RECRUITER")) {

			roleid = userService.getRoleId(rolename);
		} else {
			roleid = userService.getRoleId(rolename);
		}
		try {
			logger.info("roleid:" + roleid);
			users = userService.getAllRecruiters(roleid);
		} catch (Exception e) {

			logger.error("Exception is: " + e);
		}
		return users;
	}

	@RequestMapping(value = "/login/logout", method = RequestMethod.GET)
	public @ResponseBody String logout(Model map, HttpSession session, HttpServletRequest req) {
		Integer userId = (Integer) session.getAttribute("userid");
		logger.info("userId:" + userId);
		int deleterecord = editDetailService.saveLogoutInfo(userId);
		logger.info("deleterecord:" + deleterecord);
		req.getSession().invalidate();
		SecurityContextHolder.clearContext();
		if (deleterecord == 1) {
			return "success";
		}
		return "fail";

	}

	@RequestMapping(value = "/login/idleStatus", method = RequestMethod.GET)
	public @ResponseBody String updateLogout(Model map, HttpSession session) {
		String sessionId = (String) session.getAttribute("sessionId");
		logger.info("sessionId:" + sessionId);
		Integer userId = (Integer) session.getAttribute("userid");
		logger.info("userId:" + userId);
		User getSessionId = editDetailService.getSessions(userId);
		int idleState = 0;
		if (getSessionId.getSession_id1().equals(sessionId)) {

			idleState = editDetailService.idleStateSession1(userId);
		}
		if (idleState == 1) {
			return "success";
		}

		return "fail";
	}

	@RequestMapping(value = "/login/sessionLogout", method = RequestMethod.GET)
	public @ResponseBody String logutSession(Model map, HttpSession session, HttpServletRequest req) {
		String sessionId = (String) session.getAttribute("sessionId");
		logger.info("sessionId:" + sessionId);
		Integer userId = (Integer) session.getAttribute("userid");
		logger.info("userId:" + userId);

		User getSessionId = editDetailService.getSessions(userId);
		logger.info("getSessionId:" + getSessionId.getSession_id1());
		int logutSessions = 0;
		if (getSessionId.getSession_id1() != null) {
			if (getSessionId.getSession_id1().equals(sessionId)) {
				logger.info("sessions are  same");

				logutSessions = editDetailService.logoutSession1(userId);
			} else {

				logger.info("sessions are not same");
				req.getSession().invalidate();
				SecurityContextHolder.clearContext();
				return "success";
			}
		} else {

			logger.info("sessions are not same");
			req.getSession().invalidate();
			SecurityContextHolder.clearContext();
			return "success";
		}
		if (logutSessions == 1) {
			req.getSession().invalidate();
			SecurityContextHolder.clearContext();
			return "success";
		}

		return "fail";
	}

	@RequestMapping(value = "/login/adminsessionLogout", method = RequestMethod.GET)
	public @ResponseBody String adminlogutSession(Model map, HttpSession session, HttpServletRequest req) {
		String sessionId = (String) session.getAttribute("sessionId");
		logger.info("sessionId:" + sessionId);
		Integer userId = (Integer) session.getAttribute("userid");
		logger.info("userId:" + userId);
		req.getSession().invalidate();
		SecurityContextHolder.clearContext();
		return "success";
	}

	@RequestMapping(value = "/login/checkLogin", method = RequestMethod.POST)
	public @ResponseBody String checkLoginStatus(Model map, HttpServletRequest req, HttpSession session) {
		String sessionId = req.getSession().getId();
		req.getSession().setAttribute("sessionId", sessionId);
		logger.info("sessionId :" + sessionId);
		Integer userId = (Integer) session.getAttribute("userid");
		logger.info("userId:" + userId);
		String teamname = (String) session.getAttribute("teamname");
		logger.info("teamname:" + teamname);
		int checkUserLogin = editDetailService.checkLoginUser(userId);
		logger.info("checkUserLogin :" + checkUserLogin);

		if (checkUserLogin == 0) {
			editDetailService.insertLoginUser(userId, sessionId, teamname);
		}

		else if (checkUserLogin == 1) {

			User getSessionId = editDetailService.getSessions(userId);

			if (getSessionId.getSession_id1() == null) {

				editDetailService.insertSession1(userId, sessionId);

			}

			else {

				if (getSessionId.getSession_id1().equals(sessionId)) {

				} else {

					return "Out of limit";
				}

			}
		}

		return "";
	}

	@RequestMapping(value = "/login/loginthirdDevice", method = RequestMethod.GET)
	public @ResponseBody String loginWithThirdDevice(Model map, HttpSession session, HttpServletRequest req) {
		String sessionId = (String) session.getAttribute("sessionId");
		logger.info("sessionId:" + sessionId);
		Integer userId = (Integer) session.getAttribute("userid");
		logger.info("userId:" + userId);
		User getSessionId = editDetailService.getSessions(userId);

		// if(getSessionId.getSession_id1()!=null) {
		// req.getSession().invalidate(); SecurityContextHolder.clearContext();

		int deleteSession = editDetailService.deleteSessions(userId);
		if (deleteSession == 1) {
			editDetailService.insertSession1(userId, sessionId);
		}

		return "";
	}

	@RequestMapping(value = "/user/getStatesAndCities", method = RequestMethod.POST)
	public @ResponseBody List<User> getCityAndStateInfo(@RequestParam("phonecode") int phonecode) {
		logger.info("phonecode:" + phonecode);
		List<User> getCitiesStates = null;
		try {
			getCitiesStates = userService.getCityAndStateNames(phonecode);
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return getCitiesStates;
	}

	@RequestMapping(value = "/login/deleteSession", method = RequestMethod.GET)
	public @ResponseBody String invalidSession(HttpSession session, HttpServletRequest req) {
		String sessionId = (String) session.getAttribute("sessionId");
		logger.info("sessionId:" + sessionId);
		Integer userId = (Integer) session.getAttribute("userid");
		logger.info("userId:" + userId);

		if (userId == null && sessionId == null) {
			return "success";
		}

		else {

			User getSessionId = editDetailService.getSessions(userId);
			logger.info("sessionIds:" + getSessionId.getSession_id1());
			if (!getSessionId.getSession_id1().equals(sessionId)) {
				req.getSession().invalidate();
				SecurityContextHolder.clearContext();
				logger.info(" delete the sessions");
				return "success";
			}
		}
		return "";

	}
	// /login/idleStatus

}