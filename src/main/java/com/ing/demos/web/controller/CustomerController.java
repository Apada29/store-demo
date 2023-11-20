package com.ing.demos.web.controller;

import com.ing.demos.persistence.model.CustomerEntity;
import com.ing.demos.persistence.model.OrderEntity;
import com.ing.demos.web.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(produces = {APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<CustomerEntity> getAllCustomers() {
        return customerService.findAllCustomers();
    }

    @GetMapping(value = "/{customerId}", produces = {APPLICATION_JSON_VALUE})
    @ResponseBody
    public CustomerEntity getCustomerById(@PathVariable final Long customerId) {
        return customerService.findCustomerById(customerId);
    }

    @GetMapping(value = "/{customerId}/orders", produces = {APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<OrderEntity> getOrdersForCustomer(@PathVariable final Long customerId) {
        return customerService.findAllOrdersForCustomer(customerId);
    }

    @PostMapping(produces = {APPLICATION_JSON_VALUE}, consumes = {APPLICATION_JSON_VALUE})
    @ResponseBody
    public CustomerEntity saveCustomer(@RequestBody CustomerEntity customer) {
        return customerService.saveCustomer(customer);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public void deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
    }

}