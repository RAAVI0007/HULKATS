package com.web.hulklogic.dao;

import java.util.List;

import com.web.hulklogic.entity.Applicants;
import com.web.hulklogic.entity.CreateJob;
import com.web.hulklogic.entity.JobsDocuments;
import com.web.hulklogic.entity.User;

public interface EditDetailsDAO {

	public int updateUserDetails(User user);

	public int saveCurrentLoginInfo(int userid, String teamname);

	public int saveLogoutInfo(int userid);

	public int deleteUser(int userid);

	public int deleteJob(int jobid);

	public User getTeamIdAndMgId(String email);

	public List<User> getAllManagers(int roleid, String organization);

	public List<User> getAllRecruiters(int roleid, String teamname, String organization);

	public List<User> getAllNARecruiters(int roleid, String organization);

	public int changeJobStatus(int jobid, String jobstatus);

	public int getLastJobId();

	public int updateJob(CreateJob createJob);

	public int updateApplicant(Applicants applicant);

	public int getLastApplicantId();

	public JobsDocuments getAllDocument(int jobid, int applicantid);

	public JobsDocuments getApplicantDoc(int applicantid);

	public JobsDocuments getJobDoc(int jobid);

	public int updateJobUpload1(CreateJob createJob);

	public int updateJobUpload2(CreateJob createJob);

	public int updateApplicantUplaodcv(Applicants applicant);

	public int updateApplicantotherUpload(Applicants applicant);

	public int updateJobWithoutDocs(CreateJob createJob);

	public int updateApplicantWithoutDocs(Applicants applicant);

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
