package com.example.Employee.Management.enums;

public enum TeamEnum {
    RPG("RPG"),
    NET(".NET"),
    PYTHON("Python"),
    JAVA("Java");
	
	String teamName;

	TeamEnum(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamName() {
		return teamName;
	}
	
}
