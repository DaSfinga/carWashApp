package com.example.carWash.service;

import com.example.carWash.DTO.ProgramDTO;
import com.example.carWash.exception.NotFoundException;
import com.example.carWash.model.Bill;
import com.example.carWash.model.Customer;
import com.example.carWash.model.Program;
import com.example.carWash.repo.ProgramRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProgramService {
    private final ProgramRepo programRepo;

    @Autowired
    public ProgramService(ProgramRepo programRepo) {
        this.programRepo = programRepo;
    }


    public Program addProgram(ProgramDTO programAdd){
        Program program = new Program();
        program.setName((programAdd.getName()));
        program.setPrice(programAdd.getPrice());
        List<Bill> billList = new ArrayList<>();
        program.setBillList(billList);
        Program newProgram = programRepo.save(program);

        return newProgram;
    }

    public void addBillToProgram(Bill bill){
        Program program = findProgramById(bill.getProgramId());
        List<Bill> billList = program.getBillList();
        billList.add(bill);
        program.setBillList(billList);
        programRepo.save(program);
    }

    public List<Program> findAllPrograms(){
        return programRepo.findAll();
    }

    public Program findProgramById(Long id){
        return programRepo.findProgramById(id)
                .orElseThrow(() -> new NotFoundException("Program by id "+ id + " was not found"));
    }

    public Program findProgramByName(String name){
        Program program = programRepo.findProgramByName(name)
                .orElseThrow(() -> new NotFoundException("Program by name "+ name + " was not found"));
        return program;
    }

    public Program updateProgram(Program program){
        return programRepo.save(program);
    }

    @Transactional
    public void deleteProgram(Long id){
        programRepo.deleteProgramById(id);
    }
}
