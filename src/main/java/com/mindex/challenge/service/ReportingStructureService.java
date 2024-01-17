package com.mindex.challenge.service;

import com.mindex.challenge.data.ReportingStructure;
import org.springframework.stereotype.Service;

public interface ReportingStructureService {
	ReportingStructure getFullReportingStructure(String empId);
}
