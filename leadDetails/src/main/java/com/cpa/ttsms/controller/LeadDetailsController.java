/**
 * @author - Code Generator
 * @createdOn 16/07/2024
 * @Description Controller class for leadDetails
 * 
 */

package com.cpa.ttsms.controller;

import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cpa.ttsms.entity.LeadDetails;
import com.cpa.ttsms.exception.CPException;
import com.cpa.ttsms.helper.ResponseHandler;
import com.cpa.ttsms.repository.LeadDetailsRepo;
import com.cpa.ttsms.service.LeadDetailsService;

@RestController
@CrossOrigin
@RequestMapping("/lead")
public class LeadDetailsController {

	@Autowired
	private LeadDetailsRepo leadDetailsRepo;

	
	@Autowired
	private LeadDetailsService leadDetailsService;;
	
	private ResourceBundle resourceBunde;
	private static Logger logger;

	LeadDetailsController() {
		resourceBunde = ResourceBundle.getBundle("ErrorMessage", Locale.US);
		logger = Logger.getLogger(LeadDetailsController.class);
	}

	@PostMapping("/leaddetails")
	public ResponseEntity<Object> createLeadDetails(@RequestBody LeadDetails leadDetails) throws CPException {
		logger.debug("Entering createLeadDetails");
		logger.info("data of creating LeadDetails  :" + leadDetails.toString());

		LeadDetails createdLeadDetails = null;
		try {

			LeadDetails toCheckLeadDetails = leadDetailsService.getLeadDetailsById(leadDetails.getId());
			logger.debug("existing leadDetails :" + toCheckLeadDetails);

			if (toCheckLeadDetails == null) {

				// TODO: Uncomment below 2 lines and change the method name as per your Entity
				// class
				// leadDetails.setCreatedby("admin");
				// leadDetails.setUpdatedby("admin");

				createdLeadDetails = leadDetailsService.createLeadDetails(leadDetails);
				logger.info("LeadDetails created :" + createdLeadDetails);

				return ResponseHandler.generateResponse(createdLeadDetails, HttpStatus.CREATED);

			} else {

				logger.error(resourceBunde.getString("err003"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err003");
			}

		} catch (Exception ex) {
			logger.error("Failed LeadDetails creation : " + ex.getMessage());
			throw new CPException("err003", resourceBunde.getString("err003"));
		}
	}

	@GetMapping("/leaddetails/{userId}")
	public ResponseEntity<Object> getLeadDetailsByUserId(@PathVariable("userId") int userId) throws CPException {
		logger.debug("Entering getLeadDetailsByUserId");
		logger.info("Entered user ID: " + userId);

		List<LeadDetails> leadDetails;

		try {
			leadDetails = leadDetailsService.getLeadDetailsByUserId(userId);
			logger.info("Fetched LeadDetails: " + leadDetails);

			if (leadDetails != null && !leadDetails.isEmpty()) {
				logger.debug("LeadDetails fetched, generating response");
				return ResponseHandler.generateResponse(leadDetails, HttpStatus.OK);
			} else {
				logger.debug("LeadDetails not found");
				return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, "err001");
			}
		} catch (Exception ex) {
			logger.error("Failed getting LeadDetails: " + ex.getMessage());
			throw new CPException("err001", "Error fetching lead details");
		}
	}

	@PostMapping("/leaddetails/upload/{userId}")
	public ResponseEntity<Object> uploadLeadDetails(@RequestParam("file") MultipartFile file , @PathVariable("userId")int userId) throws CPException {
		logger.debug("Entering uploadLeadDetails");
		logger.info("Received file: " + file.getOriginalFilename());

		try (InputStream inputStream = file.getInputStream()) {
			List<LeadDetails> leadDetailsList = leadDetailsService.saveLeadDetailsFromExcel(inputStream , userId);
			logger.info("LeadDetails saved from Excel: " + leadDetailsList);
			return ResponseHandler.generateResponse(leadDetailsList, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("Failed to upload LeadDetails: " + ex.getMessage());
			throw new CPException("err002", "Failed to upload LeadDetails from Excel file");
		}
	}

	@DeleteMapping("/leaddetails/delete/{id}")
	public ResponseEntity<Object> deleteLeadDetailsById(@PathVariable("id") int id) throws CPException {
	    logger.debug("Entering deleteLeadDetailsById");
	    logger.info("Deleting LeadDetails with ID: " + id);

	    try {
	        leadDetailsService.deleteLeadDetailsById(id);
	        logger.info("LeadDetails with ID " + id + " deleted successfully");
	        return ResponseHandler.generateResponse(null, HttpStatus.NO_CONTENT);
	    } catch (Exception ex) {
	        logger.error("Failed to delete LeadDetails with ID " + id + ": " + ex.getMessage());
	        throw new CPException("err003", "Failed to delete LeadDetails");
	    }
	}

//	@GetMapping("/leaddetails")
//	public ResponseEntity<List<Object>> getAllLeadDetailss(@RequestParam(name = "userId") String userId)
//			throws CPException {
//		logger.debug("Entering getAllLeadDetails");
//		logger.info("Parameter  :" + userId);
//		
//		List<Object> leadDetailss = null;
//
//		try {
//
//			if (userId.equalsIgnoreCase("all")) {
//
//				leadDetailss = leadDetailsService.getAllLeadDetailss();
//				logger.info("Fetched all LeadDetails :" + leadDetailss);
//
//				return ResponseHandler.generateListResponse(leadDetailss, HttpStatus.OK);
//			} else {
//
//				logger.info(resourceBunde.getString("err002"));
//				return ResponseHandler.generateListResponse(HttpStatus.NOT_FOUND, "err002");
//			}
//
//		} catch (Exception ex) {
//
//			logger.error("Failed getting all leadDetailss : " + ex.getMessage());
//			throw new CPException("err002", resourceBunde.getString("err002"));
//
//		}
//	}

//	@DeleteMapping("/leaddetails/{userId}")
//	public ResponseEntity<Object> deleteLeadDetailsByuserId(@PathVariable("userId") String userId) throws CPException {
//		logger.debug("Entering deleteAuthUser");
//		logger.info("entered deleteLeadDetails  :" + userId);
//		//TODO - implement the business logic
//		
//		int count = 0;
//
//		try {
//			count = leadDetailsService.deleteLeadDetailsByuserId(userId);
//			if (count >= 1) {
//				logger.info("deleted LeadDetails : userId = " + userId);
//				return ResponseHandler.generateResponse(HttpStatus.NO_CONTENT);
//			} else {
//				logger.info(resourceBunde.getString("err005"));
//				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err005");
//			}
//
//		} catch (Exception ex) {
//			logger.error("Failed to delete LeadDetails :" + ex.getMessage());
//			throw new CPException("err005", resourceBunde.getString("err005"));
//		}
//		
//
//	}

	@PutMapping("/leaddetails/update/{id}")
	public ResponseEntity<Object> updateLeadDetailsByuserId(@RequestBody LeadDetails leadDetails,
			@PathVariable("id") int id) throws CPException {
		logger.debug("Entering updateLeadDetails");
		logger.info("entered  updateLeadDetails :" + leadDetails);

		LeadDetails updatedLeadDetails = null;

		try { 
			updatedLeadDetails = leadDetailsService.updateLeadDetailsById(leadDetails, id);

			if (updatedLeadDetails == null) {
				logger.info(resourceBunde.getString("err004"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err004");
			} else {
				logger.info("updated leadDetails : " + updatedLeadDetails);
				return ResponseHandler.generateResponse(updatedLeadDetails, HttpStatus.CREATED);
			}

		} catch (Exception ex) {
			logger.error("Failed update LeadDetails : " + ex.getMessage());
			throw new CPException("err004", resourceBunde.getString("err004"));

		}

	}
	
	
	
	  
	  
	  @GetMapping("/searchupdate")
	    public List<LeadDetails> searchProducts(@RequestParam(required = false) String companyName, @RequestParam(required = false) String recruiterName, @RequestParam(required = false) String recruiterMail, @RequestParam(required = false) String positionName, @RequestParam(required = false) String jobLocation) {
		     System.out.println("In controller...");
		     if(companyName == null && recruiterName ==null && recruiterMail==null && positionName ==null &&  jobLocation ==null ) {
		    	 return leadDetailsRepo.search(null,null,null,null,null);
		     }
		     else if(recruiterName ==null && recruiterMail==null && positionName ==null &&  jobLocation ==null) {
		    	 return  leadDetailsRepo.searchByCompanyName(companyName);
		     }
		     else if(companyName ==null && recruiterMail==null && positionName ==null &&  jobLocation ==null) {
		    	 	return leadDetailsRepo.searchByRecuiterName(recruiterName);
		     }
		     else if(companyName ==null && recruiterName==null && positionName ==null &&  jobLocation ==null) {
		    	 return leadDetailsRepo.searchByRecruiterMail(recruiterMail);
		     }
		     else if(companyName ==null && recruiterName==null && recruiterMail ==null &&  jobLocation ==null) {
		    	 return leadDetailsRepo.searchBypositionName(positionName);
		     }
		     else if(companyName ==null && recruiterName==null && recruiterMail ==null &&  positionName ==null) {
		    	 return leadDetailsRepo.searchByjobLocation(jobLocation);
		     }
		     else {
		    	 return leadDetailsRepo.search(companyName,recruiterName,recruiterMail,positionName,jobLocation);
		    	 
		     }
	    }
	
	
	

}

