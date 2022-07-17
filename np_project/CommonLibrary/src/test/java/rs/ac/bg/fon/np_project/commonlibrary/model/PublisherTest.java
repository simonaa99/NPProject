package rs.ac.bg.fon.np_project.commonlibrary.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PublisherTest {

	Publisher p;
	
	@BeforeEach
	void setUp() throws Exception {
		p = new Publisher();
	}

	@AfterEach
	void tearDown() throws Exception {
		p = null;
	}

	@Test
	void testGetAttributeList() {
		assertEquals("imePrezime", p.getAttributeList());
	}

	@Test
	void testGetClassName() {
		assertEquals("izdavac", p.getClassName());
	}

	@Test
	void testGetAttributeValues() {
		p.setPublisherId(5L);
		p.setPublisherName("Kiki");
		
		assertEquals("'"+p.getPublisherName()+"'", p.getAttributeValues());
	}

	@Test
	void testGetQueryCondition() {
		p.setPublisherId(10L);
		p.setPublisherName("Lagun");
		
		assertEquals("id= "+p.getPublisherId(), p.getQueryCondition());
	}

	@Test
	void testPublisherLongString() {
		p = new Publisher(5L, "Ottawa");

		assertEquals(5L, p.getPublisherId());
		assertEquals("Ottawa", p.getPublisherName());
	}


	@ParameterizedTest
    @CsvSource({"1", "8", "4", "100"})
	void testSetPublisherId(Long pID) {
		p.setPublisherId(pID);
		assertEquals(pID, p.getPublisherId());
	}

	@Test
	void testSetPublisherName() {
		p.setPublisherName("Log");
		assertEquals("Log", p.getPublisherName());
	}

	@Test
	void testToString() {
		p.setPublisherName("Kira");

		String s = p.toString();

		assertTrue(s.contains("Kira"));
	}

}
