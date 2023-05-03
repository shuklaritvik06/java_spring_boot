package com.ritvik.mysql.controllers;

import com.ritvik.mysql.entity.EmployeeEntity;
import com.ritvik.mysql.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<EmployeeEntity>> getEmployee(@PathVariable Long id){
        return new ResponseEntity<>(employeeService.findEmployeeById(id), HttpStatus.OK);
    }
    @PostMapping("/insert")
    public ResponseEntity<EmployeeEntity> addEmployee(@RequestBody EmployeeEntity employee){
        return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Optional<EmployeeEntity>> updateEmployee(@PathVariable Long id, @RequestBody EmployeeEntity employee){
        return new ResponseEntity<>(employeeService.updateEmployeeById(id,employee), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Optional<EmployeeEntity>> deleteEmployee(@PathVariable Long id){
        return new ResponseEntity<>(employeeService.deleteEmployeeById(id), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<EmployeeEntity>> getAll(){
        return new ResponseEntity<>(employeeService.getAll(), HttpStatus.OK);
    }
}
