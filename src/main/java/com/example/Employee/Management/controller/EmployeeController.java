package com.example.Employee.Management.controller;



import java.io.IOException;
import java.lang.Exception;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Employee.Management.Excel.EmployeeExcelExporter;
import com.example.Employee.Management.dtos.EmployeeDto;
import com.example.Employee.Management.dtos.EmployeeUpdateDto;
import com.example.Employee.Management.entities.EmployeeDetails;
import com.example.Employee.Management.enums.TeamEnum;
import com.example.Employee.Management.service.EmployeeDetailsService;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeDetailsService employeeDetailsService;
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@PostMapping("/employee")
	public EmployeeDto addEmployee(@RequestBody EmployeeDto employeeDto) {
		logger.info("Create new employee");
		EmployeeDetails employeeDetails = new EmployeeDetails();
		
		employeeDetails.setName(employeeDto.getName());
		employeeDetails.setEmailId(employeeDto.getEmailId());
		employeeDetails.setAddress(employeeDto.getAddress());
		employeeDetails.setGender(employeeDto.getGender());
		employeeDetails.setMobileNumber(employeeDto.getMobileNumber());
		employeeDetails.setProgrammingLanguage(String.join(",", employeeDto.getProgrammingLanguage()));
		employeeDetails.setTeam(employeeDto.getTeam());
		
		employeeDetails = employeeDetailsService.saveEmployee(employeeDetails);
		employeeDto.setId(employeeDetails.getId());
		logger.info("Employee Created "+employeeDetails.getName());
		return employeeDto;
	}
	
	@GetMapping("/allEmployee")
	public List<EmployeeDetails> employee() {
		logger.info("Get list of employee");
		return employeeDetailsService.getAllEmployee();
	}
	
	@GetMapping("/employee/{employeeId}")
	public EmployeeDetails employeeById(@PathVariable Integer employeeId){		
		logger.info("Get employee by id");
		return employeeDetailsService.getEmployeeById(employeeId);
		
	}
	
	@GetMapping("/employee/name/{employeeName}")
	public List<EmployeeDetails> employeeByName(@PathVariable String employeeName) {	
		logger.info("Get employee by name");
		return employeeDetailsService.getEmployeeByName(employeeName);
	}
	
	@GetMapping("/employee/email/{employeeEmailId}")
	public List<EmployeeDetails> employeeByEmailId(@PathVariable String employeeEmailId) {	
		logger.info("Get employee by email id");
		return employeeDetailsService.getEmployeeByEmailId(employeeEmailId);
	}
	
	@GetMapping("/employee/mobileNumber/{employeeMobileNumber}")
	public EmployeeDetails employeeByMobileNumber(@PathVariable String employeeMobileNumber) {	
		logger.info("Get employee by mobile number");
		return employeeDetailsService.getEmployeeByMobileNumber(employeeMobileNumber);
	}
	
	@GetMapping("/employee/porgramminglanguage/{employeeProgrammingLanguage}")
	public List<EmployeeDetails> employeeByProgrammingLanguage(@PathVariable String[] employeeProgrammingLanguage) {
		logger.info("Get employee by programming language");
		List<EmployeeDetails> employeeDetailsList = employeeDetailsService.getAllEmployee();
		List<EmployeeDetails> employeeDetailsListSelected = new ArrayList<EmployeeDetails>();
		
		for(EmployeeDetails employeeDetails : employeeDetailsList) {
			for(String language : employeeProgrammingLanguage) {
				if(employeeDetails.getProgrammingLanguage().contains(language)) {
					System.out.println(employeeDetails.getId());
					employeeDetailsListSelected.add(employeeDetails);
					break;
				}
			}
		}
		logger.info("Get successfully");
		return employeeDetailsListSelected;
	}
	
	@GetMapping("/employee/team/{employeeTeamName}")
	public List<EmployeeDetails> employeeByTeam(@PathVariable TeamEnum[] employeeTeamName) {	
		logger.info("Get employee by team name");
		List<EmployeeDetails> employeeDetailsList = employeeDetailsService.getAllEmployee();
		List<EmployeeDetails> employeeDetailsListSelected = new ArrayList<EmployeeDetails>();
		
		for(EmployeeDetails employeeDetails : employeeDetailsList) {
			for(TeamEnum team : employeeTeamName) {
				if(employeeDetails.getTeam().equals(team)) {
					System.out.println(employeeDetails.getId());
					employeeDetailsListSelected.add(employeeDetails);
					break;
				}
			}
		}
		logger.info("Get successfully");
		return employeeDetailsListSelected;
	}
	
	@DeleteMapping("/employee/delete/{employeeId}")
	public String employeeDeleteById(@PathVariable Integer employeeId) {	
		logger.info("Delete employee successfully");
		 employeeDetailsService.deleteEmployeeById(employeeId);
		 return "Employee Details has been deleted";
	}
	
	@PutMapping("/employee/update/{employeeId}")
	public EmployeeDetails employeeDetailsUpdate(@RequestBody EmployeeUpdateDto employeeUpdateDto,@PathVariable Integer employeeId) {
		logger.info("Update employee");
		EmployeeDetails employeeDetails = employeeDetailsService.getEmployeeById(employeeId);
		
		if(employeeUpdateDto.getAddress() != null) {
			employeeDetails.setAddress(employeeUpdateDto.getAddress());
			logger.info("Update employee address");
		}
		if(employeeUpdateDto.getEmailId() != null) {
			employeeDetails.setEmailId(employeeUpdateDto.getEmailId());
			logger.info("Update employee email id");
		}
		if(employeeUpdateDto.getMobileNumber() != null) {
			employeeDetails.setMobileNumber(employeeUpdateDto.getMobileNumber());
			logger.info("Update employee mobile number");
		}
		
		employeeDetailsService.saveEmployee(employeeDetails);
		logger.info("Update employee successfully");
		return employeeDetails;
	}

    @GetMapping("/employee/excelExport")
    public void exportToExcel(HttpServletResponse response) throws IOException {
    	logger.info("Download employee details excel file");
    	response.setContentType("application/octet-stream");
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=EmployeeDetails.xlsx";
        response.setHeader(headerKey, headerValue);
         
        List<EmployeeDetails> listEmployee = employeeDetailsService.getAllEmployee();
         
        EmployeeExcelExporter excelExporter = new EmployeeExcelExporter(listEmployee);
        logger.info("Download employee details excel file successfully");
        excelExporter.export(response);    
    }
    
   

}
