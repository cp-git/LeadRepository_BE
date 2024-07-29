/**
 * @author  - Code Generator
 * @createdOn -  16/07/2024
 * @Description Entity class for LeadDetails Service
 * 
 */

package com.cpa.ttsms.service;

import java.io.InputStream;
import java.util.List;

import com.cpa.ttsms.entity.LeadDetails;

public interface LeadDetailsService {

	LeadDetails createLeadDetails(LeadDetails leadDetails);

	LeadDetails getLeadDetailsById(int id);

	List<LeadDetails> getLeadDetailsByUserId(int userId);



	List<LeadDetails> saveLeadDetailsFromExcel(InputStream inputStream , int userId);
	
	void deleteLeadDetailsById(int id );

//	List<Object> getAllLeadDetailss();
//
LeadDetails updateLeadDetailsById(LeadDetails leadDetails, int id);
//
//	int deleteLeadDetailsByuserId(String userid);

	List<LeadDetails> searchProducts(String query);

}