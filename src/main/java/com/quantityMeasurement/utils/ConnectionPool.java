package com.quantityMeasurement.utils;

import com.quantityMeasurement.exception.DatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
	private static final Logger logger = LoggerFactory.getLogger(ConnectionPool.class);
	private static final List<Connection> connectionPool = new ArrayList<>();
	private static final List<Connection> usedConnections = new ArrayList<>();

	static {
		try {
			Class.forName(ApplicationConfig.getProperty("db.driver"));
			int initialSize = Integer.parseInt(ApplicationConfig.getProperty("pool.initialSize"));
			for (int i = 0; i < initialSize; i++) {
				connectionPool.add(createConnection());
			}
		} catch (Exception e) {
			logger.error("Failed to initialize connection pool", e);
		}
	}

	private static Connection createConnection() throws SQLException {
		return DriverManager.getConnection(ApplicationConfig.getProperty("db.url"),
				ApplicationConfig.getProperty("db.user"), ApplicationConfig.getProperty("db.password"));
	}

	public static synchronized Connection getConnection() {
		if (connectionPool.isEmpty())
			throw new DatabaseException("No available connections.");
		Connection connection = connectionPool.remove(connectionPool.size() - 1);
		usedConnections.add(connection);
		return connection;
	}

	public static synchronized void releaseConnection(Connection connection) {
		if (usedConnections.remove(connection)) {
			connectionPool.add(connection);
		}
	}

	public static String getStatistics() {
		return "Active: " + usedConnections.size() + ", Idle: " + connectionPool.size();
	}
}