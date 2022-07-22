package rs.ac.bg.fon.np_project.server.so.rent;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
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
import rs.ac.bg.fon.np_project.commonlibrary.model.User;
import rs.ac.bg.fon.np_project.commonlibrary.model.UserCard;
import rs.ac.bg.fon.np_project.commonlibrary.model.UserCategory;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryGame;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryRent;
import rs.ac.bg.fon.np_project.server.so.AbstractSOTest;
import rs.ac.bg.fon.np_project.server.so.game.GetGamesByQuerySO;

@ExtendWith(MockitoExtension.class)
class RentGameSOTest extends AbstractSOTest {

	@Mock
	RepositoryRent rent = new RepositoryRent();
	
	@Mock
	 RepositoryGame game = new RepositoryGame();
	
	@InjectMocks
	RentGameSO rentGameSO = new RentGameSO();
	
	Object param;
	
	@BeforeEach
	void setUp() throws Exception {
		param = new Object();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		rent = null;
		game = null;
		param = null;
		rentGameSO = null;
	}
	
	@Test
	void testPreconditionNull() throws Exception {
		assertNotEquals(null,param,"Poslati objekat je neodgovarajuceg tipa!");
	}
	
	@Test
	void testPreconditionSize0() {
		param = new Object();
		List<Object> p = new ArrayList<>();
		assertThrows(java.lang.Exception.class,()->rentGameSO.precondition(p) , "Poslati objekat je neodgovarajuceg tipa!");

	}
	
	@Test
	void testPreconditionSize1() {
		param = new Object();
		List<Object> p = new ArrayList<>();
		p.add(param);
		assertThrows(java.lang.Exception.class,()->rentGameSO.precondition(p) , "Poslati objekat je neodgovarajuceg tipa!");

	}
	
	@Test
	void testPreconditionNotInstanceOfUser() {
		List<User> u = new ArrayList<>();
		User user = new User();
		u.add(user);
		assertTrue((u.get(0) instanceof User), "Poslati objekat je neodgovarajuceg tipa!");
	}
	
	@Test
	void testPreconditionNotInstanceOfGame() {
		List<Game> p = new ArrayList<Game>();
		Game g1 = new Game();
		p.add(g1);
		Game g2 = new Game();
		p.add(g2);
		assertTrue((p.get(1) instanceof Game), "Poslati objekat je neodgovarajuceg tipa!");
	}
	
	
	@Test
	void testExecuteOperation() throws Exception {
		List<Object> p = new LinkedList<>();
		
		User user1 = new User();
		user1.setUserId(1L);
		user1.setName("Janko");
		user1.setLastName("Lovic");
		user1.setAddress("Ugrinovacka 14, 11000 Beograd");
		user1.setPhoneNumber("0689745698");
		
		UserCard uCard = new UserCard();
		uCard.setId(8L);
		uCard.setCardNumber("6546566616646");
		uCard.setExpiryDate(LocalDate.of(2023, 12, 12));
		uCard.setIssueDate(LocalDate.of(2022, 12, 12));
		user1.setUsercard(uCard);
	
		UserCategory uC = new UserCategory();
		uC.setUserCategoryId(5L);
		uC.setName("Sampion");
		uC.setMembershipFeeDiscount(60.6);
		user1.setUserCategory(uC);
		
		Game g1 = new Game();
		g1.setGameid(1L);
		g1.setGameName("Monopol");
		g1.setNumberInStock(15);
		g1.setNumPlayers(20);
		g1.setGameCategory(GameCategory.Porodicne_igre);
		Publisher pub = new Publisher(2L, "Mirko Markovic");
		g1.setPublisher(pub);
		
		p.add(user1);
		p.add(g1);
		User u = (User) p.get(0);
		Game g = (Game) p .get(1);
		Mockito.doNothing().when(rent).rentGame(u, g);
		
		assertDoesNotThrow(()->{rentGameSO.executeOperation(p);});
	}

	@Test
	void testRentGameSO() {
		assertNotEquals(null, rent);
		assertNotEquals(null, game);
	}

}
