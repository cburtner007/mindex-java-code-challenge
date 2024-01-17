package com.mindex.challenge.data;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ReportingStructure {
	private Employee employee;
	private int numberOfReports;

	@JsonIgnore
	private Map<String, Boolean> countedReports = new HashMap<String, Boolean>();
	public ReportingStructure() {
		
	}
	
	public ReportingStructure(Employee e) {
		this.employee = e;
		this.numberOfReports = 0;
		this.setUniqueReports(e);
	}

	private void setUniqueReports(Employee e) {
		if (e.getDirectReports() != null) {
			for (Employee report : e.getDirectReports()) {
				if (countedReports.get(report.getEmployeeId()) == null) {
					countedReports.put(report.getEmployeeId(), true);
					numberOfReports++;
				}
				setUniqueReports(report);
			}
		}
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public int getNumberOfReports() {
		return numberOfReports;
	}

	public void setNumberOfReports(int numberOfReports) {
		this.numberOfReports = numberOfReports;
	}
	
}
