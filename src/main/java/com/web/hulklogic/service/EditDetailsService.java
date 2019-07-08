package com.web.hulklogic.service;

import java.util.List;

import com.web.hulklogic.entity.AlertDetails;
import com.web.hulklogic.entity.Applicants;
import com.web.hulklogic.entity.CreateJob;
import com.web.hulklogic.entity.JobsDocuments;
import com.web.hulklogic.entity.User;

public interface EditDetailsService {
	public boolean updateUserDetails(User user);

	public int saveCurrentLoginInfo(int userid, String teamname);

	public int saveLogoutInfo(int userid);

	public int deleteUser(int userid);
	
	public int deleteJob(int jobid);

	public User getTeamIdAndMgId(String email);

	public List<User> getAllManagers(int roleid, String organization);

	public List<User> getAllRecruiters(int roleid, String teamname, String organization);

	public List<User> getAllNARecruiters(int roleid, String organization);

	public String changeJobStatus(AlertDetails alertDetails);

	public int getLastJobId();

	public String updateJob(CreateJob createJob);

	public String updateApplicant(Applicants applicant);

	public int getLastApplicantId();

	public JobsDocuments getAllDocument(int jobid, int applicantid);

	public JobsDocuments getApplicantDoc(int applicantid);

	public JobsDocuments getJobDoc(int jobid);

	public int updateLogout(int userId);

	public int checkLoginUser(int userId);

	public int insertLoginUser(int userId, String sessionId1, String teamname);

	public User getSessions(int userId);

	public int insertSession1(int userId, String sessionId1);

	public int insertSession2(String sessionId2, int userId);

	public int logoutSession1(int userId);

	public int logoutSession2(int userId);

	public int idleStateSession1(int userId);

	public int idleStateSessioon2(int userId);

	public int deleteSessions(int userId);

}
