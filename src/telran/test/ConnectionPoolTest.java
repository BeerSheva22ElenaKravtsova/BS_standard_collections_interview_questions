package telran.test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedHashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.ConnectionPoolAppl;
import telran.util.ConnectionPoolAppl.Connection;

class ConnectionPoolTest {
	static ConnectionPoolAppl connectionPool;

	@BeforeEach
	void setUp() throws Exception {
		connectionPool = new ConnectionPoolAppl(3);
		assertTrue(connectionPool.addConnection(new Connection(0, "IP Adress 0", 123)));
		assertFalse(connectionPool.addConnection(new Connection(0, "IP Adress 1", 124)));
		assertTrue(connectionPool.addConnection(new Connection(1, "IP Adress 0", 123)));
		assertTrue(connectionPool.addConnection(new Connection(2, "IP Adress 1", 124)));
		assertTrue(connectionPool.addConnection(new Connection(3, "IP Adress 0", 123)));
	}

	@Test
	void testGetConnection() {
		Connection connection = new Connection(1, "IP Adress 0", 123);
		
		assertEquals(connection, connectionPool.getConnection(1));
		assertNull(connectionPool.getConnection(0));
		assertNull(connectionPool.getConnection(4));
	}
}
