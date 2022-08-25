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
	void testSetFirstNamePrazanString() {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> w.setFirstName("") );
	}
	
	@Test
	void testSetFirstNameNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> w.setFirstName(null) );
	}

	@Test
	void testSetLastName() {
		w.setLastName("Makovic");
		assertEquals("Makovic",w.getLastName());
	}
	
	@Test
	void testSetLastNamePrazanString() {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> w.setLastName("") );
	}
	
	@Test
	void testSetLastNameNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> w.setLastName(null) );
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
	
	@ParameterizedTest
	@CsvSource({
		"0", "-5", "-11111"
	})
	void testSetIdNedozvoljen(Long wId) {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> w.setId(wId));
	}

	@Test
	void testSetUsername() {
		w.setUsername("tanja");
		assertEquals("tanja",w.getUsername());
	}
	
	@Test
	void testSetUsernamePrazanString() {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> w.setUsername("") );
	}
	
	@Test
	void testSetUsernameNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> w.setUsername(null) );
	}

	@Test
	void testSetPassword() {
		w.setUsername("m1456");
		assertEquals("m1456",w.getUsername());
	}
	
	@Test
	void testSetPasswordPrazanString() {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> w.setPassword("") );
	}
	
	@Test
	void testSetPasswordNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> w.setPassword(null) );
	}
	
	@ParameterizedTest
	@CsvSource({
			"1234, 1234, Mina, Mina, Peric, Peric, true",
			"1234, 1234, Mina, Mina, Peric, Maric, false",
			"1234, 1234, Mina, Nina, Peric, Peric, false",
			"1234, 4321, Mina, Mina, Peric, Peric, false",
			"1234, 4321, Mina, Nina, Peric, Peric, false",
			"1234, 4321, Mina, Mina, Peric, Maric, false",
			"1234, 1234, Mina, Nina, Peric, Maric, false",
			"1234, 4321, Mina, Nina, Peric, Maric, false",
	})
	void testEqualsObject(Long id1, Long id2, String ime1, String ime2, String prezime1, String prezime2, boolean eq) {
		w.setId(id1);
		w.setFirstName(ime1);
		w.setLastName(prezime1);
		
		Worker w2 = new Worker();
		w2.setId(id2);
		w2.setFirstName(ime2);
		w2.setLastName(prezime2);
		
		assertEquals(eq, w.equals(w2));
	}

}
