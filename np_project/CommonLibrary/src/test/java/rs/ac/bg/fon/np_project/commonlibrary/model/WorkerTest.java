package rs.ac.bg.fon.np_project.commonlibrary.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WorkerTest {

	Worker w;
	
	@BeforeEach
	void setUp() throws Exception {
		w = new Worker();
	}

	@AfterEach
	void tearDown() throws Exception {
		w=null;
	}

	@Test
	void testGetAttributeList() {
		assertEquals("username, password, ime, prezime", w.getAttributeList());
	}

	@Test
	void testGetClassName() {
		assertEquals("radnik", w.getClassName());
	}

	@Test
	void testGetAttributeValues() {
		w.setId(5L);
		w.setFirstName("Marko");
		w.setLastName("Markovic");
		w.setLoggedIn(true);
		w.setPassword("12358");
		w.setUsername("mare88");
		
		assertEquals("'"+w.getUsername()+"', '"+w.getPassword()+"', '"+w.getFirstName()+"', '"+w.getLastName()+"'", w.getAttributeValues());
	}

	@Test
	void testGetQueryCondition() {
		w.setId(77L);
		w.setFirstName("Mara");
		w.setLastName("Lolic");
		w.setLoggedIn(true);
		w.setPassword("1475");
		w.setUsername("marabubamara");
		
		assertEquals("radnikID="+w.getId(), w.getQueryCondition());
	}

	@Test
	void testSetLoggedIn() {
		w.setLoggedIn(true);
		assertEquals(true, w.isLoggedIn());
	}

	@Test
	void testSetFirstName() {
		w.setFirstName("Helena");
		assertEquals("Helena",w.getFirstName());
	}

	@Test
	void testSetLastName() {
		w.setLastName("Makovic");
		assertEquals("Makovic",w.getLastName());
	}

	@Test
	void testWorkerLongStringString() {
		w = new Worker(5L, "jana147","17051994");

		assertEquals(5L, w.getId());
		assertEquals("jana147", w.getUsername());
		assertEquals("17051994", w.getPassword());
	}

	@ParameterizedTest
    @CsvSource({"1", "8", "4", "100"})
	void testSetId(Long wID) {
		w.setId(wID);
		assertEquals(wID, w.getId());
	}

	@Test
	void testSetUsername() {
		w.setUsername("tanja");
		assertEquals("tanja",w.getUsername());
	}

	@Test
	void testSetPassword() {
		w.setUsername("m1456");
		assertEquals("m1456",w.getUsername());
	}

}
