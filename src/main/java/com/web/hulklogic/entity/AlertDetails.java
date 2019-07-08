package com.web.hulklogic.entity;

import java.util.Date;

public class AlertDetails {
	private Integer userid;
	private String created_alert;
	private Integer ref_jobid;
	private Integer ref_applicantid;
	private Integer ref_app_id;
	private String alert_type;
	private Integer status;
	private String jobstatus;
	private String sender_email;
	private Date created_at;
	private String Name;
	private String jobassignemail;
	private Integer alert_id;

	public Integer getAlert_id() {
		return alert_id;
	}

	public void setAlert_id(Integer alert_id) {
		this.alert_id = alert_id;
	}

	public String getJobassignemail() {
		return jobassignemail;
	}

	public void setJobassignemail(String jobassignemail) {
		this.jobassignemail = jobassignemail;
	}

	public String getSender_email() {
		return sender_email;
	}

	public void setSender_email(String sender_email) {
		this.sender_email = sender_email;
	}

	public String getJobstatus() {
		return jobstatus;
	}

	public void setJobstatus(String jobstatus) {
		this.jobstatus = jobstatus;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getCreated_alert() {
		return created_alert;
	}

	public void setCreated_alert(String created_alert) {
		this.created_alert = created_alert;
	}

	public Integer getRef_jobid() {
		return ref_jobid;
	}

	public void setRef_jobid(Integer ref_jobid) {
		this.ref_jobid = ref_jobid;
	}

	public Integer getRef_applicantid() {
		return ref_applicantid;
	}

	public void setRef_applicantid(Integer ref_applicantid) {
		this.ref_applicantid = ref_applicantid;
	}

	public Integer getRef_app_id() {
		return ref_app_id;
	}

	public void setRef_app_id(Integer ref_app_id) {
		this.ref_app_id = ref_app_id;
	}

	public String getAlert_type() {
		return alert_type;
	}

	public void setAlert_type(String alert_type) {
		this.alert_type = alert_type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

}
