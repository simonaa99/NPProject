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

	@Test
	void testSetName() {
		uC.setName("Takmicar");
		
		assertEquals("Takmicar", uC.getName());
	}

	@Test
	void testSetMembershipFeeDiscount() {
		uC.setMembershipFeeDiscount(55.5);
		
		assertEquals(55.5, uC.getMembershipFeeDiscount());
	}

	@Test
	void testToString() {
		uC.setName("Sampion");
		
		String s = uC.toString();

		assertTrue(s.contains("Sampion"));
	}

}
