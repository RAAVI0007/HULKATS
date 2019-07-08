package com.web.hulklogic.entity;

import java.sql.Date;

public class ApplicationHistory {
	private Integer historyid;
	private Integer app_id;
	private String createdemail;
	private String particulars;
	private String status;
	private String statuscomment;
	private String subject;
	private String body;
	private String comment;
	private String attachement1;
	private String attachement2;
	private String attachement3;
	private String attachement4;
	private String source;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	private Date created_at;

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Integer getHistoryid() {
		return historyid;
	}

	public void setHistoryid(Integer historyid) {
		this.historyid = historyid;
	}

	public Integer getApp_id() {
		return app_id;
	}

	public void setApp_id(Integer app_id) {
		this.app_id = app_id;
	}

	public String getCreatedemail() {
		return createdemail;
	}

	public void setCreatedemail(String createdemail) {
		this.createdemail = createdemail;
	}

	public String getParticulars() {
		return particulars;
	}

	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatuscomment() {
		return statuscomment;
	}

	public void setStatuscomment(String statuscomment) {
		this.statuscomment = statuscomment;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAttachement1() {
		return attachement1;
	}

	public void setAttachement1(String attachement1) {
		this.attachement1 = attachement1;
	}

	public String getAttachement2() {
		return attachement2;
	}

	public void setAttachement2(String attachement2) {
		this.attachement2 = attachement2;
	}

	public String getAttachement3() {
		return attachement3;
	}

	public void setAttachement3(String attachement3) {
		this.attachement3 = attachement3;
	}

	public String getAttachement4() {
		return attachement4;
	}

	public void setAttachement4(String attachement4) {
		this.attachement4 = attachement4;
	}

}
