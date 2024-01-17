package com.mindex.challenge.Integration;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.data.ReportingStructure;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureIntegrationTest {

    private String reportStructIdUrl;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
    	reportStructIdUrl = "http://localhost:" + port + "/reportingstructure/{id}";
    }
    
    @Test
    public void getLennonReportStruct() {
        //
        ReportingStructure readStruct = restTemplate.getForEntity(reportStructIdUrl,
        		ReportingStructure.class,
        		"16a596ae-edd3-4847-99fe-c4518e82c86f") //Lennon's UUID
        		.getBody();
        assertEquals(4, readStruct.getNumberOfReports());

    }
}

