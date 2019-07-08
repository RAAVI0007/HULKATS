package com.web.hulklogic.entity;

import java.util.Date;

public class Target {
	private int teamtarget;
	private int rectarget;
	private Date startdate;
	private String periodic;
	private String teamname;
	private String email;
	private int userid;
	private String name;
	private Integer hired;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getHired() {
		return hired;
	}

	public void setHired(Integer hired) {
		this.hired = hired;
	}

	public int getTeamtarget() {
		return teamtarget;
	}

	public void setTeamtarget(int teamtarget) {
		this.teamtarget = teamtarget;
	}

	public int getRectarget() {
		return rectarget;
	}

	public void setRectarget(int rectarget) {
		this.rectarget = rectarget;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public String getPeriodic() {
		return periodic;
	}

	public void setPeriodic(String periodic) {
		this.periodic = periodic;
	}

	public String getTeamname() {
		return teamname;
	}

	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

}
