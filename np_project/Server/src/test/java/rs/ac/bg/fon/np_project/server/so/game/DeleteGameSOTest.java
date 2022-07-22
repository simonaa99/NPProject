package rs.ac.bg.fon.np_project.server.so.game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import rs.ac.bg.fon.np_project.commonlibrary.model.Game;
import rs.ac.bg.fon.np_project.commonlibrary.model.GameCategory;
import rs.ac.bg.fon.np_project.commonlibrary.model.Publisher;
import rs.ac.bg.fon.np_project.commonlibrary.model.Worker;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryGame;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryPublisher;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryRent;
import rs.ac.bg.fon.np_project.server.so.AbstractSOTest;

@ExtendWith(MockitoExtension.class)
class DeleteGameSOTest extends AbstractSOTest {

	@Mock
	RepositoryGame game = new RepositoryGame();
	
	@Mock
	RepositoryRent rent = new RepositoryRent();
	
	@Mock
	RepositoryPublisher publisher = new RepositoryPublisher();
	
	@InjectMocks
	DeleteGameSO deleteGameSO = new DeleteGameSO();
	
	Object param;
	
	@BeforeEach
	void setUp() throws Exception {
		param = new Object();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		game = null;
		rent = null;
		publisher = null;
		deleteGameSO = null;
		param = null;
	}
	
	@Test
	void testPreconditionNull() throws Exception {
		assertNotEquals(null,param,"Poslati objekat je neodgovarajuceg tipa!");
	}
	
	@Test
	void testPreconditionNotInstanceOfGame() {
		Object p = new Game();
		assertNotEquals(p,param,"Poslati objekat je neodgovarajuceg tipa!");
	}

	@Test
	void testPrecondition() throws Exception {
		Game g1 = new Game();
		g1.setGameid(1L);
		g1.setGameName("Monopol");
		g1.setNumberInStock(15);
		g1.setNumPlayers(20);
		g1.setGameCategory(GameCategory.Porodicne_igre);
		Publisher p = new Publisher(2L, "Mirko Markovic");
		g1.setPublisher(p);
		
		assertDoesNotThrow(()->{deleteGameSO.precondition(g1);});
		
	}

	@Test
	void testExecuteOperation() throws Exception {
		Game g = new Game();
		g.setGameid(1L);
		g.setGameName("Monopol");
		g.setNumberInStock(15);
		g.setNumPlayers(20);
		g.setGameCategory(GameCategory.Porodicne_igre);
		Publisher p = new Publisher(2L, "Mirko Markovic");
		g.setPublisher(p);
		
		Mockito.doNothing().when(rent).deleteGameRents(g);
		assertDoesNotThrow(()->{rent.deleteGameRents(g);});
		Mockito.doNothing().when(game).delete(g);
		assertDoesNotThrow(()->{game.delete(g);});
	}

	@Test
	void testDeleteGameSO() {		
		assertNotEquals(null, game);
		assertNotEquals(null, publisher);
		assertNotEquals(null, rent);
	}



}
