package com.quantityMeasurement.repository;

import com.quantityMeasurement.entities.QuantityMeasurementEntity;
import com.quantityMeasurement.exception.DatabaseException;
import com.quantityMeasurement.utils.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuantityMeasurementDatabaseRepository implements IQuantityMeasurementRepository {
	private static final Logger logger = LoggerFactory.getLogger(QuantityMeasurementDatabaseRepository.class);

	@Override
	public void save(QuantityMeasurementEntity entity) {
		String query = "INSERT INTO quantity_measurement_entity (operand_1, operand_2, operation_type, measurement_type, result, has_error) VALUES (?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnection();
			try (PreparedStatement pstmt = conn.prepareStatement(query)) {
				pstmt.setString(1, entity.operand1);
				pstmt.setString(2, entity.operand2);
				pstmt.setString(3, entity.operation);
				pstmt.setString(4, entity.measurementType);
				pstmt.setString(5, entity.result);
				pstmt.setBoolean(6, entity.hasError);
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			throw new DatabaseException("Failed to persist entity.", e);
		} finally {
			if (conn != null)
				ConnectionPool.releaseConnection(conn);
		}
	}

	@Override
	public long getTotalCount() {
		String query = "SELECT COUNT(*) FROM quantity_measurement_entity";
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnection();
			try (PreparedStatement pstmt = conn.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {
				if (rs.next())
					return rs.getLong(1);
			}
		} catch (SQLException e) {
			throw new DatabaseException("Failed to get count.", e);
		} finally {
			if (conn != null)
				ConnectionPool.releaseConnection(conn);
		}
		return 0;
	}

	@Override
	public void deleteAll() {
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnection();
			try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM quantity_measurement_entity")) {
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			throw new DatabaseException("Failed to delete entities.", e);
		} finally {
			if (conn != null)
				ConnectionPool.releaseConnection(conn);
		}
	}

	@Override
	public String getPoolStatistics() {
		return ConnectionPool.getStatistics();
	}

	@Override
	public void closeResources() {
		logger.info("Database resources closed.");
	}
}