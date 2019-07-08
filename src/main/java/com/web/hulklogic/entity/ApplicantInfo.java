package com.web.hulklogic.entity;

public class ApplicantInfo {

	private String email;
	private String contactno;
	private String jobtitle;
	private String name;
	private Integer app_id;
	private String status;
	private String comment;
	private Integer userid;
	private String senderEmail;
	private String source;
	private String profilename;
	private String applicantdetails_id;
	private Integer jobid;
	private String profilejson;
	
	public String getProfilejson() {
		return profilejson;
	}

	public void setProfilejson(String profilejson) {
		this.profilejson = profilejson;
	}

	public String getProfilename() {
		return profilename;
	}

	public void setProfilename(String profilename) {
		this.profilename = profilename;
	}

	public String getApplicantdetails_id() {
		return applicantdetails_id;
	}

	public void setApplicantdetails_id(String applicantdetails_id) {
		this.applicantdetails_id = applicantdetails_id;
	}

	public Integer getJobid() {
		return jobid;
	}

	public void setJobid(Integer jobid) {
		this.jobid = jobid;
	}

	

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getContactno() {
		return contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	public Integer getApp_id() {
		return app_id;
	}

	public void setApp_id(Integer app_id) {
		this.app_id = app_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJobtitle() {
		return jobtitle;
	}

	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getSenderEmail() {
		return senderEmail;
	}

	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

}
