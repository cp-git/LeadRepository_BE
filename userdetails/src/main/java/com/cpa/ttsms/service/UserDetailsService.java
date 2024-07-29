/**
 * @author  - Code Generator
 * @createdOn -  16/07/2024
 * @Description Entity class for UserDetails Service
 * 
 */

package com.cpa.ttsms.service;

import com.cpa.ttsms.entity.UserDetails;

public interface UserDetailsService {

	UserDetails createUserDetails(UserDetails userdetails);

	UserDetails getUserDetailsByuserId(int userid);

	UserDetails getUserDetailsByUserName(String userName);

	UserDetails getUserByUserNameAndPassoword(String username, String password);

//	List<Object> getAllUserDetailss();

//	UserDetails updateUserDetailsByuserId(UserDetails userdetails, String userid);

//	int deleteUserDetailsByuserId(String userid);

}