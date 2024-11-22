package com.akhm.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akhm.dto.CustomerRequestDTO;
import com.akhm.dto.CustomerResponseDTO;
import com.akhm.entity.Customer;
import com.akhm.repository.CustomerRepository;
import com.akhm.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ModelMapper modelMapper;

	public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
		this.customerRepository = customerRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public CustomerResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO) {
		Customer customer = modelMapper.map(customerRequestDTO, Customer.class);
		Customer savedCustomer = customerRepository.save(customer);
		return modelMapper.map(savedCustomer, CustomerResponseDTO.class);
	}

	@Override
	public CustomerResponseDTO getCustomerById(Long customerId) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer not found"));
		return modelMapper.map(customer, CustomerResponseDTO.class);
	}

	@Override
	public List<CustomerResponseDTO> getAllCustomers() {
		List<Customer> customers = customerRepository.findAll();
		return customers.stream().map(customer -> modelMapper.map(customer, CustomerResponseDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public CustomerResponseDTO updateCustomer(Long customerId, CustomerRequestDTO customerRequestDTO) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer not found"));

		modelMapper.map(customerRequestDTO, customer); // Update fields
		Customer updatedCustomer = customerRepository.save(customer);
		return modelMapper.map(updatedCustomer, CustomerResponseDTO.class);
	}

	@Override
	public void deleteCustomer(Long customerId) {
		if (!customerRepository.existsById(customerId)) {
			throw new RuntimeException("Customer not found");
		}
		customerRepository.deleteById(customerId);
	}
}
