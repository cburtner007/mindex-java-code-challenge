package com.mindex.challenge.Integration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.data.Compensation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Not much to do with unit testing, as all methods are basically pass through.
 * But here are some integration tests!
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationIntegrationTest {
    private String compCreateUrl;
    private String compReadUrl;
    private String compReadByEmpIdUrl;
    
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    @Before
    public void setup() {
    	compCreateUrl = "http://localhost:" + port + "/compensation";
    	compReadUrl = "http://localhost:" + port + "/compensation/{id}";
    	compReadByEmpIdUrl = "http://localhost:" + port + "/compensationbyempid/{id}";
    }

    @Test
    public void testCreateRead() throws ParseException {
        Compensation testComp = new Compensation();
        testComp.setEmployeeId("03aa1462-ffa9-4978-901b-7c001562cf6f"); //Ringo
        testComp.setSalary(100000);
        testComp.setEffectiveDate(dateFormat.parse("2024-01-01"));
        
        Compensation createdComp = restTemplate.postForEntity(compCreateUrl, testComp, Compensation.class).getBody();
        assertNotNull(createdComp.getEmployeeId());
        assertCompEquivalence(testComp, createdComp);

        Compensation readComp = restTemplate.getForEntity(compReadUrl, Compensation.class, createdComp.getId()).getBody();
        assertEquals(createdComp.getId(), readComp.getId());
        assertCompEquivalence(createdComp, readComp);
    }
    
    @Test
    public void testCreateReadAll() {
        //Create many compensations for Lennon
        Compensation testComp1 = new Compensation();
        testComp1.setEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f"); //Lennon
        testComp1.setSalary(100000);
        testComp1.setEffectiveDate(new Date());

        Compensation testComp2 = new Compensation();
        testComp2.setEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f"); //Lennon
        testComp2.setSalary(200000);
        testComp2.setEffectiveDate(new Date());

        Compensation testComp3 = new Compensation();
        testComp3.setEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f"); //Lennon
        testComp3.setSalary(300000);
        testComp3.setEffectiveDate(new Date());

        Compensation testComp4 = new Compensation();
        testComp4.setEmployeeId("b7839309-3348-463b-a7e3-5de1c168beb3"); //Paul
        testComp4.setSalary(33);
        testComp4.setEffectiveDate(new Date());
        
        //Post them
        Compensation createdComp1 = restTemplate.postForEntity(compCreateUrl, testComp1, Compensation.class).getBody();
        Compensation createdComp2 = restTemplate.postForEntity(compCreateUrl, testComp2, Compensation.class).getBody();
        Compensation createdComp3 = restTemplate.postForEntity(compCreateUrl, testComp3, Compensation.class).getBody();
        Compensation createdComp4 = restTemplate.postForEntity(compCreateUrl, testComp4, Compensation.class).getBody();
        
        //Read all of them at once
        ResponseEntity<List<Compensation>> response = restTemplate.exchange(
                compReadByEmpIdUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Compensation>>() {},
                "16a596ae-edd3-4847-99fe-c4518e82c86f"
            );
        List<Compensation> readComps = response.getBody();
       
        //Check that they are all there
        assertEquals(3, readComps.size());
    }
    

    private static void assertCompEquivalence(Compensation expected, Compensation actual) {
        assertEquals(expected.getEmployeeId(), actual.getEmployeeId());
        assertTrue(expected.getSalary() == actual.getSalary());
        assertEquals(expected.getEffectiveDate(), actual.getEffectiveDate());
    }
    
}
