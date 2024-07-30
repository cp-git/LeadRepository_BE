/**
 * @author  - Code Generator
 * @createdOn -  16/07/2024
 * @Description Entity class for LeadDetails
 * 
 */

package com.cpa.ttsms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cpa.ttsms.entity.LeadDetails;

@Repository
public interface LeadDetailsRepo extends JpaRepository<LeadDetails, Integer> {

	public LeadDetails findById(int id);

	public List<LeadDetails> findByUserId(int userId);
	


//	public List<Object> findByLeadDetailsIsActiveTrue();

//	@Transactional
//	@Modifying
//	@Query(value = "UPDATE leaddetails SET is_active=false WHERE userid = ?1", nativeQuery = true)
//	public int deleteLeadDetailsByuserId(String userid);
	
	void deleteById(int id);
	
	
//	 @Query(value=("SELECT * FROM LeadDetails WHERE companyname LIKE %:searchTerm%  OR recruitername LIKE %:searchTerm%"),nativeQuery=true)
//	 List<LeadDetails> search(@Param("searchTerm") String searchTerm);
	 
	 
	 @Query("SELECT p FROM LeadDetails p WHERE (:companyName IS NULL OR p.companyName LIKE %:companyName%)")
	  List<LeadDetails> searchByCompanyName(@Param("companyName") String companyName);
	 
	 
	 @Query("SELECT p FROM LeadDetails p WHERE (:recruiterName IS NULL OR p.recruiterName LIKE %:recruiterName%) ")
	  List<LeadDetails> searchByRecuiterName(@Param("recruiterName") String recruiterName);
	 
	 
	 @Query("SELECT p FROM LeadDetails p WHERE (:recruiterMail IS NULL OR p.recruiterMail LIKE %:recruiterMail%) ")
	  List<LeadDetails> searchByRecruiterMail(@Param("recruiterMail") String recruiterMail);
	 
	 
	 @Query("SELECT p FROM LeadDetails p WHERE (:positionName IS NULL OR p.positionName LIKE %:positionName%) ")
	  List<LeadDetails> searchBypositionName(@Param("positionName") String positionName);
	 
	 @Query("SELECT p FROM LeadDetails p WHERE (:jobLocation IS NULL OR p.jobLocation LIKE %:jobLocation%) ")
	  List<LeadDetails> searchByjobLocation(@Param("jobLocation") String jobLocation);
	 
	 

	 
	   @Query("SELECT p FROM LeadDetails p WHERE (:companyName IS NULL OR p.companyName LIKE %:companyName%) OR (:recruiterName IS NULL OR p.recruiterName LIKE %:recruiterName%) OR (:recruiterMail IS NULL OR p.recruiterMail LIKE %:recruiterMail%) OR (:positionName IS NULL OR p.positionName LIKE %:positionName%) OR (:jobLocation IS NULL OR p.jobLocation LIKE %:jobLocation%)")
	    List<LeadDetails> search(@Param("companyName") String companyName, @Param("recruiterName") String recruiterName, @Param("recruiterMail") String recruiterMail,@Param("positionName") String positionName,@Param("jobLocation") String jobLocation);
}
