package com.example.Employee.Management.dtos;


import com.example.Employee.Management.enums.GenderEnum;
import com.example.Employee.Management.enums.TeamEnum;

public class EmployeeDto {

	private Integer id;
	private String name;	
	private String emailId;	
	private String address;	
	private String mobileNumber;	
	private GenderEnum gender;
	private String[] programmingLanguage;	
	private TeamEnum team;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public GenderEnum getGender() {
		return gender;
	}
	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}
	public String[] getProgrammingLanguage() {
		return programmingLanguage;
	}
	public void setProgrammingLanguage(String[] programmingLanguage) {
		this.programmingLanguage = programmingLanguage;
	}
	public TeamEnum getTeam() {
		return team;
	}
	public void setTeam(TeamEnum team) {
		this.team = team;
	}
	
	

}
