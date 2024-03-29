package rs.ac.bg.fon.np_project.commonlibrary.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GameTest {

	Game g;
	
	@BeforeEach
	void setUp() throws Exception {
		g = new Game();
	}

	@AfterEach
	void tearDown() throws Exception {
		g = null;
	}

	@Test
	void testGetAttributeList() {
		assertEquals("naziv, brojIgraca, kategorijaId, izdavacId, kolicina", g.getAttributeList());
	}

	@Test
	void testGetClassName() {
		assertEquals("igra", g.getClassName());
	}

	@Test
	void testGetAttributeValues() {
		g.setGameid(1L);
		g.setGameName("Havana");
		g.setNumberInStock(15);
		g.setNumPlayers(10);
		g.setPublisher(new Publisher(1L,"Jawa"));
		g.setGameCategory(GameCategory.Party_games);
		
		
		assertEquals("'"+g.getGameName()+"', "+g.getNumPlayers()+", "+(g.getGameCategory().ordinal()+1)+", "+g.getPublisher().getPublisherId()+", "+g.getNumberInStock(), g.getAttributeValues());
	}

	@Test
	void testGetQueryCondition() {
		g.setGameid(1L);
		g.setGameName("Havana");
		g.setNumberInStock(15);
		g.setNumPlayers(10);
		g.setPublisher(new Publisher(1L,"Jawa"));
		g.setGameCategory(GameCategory.Party_games);
		
		assertEquals("id="+g.getGameid(), g.getQueryCondition());
	}

	
	@ParameterizedTest
    @CsvSource({"1", "8", "4", "100"})
	void testSetGameid(Long gameId) {
		g.setGameid(gameId);
		assertEquals(gameId, g.getGameid());
	}
	
	@ParameterizedTest
	@CsvSource({
		"0", "-5", "-11111"
	})
	void testSetGameidNedozvoljen(Long gameId) {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> g.setGameid(gameId));
	}

	@Test
	void testSetGameName() {
		g.setGameName("Riziko");
		assertEquals("Riziko", g.getGameName());
	}
	
	@Test
	void testSetGameNamePrazanString() {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> g.setGameName("") );
	}
	
	@Test
	void testSetGameNameNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> g.setGameName(null) );
	}

	@ParameterizedTest
    @CsvSource({"1", "20", "4", "6"})
	void testSetNumPlayers(Integer numPlayers) {
		g.setNumPlayers(numPlayers);
		assertEquals(numPlayers, g.getNumPlayers());
	}
	
	@ParameterizedTest
	@CsvSource({
		"0", "-5", "-11111"
	})
	void testSetNumPlayersNedozvoljen(Integer numPlayers) {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> g.setNumPlayers(numPlayers));
	}

	@Test
	void testSetGameCategory() {
		g.setGameCategory(GameCategory.Igre_na_srpskom);
		assertEquals(GameCategory.Igre_na_srpskom,g.getGameCategory());
	}
	
	@Test
	void testSetGameCategoryNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> g.setGameCategory(null) );
	}

	@Test
	void testSetPublisher() {
		Publisher p = new Publisher(1L, "Monopol Inc");
		
		g.setPublisher(p);
		assertEquals(p, g.getPublisher());
	}
	
	@Test
	void testSetPublisherNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> g.setPublisher(null) );
	}

	@ParameterizedTest
    @CsvSource({"1", "20", "4", "6"})
	void testSetNumberInStock(Integer numberInStock) {
		g.setNumberInStock(numberInStock);
		assertEquals(numberInStock, g.getNumberInStock());
	}
	
	@ParameterizedTest
	@CsvSource({
		"0", "-5", "-11111"
	})
	void testSetNumberInStockNedozvoljen(Integer numberInStock) {
		assertThrows(java.lang.IllegalArgumentException.class,
				() -> g.setNumPlayers(numberInStock));
	}
	
	@ParameterizedTest
	@CsvSource({
			"1234, 1234, Monopol, Monopol, true",
			"543, 543, Monopol, Catan, false",
			"543, 1111, Monopol, Monopol, false",
			"543, 11111, Monopol, Catan, false"
	})
	void testEqualsObject(Long id1, Long id2, String ime1, String ime2, boolean eq) {
		g.setGameid(id1);
		g.setGameName(ime1);
		
		Game g2 = new Game();
		g2.setGameid(id2);
		g2.setGameName(ime2);
		
		assertEquals(eq, g.equals(g2));
	}

}
