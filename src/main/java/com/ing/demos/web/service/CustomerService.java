package com.ing.demos.web.service;

import com.ing.demos.persistence.model.CustomerEntity;
import com.ing.demos.persistence.model.OrderEntity;
import com.ing.demos.persistence.repository.CustomerRepository;
import com.ing.demos.web.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerEntity> findAllCustomers() {
        return (List<CustomerEntity>) customerRepository.findAll();
    }

    public CustomerEntity findCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<OrderEntity> findAllOrdersForCustomer(Long id) {
        return this.findCustomerById(id).getOrders();
    }

    public CustomerEntity saveCustomer(CustomerEntity customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

}
