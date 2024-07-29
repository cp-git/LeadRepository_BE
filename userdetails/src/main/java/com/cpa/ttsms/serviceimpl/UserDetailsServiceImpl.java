/**
 * @author - Code Generator
 * @createdOn 16/07/2024
 * @Description Controller class for userdetails
 * 
 */

package com.cpa.ttsms.serviceimpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpa.ttsms.entity.UserDetails;
import com.cpa.ttsms.repository.UserDetailsRepo;
import com.cpa.ttsms.service.UserDetailsService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDetailsRepo userdetailsRepo;
	private static Logger logger;

	public UserDetailsServiceImpl() {
		logger = Logger.getLogger(UserDetailsServiceImpl.class);
	}

	/**
	 * @param : UserDetails userdetails
	 * @return : UserDetails createdUserDetails
	 * @description : For creating/inserting entry in userdetials table
	 */
	@Override
	public UserDetails createUserDetails(UserDetails userdetails) {
		logger.debug("Entering createUserDetails");
		UserDetails createdUserDetails = null;

		// userdetails.setUserDetailsCreatedBy("admin");
		// userdetails.setUserDetailsModifiedBy("admin");

		createdUserDetails = userdetailsRepo.save(userdetails);
		logger.info("created UserDetails :" + createdUserDetails);
		return createdUserDetails;
	}

	/**
	 * @param : String userid
	 * @return : UserDetails userdetails
	 * @description : For get entry in userdetials table
	 */
	@Override
	public UserDetails getUserDetailsByuserId(int userid) {
		logger.debug("Entering getUserDetailsByuserId");

		UserDetails userdetails = userdetailsRepo.findById(userid);
		logger.info("Founded userdetails :" + userdetails);

		return userdetails;
	}

	@Override
	public UserDetails getUserByUserNameAndPassoword(String username, String password) {
		UserDetails userDetails = null;

		userDetails = userdetailsRepo.findByUserNameAndPassword(username, password);
		return userDetails;
	}

	@Override
	public UserDetails getUserDetailsByUserName(String userName) {
		logger.debug("Entering getUserDetailsByuserId");

		UserDetails userdetails = userdetailsRepo.findByUserName(userName);
		logger.info("Founded userdetails :" + userdetails);

		return userdetails;
	}

	/**
	 * @return : List<Object> userdetails
	 * @description : For fetching all userdetails which are active state from
	 *              userdetials table //
	 */
//	@Override
//	public List<Object> getAllUserDetailss() {
//		logger.debug("Entering getAllUserDetailss");
//
//		List<Object> userdetailss = userdetailsRepo.findByUserDetailsIsActiveTrue();
//		logger.info("Fetched all active userdetails :" + userdetailss);
//		return userdetailss;
//	}

	/**
	 * @param : UserDetails to update
	 * @return : userdetails
	 * @description : For updating userdetails of userdetials table
	 */
//	@Override
//	public UserDetails updateUserDetailsByuserId(UserDetails userdetails, String userid) {
//		logger.debug("Entering updateUserDetails");
//
//		UserDetails toUpdatedUserDetails = null;
//		UserDetails updatedUserDetails = null;
//
//		toUpdatedUserDetails = userdetailsRepo.findByUserDetailsuserId(userid);
//		logger.info("exisitng UserDetails :: " + toUpdatedUserDetails);
//
//		if (toUpdatedUserDetails != null) {
//			logger.debug("setting new data of UserDetails to exisitng UserDetails");
//
////			userdetails.setModifiedBy("admin");
//
//			updatedUserDetails = userdetailsRepo.save(userdetails);
//
//			logger.info("updated UserDetails :" + updatedUserDetails);
//		}
//
//		return updatedUserDetails;
//	}

	/**
	 * @param : String userid
	 * @return : int (count of record updated)
	 * @description : This is function is used to soft delete the record of
	 *              UserDetails
	 * 
	 */
//	@Override
//	public int deleteUserDetailsByuserId(String userid) {
//		logger.debug("Entering deleteUserDetailsByuserId");
//
//		int count =  userdetailsRepo.deleteUserDetailsByuserId(userid);
//		logger.info("deleted UserDetails count : " + count);
//		return count;
//	}

}
