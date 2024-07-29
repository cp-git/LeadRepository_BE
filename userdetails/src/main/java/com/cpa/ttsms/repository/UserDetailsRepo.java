/**
 * @author  - Code Generator
 * @createdOn -  16/07/2024
 * @Description Entity class for UserDetails
 * 
 */

package com.cpa.ttsms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cpa.ttsms.entity.UserDetails;

@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetails, Integer> {

	public UserDetails findByUserName(String userName);

	public UserDetails findById(int id);

	public UserDetails findByUserNameAndPassword(String userName, String password);

//	public List<Object> findByUserDetailsIsActiveTrue();
//
//	@Transactional
//	@Modifying
//	@Query(value = "UPDATE userdetials SET is_active=false WHERE userid = ?1", nativeQuery = true)
//	public int deleteUserDetailsByuserId(String userid);

}
