package com.web.hulklogic.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.hulklogic.dao.TemplateDAO;
import com.web.hulklogic.entity.ApplicationHistory;
import com.web.hulklogic.entity.AutoSignature;
import com.web.hulklogic.entity.BulkEmailSend;
import com.web.hulklogic.entity.CreateTemplate;
import com.web.hulklogic.entity.SenderEmail;
import com.web.hulklogic.service.TemplateService;

@Service
public class TemplateServiceImpl implements TemplateService {
	@Autowired
	private TemplateDAO templateDao;

	@Override
	public CreateTemplate getTempalteDocumentsById(int tempalteId) {
		return templateDao.getTempalteDocumentsById(tempalteId);
	}

	@Override
	public boolean checkTemplateName(String templateName) {
		int result = templateDao.checkTemplateName(templateName);
		if (result > 0) {
			return true;
		}

		return false;
	}

	@Override
	public int updateTemplateAttachement(String attachement, int template_id) {
		return templateDao.updateTemplateAttachement(attachement, template_id);
	}

	@Override
	public int updateTemplateAttachement1(String attachement, int template_id) {
		return templateDao.updateTemplateAttachement1(attachement, template_id);
	}

	@Override
	public String saveIndividualHistroy(SenderEmail senderEmail) {
		int result = templateDao.saveIndividualHistroy(senderEmail);
		if (result > 0) {
			return "sucess";
		}
		return "fail";
	}

	@Override
	public List<ApplicationHistory> getApplicationHistoryByappId(int appId) {

		return templateDao.getApplicationHistoryByappId(appId);
	}

	@Override
	public List<String> getEmailByAppId(List<Integer> appIds) {

		return templateDao.getEmailByAppId(appIds);
	}

	@Override
	public String saveBulkEmailHistory(List<Integer> appId, BulkEmailSend bulkEmailSend) {
		int[] result = templateDao.saveBulkEmailHistory(appId, bulkEmailSend);

		if (result != null) {
			return "success";
		}

		return "fail";
	}

	@Override
	public ApplicationHistory getApplicationHistoryByhistoryId(int historyId) {
		return templateDao.getApplicationHistoryByhistoryId(historyId);
	}

	@Override
	public int updateAttachments(String attachement, int historyId) {
		return templateDao.updateAttachments(attachement, historyId);
	}

	@Override
	public int updateAttachments1(String attachement, int historyId) {
		return templateDao.updateAttachments1(attachement, historyId);
	}

	@Override
	public int updateAttachments2(String attachement, int historyId) {
		return templateDao.updateAttachments2(attachement, historyId);
	}

	@Override
	public int updateAttachments3(String attachement, int historyId) {
		return templateDao.updateAttachments3(attachement, historyId);
	}

	@Override
	public String updateTeamname(String teamname, String oldteamname) {
		int result = templateDao.updateTeamname(teamname, oldteamname);

		if (result > 0) {
			return "success";
		}
		return "fail";
	}

	@Override
	public AutoSignature geAutoSignatureByEmail(String email) {

		return templateDao.geAutoSignatureByEmail(email);
	}

	@Override
	public int saveTermsAndCondition(int userid) {
		return templateDao.saveTermsAndCondition(userid);
	}

	@Override
	public int getTerms(int userid) {
		return templateDao.getTerms(userid);
	}

	@Override
	public CreateTemplate getTemplatesDetails(int templateId) {

		return templateDao.getTemplatesDetails(templateId);
	}

}
