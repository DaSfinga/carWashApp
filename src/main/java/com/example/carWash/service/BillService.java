package com.example.carWash.service;

import com.example.carWash.DTO.BillDTO;
import com.example.carWash.exception.NotFoundException;
import com.example.carWash.model.Bill;
import com.example.carWash.model.Customer;
import com.example.carWash.model.Program;
import com.example.carWash.repo.BillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BillService {
    private final BillRepo billRepo;
    private final CustomerService customerService;
    private final ProgramService programService;

    @Autowired
    public BillService(BillRepo billRepo, CustomerService customerService, ProgramService programService) {
        this.billRepo = billRepo;
        this.customerService = customerService;
        this.programService = programService;
    }

    public Bill addBill(BillDTO billAdd){
        Bill bill = new Bill();
        Customer customer = customerService.findCustomerById(billAdd.getCustomerId());
        Program program = programService.findProgramById((billAdd.getProgramId()));
        bill.setCustomer(customer);
        bill.setProgram(program);
        customer.setNumberOfRuns(customer.getNumberOfRuns()+1);
        if(customer.getNumberOfRuns()%10 == 0)
            bill.setPrice(program.getPrice() * 0.9);
        else
            bill.setPrice(program.getPrice());

        bill.setBillDate(LocalDateTime.now());


        Bill newBill = billRepo.save(bill);

        customerService.addBillToCustomer(newBill);
        programService.addBillToProgram(newBill);

        return newBill;
    }

    public List<Bill> findAllBills(){
        return billRepo.findAll();
    }

    /*public List<Bill> findBillsByCustomer(Long id){
        return billRepo.findBillsByCustomerId(id);
    }*/

    public Bill findBillById(Long id){
        return billRepo.findBillById(id)
                .orElseThrow(() -> new NotFoundException("Bill by id "+ id + " was not found"));
    }

}
