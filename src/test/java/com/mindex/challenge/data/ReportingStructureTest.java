package com.mindex.challenge.data;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class ReportingStructureTest {

	Employee e1, e2, e3, e4, e5, e6;

	//Not all employees are relevant to all tests, but this is too convenient
	@Before
	public void setUpEmps() {
		e1 = new Employee("1", "Bob");
		e2 = new Employee("2", "John");
		e3 = new Employee("3", "Scott");
		e4 = new Employee("4", "Jane");
		e5 = new Employee("5", "Michael");
		e6 = new Employee("6", "Jim");
	}

	@Test
	public void countReports_top() {
		e1.setDirectReports(Arrays.asList(e2));
		e2.setDirectReports(Arrays.asList(e3));
		
		ReportingStructure rs = new ReportingStructure(e1);

		assertEquals(2, rs.getNumberOfReports());
	}

	@Test
	public void countReports_bottom() {
		e1.setDirectReports(Arrays.asList(e2));
		e2.setDirectReports(Arrays.asList(e3));

		ReportingStructure rs = new ReportingStructure(e3);

		assert(rs.getNumberOfReports() == 0);
	}

	@Test
	public void countReports_topTree(){
		e1.setDirectReports(Arrays.asList(new Employee[] {e2, e3}));
		e2.setDirectReports(Arrays.asList(new Employee[] {e4, e5}));
		e3.setDirectReports(Arrays.asList(new Employee[] {e6}));

		ReportingStructure rs = new ReportingStructure(e1);

		assert(rs.getNumberOfReports() == 5);
	}

	@Test
	public void countReports_topTreeNonUniques(){
		e1.setDirectReports(Arrays.asList(new Employee[] {e2, e3, e6}));
		e2.setDirectReports(Arrays.asList(new Employee[] {e4, e5, e6}));
		e3.setDirectReports(Arrays.asList(new Employee[] {e6}));

		ReportingStructure rs = new ReportingStructure(e1);

		assert(rs.getNumberOfReports() == 5);
	}
}
