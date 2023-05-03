package com.ritvik.mysql.services;

import com.ritvik.mysql.entity.EmployeeEntity;
import com.ritvik.mysql.repositories.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    public EmployeeEntity addEmployee(@RequestBody EmployeeEntity employee){
        return employeeRepository.save(employee);
    }
    public Optional<EmployeeEntity> deleteEmployeeById(Long id){
        Optional<EmployeeEntity> data = employeeRepository.findById(id);
        employeeRepository.deleteById(id);
        return data;
    }
    public Optional<EmployeeEntity> findEmployeeById(Long id){
        Optional<EmployeeEntity> data = employeeRepository.findById(id);
        return data;
    }
    public Optional<EmployeeEntity> updateEmployeeById(Long id, @RequestBody EmployeeEntity employee){
        Optional<EmployeeEntity> data = employeeRepository.findById(id);
        try {
            EmployeeEntity employee1 = data.get();
            employee1.setName(employee.getName());
            employee1.setDept(employee.getDept());
            employee1.setEmail(employee.getEmail());
            employeeRepository.save(employee1);
        }catch (NoSuchElementException e){
            System.out.println(e.getLocalizedMessage());
        }
        return data;
    }
    public List<EmployeeEntity> getAll(){
        return employeeRepository.findAll();
    }
}
