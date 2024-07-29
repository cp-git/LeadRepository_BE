/**
 * @author - Code Generator
 * @createdOn 16/07/2024
 * @Description Controller class for userdetails
 * 
 */

package com.cpa.ttsms.controller;

import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cpa.ttsms.entity.UserDetails;
import com.cpa.ttsms.exception.CPException;
import com.cpa.ttsms.helper.ResponseHandler;
import com.cpa.ttsms.service.UserDetailsService;

@RestController
@CrossOrigin
@RequestMapping("/lead")
public class UserDetailsController {

	@Autowired
	private UserDetailsService userdetailsService;;

	private ResourceBundle resourceBunde;
	private static Logger logger;

	UserDetailsController() {
		resourceBunde = ResourceBundle.getBundle("ErrorMessage", Locale.US);
		logger = Logger.getLogger(UserDetailsController.class);
	}

	@PostMapping("/userDetails")
	public ResponseEntity<Object> createUserDetails(@RequestBody UserDetails userdetails) throws CPException {
		logger.debug("Entering createUserDetails");
		logger.info("data of creating UserDetails  :" + userdetails.toString());

		UserDetails createdUserDetails = null;
		try {

			UserDetails toCheckUserDetails = userdetailsService.getUserDetailsByUserName(userdetails.getUserName());
			logger.debug("existing userdetails :" + toCheckUserDetails);

			if (toCheckUserDetails == null) {

				// TODO: Uncomment below 2 lines and change the method name as per your Entity
				// class
				// userdetails.setCreatedby("admin");
				// userdetails.setUpdatedby("admin");

				createdUserDetails = userdetailsService.createUserDetails(userdetails);
				logger.info("UserDetails created :" + createdUserDetails);

				return ResponseHandler.generateResponse(createdUserDetails, HttpStatus.CREATED);

			} else {

				logger.error(resourceBunde.getString("err003"));
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err003");
			}

		} catch (Exception ex) {
			logger.error("Failed UserDetails creation : " + ex.getMessage());
			throw new CPException("err003", resourceBunde.getString("err003"));
		}
	}

	@GetMapping("/userDetails/userId/{userId}")
	public ResponseEntity<Object> getUserDetailsByuserId(@PathVariable("userId") int userId) throws CPException {
		logger.debug("Entering getUserDetailsByuserId");
		logger.info("entered user name :" + userId);

		UserDetails userdetails = null;

		try {

			userdetails = userdetailsService.getUserDetailsByuserId(userId);
			logger.info("fetched UserDetails :" + userdetails);

			if (userdetails != null) {
				logger.debug("UserDetails fetched generating response");
				return ResponseHandler.generateResponse(userdetails, HttpStatus.OK);
			} else {
				logger.debug("UserDetails not found");
				return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, "err001");
			}

		} catch (Exception ex) {

			logger.error("Failed getting userdetails : " + ex.getMessage());
			throw new CPException("err001", resourceBunde.getString("err001"));
		}

	}

	@GetMapping("finduser/{username}/{password}")
	public ResponseEntity<Object> findUserByUsernameAndPassword(@PathVariable String username,
			@PathVariable String password) throws CPException {
		UserDetails isUserValid = null;
		try {

			isUserValid = userdetailsService.getUserByUserNameAndPassoword(username, password);

			if (isUserValid != null) {
				return ResponseHandler.generateResponse(isUserValid, HttpStatus.CREATED);
			} else {
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err003");
			}
		} catch (Exception ex) {

			logger.error("Failed getting all employees: " + ex.getMessage());
			throw new CPException("err002", "Error while retrieving all");
		}
	}

//	@GetMapping("/userDetails")
//	public ResponseEntity<List<Object>> getAllUserDetailss(@RequestParam(name = "userId") String userId)
//			throws CPException {
//		logger.debug("Entering getAllUserDetails");
//		logger.info("Parameter  :" + userId);
//
//		List<Object> userdetailss = null;
//
//		try {
//
//			if (userId.equalsIgnoreCase("all")) {
//
//				userdetailss = userdetailsService.getAllUserDetailss();
//				logger.info("Fetched all UserDetails :" + userdetailss);
//
//				return ResponseHandler.generateListResponse(userdetailss, HttpStatus.OK);
//			} else {
//
//				logger.info(resourceBunde.getString("err002"));
//				return ResponseHandler.generateListResponse(HttpStatus.NOT_FOUND, "err002");
//			}
//
//		} catch (Exception ex) {
//
//			logger.error("Failed getting all userdetailss : " + ex.getMessage());
//			throw new CPException("err002", resourceBunde.getString("err002"));
//
//		}
//	}

//	@DeleteMapping("/userDetails/{userId}")
//	public ResponseEntity<Object> deleteUserDetailsByuserId(@PathVariable("userId") String userId) throws CPException {
//		logger.debug("Entering deleteAuthUser");
//		logger.info("entered deleteUserDetails  :" + userId);
//		// TODO - implement the business logic
//
//		int count = 0;
//
//		try {
//			count = userdetailsService.deleteUserDetailsByuserId(userId);
//			if (count >= 1) {
//				logger.info("deleted UserDetails : userId = " + userId);
//				return ResponseHandler.generateResponse(HttpStatus.NO_CONTENT);
//			} else {
//				logger.info(resourceBunde.getString("err005"));
//				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err005");
//			}
//
//		} catch (Exception ex) {
//			logger.error("Failed to delete UserDetails :" + ex.getMessage());
//			throw new CPException("err005", resourceBunde.getString("err005"));
//		}
//
//	}

//	@PutMapping("/userDetails/{userId}")
//	public ResponseEntity<Object> updateUserDetailsByuserId(@RequestBody UserDetails userdetails,
//			@PathVariable("userId") String userId) throws CPException {
//		logger.debug("Entering updateUserDetails");
//		logger.info("entered  updateUserDetails :" + userdetails);
//
//		UserDetails updatedUserDetails = null;
//
//		try {
//			updatedUserDetails = userdetailsService.updateUserDetailsByuserId(userdetails, userId);
//
//			if (updatedUserDetails == null) {
//				logger.info(resourceBunde.getString("err004"));
//				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "err004");
//			} else {
//				logger.info("updated userdetails : " + updatedUserDetails);
//				return ResponseHandler.generateResponse(updatedUserDetails, HttpStatus.CREATED);
//			}
//
//		} catch (Exception ex) {
//			logger.error("Failed update UserDetails : " + ex.getMessage());
//			throw new CPException("err004", resourceBunde.getString("err004"));
//
//		}
//
//	}

}
