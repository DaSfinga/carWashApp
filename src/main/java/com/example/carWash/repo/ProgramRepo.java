package com.example.carWash.repo;

import com.example.carWash.model.Customer;
import com.example.carWash.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProgramRepo extends JpaRepository<Program, Long> {
    void deleteProgramById(Long id);

    Optional<Program> findProgramByName(String name);
    Optional<Program> findProgramById(Long id);
}
