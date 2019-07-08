package com.web.hulklogic.entity;

import java.util.List;

public class BulkEmailSend {

	public String pwd;
	private String body;
	private String subject;
	private List<String> emails;
	private String currentemail;
	private String firstname;
	private long concatcno;
	private String auto_Signature;
	private String templateDocument;
	private String templateDocument1;
	private String fileType1;
	private String fileType;
	private String[] totalDocuments;
	private String attachment;
	private String attachment1;
	private String attachment2;
	private String attachment3;
	private String createAttachment;
	private String createAttachment1;
	private List<Integer> app_id;
	private Integer templatedesignid;
	private String propertyname;


	public List<Integer> getApp_id() {
		return app_id;
	}

	public void setApp_id(List<Integer> app_id) {
		this.app_id = app_id;
	}

	public String getCreateAttachment() {
		return createAttachment;
	}

	public void setCreateAttachment(String createAttachment) {
		this.createAttachment = createAttachment;
	}

	public String getCreateAttachment1() {
		return createAttachment1;
	}

	public void setCreateAttachment1(String createAttachment1) {
		this.createAttachment1 = createAttachment1;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	public String getCurrentemail() {
		return currentemail;
	}

	public void setCurrentemail(String currentemail) {
		this.currentemail = currentemail;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public long getConcatcno() {
		return concatcno;
	}

	public void setConcatcno(long concatcno) {
		this.concatcno = concatcno;
	}

	public String getAuto_Signature() {
		return auto_Signature;
	}

	public void setAuto_Signature(String auto_Signature) {
		this.auto_Signature = auto_Signature;
	}

	public String getTemplateDocument() {
		return templateDocument;
	}

	public void setTemplateDocument(String templateDocument) {
		this.templateDocument = templateDocument;
	}

	public String getTemplateDocument1() {
		return templateDocument1;
	}

	public void setTemplateDocument1(String templateDocument1) {
		this.templateDocument1 = templateDocument1;
	}

	public String getFileType1() {
		return fileType1;
	}

	public void setFileType1(String fileType1) {
		this.fileType1 = fileType1;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String[] getTotalDocuments() {
		return totalDocuments;
	}

	public void setTotalDocuments(String[] totalDocuments) {
		this.totalDocuments = totalDocuments;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getAttachment1() {
		return attachment1;
	}

	public void setAttachment1(String attachment1) {
		this.attachment1 = attachment1;
	}

	public String getAttachment2() {
		return attachment2;
	}

	public void setAttachment2(String attachment2) {
		this.attachment2 = attachment2;
	}

	public String getAttachment3() {
		return attachment3;
	}

	public void setAttachment3(String attachment3) {
		this.attachment3 = attachment3;
	}

	public Integer getTemplatedesignid() {
		return templatedesignid;
	}

	public void setTemplatedesignid(Integer templatedesignid) {
		this.templatedesignid = templatedesignid;
	}

	public String getPropertyname() {
		return propertyname;
	}

	public void setPropertyname(String propertyname) {
		this.propertyname = propertyname;
	}

}
