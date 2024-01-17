package com.mindex.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class ReportingStructureController {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private ReportingStructureService reportingStructureService;

    @GetMapping("/reportingstructure/{id}")
    public ReportingStructure read(@PathVariable String id) {
        LOG.debug("Received Reporting Structure GET request for id [{}]", id);

        ReportingStructure returnStruct = reportingStructureService.getFullReportingStructure(id);        
        return returnStruct;
    }
}