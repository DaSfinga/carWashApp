package com.example.carWash.service;

import com.example.carWash.DTO.CustomerDTO;
import com.example.carWash.exception.NotFoundException;
import com.example.carWash.model.Bill;
import com.example.carWash.model.Customer;
import com.example.carWash.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;

    @Autowired
    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public Customer addCustomer(CustomerDTO customerAdd){
        Customer customer = new Customer();
        customer.setFirstName((customerAdd.getFirstName()));
        customer.setLastName(customerAdd.getLastName());
        customer.setNumberOfRuns(0);
        List<Bill> billList = new ArrayList<>();
        customer.setBillList(billList);
        Customer newCustomer = customerRepo.save(customer);

        return newCustomer;
    }

    public List<Customer> findAllCustomers(){
        return customerRepo.findAll();
    }

    public void addBillToCustomer(Bill bill){
        Customer customer = findCustomerById(bill.getCustomerId());
        List<Bill> billList = customer.getBillList();
        billList.add(bill);
        customer.setBillList(billList);
        customerRepo.save(customer);
    }

    public Customer findCustomerById(Long id){
        return customerRepo.findCustomerById(id)
                .orElseThrow(() -> new NotFoundException("User by id "+ id + " was not found"));
    }

    public Customer updateCustomer(Customer customer){
        return customerRepo.save(customer);
    }

    @Transactional
    public void deleteCustomer(Long id){
        customerRepo.deleteCustomerById(id);
    }
}
