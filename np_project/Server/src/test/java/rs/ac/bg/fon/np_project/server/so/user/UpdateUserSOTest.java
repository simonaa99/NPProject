package rs.ac.bg.fon.np_project.server.so.user;

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

import rs.ac.bg.fon.np_project.commonlibrary.model.User;
import rs.ac.bg.fon.np_project.commonlibrary.model.UserCard;
import rs.ac.bg.fon.np_project.commonlibrary.model.UserCategory;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUser;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUserCard;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUserCategory;
import rs.ac.bg.fon.np_project.server.so.AbstractSOTest;
@ExtendWith(MockitoExtension.class)
class UpdateUserSOTest extends AbstractSOTest {

	@Mock
	RepositoryUser user = new RepositoryUser();
	
	@Mock
	RepositoryUserCard userCard = new RepositoryUserCard();
	
	@Mock
	RepositoryUserCategory userCategory = new RepositoryUserCategory();
	
	@InjectMocks
	UpdateUserSO updateUserSO = new UpdateUserSO();
	
	Object param;
	
	@BeforeEach
	void setUp() throws Exception {
		param = new Object();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		user = null;
		updateUserSO = null;
		param = null;
		userCard = null;
		userCategory = null;
	}
	
	@Test
	void testPreconditionNull() {
		assertNotEquals(null,param,"Poslati objekat je neodgovarajuceg tipa!");
	}

	@Test
	void testPreconditionSize0() {
		param = new Object();
		List<Object> p = new ArrayList<>();
		assertThrows(java.lang.Exception.class,()->updateUserSO.precondition(p) , "Poslati objekat je neodgovarajuceg tipa!");

	}
		
	@Test
	void testPreconditionSize1() {
		param = new Object();
		List<Object> p = new ArrayList<>();
		p.add(param);
		assertThrows(java.lang.Exception.class,()->updateUserSO.precondition(p) , "Poslati objekat je neodgovarajuceg tipa!");

	}
	
	@Test
	void testPreconditionElementsNull() {
		List<User> users = new LinkedList<>();
		
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
		
		User u2 = new User();
		u2.setUserId(5L);
		u2.setName("Mara");
		u2.setLastName("Portic");
		u2.setAddress("Ugrinovacka 55, 11000 Beograd");
		u2.setPhoneNumber("0625894569");
		
		UserCard u2Card = new UserCard();
		u2Card.setId(2L);
		u2Card.setCardNumber("3768632785257465");
		u2Card.setExpiryDate(LocalDate.of(2023, 1, 12));
		u2Card.setIssueDate(LocalDate.of(2022, 1, 12));
		u2.setUsercard(uCard);
	
		UserCategory u2C = new UserCategory();
		u2C.setUserCategoryId(2L);
		u2C.setName("Takmicar");
		u2C.setMembershipFeeDiscount(40.6);
		u2.setUserCategory(u2C);
		users.add(u);
		users.add(u2);
		assertDoesNotThrow(()-> updateUserSO.precondition(users));
		
		
	}

	@Test
	void testPreconditionInstanceOfUser() {
		Object p = new User();
		assertNotEquals(p,param,"Poslati objekat je neodgovarajuceg tipa!");
		
	}
     
	@Test
	void testPrecondition() {
		List<User> newUser = new LinkedList<>();
		newUser.add(new User());
		newUser.add(new User());
		newUser.add(new User());
		
		assertDoesNotThrow(()->updateUserSO.precondition(newUser));
	}


	@Test
	void testExecuteOperation() throws Exception {
		List<User> usersForUpdate = new LinkedList<>();
		
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
		
		User u2 = new User();
		u2.setUserId(5L);
		u2.setName("Mara");
		u2.setLastName("Portic");
		u2.setAddress("Ugrinovacka 55, 11000 Beograd");
		u2.setPhoneNumber("0625894569");
		
		UserCard u2Card = new UserCard();
		u2Card.setId(2L);
		u2Card.setCardNumber("147896321458");
		u2Card.setExpiryDate(LocalDate.of(2023, 1, 12));
		u2Card.setIssueDate(LocalDate.of(2022, 1, 12));
		u2.setUsercard(u2Card);
	
		UserCategory u2C = new UserCategory();
		u2C.setUserCategoryId(2L);
		u2C.setName("Takmicar");
		u2C.setMembershipFeeDiscount(40.6);
		u2.setUserCategory(u2C);
		usersForUpdate.add(u);
		usersForUpdate.add(u2);
		
        User oldUser = usersForUpdate.get(0);
        User newUser = usersForUpdate.get(1);
        String query = "SELECT * FROM kategorijaclanova WHERE naziv='" + newUser.getUserCategory().getName() + "'";
        Mockito.when(userCategory.getByQuery(query)).thenReturn(List.of(u2C));
        UserCategory uc = userCategory.getByQuery(query).get(0);
        newUser.setUserCategory(uc);
        Mockito.doNothing().when(user).edit(oldUser, newUser);
        user.edit(oldUser, newUser);
        if (!(oldUser.getUsercard().getCardNumber().equals(newUser.getUsercard().getCardNumber()))) {
        	Mockito.doNothing().when(userCard).updateCardNumber(oldUser.getUsercard(), newUser.getUsercard());
            userCard.updateCardNumber(oldUser.getUsercard(), newUser.getUsercard());
          }
            assertDoesNotThrow(()->updateUserSO.executeOperation(usersForUpdate));
	}

	@Test
	void testUpdateUserSO() {
		assertNotEquals(null, user);
		assertNotEquals(null, userCard);
		assertNotEquals(null, userCategory);
	}

}
