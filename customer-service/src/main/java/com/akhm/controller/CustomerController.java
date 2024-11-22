package com.akhm.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akhm.dto.CustomerRequestDTO;
import com.akhm.dto.CustomerResponseDTO;
import com.akhm.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@PostMapping
	public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) {
		CustomerResponseDTO response = customerService.createCustomer(customerRequestDTO);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable Long id) {
		CustomerResponseDTO response = customerService.getCustomerById(id);
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {
		List<CustomerResponseDTO> response = customerService.getAllCustomers();
		return ResponseEntity.ok(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CustomerResponseDTO> updateCustomer(@PathVariable Long id,
			@RequestBody CustomerRequestDTO customerRequestDTO) {
		CustomerResponseDTO response = customerService.updateCustomer(id, customerRequestDTO);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomer(id);
		return ResponseEntity.noContent().build();
	}
}
