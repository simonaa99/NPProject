package rs.ac.bg.fon.np_project.server.so.game;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import rs.ac.bg.fon.np_project.commonlibrary.model.Game;
import rs.ac.bg.fon.np_project.commonlibrary.model.GameCategory;
import rs.ac.bg.fon.np_project.commonlibrary.model.Publisher;
import rs.ac.bg.fon.np_project.commonlibrary.model.Worker;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryGame;
import rs.ac.bg.fon.np_project.server.so.AbstractSOTest;

class GetGamesByQuerySOTest extends AbstractSOTest {

	@Mock
	RepositoryGame game = new RepositoryGame();
	
	@InjectMocks
	GetGamesByQuerySO getGamesByQuerySO = new GetGamesByQuerySO();
	
	Object param;
	
	@BeforeEach
	void setUp() throws Exception {
		param = new Object();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		game = null;
		getGamesByQuerySO = null;
		param = null;
	}
	
	@Test
	void testPreconditionNull() throws Exception {
		assertNotEquals(null,param,"Poslati objekat je neodgovarajuceg tipa!");
	}
	
	@Test
	void testPreconditionNotInstanceOfString() {
		Object p = new String();
		assertNotEquals(p,param,"Poslati objekat je neodgovarajuceg tipa!");
	}
 
	@Test
	void testExecuteOperation() throws Exception {
		param = new String();
		List<Game> games;
        String query= (String) param;
        
        Game g1 = new Game();
		g1.setGameid(1L);
		g1.setGameName("Monopol");
		g1.setNumberInStock(15);
		g1.setNumPlayers(20);
		g1.setGameCategory(GameCategory.Porodicne_igre);
		Publisher p = new Publisher(2L, "Mirko Markovic");
		g1.setPublisher(p);
		
		Game g2 = new Game();
		g2.setGameid(2L);
		g2.setGameName("Uno");
		g2.setNumberInStock(5);
		g2.setNumPlayers(30);
		g2.setGameCategory(GameCategory.Porodicne_igre);
		Publisher pub = new Publisher(2L, "Mirko Markovic");
		g2.setPublisher(pub);
        
        Mockito.when(game.getByQuery(query)).thenReturn(List.of(g1,g2));
        games = game.getByQuery(query);
        
        List<Game> games2 = new ArrayList<>();
        games2.add(g1);
        games2.add(g2);
        
        assertEquals(games2, games, "Greska prilikom ucitavanja igara.");
        assertDoesNotThrow(()-> getGamesByQuerySO.executeOperation(param));
        
	}

	@Test
	void testGetGamesByQuerySO() {
		assertNotEquals(null, game);
	}

}
