package com.ing.demos.web.service;

import com.ing.demos.persistence.model.CustomerEntity;
import com.ing.demos.persistence.model.OrderEntity;
import com.ing.demos.persistence.repository.CustomerRepository;
import com.ing.demos.web.exception.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerService = new CustomerService(customerRepository);
    }

    @Test
    void whenGetCustomerById_thenNotFound() {
        CustomerEntity customer = CustomerEntity.builder().id(1L).build();

        assertThrows(EntityNotFoundException.class, () -> customerService.findCustomerById(customer.getId()));
        verify(customerRepository, times(1)).findById(any());
    }

    @Test
    void whenGetOrdersForCreatedCustomer_thenDeleteCustomer() {
        List<OrderEntity> orders = Arrays.asList(OrderEntity.builder().id(1L).customer(any()).build());

        CustomerEntity customer = CustomerEntity.builder().id(1L).orders(orders).build();
        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));

        customerService.saveCustomer(customer);
        verify(customerRepository, times(1)).save(any());

        List<OrderEntity> ordersForCustomer = customerService.findAllOrdersForCustomer(customer.getId());
        assertNotNull(ordersForCustomer);
        assertEquals(1, ordersForCustomer.size());
        assertEquals(1L, ordersForCustomer.get(0).getId());

        customerService.deleteCustomer(customer.getId());
        verify(customerRepository, times(1)).deleteById(any());
    }
}
