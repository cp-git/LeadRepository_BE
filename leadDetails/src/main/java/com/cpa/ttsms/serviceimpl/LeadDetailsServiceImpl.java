/**
 * @author - Code Generator
 * @createdOn 16/07/2024
 * @Description Controller class for leadDetails
 * 
 */

package com.cpa.ttsms.serviceimpl;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.cpa.ttsms.controller.LeadDetailsController;
import com.cpa.ttsms.entity.LeadDetails;
import com.cpa.ttsms.repository.LeadDetailsRepo;
import com.cpa.ttsms.service.LeadDetailsService;

@Service
public class LeadDetailsServiceImpl implements LeadDetailsService {
	@Autowired
	private LeadDetailsRepo leadDetailsRepo;
	private static Logger logger;

	public LeadDetailsServiceImpl() {
		logger = Logger.getLogger(LeadDetailsServiceImpl.class);
	}

	/**
	 * @param : LeadDetails leadDetails
	 * @return : LeadDetails createdLeadDetails
	 * @description : For creating/inserting entry in leaddetails table
	 */
	@Override
	public LeadDetails createLeadDetails(LeadDetails leadDetails) {
		logger.debug("Entering createLeadDetails");
		LeadDetails createdLeadDetails = null;

		// leadDetails.setLeadDetailsCreatedBy("admin");
		// leadDetails.setLeadDetailsModifiedBy("admin");

		createdLeadDetails = leadDetailsRepo.save(leadDetails);
		logger.info("created LeadDetails :" + createdLeadDetails);
		return createdLeadDetails;
	}

	/**
	 * @param : String userid
	 * @return : LeadDetails leadDetails
	 * @description : For get entry in leaddetails table
	 */
	@Override
	public LeadDetails getLeadDetailsById(int id) {
		logger.debug("Entering getLeadDetailsByuserId");

		LeadDetails leadDetails = leadDetailsRepo.findById(id);
		logger.info("Founded leadDetails :" + leadDetails);

		return leadDetails;
	}

	@Override
	public List<LeadDetails> getLeadDetailsByUserId(int userId) {
		List<LeadDetails> leadDetails = null;
		leadDetails = leadDetailsRepo.findByUserId(userId);
		System.out.println("######"+ leadDetails);
		return leadDetails;
	}



	  @Override
	    public List<LeadDetails> saveLeadDetailsFromExcel(InputStream inputStream , int userId) {
	        logger.debug("Entering saveLeadDetailsFromExcel");
	        List<LeadDetails> leadDetailsList = new ArrayList<>();
	        try (Workbook workbook = WorkbookFactory.create(inputStream)) {
	            Sheet sheet = workbook.getSheetAt(0);
	            Iterator<Row> rowIterator = sheet.iterator();
	            while (rowIterator.hasNext()) {
	                Row row = rowIterator.next();
	                if (row.getRowNum() == 0) {
	                	continue; // Skip header row
	                }

	                LeadDetails leadDetails = new LeadDetails();
	                leadDetails.setUserId(userId); // Or set it appropriately
	                leadDetails.setDate(convertToLocalDate(row.getCell(1)));
	                leadDetails.setCompanyName(getCellValueAsString(row.getCell(2)));
	                leadDetails.setRecruiterName(getCellValueAsString(row.getCell(3)));
	                leadDetails.setJobLocation(getCellValueAsString(row.getCell(4)));
	                leadDetails.setPositionName(getCellValueAsString(row.getCell(5)));
	                leadDetails.setRecruiterMail(getCellValueAsString(row.getCell(6)));
	                leadDetails.setLink(getCellValueAsString(row.getCell(7)));
	                leadDetails.setRecruiterNumber(getCellValueAsString(row.getCell(8)));
	                leadDetails.setStatus(getCellValueAsString(row.getCell(9)));
	                leadDetails.setComment(getCellValueAsString(row.getCell(10)));
	                leadDetailsList.add(leadDetails);
	                leadDetailsRepo.saveAll(leadDetailsList);
	                System.out.println("####" + leadDetailsList);
	            }
	           
	            logger.info("Saved LeadDetails from Excel: " + leadDetailsList);
	        } catch (Exception e) {
	            logger.error("Failed to save LeadDetails from Excel: " + e.getMessage());
	        }
	        return leadDetailsList;
	    }
	  
