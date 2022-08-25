package rs.ac.bg.fon.np_project.commonlibrary.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class UserCategoryTest {

	UserCategory uC;
	
	@BeforeEach
	void setUp() throws Exception {
		uC = new UserCategory();
	}

	@AfterEach
	void tearDown() throws Exception {
		uC = null;
	}

	@ParameterizedTest
    @CsvSource({"1", "8", "4", "100"})
	void testSetUserCategoryId(Long uCID) {
		uC.setUserCategoryId(uCID);
		assertEquals(uCID, uC.getUserCategoryId());
	}
	
	@ParameterizedTest
	@CsvSource({
		"0", "-5", "-11111"
	})
	void testSetUserCategoryIdNedozvoljen(Long uCId) {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> uC.setUserCategoryId(uCId));
	}

	@Test
	void testSetName() {
		uC.setName("Takmicar");
		
		assertEquals("Takmicar", uC.getName());
	}
	
	@Test
	void testSetNamePrazanString() {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> uC.setName("") );
	}
	
	@Test
	void testSetNameNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> uC.setName(null) );
	}

	@Test
	void testSetMembershipFeeDiscount() {
		uC.setMembershipFeeDiscount(55.5);
		
		assertEquals(55.5, uC.getMembershipFeeDiscount());
	}
	
	@ParameterizedTest
	@CsvSource({
		"0.0", "-5.5", "-11111.11"
	})
	void testSetMembershipFeeDiscountNedozvoljen(double md) {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> uC.setMembershipFeeDiscount(md));
	}
	

	@Test
	void testToString() {
		uC.setName("Sampion");
		
		String s = uC.toString();

		assertTrue(s.contains("Sampion"));
	}

	@ParameterizedTest
	@CsvSource({
			"1234, 1234, Takmicar, Takmicar, true",
			"1234, 1234, Takmicar, Pobednik, false",
			"1234, 4321, Takmicar, Takmicar, false",
			"1234, 4321, Takmicar, Pobednik, false"
	})
	void testEqualsObject(Long id1, Long id2, String ime1, String ime2, boolean eq) {
		uC.setUserCategoryId(id1);
		uC.setName(ime1);
		
		UserCategory uC2 = new UserCategory();
		uC2.setUserCategoryId(id2);
		uC2.setName(ime2);
		
		assertEquals(eq, uC.equals(uC2));
	}
}
