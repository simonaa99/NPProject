package rs.ac.bg.fon.np_project.server.so;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Execute;

import rs.ac.bg.fon.np_project.server.db.DbConnectionFactory;
import rs.ac.bg.fon.np_project.server.so.game.AddGameSO;

@ExtendWith(MockitoExtension.class)
public abstract class AbstractSOTest {

	@Mock
	DbConnectionFactory db;
	
	public AbstractSO abstractSO;
	
	Object param;
	
	
	@Test
	void testComitTransaction() {
		assertThrows(java.lang.Exception.class,()->{db.getInstance().getConnection().commit();});
		
	}

	@Test
	void testRollbackTransaction() {
		assertThrows(java.lang.Exception.class,()->{db.getInstance().getConnection().rollback();});
	}

}
