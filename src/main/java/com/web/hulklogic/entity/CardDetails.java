package com.web.hulklogic.entity;

public class CardDetails {
	private String name;
	private String firstname;
	private String lastname;
	private String contactno;
	private String email;
	private String address;
	private String city;
	private String state;
	private String country;
	private String pincode;
	private String organization;
	private String websiteurl;
	private String designation;
	private String encodedurl;
	private int card_id;
	private String cardname;
	private String created_at;

	public String getName() {
		return name;
	}

	public String getContactno() {
		return contactno;
	}

	public String getEmail() {
		return email;
	}

	public String getOrganization() {
		return organization;
	}

	public String getWebsiteurl() {
		return websiteurl;
	}

	public String getDesignation() {
		return designation;
	}

	public String getEncodedurl() {
		return encodedurl;
	}

	public int getCard_id() {
		return card_id;
	}

	public String getCardname() {
		return cardname;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public void setWebsiteurl(String websiteurl) {
		this.websiteurl = websiteurl;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public void setEncodedurl(String encodedurl) {
		this.encodedurl = encodedurl;
	}

	public void setCard_id(int card_id) {
		this.card_id = card_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCardname(String cardname) {
		this.cardname = cardname;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}

	public String getPincode() {
		return pincode;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

}
