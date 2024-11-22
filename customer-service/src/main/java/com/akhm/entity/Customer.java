package com.akhm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;

	private String name;
	private String email;
	private String phone;

	// @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
//	private List<Order> orders = new ArrayList<>();

	// @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	// private List<Notification> notifications = new ArrayList<>();

}
