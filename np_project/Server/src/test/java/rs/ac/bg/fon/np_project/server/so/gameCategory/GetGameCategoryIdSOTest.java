package rs.ac.bg.fon.np_project.server.so.gameCategory;

import static org.junit.jupiter.api.Assertions.*;

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
import rs.ac.bg.fon.np_project.commonlibrary.model.Worker;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryGame;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryGameCategory;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryPublisher;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryRent;
import rs.ac.bg.fon.np_project.server.so.AbstractSOTest;
import rs.ac.bg.fon.np_project.server.so.game.DeleteGameSO;

@ExtendWith(MockitoExtension.class)
class GetGameCategoryIdSOTest extends AbstractSOTest {

	@Mock
	RepositoryGameCategory gameC=new RepositoryGameCategory();;

	@InjectMocks
	GetGameCategoryIdSO getGameCategoryIdSO = new GetGameCategoryIdSO();
	
	Object param;
	
	@BeforeEach
	void setUp() throws Exception {
		param = new Object();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		gameC = null;
		getGameCategoryIdSO = null;
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
		Long p1 = 1L;
		String name = "Monopol";
		
		Mockito.when(gameC.getGameCategoryId(name)).thenReturn(p1);
		Long p2 = gameC.getGameCategoryId(name);
		assertDoesNotThrow(()->getGameCategoryIdSO.executeOperation(name));
		
	}

	@Test
	void testGetGameCategoryIdSO() {
		assertNotEquals(null, gameC);

	}

}
