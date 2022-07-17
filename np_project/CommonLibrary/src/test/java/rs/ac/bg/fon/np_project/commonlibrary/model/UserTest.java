package rs.ac.bg.fon.np_project.commonlibrary.model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class UserTest {

	User u;
	
	@BeforeEach
	void setUp() throws Exception {
		u = new User();
	}

	@AfterEach
	void tearDown() throws Exception {
		u = null;
	}

	@Test
	void testGetAttributeList() {
		assertEquals("ime, prezime, brojTelefona, adresa, kategorijaId, clanskaKartaId", u.getAttributeList());
	}

	@Test
	void testGetClassName() {
		assertEquals("clan", u.getClassName());
	}

	@Test
	void testGetAttributeValues() {
		User user = new User();
		user.setUserId(7L);
		user.setName("Mirko");
		user.setLastName("Kovic");
		
		UserCategory userCategory = new UserCategory();
		userCategory.setUserCategoryId(2L);	
		userCategory.setName("Takmicar");
		userCategory.setMembershipFeeDiscount(50.0);
		user.setUserCategory(userCategory);
		
		user.setAddress("Majka Jevrosima 44, 11000 Beograd");
		user.setPhoneNumber("0659761345");
		
		UserCard userCard = new UserCard();
		userCard.setId(7L);
		userCard.setCardNumber("15978462315789632145");
		userCard.setExpiryDate(LocalDate.of(2022, 1, 1));
		userCard.setIssueDate(LocalDate.of(2022, 1, 1));
		user.setUsercard(userCard);
		u = user;

		assertEquals("'"+u.getName()+"', '"+u.getLastName()+"', '"+u.getPhoneNumber()+"', '"+u.getAddress()+"', "+u.getUserCategory().getUserCategoryId()+", "+u.getUsercard().getId(), u.getAttributeValues());
	}

	@Test
	void testGetQueryCondition() {
		User user = new User();
		user.setUserId(7L);
		user.setName("Mirko");
		user.setLastName("Kovic");
		
		UserCategory userCategory = new UserCategory();
		userCategory.setUserCategoryId(2L);	
		userCategory.setName("Takmicar");
		userCategory.setMembershipFeeDiscount(50.0);
		user.setUserCategory(userCategory);
		
		user.setAddress("Majka Jevrosima 44, 11000 Beograd");
		user.setPhoneNumber("0659761345");
		
		UserCard userCard = new UserCard();
		userCard.setId(7L);
		userCard.setCardNumber("15978462315789632145");
		userCard.setExpiryDate(LocalDate.of(2022, 1, 1));
		userCard.setIssueDate(LocalDate.of(2022, 1, 1));
		user.setUsercard(userCard);
		u = user;

		
		assertEquals("id="+u.getUserId(), u.getQueryCondition());
	}

	@ParameterizedTest
    @CsvSource({"1", "8", "4", "100"})
	void testSetUserId(Long uID) {
		u.setUserId(uID);
		assertEquals(uID, u.getUserId());
	}

	@Test
	void testSetName() {
		u.setName("Jovan");
		
		assertEquals("Jovan", u.getName());
	}

	@Test
	void testSetLastName() {
		u.setLastName("Jovanovic");
		
		assertEquals("Jovanovic", u.getLastName());
	}

	@Test
	void testSetPhoneNumber() {
		u.setPhoneNumber("0648751489");;
		
		assertEquals("0648751489", u.getPhoneNumber());
	}

	@Test
	void testSetAddress() {
		u.setAddress("Milutina Ostojica 14, 11000 Beograd");
		
		assertEquals("Milutina Ostojica 14, 11000 Beograd", u.getAddress());
	}

	@Test
	void testSetUserCategory() {
		UserCategory uC = new UserCategory();
		
		uC.setUserCategoryId(5L);
		uC.setName("Sampion");
		uC.setMembershipFeeDiscount(100.00);
		u.setUserCategory(uC);
		
		assertEquals(uC, u.getUserCategory());
	}

	@Test
	void testSetUsercard() {
		UserCard userCard = new UserCard();
		
		userCard.setId(7L);
		userCard.setCardNumber("15978462315789632145");
		userCard.setExpiryDate(LocalDate.of(2022, 1, 1));
		userCard.setIssueDate(LocalDate.of(2022, 1, 1));
		u.setUsercard(userCard);
		
		assertEquals(userCard, u.getUsercard());
	}

}
