package rs.ac.bg.fon.np_project.server.so.user;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
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
import rs.ac.bg.fon.np_project.commonlibrary.model.Worker;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryGame;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryRent;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUser;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUserCard;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUserCategory;
import rs.ac.bg.fon.np_project.server.so.AbstractSOTest;
import rs.ac.bg.fon.np_project.server.so.rent.RentGameSO;
@ExtendWith(MockitoExtension.class)
class AddUserSOTest extends AbstractSOTest {

	@Mock
	RepositoryUser user = new RepositoryUser();
	
	@Mock
	RepositoryUserCard userCard = new RepositoryUserCard();
	
	@Mock
	RepositoryUserCategory userCategory = new RepositoryUserCategory();
	
	@InjectMocks
	AddUserSO addUserSO = new AddUserSO();
	
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
		userCategory = null;
		addUserSO = null;
	}
	
	@Test
	void testPreconditionNull() throws Exception {
		assertNotEquals(null,param,"Poslati objekat je neodgovarajuceg tipa!");
	}
	
	@Test
	void testPreconditionNotInstanceOfUser() {
		Object p = new User();
		assertNotEquals(p,param,"Poslati objekat je neodgovarajuceg tipa!");
	}

	@Test
	void testPrecondition() throws Exception {
		User u1 = new User();
		assertDoesNotThrow(()->{addUserSO.precondition(u1);});
		
	}

	@Test
	void testExecuteOperation() throws Exception {
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
		
		userCard.add(u.getUsercard());
		String query="SELECT * FROM clanskakarta WHERE brojClanskeKarte= '"+u.getUsercard().getCardNumber()+"'";
		Mockito.when(userCard.getByQuery(query)).thenReturn(List.of(uCard));
		UserCard card=userCard.getByQuery(query).get(0);
		u.setUsercard(card); 
		
		query = "SELECT * FROM kategorijaclanova WHERE naziv='" + u.getUserCategory().getName() + "'";
		Mockito.when(userCategory.getByQuery(query)).thenReturn(List.of(uC));
		UserCategory category=userCategory.getByQuery(query).get(0);
		u.setUserCategory(category);
        user.add(u);
        
        assertDoesNotThrow(()->(addUserSO.executeOperation(u)));
		
		

	}

	@Test
	void testAddUserSO() {
		assertNotEquals(null, user);
		assertNotEquals(null, userCard);
		assertNotEquals(null, userCategory);
	}

}
