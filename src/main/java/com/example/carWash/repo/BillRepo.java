package com.example.carWash.repo;

import com.example.carWash.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BillRepo extends JpaRepository<Bill, Long> {

    //List<Bill> findBillsByCustomerId(Long id);
    Optional<Bill> findBillById(Long id);
}
