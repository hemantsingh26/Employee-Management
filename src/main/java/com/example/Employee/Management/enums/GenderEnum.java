package com.example.Employee.Management.enums;

public enum GenderEnum {
	MALE("Male"),
    FEMALE("Female");
    
	
	String gender;

    GenderEnum(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return gender;
	}
}
