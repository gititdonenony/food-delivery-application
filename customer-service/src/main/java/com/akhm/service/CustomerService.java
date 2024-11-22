package com.akhm.service;

import java.util.List;

import com.akhm.dto.CustomerRequestDTO;
import com.akhm.dto.CustomerResponseDTO;

public interface CustomerService {
	CustomerResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO);

	CustomerResponseDTO getCustomerById(Long customerId);

	List<CustomerResponseDTO> getAllCustomers();

	CustomerResponseDTO updateCustomer(Long customerId, CustomerRequestDTO customerRequestDTO);

	void deleteCustomer(Long customerId);
}