	  private String getCellValueAsString(Cell cell) {
	        if (cell == null) {
	            return null;
	        }
	        return cell.getCellType() == CellType.STRING ? cell.getStringCellValue() : cell.toString();
	    }

	    private LocalDate convertToLocalDate(Cell cell) {
	        if (cell == null || cell.getCellType() != CellType.NUMERIC || !DateUtil.isCellDateFormatted(cell)) {
	            return null;
	        }
	        Date date = cell.getDateCellValue();
	        return date.toInstant().atZone(TimeZone.getDefault().toZoneId()).toLocalDate();
	    }

	    private Double getCellValueAsDouble(Cell cell) {
	        if (cell == null || cell.getCellType() != CellType.NUMERIC) {
	            return null;
	        }
	        return cell.getNumericCellValue();
	    }

	    private Integer getCellValueAsInteger(Cell cell) {
	        if (cell == null || cell.getCellType() != CellType.NUMERIC) {
	            return null;
	        }
	        return (int) cell.getNumericCellValue();
	    }

		@Override
		public void deleteLeadDetailsById(int id) {
			// TODO Auto-generated method stub
			leadDetailsRepo.deleteById(id);
		}

		
	    
	/**
	 * @return : List<Object> leadDetails
	 * @description : For fetching all leadDetails which are active state from
	 *              leaddetails table
	 */
//	@Override
//	public List<Object> getAllLeadDetailss() {
//		logger.debug("Entering getAllLeadDetailss");
//
//		List<Object> leadDetailss = leadDetailsRepo.findByLeadDetailsIsActiveTrue();
//		logger.info("Fetched all active leadDetails :" + leadDetailss);
//		return leadDetailss;
//	}

	/**
	 * @param : LeadDetails to update
	 * @return : leadDetails
	 * @description : For updating leadDetails of leaddetails table
	 */
	@Override
	public LeadDetails updateLeadDetailsById(LeadDetails leadDetails, int id) {
		logger.debug("Entering updateLeadDetails");

		LeadDetails toUpdatedLeadDetails = null;
		LeadDetails updatedLeadDetails = null;

		toUpdatedLeadDetails = leadDetailsRepo.findById(id);
		logger.info("exisitng LeadDetails :: " + toUpdatedLeadDetails);

		if (toUpdatedLeadDetails != null) {
			logger.debug("setting new data of LeadDetails to exisitng LeadDetails");

			toUpdatedLeadDetails.setCompanyName(leadDetails.getCompanyName());
			toUpdatedLeadDetails.setRecruiterName(leadDetails.getRecruiterName());
			toUpdatedLeadDetails.setRecruiterMail(leadDetails.getRecruiterMail());
			toUpdatedLeadDetails.setRecruiterNumber(leadDetails.getRecruiterNumber());
			toUpdatedLeadDetails.setJobLocation(leadDetails.getJobLocation());
			toUpdatedLeadDetails.setPositionName(leadDetails.getPositionName());;
			toUpdatedLeadDetails.setJobLocation(leadDetails.getJobLocation());
			toUpdatedLeadDetails.setDate(leadDetails.getDate());
			toUpdatedLeadDetails.setLink(leadDetails.getLink());
			toUpdatedLeadDetails.setStatus(leadDetails.getStatus());
			toUpdatedLeadDetails.setComment(leadDetails.getComment());
						
			updatedLeadDetails = leadDetailsRepo.save(toUpdatedLeadDetails);

			logger.info("updated LeadDetails :" + updatedLeadDetails);
		}

		return updatedLeadDetails;
	}

	/**
	 * @param : String userid
	 * @return : int (count of record updated)
	 * @description : This is function is used to soft delete the record of
	 *              LeadDetails
	 * 
	 */
//	@Override
//	public int deleteLeadDetailsByuserId(String userid) {
//		logger.debug("Entering deleteLeadDetailsByuserId");
//
//		int count =  leadDetailsRepo.deleteLeadDetailsByuserId(userid);
//		logger.info("deleted LeadDetails count : " + count);
//		return count;
//	}
	
	

}
