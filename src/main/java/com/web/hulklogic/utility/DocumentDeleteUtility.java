package com.web.hulklogic.utility;

import java.io.File;

import org.apache.log4j.Logger;

public class DocumentDeleteUtility {
	private static Logger logger = Logger.getLogger(DocumentDeleteUtility.class);

	public boolean deleteDocument(File destinationFile, String docxFileName) {
		logger.info("destinationFile:" + destinationFile);
		logger.info("docxFileName:" + docxFileName);
		for (File file : destinationFile.listFiles()) {
			logger.info("file ");
			if (file.isFile() && (file.getName().equalsIgnoreCase(docxFileName))) {
				file.delete(); // done
				logger.info("file deleteion");
				return true;
			}
		}
		return false;
	}
}
