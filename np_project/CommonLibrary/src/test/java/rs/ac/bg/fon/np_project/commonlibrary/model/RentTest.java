package rs.ac.bg.fon.np_project.commonlibrary.model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RentTest {

	Rent r;
	
	@BeforeEach
	void setUp() throws Exception {
		r = new Rent();
	}

	@AfterEach
	void tearDown() throws Exception {
		r = null;
	}

	@Test
	void testGetAttributeList() {
		assertEquals("clanId, igraId, datumIznajmljivanja", r.getAttributeList());
	}

	@Test
	void testGetClassName() {
		assertEquals("iznajmljivanje", r.getClassName());
	}

	@Test
	void testGetAttributeValues() {
		r.setId(5L);
		
		Game g = new Game();
		g.setGameid(1L);
		g.setGameName("Karte");
		g.setNumberInStock(20);
		g.setNumPlayers(7);
		g.setPublisher(new Publisher(1L,"Cards Inc"));
		g.setGameCategory(GameCategory.Party_games);
		r.setGame(g);
		
		User user = new User();
		user.setUserId(8L);
		r.setUser(user);
		
		r.setRentalDate(LocalDate.of(2022, 1, 1));
		r.setReturnDate(LocalDate.of(2022, 4, 4));
		
		
		assertEquals(""+r.getUser().getUserId()+", "+r.getGame().getGameid()+", "+"'"+Date.valueOf(r.getRentalDate())+"'", r.getAttributeValues());
	}

	@Test
	void testGetQueryCondition() {
		r = null;
		assertNull(r);
	}

	@ParameterizedTest
    @CsvSource({"1", "8", "4", "100"})
	void testSetId(Long rID) {
		r.setId(rID);
		assertEquals(rID, r.getId());
	}
	
	@ParameterizedTest
	@CsvSource({
		"0", "-5", "-11111"
	})
	void testSetIdNedozvoljen(Long rId) {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> r.setId(rId));
	}

	@Test
	void testSetGame() {
		Game g = new Game();
		g.setGameid(1L);
		g.setGameName("Havana");
		g.setNumberInStock(15);
		g.setNumPlayers(10);
		g.setPublisher(new Publisher(1L,"Jawa"));
		g.setGameCategory(GameCategory.Party_games);
		r.setGame(g);
		
		assertEquals(g,r.getGame());
	}
	
	@Test
	void testSetGameNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> r.setGame(null) );
	}

	@Test
	void testSetUser() {
		User user = new User();
		user.setUserId(7L);
		user.setName("Mirko");
		user.setLastName("Kovic");
		
		UserCategory userCategory = new UserCategory();
		userCategory.setName("Takmicar");
		userCategory.setUserCategoryId(2L);
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
		r.setUser(user);
		
		assertEquals(user,r.getUser());
		
	}
	
	@Test
	void testSetUserNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> r.setUser(null) );
	}

	@Test
	void testSetRentalDate() {
		r.setRentalDate(LocalDate.of(2022, 4, 4));

        assertEquals(LocalDate.of(2022, 4, 4), r.getRentalDate());
	}
	
	@Test
	void testSetRentalDateNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> r.setRentalDate(null) );
	}

	@Test
	void testSetReturnDate() {
		r.setReturnDate(LocalDate.of(2022, 10, 5));

        assertEquals(LocalDate.of(2022, 10, 5), r.getReturnDate());
	}
	
	@Test
	void testSetReturnDateNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> r.setReturnDate(null) );
	}
	
	@ParameterizedTest
	@CsvSource({
			"11, 11,true",
			"11, 22,false"
	})
	void testEqualsObject(Long id1, Long id2,boolean eq) {
		r.setId(id1);		
		
		Rent r2 = new Rent();
		r2.setId(id2);
		
		assertEquals(eq, r.equals(r2));
	}

}
