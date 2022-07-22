package rs.ac.bg.fon.np_project.server.so.user;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

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
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUser;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUserCard;
import rs.ac.bg.fon.np_project.server.so.AbstractSOTest;
import rs.ac.bg.fon.np_project.server.so.game.AddGameSO;
@ExtendWith(MockitoExtension.class)
class DeleteUserSOTest extends AbstractSOTest {

	@Mock
	RepositoryUser user = new RepositoryUser();
	
	@Mock
	RepositoryUserCard userCard = new RepositoryUserCard();
	
	@InjectMocks
	DeleteUserSO deleteUserSO = new DeleteUserSO();
	
	Object param;
	
	@BeforeEach
	void setUp() throws Exception {
		param = new Object();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		user = null;
		userCard = null;
		param = null;
		deleteUserSO = null;
	}
	
	@Test
	void testPreconditionNull() throws Exception {
		assertNotEquals(null,param,"Poslati objekat je neodgovarajuceg tipa!");
	}
	
	@Test
	void testPreconditionNotInstanceOfUser() {
		param = new User();
		assertTrue(param instanceof User, "Poslati objekat je neodgovarajuceg tipa!");
	}

	@Test
	void testPrecondition() throws Exception {
		User u = new User();
		u.setUserId(1L);
		u.setName("Janko");
		u.setLastName("Lovic");
		u.setAddress("Ugrinovacka 14, 11000 Beograd");
		u.setPhoneNumber("0689745698");
		
		UserCard uCard = new UserCard();
		uCard.setId(8L);
		uCard.setCardNumber("6546566616646");
		uCard.setExpiryDate(LocalDate.of(2023, 12, 12));
		uCard.setIssueDate(LocalDate.of(2022, 12, 12));
		u.setUsercard(uCard);
	
		UserCategory uC = new UserCategory();
		uC.setUserCategoryId(5L);
		uC.setName("Sampion");
		uC.setMembershipFeeDiscount(60.6);
		u.setUserCategory(uC);
		
		assertDoesNotThrow(()->{deleteUserSO.precondition(u);});
		
	}

	@Test
	void testExecuteOperation() throws Exception {
		param = new User();
		User u = (User) param;
		Mockito.doNothing().when(user).delete(u);
		user.delete(u);
		Mockito.doNothing().when(userCard).delete(u.getUsercard());
		userCard.delete(u.getUsercard());
		assertDoesNotThrow(()->{deleteUserSO.executeOperation(param);});
	}

	@Test
	void testDeleteUserSO() {
		assertNotEquals(null, user);
		assertNotEquals(null, userCard);
	}

}
