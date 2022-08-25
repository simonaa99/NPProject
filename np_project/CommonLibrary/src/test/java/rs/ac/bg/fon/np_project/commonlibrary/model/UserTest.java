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
	
	@ParameterizedTest
	@CsvSource({
		"0", "-5", "-11111"
	})
	void testSetUserIdNedozvoljen(Long uId) {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> u.setUserId(uId));
	}

	@Test
	void testSetName() {
		u.setName("Jovan");
		
		assertEquals("Jovan", u.getName());
	}
	
	@Test
	void testSetNamePrazanString() {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> u.setName("") );
	}
	
	@Test
	void testSetNameNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> u.setName(null) );
	}

	@Test
	void testSetLastName() {
		u.setLastName("Jovanovic");
		
		assertEquals("Jovanovic", u.getLastName());
	}
	
	@Test
	void testSetLastNamePrazanString() {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> u.setLastName("") );
	}
	
	@Test
	void testSetLastNameNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> u.setLastName(null) );
	}

	@Test
	void testSetPhoneNumber() {
		u.setPhoneNumber("0648751489");;
		
		assertEquals("0648751489", u.getPhoneNumber());
	}
	
	@Test
	void testSetPhoneNumberPrazanString() {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> u.setPhoneNumber("") );
	}
	
	@Test
	void testSetPhoneNumberNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> u.setPhoneNumber(null) );
	}

	@Test
	void testSetAddress() {
		u.setAddress("Milutina Ostojica 14, 11000 Beograd");
		
		assertEquals("Milutina Ostojica 14, 11000 Beograd", u.getAddress());
	}
	
	@Test
	void testSetAddressPrazanString() {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> u.setAddress("") );
	}
	
	@Test
	void testSetAddressNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> u.setAddress(null) );
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
	void testSetUserCategoryNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> u.setUserCategory(null) );
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
	
	@Test
	void testSetUsercardNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> u.setUsercard(null) );
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
		u.setUserId(id1);
		u.setName(ime1);
		u.setLastName(prezime1);
		
		User u2 = new User();
		u2.setUserId(id2);
		u2.setName(ime2);
		u2.setLastName(prezime2);
		
		assertEquals(eq, u.equals(u2));
	}

}
