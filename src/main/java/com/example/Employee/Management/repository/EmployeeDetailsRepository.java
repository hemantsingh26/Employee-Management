package com.example.Employee.Management.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.Employee.Management.entities.EmployeeDetails;
import com.example.Employee.Management.enums.TeamEnum;

@Repository
public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails,Integer>{
	
	 @Query("select e from EmployeeDetails e where e.id= :EmployeeId")
	  public EmployeeDetails findEmployeeById(@Param("EmployeeId") Integer EmployeeId);

	 @Query("select e from EmployeeDetails e where e.name= :EmployeeName")
	  public List<EmployeeDetails> findEmployeeByName(@Param("EmployeeName") String EmployeeName);
	 
	 @Query("select e from EmployeeDetails e where e.emailId= :EmployeeEmailId")
	  public List<EmployeeDetails> findEmployeeByEmailId(@Param("EmployeeEmailId") String EmployeeEmailId);
	 
	 @Query("select e from EmployeeDetails e where e.mobileNumber= :EmployeeMobileNumber")
	  public EmployeeDetails findEmployeeByMobileNumber(@Param("EmployeeMobileNumber") String EmployeeMobileNumber);
	 
	 @Transactional
	 @Modifying
	 @Query("delete from EmployeeDetails e where e.id= :EmployeeId")
	  public void deleteEmployeeById(@Param("EmployeeId") Integer EmployeeId);

}
