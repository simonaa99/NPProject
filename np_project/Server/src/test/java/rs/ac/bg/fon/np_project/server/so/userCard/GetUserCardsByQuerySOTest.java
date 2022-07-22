package rs.ac.bg.fon.np_project.server.so.userCard;

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

import rs.ac.bg.fon.np_project.commonlibrary.model.UserCard;
import rs.ac.bg.fon.np_project.commonlibrary.model.Worker;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryGame;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryRent;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUserCard;
import rs.ac.bg.fon.np_project.server.so.AbstractSOTest;
import rs.ac.bg.fon.np_project.server.so.rent.RentGameSO;
@ExtendWith(MockitoExtension.class)
class GetUserCardsByQuerySOTest extends AbstractSOTest {

	@Mock
	RepositoryUserCard userCard = new RepositoryUserCard();
	
	
	@InjectMocks
	GetUserCardsByQuerySO getUserCardsByQuerySO = new GetUserCardsByQuerySO();
	
	Object param;
	
	@BeforeEach
	void setUp() throws Exception {
		param = new Object();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		userCard = null;
		getUserCardsByQuerySO = null;
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
		List<UserCard> cards = new ArrayList<>();
		String p = "";
        String query=p;
        UserCard userC = new UserCard();
        userC.setId(8L);
        userC.setCardNumber("6546566616646");
        userC.setExpiryDate(LocalDate.of(2023, 12, 12));
        userC.setIssueDate(LocalDate.of(2022, 12, 12));
        Mockito.when(userCard.getByQuery(query)).thenReturn(List.of(userC));
            cards = userCard.getByQuery(query);
        List<UserCard> list = new LinkedList<>();
        list.add(userC);
        assertEquals(list, getUserCardsByQuerySO.executeOperation(p));
        assertDoesNotThrow(()->getUserCardsByQuerySO.executeOperation(p));
        
	}

	@Test
	void testGetUserCardsByQuerySO() {
		assertNotEquals(null, userCard);
	}

}
