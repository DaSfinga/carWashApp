package com.example.carWash.controller;

import com.example.carWash.DTO.CustomerDTO;
import com.example.carWash.DTO.ProgramDTO;
import com.example.carWash.model.Customer;
import com.example.carWash.model.Program;
import com.example.carWash.service.CustomerService;
import com.example.carWash.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/program")
public class ProgramController {
    private final ProgramService programService;

    @Autowired
    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @PostMapping("/addProgram")
    public ResponseEntity<?> addProgram(@RequestBody ProgramDTO program) {
        Program newProgram = programService.addProgram(program);
        return new ResponseEntity<>(newProgram, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Program>> getPrograms () {
        List<Program> programs = programService.findAllPrograms();
        return new ResponseEntity<>(programs, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Program> getProgramById (@PathVariable("id") Long id) {
        Program program = programService.findProgramById(id);
        return new ResponseEntity<>(program, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Program> updateProgram(@RequestBody Program program) {
        Program updateProgram = programService.updateProgram(program);
        return new ResponseEntity<>(updateProgram, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProgram(@PathVariable("id") Long id) {
        programService.deleteProgram(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
