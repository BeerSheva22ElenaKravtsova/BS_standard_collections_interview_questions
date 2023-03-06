package telran.util;

import java.util.HashMap;
import telran.structure.ConnectionPool;

public class ConnectionPoolAppl implements ConnectionPool {
	protected HashMap<Integer, Node> connectionPool = new HashMap<>();
	protected int maxSize;

	public ConnectionPoolAppl(int maxSize) {
		this.maxSize = maxSize;
	}

	public static class Connection {
		int id;
		String ipAdress;
		int port;

		public Connection(int id, String ipAddress, int port) {
			this.id = id;
			this.ipAdress = ipAddress;
			this.port = port;
		}

		public int getId() {
			return id;
		}

		public String getIpAddress() {
			return ipAdress;
		}

		public int getPort() {
			return port;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (!(obj instanceof Connection)) {
				return false;
			}
			Connection other = (Connection) obj;
			if (id != other.id) {
				return false;
			}
			return true;
		}
	}
	
	private static class Node {
		Connection obj;
		Node prev;
		Node next;

		Node(Connection obj) {
			this.obj = obj;
		}
	}

	private Node head;
	private Node tail;

	@Override
	public boolean addConnection(Connection connection) {
		Node node = new Node(connection);
		Integer id = connection != null ? connection.id : null;
		if (connectionPool.isEmpty()) {
			connectionPool.put(id, node);
			head = tail = node;
			return true;
		} else if (!connectionPool.containsKey(id)) {
			if (connectionPool.size() == maxSize) {
				Integer tailId = tail.obj.id;
				tail = tail.prev;
				connectionPool.remove(tailId);
			}
			connectionPool.put(id, node);
			head.prev = node;
			node.next = head;
			head = node;
			return true;
		}
		return false;
	}

	@Override
	public Connection getConnection(int id) {
		Connection res = null;
		if (!connectionPool.isEmpty() && connectionPool.containsKey(id)) {
			Connection connection = connectionPool.get(id).obj;
			connectionPool.remove(id);
			addConnection(connection);
			res = connection;
		} else {
			addConnection(null);
		}
		return res;
	}


}
