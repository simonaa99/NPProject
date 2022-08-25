package rs.ac.bg.fon.np_project.commonlibrary.model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class UserCardTest {

	UserCard uC;
	
	@BeforeEach
	void setUp() throws Exception {
		uC = new UserCard();
	}

	@AfterEach
	void tearDown() throws Exception {
		uC = null;
	}

	@Test
	void testGetAttributeList() {
		assertEquals("brojClanskeKarte, datumIzdavanja, datumIsteka", uC.getAttributeList());
	}

	@Test
	void testGetClassName() {
		assertEquals("clanskaKarta", uC.getClassName());
	}

	@Test
	void testGetAttributeValues() {
		uC.setId(7L);
		uC.setCardNumber("147852369874125");
		uC.setExpiryDate(LocalDate.of(2022, 12, 12));
		uC.setIssueDate(LocalDate.of(2021, 12, 12));


		assertEquals("'"+uC.getCardNumber()+"', '"+Date.valueOf(uC.getIssueDate())+"', '"+Date.valueOf(uC.getExpiryDate())+"'", uC.getAttributeValues());
	}

	@Test
	void testGetQueryCondition() {
		uC.setId(1L);
		uC.setCardNumber("874512369852");
		uC.setExpiryDate(LocalDate.of(2022, 5, 5));
		uC.setIssueDate(LocalDate.of(2021, 12, 12));

		assertEquals("brojClanskeKarte= '"+uC.getCardNumber()+"'", uC.getQueryCondition());
	}

	@ParameterizedTest
    @CsvSource({"1", "8", "4", "100"})
	void testSetId(Long uCID) {
		uC.setId(uCID);
		assertEquals(uCID, uC.getId());
	}
	
	@ParameterizedTest
	@CsvSource({
		"0", "-5", "-11111"
	})
	void testSetIdNedozvoljen(Long uCId) {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> uC.setId(uCId));
	}

	@Test
	void testSetCardNumber() {
		uC.setCardNumber("259874163987");
		assertEquals("259874163987", uC.getCardNumber());
	}
	
	@Test
	void testSetCardNumberPrazanString() {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> uC.setCardNumber("") );
	}
	
	@Test
	void testSetCardNumberNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> uC.setCardNumber(null) );
	}

	@Test
	void testSetIssueDate() {
		uC.setIssueDate(LocalDate.of(2022, 4, 4));

        assertEquals(LocalDate.of(2022, 4, 4), uC.getIssueDate());
	}
	
	@Test
	void testSetIssueDateNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> uC.setIssueDate(null) );
	}

	@Test
	void testSetExpiryDate() {
		uC.setExpiryDate(LocalDate.of(2022, 8, 4));

        assertEquals(LocalDate.of(2022, 8, 4), uC.getExpiryDate());
	}
	
	@Test
	void testSetExpiryDateNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> uC.setExpiryDate(null) );
	}

	@Test
	void testToString() {
		uC.setCardNumber("587412369874");

		String s = uC.toString();

		assertTrue(s.contains("587412369874"));
	}

	@ParameterizedTest
	@CsvSource({
			"11, 11, 123456, 123456, true",
			"11, 22, 123456, 123456, false",
			"11, 11, 123456, 654321, false",
			"11, 22, 123456, 654321, false"
	})
	void testEqualsObject(Long id1, Long id2, String brojKart1, String brojKart2, boolean eq) {
		uC.setId(id1);
		uC.setCardNumber(brojKart1);
		
		UserCard uC2 = new UserCard();
		uC2.setId(id2);
		uC2.setCardNumber(brojKart2);
		
		assertEquals(eq, uC.equals(uC2));
	}
}
