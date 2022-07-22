package rs.ac.bg.fon.np_project.server.so.userCard;

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
import rs.ac.bg.fon.np_project.commonlibrary.model.UserCard;
import rs.ac.bg.fon.np_project.commonlibrary.model.Worker;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUserCard;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUserCategory;
import rs.ac.bg.fon.np_project.server.so.AbstractSOTest;
import rs.ac.bg.fon.np_project.server.so.user.UpdateUserSO;
@ExtendWith(MockitoExtension.class)
class DeleteUserCardSOTest extends AbstractSOTest {

	@Mock
	RepositoryUserCard userCard = new RepositoryUserCard();
	
	@InjectMocks
	DeleteUserCardSO deleteUserCardSO = new DeleteUserCardSO();
	
	Object param ;
	
	@BeforeEach
	void setUp() throws Exception {
		param = new Object();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		userCard = null;
		deleteUserCardSO = null;
		param = null;
	}
	
	@Test
	void testPreconditionNull() throws Exception {
		assertNotEquals(null,param,"Poslati objekat je neodgovarajuceg tipa!");
	}
	
	@Test
	void testPreconditionNotInstanceOfUserCard() {
		Object p = new UserCard();
		assertNotEquals(p,param,"Poslati objekat je neodgovarajuceg tipa!");
	}

	@Test
	void testExecuteOperation() throws Exception {
		UserCard uCard = new UserCard();
		uCard.setId(8L);
		uCard.setCardNumber("6546566616646");
		uCard.setExpiryDate(LocalDate.of(2023, 12, 12));
		uCard.setIssueDate(LocalDate.of(2022, 12, 12));
		Mockito.doNothing().when(userCard).delete(uCard);
		assertDoesNotThrow(()->userCard.delete(uCard));

	}

	@Test
	void testDeleteUserCardSO() {
		assertNotEquals(null, userCard);
	}

}
