package rs.ac.bg.fon.np_project.server.so.userCategory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUserCard;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUserCategory;
import rs.ac.bg.fon.np_project.server.so.AbstractSOTest;
import rs.ac.bg.fon.np_project.server.so.userCard.GetUserCardsByQuerySO;
@ExtendWith(MockitoExtension.class)
class GetAllUserCategoriesSOTest extends AbstractSOTest {

	@Mock
	RepositoryUserCategory userCategory = new RepositoryUserCategory();
	
	@InjectMocks
	GetAllUserCategoriesSO getAllUserCategoriesSO = new GetAllUserCategoriesSO();
	
	Object param;
	
	@BeforeEach
	void setUp() throws Exception {
		param = new Object();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		userCategory = null;
		getAllUserCategoriesSO = null;
		param = null;
	}

	@Test
	void testExecuteOperation() {
		assertDoesNotThrow(()->getAllUserCategoriesSO.executeOperation(param));
	}

	@Test
	void testGetAllUserCategoriesSO() {
		assertNotEquals(null, userCategory);
	}

}
