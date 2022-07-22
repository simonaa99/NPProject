package rs.ac.bg.fon.np_project.server.so.game;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
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
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryGame;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryPublisher;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryRent;
import rs.ac.bg.fon.np_project.server.so.AbstractSOTest;

@ExtendWith(MockitoExtension.class)
class GetAllGamesSOTest extends AbstractSOTest {

	@Mock
	RepositoryGame game = new RepositoryGame();
	
	@InjectMocks
	GetAllGamesSO getAllGamesSO = new GetAllGamesSO();
	
	Object param;
	
	@BeforeEach
	void setUp() throws Exception {
		param = new Object();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		game = null;
		getAllGamesSO = null;
		param = null;
	}

	@Test
	void testExecuteOperation() throws Exception, IOException {
		List<Game> list;
        String query = "SELECT * FROM igra ORDER BY naziv ASC";
        
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
        
        Mockito.doNothing().when(game).connect();
        game.connect();
        Mockito.when(game.getByQuery(query)).thenReturn(List.of(g1,g2));
        list = game.getByQuery(query);
        
        assertDoesNotThrow(()->getAllGamesSO.executeOperation(list));

	}

	@Test
	void testGetAllGamesSO() {
		assertNotEquals(null, game);
	}

}
