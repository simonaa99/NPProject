package rs.ac.bg.fon.np_project.server.so.game;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

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
import rs.ac.bg.fon.np_project.server.so.AbstractSOTest;

@ExtendWith(MockitoExtension.class)
class AddGameSOTest extends AbstractSOTest {

	@Mock
	RepositoryGame game = new RepositoryGame();
	
	@Mock
	RepositoryPublisher publisher = new RepositoryPublisher();
	
	@InjectMocks
	AddGameSO addGameSO = new AddGameSO();
	
	Object param;
	
	@BeforeEach
	void setUp() throws Exception {
		param = new Object();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		game = null;
		publisher = null;
		param = null;
		addGameSO = null;
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
		assertDoesNotThrow(()->{addGameSO.precondition(g1);});
		
	}
	
	@Test
	void testExecuteOperationNijePraznaLista() throws Exception, IOException {
		Game g = new Game();
		g.setGameid(1L);
		g.setGameName("Monopol");
		g.setNumberInStock(15);
		g.setNumPlayers(20);
		g.setGameCategory(GameCategory.Porodicne_igre);
		Publisher p = new Publisher(2L, "Mirko Markovic");
		g.setPublisher(p);
		
        game.connect();
        Publisher p1 = new Publisher(5L, "Nikoto");
        Publisher p2 = new Publisher(10L, "Lolo");
        Mockito.when(publisher.getByQuery("SELECT * FROM izdavac WHERE imePrezime='" + g.getPublisher().getPublisherName() + "'")).thenReturn(List.of(p1,p2));
        List<Publisher> dbPublisher = publisher.getByQuery("SELECT * FROM izdavac WHERE imePrezime='" + g.getPublisher().getPublisherName() + "'");
            if (dbPublisher.size() == 0) {
            	Mockito.doNothing().when(publisher).add(g.getPublisher());
                publisher.add(g.getPublisher());
                
            }
            Publisher pub=publisher.getByQuery("SELECT * FROM izdavac WHERE imePrezime='" + g.getPublisher().getPublisherName() + "'").get(0);
            g.setPublisher(pub);
            Mockito.doNothing().when(game).add(g);
            game.add(g);
            
	}

	
	@Test
	void testExecuteOperationListSize0() throws Exception, IOException {
		Game g = new Game();
		g.setGameid(1L);
		g.setGameName("Monopol");
		g.setNumberInStock(15);
		g.setNumPlayers(20);
		g.setGameCategory(GameCategory.Porodicne_igre);
		Publisher p = new Publisher(2L, "Mirko Markovic");
		g.setPublisher(p);
		
        game.connect();
        Publisher p1 = new Publisher(5L, "Nikoto");
        Publisher p2 = new Publisher(10L, "Lolo");
        Publisher publ = new Publisher();
        Mockito.when(publisher.getByQuery("SELECT * FROM izdavac WHERE imePrezime='" + g.getPublisher().getPublisherName() + "'")).thenReturn(List.of());
        List<Publisher> dbPublisher = publisher.getByQuery("SELECT * FROM izdavac WHERE imePrezime='" + g.getPublisher().getPublisherName() + "'");
            if (dbPublisher.size() == 0) {
            	Mockito.doNothing().when(publisher).add(g.getPublisher());
                publisher.add(g.getPublisher());
                publ = g.getPublisher();
                
            }
            g.setPublisher(publ);
            Mockito.doNothing().when(game).add(g);
            game.add(g);
	}

	@Test
	void testAddGameSO() {
		assertNotEquals(null, game);
		assertNotEquals(null, publisher);

	}

	@Test
	void testExistsPostoji() throws Exception {
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
		
		Game g3 = new Game();
		g3.setGameid(2L);
		g3.setGameName("Uno");
		g3.setNumberInStock(5);
		g3.setNumPlayers(30);
		g3.setGameCategory(GameCategory.Porodicne_igre);
		Publisher publ = new Publisher(2L, "Mirko Markovic");
		g3.setPublisher(publ);
		
		Mockito.when(game.getAll()).thenReturn(List.of(g1,g2));
		List<Game> list = game.getAll();
		boolean result = false;
		for(Game igra:list) {
			if(igra.getGameName().equals(g3.getGameName()) && igra.getPublisher().getPublisherId().equals(g3.getPublisher().getPublisherId())) {
				result = true;
			}
		}
		
		assertEquals(true, result);

	}

	@Test
	void testExistsNePostoji() throws Exception {
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
		
		Game g3 = new Game();
		g3.setGameid(5L);
		g3.setGameName("Kluedo");
		g3.setNumberInStock(5);
		g3.setNumPlayers(30);
		g3.setGameCategory(GameCategory.Porodicne_igre);
		Publisher publ = new Publisher(8L, "Nuja Inc");
		g3.setPublisher(publ);
		
		Mockito.when(game.getAll()).thenReturn(List.of(g1,g2));
		List<Game> list = game.getAll();
		boolean result = false;
		for(Game igra:list) {
			if(igra.getGameName().equals(g3.getGameName()) && igra.getPublisher().getPublisherId().equals(g3.getPublisher().getPublisherId())) {
				result = true;
			}
		}
		
		assertEquals(false, result);

	}


}

