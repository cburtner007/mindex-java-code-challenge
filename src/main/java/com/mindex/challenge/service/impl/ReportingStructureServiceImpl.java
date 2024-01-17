package com.mindex.challenge.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService{
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeService employeeService;

	@Override
	public ReportingStructure getFullReportingStructure(String empId) {
		Employee e = readEmployeeRecursive(empId);
		ReportingStructure rs =  new ReportingStructure(e);
		return rs;
	}
	
	private Employee readEmployeeRecursive(String empId){
		Employee e = employeeService.read(empId);
		
		//Recursion continues if given node has children
		if (e.getDirectReports() != null) {
			//recurse through all child nodes
			for (Employee report : e.getDirectReports()) {
		        LOG.debug("Recursing into employee [{}]", report.getEmployeeId());
				Employee recursedReport = readEmployeeRecursive(report.getEmployeeId());
				
				//While undwinding, build up direct reports of child nodes
				if(recursedReport.getDirectReports() != null) {
					report.setDirectReports(recursedReport.getDirectReports());
				}
			}
		}
		return e;
	}
}
