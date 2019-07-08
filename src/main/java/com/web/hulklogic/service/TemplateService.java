package com.web.hulklogic.service;

import java.util.List;
import com.web.hulklogic.entity.ApplicationHistory;
import com.web.hulklogic.entity.AutoSignature;
import com.web.hulklogic.entity.BulkEmailSend;
import com.web.hulklogic.entity.CreateTemplate;
import com.web.hulklogic.entity.SenderEmail;

public interface TemplateService {
	public CreateTemplate getTempalteDocumentsById(int tempalteId);

	public boolean checkTemplateName(String templateName);

	public int updateTemplateAttachement(String attachement, int template_id);

	public int updateTemplateAttachement1(String attachement, int template_id);

	public String saveIndividualHistroy(SenderEmail senderEmail);

	public List<ApplicationHistory> getApplicationHistoryByappId(int appId);

	public List<String> getEmailByAppId(List<Integer> appIds);

	public String saveBulkEmailHistory(List<Integer> appId, BulkEmailSend bulkEmailSend);

	public ApplicationHistory getApplicationHistoryByhistoryId(int historyId);

	public int updateAttachments(String attachement, int historyId);

	public int updateAttachments1(String attachement, int historyId);

	public int updateAttachments2(String attachement, int historyId);

	public int updateAttachments3(String attachement, int historyId);

	public String updateTeamname(String teamname, String oldteamname);

	public AutoSignature geAutoSignatureByEmail(String email);

	public int saveTermsAndCondition(int userid);

	public int getTerms(int userid);

	public CreateTemplate getTemplatesDetails(int templateId);
}
