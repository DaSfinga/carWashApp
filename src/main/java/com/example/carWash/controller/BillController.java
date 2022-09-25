package com.example.carWash.controller;

import com.example.carWash.DTO.BillDTO;
import com.example.carWash.DTO.ProgramDTO;
import com.example.carWash.model.Bill;
import com.example.carWash.model.Program;
import com.example.carWash.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {
    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }


    @PostMapping("/addBill")
    public ResponseEntity<?> addBill(@RequestBody BillDTO bill) {
        Bill newBill = billService.addBill(bill);
        return new ResponseEntity<>(newBill, HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Bill>> getBills () {
        List<Bill> bills = billService.findAllBills();
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Bill> getBillById (@PathVariable("id") Long id) {
        Bill bill = billService.findBillById(id);
        return new ResponseEntity<>(bill, HttpStatus.OK);
    }
}
