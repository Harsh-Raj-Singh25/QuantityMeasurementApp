CREATE TABLE IF NOT EXISTS quantity_measurement_entity (
    id INT AUTO_INCREMENT PRIMARY KEY,
    operand_1 VARCHAR(50) NOT NULL,
    operand_2 VARCHAR(50),
    operation_type VARCHAR(20) NOT NULL,
    measurement_type VARCHAR(20) NOT NULL,
    result VARCHAR(100),
    has_error BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_measurement_type ON quantity_measurement_entity(measurement_type);