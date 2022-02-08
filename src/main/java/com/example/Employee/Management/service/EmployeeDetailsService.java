package com.example.Employee.Management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Employee.Management.entities.EmployeeDetails;
import com.example.Employee.Management.enums.TeamEnum;
import com.example.Employee.Management.repository.EmployeeDetailsRepository;
import java.util.List;

@Service
public class EmployeeDetailsService {

	@Autowired
	EmployeeDetailsRepository employeeDetailsRepository;
	
	public EmployeeDetails saveEmployee(EmployeeDetails employee) {
		return employeeDetailsRepository.save(employee);
	}
	
	public List<EmployeeDetails> getAllEmployee() {
		return employeeDetailsRepository.findAll();
	}
	
	public EmployeeDetails getEmployeeById(Integer employeeId) {
	   return employeeDetailsRepository.findEmployeeById(employeeId);
	}

	public List<EmployeeDetails> getEmployeeByName(String employeeName) {
		return employeeDetailsRepository.findEmployeeByName(employeeName);
	}
	
	public List<EmployeeDetails> getEmployeeByEmailId(String employeeEmailId) {
		return employeeDetailsRepository.findEmployeeByEmailId(employeeEmailId);
	}
	
	public EmployeeDetails getEmployeeByMobileNumber(String employeeMobileNumber) {
		return employeeDetailsRepository.findEmployeeByMobileNumber(employeeMobileNumber);
	}
	
	public void deleteEmployeeById(Integer employeeId) {
		 employeeDetailsRepository.deleteEmployeeById(employeeId);
	}


}
