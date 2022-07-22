package rs.ac.bg.fon.np_project.server.so.user;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import rs.ac.bg.fon.np_project.commonlibrary.model.Game;
import rs.ac.bg.fon.np_project.commonlibrary.model.User;
import rs.ac.bg.fon.np_project.commonlibrary.model.UserCard;
import rs.ac.bg.fon.np_project.commonlibrary.model.UserCategory;
import rs.ac.bg.fon.np_project.commonlibrary.model.Worker;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUser;
import rs.ac.bg.fon.np_project.server.so.AbstractSOTest;

class GetUsersByQuerySOTest extends AbstractSOTest {

	@Mock
	RepositoryUser user = new RepositoryUser();
	
	@InjectMocks
	GetUsersByQuerySO getUsersByQuerySO = new GetUsersByQuerySO();
	
	Object param;
	
	@BeforeEach
	void setUp() throws Exception {
		param = new Object();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		user = null;
		getUsersByQuerySO = null;
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
		 List<User> users;
		 String p = new String();
	     String query=p;
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
	     Mockito.when(user.getByQuery(query)).thenReturn(List.of(u));
	     List<User> list = new LinkedList<>();
 		list.add(u);
	     users = user.getByQuery(query);
	     assertEquals(list, users);
	     assertDoesNotThrow(()->getUsersByQuerySO.executeOperation(p));
	}

	@Test
	void testGetUsersByQuerySO() {
		assertNotEquals(null, user);
	}

}
