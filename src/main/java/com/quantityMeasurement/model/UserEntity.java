package com.quantityMeasurement.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String email;

	private String name;
	private String provider; // e.g., "google"
	private String role; // e.g., "ROLE_USER"

	public UserEntity(String email, String name, String provider, String role) {
		this.email = email;
		this.name = name;
		this.provider = provider;
		this.role = role;
	}
}