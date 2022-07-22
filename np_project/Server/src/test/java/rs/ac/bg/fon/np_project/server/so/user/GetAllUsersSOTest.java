package rs.ac.bg.fon.np_project.server.so.user;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

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
import rs.ac.bg.fon.np_project.server.so.AbstractSOTest;
@ExtendWith(MockitoExtension.class)
class GetAllUsersSOTest extends AbstractSOTest {

	@Mock
	RepositoryUser user = new RepositoryUser();
	
	@InjectMocks
	GetAllUsersSO getAllUsersSO = new GetAllUsersSO();
	
	Object param;
	
	@Test
	void testExecuteOperation() throws Exception {
            String query = "SELECT * FROM clan ORDER BY ime ASC";
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
    		List<User> list = new LinkedList<>();
    		list.add(u);
    		
    		Mockito.when(user.getByQuery(query)).thenReturn(List.of(u));
            assertEquals(list,user.getByQuery(query));
	}

	@Test
	void testGetAllUsersSO() {
		assertNotEquals(null, user);
	}

}
