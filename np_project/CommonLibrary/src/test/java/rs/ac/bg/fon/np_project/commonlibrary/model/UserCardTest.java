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

	@Test
	void testSetCardNumber() {
		uC.setCardNumber("259874163987");
		assertEquals("259874163987", uC.getCardNumber());
	}

	@Test
	void testSetIssueDate() {
		uC.setIssueDate(LocalDate.of(2022, 4, 4));

        assertEquals(LocalDate.of(2022, 4, 4), uC.getIssueDate());
	}

	@Test
	void testSetExpiryDate() {
		uC.setExpiryDate(LocalDate.of(2022, 8, 4));

        assertEquals(LocalDate.of(2022, 8, 4), uC.getExpiryDate());
	}

	@Test
	void testToString() {
		uC.setCardNumber("587412369874");

		String s = uC.toString();

		assertTrue(s.contains("587412369874"));
	}

}
