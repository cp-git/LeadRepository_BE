/**
 * @author  - Code Generator
 * @createdOn -  16/07/2024
 * @Description Entity class for leaddetails
 * 
 */

package com.cpa.ttsms.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name = "leaddetails")
public class LeadDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "userid")
	private int userId;

	@Column(name = "companyname")
	private String companyName;

	@Column(name = "recruitername")
	private String recruiterName;

	@Column(name = "joblocation")
	private String jobLocation;

	@Column(name = "positionname")
	private String positionName;

	@Column(name = "recruiternumber")
	private String recruiterNumber;

	@Column(name = "recruitermail")
	private String recruiterMail;

	@Column(name = "link")
	private String link;

	@Column(name = "date")
	private LocalDate date;

	@Column(name = "status")
	private String status;

	@Column(name = "comment")
	private String comment;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the recruiterName
	 */
	public String getRecruiterName() {
		return recruiterName;
	}

	/**
	 * @param recruiterName the recruiterName to set
	 */
	public void setRecruiterName(String recruiterName) {
		this.recruiterName = recruiterName;
	}

	/**
	 * @return the jobLocation
	 */
	public String getJobLocation() {
		return jobLocation;
	}

	/**
	 * @param jobLocation the jobLocation to set
	 */
	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}

	/**
	 * @return the positionName
	 */
	public String getPositionName() {
		return positionName;
	}

	/**
	 * @param positionName the positionName to set
	 */
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	/**
	 * @return the recruiterNumber
	 */
	public String getRecruiterNumber() {
		return recruiterNumber;
	}

	/**
	 * @param recruiterNumber the recruiterNumber to set
	 */
	public void setRecruiterNumber(String recruiterNumber) {
		this.recruiterNumber = recruiterNumber;
	}

	/**
	 * @return the recruiterMail
	 */
	public String getRecruiterMail() {
		return recruiterMail;
	}

	/**
	 * @param recruiterMail the recruiterMail to set
	 */
	public void setRecruiterMail(String recruiterMail) {
		this.recruiterMail = recruiterMail;
	}

	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @param id
	 * @param userId
	 * @param companyName
	 * @param recruiterName
	 * @param jobLocation
	 * @param positionName
	 * @param recruiterNumber
	 * @param recruiterMail
	 * @param link
	 * @param date
	 * @param status
	 * @param comment
	 */
	public LeadDetails(int id, int userId, String companyName, String recruiterName, String jobLocation,
			String positionName, String recruiterNumber, String recruiterMail, String link, LocalDate date,
			String status, String comment) {
		super();
		this.id = id;
		this.userId = userId;
		this.companyName = companyName;
		this.recruiterName = recruiterName;
		this.jobLocation = jobLocation;
		this.positionName = positionName;
		this.recruiterNumber = recruiterNumber;
		this.recruiterMail = recruiterMail;
		this.link = link;
		this.date = date;
		this.status = status;
		this.comment = comment;
	}

	/**
	 * 
	 */
	public LeadDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "LeadDetails [id=" + id + ", userId=" + userId + ", companyName=" + companyName + ", recruiterName="
				+ recruiterName + ", jobLocation=" + jobLocation + ", positionName=" + positionName
				+ ", recruiterNumber=" + recruiterNumber + ", recruiterMail=" + recruiterMail + ", link=" + link
				+ ", date=" + date + ", status=" + status + ", comment=" + comment + "]";
	}

}
