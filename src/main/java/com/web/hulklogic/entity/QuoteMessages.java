package com.web.hulklogic.entity;

public class QuoteMessages {
	private String name;
	private String email;
	private String contactno;
	private String organization;
	private String timeslot;
	private String location;
	private String message;
	private String subject;
	private String product;
	private String volume;
	private String created_at;

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getContactno() {
		return contactno;
	}

	public String getOrganization() {
		return organization;
	}

	public String getTimeslot() {
		return timeslot;
	}

	public String getLocation() {
		return location;
	}

	public String getMessage() {
		return message;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public void setTimeslot(String timeslot) {
		this.timeslot = timeslot;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

}
