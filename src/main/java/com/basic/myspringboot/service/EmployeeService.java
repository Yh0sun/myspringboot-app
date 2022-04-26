package com.basic.myspringboot.service;

import com.basic.myspringboot.entity.Employee;
import com.basic.myspringboot.expcection.ResourceNotFoundException;
import com.basic.myspringboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee insertEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    @Transactional(readOnly = true)
    public List<Employee> selectAllEmployee(){
        return employeeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Employee selectEmployee(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        Employee existEmployee = optionalEmployee.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        return existEmployee;
    }

    public Employee updateEmployee(Long id, Employee employeeDetail){
        Employee existEmployee=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee", "id", id));
        existEmployee.setFirstName(employeeDetail.getFirstName());
        existEmployee.setLastName(employeeDetail.getLastName());
        existEmployee.setEmail(employeeDetail.getEmail());
        return existEmployee;
    }

    public ResponseEntity<?> deleteEmployee(Long id){
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if(!optionalEmployee.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id+" Employee Not Found");
        }
        Employee existEmployee = optionalEmployee.get();
        employeeRepository.delete(existEmployee);
        return ResponseEntity.ok("Employee Delete OK");

    }
}
