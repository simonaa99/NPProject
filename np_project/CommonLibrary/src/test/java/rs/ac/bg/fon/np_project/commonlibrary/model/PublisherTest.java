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
	
	@ParameterizedTest
	@CsvSource({
		"0", "-5", "-11111"
	})
	void testSetPublisheridNedozvoljen(Long pId) {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> p.setPublisherId(pId));
	}

	@Test
	void testSetPublisherName() {
		p.setPublisherName("Log");
		assertEquals("Log", p.getPublisherName());
	}
	
	@Test
	void testSetPublisherNamePrazanString() {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> p.setPublisherName("") );
	}
	
	@Test
	void testSetPublisherNameNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> p.setPublisherName(null) );
	}

	@Test
	void testToString() {
		p.setPublisherName("Kira");

		String s = p.toString();

		assertTrue(s.contains("Kira"));
	}
	
	@ParameterizedTest
	@CsvSource({
			"11, 11, true",
			"11, 22, false"
	})
	void testEqualsObject(Long id1, Long id2, boolean eq) {
		p.setPublisherId(id1);
		
		Publisher p2 = new Publisher();
		p2.setPublisherId(id2);
		
		assertEquals(eq, p.equals(p2));
	}

}
