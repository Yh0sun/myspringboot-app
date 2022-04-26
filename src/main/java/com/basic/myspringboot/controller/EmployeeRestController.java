package com.basic.myspringboot.controller;

import com.basic.myspringboot.entity.Employee;
import com.basic.myspringboot.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/employees")
@RequiredArgsConstructor
public class EmployeeRestController {
    @Autowired
    private EmployeeService employeeService;

// 생성자 주입 방식
//    public EmployeeRestController(EmployeeService employeeService){
//        this.employeeService=employeeService;
//    }
    // get all employees
    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.selectAllEmployee();
    }

    // create employee rest api
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.insertEmployee(employee);
    }

    // get employee by id rest api
    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employeeService.selectEmployee(id);
    }

    // update employee rest api
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
        return employeeService.updateEmployee(id, employeeDetails);
    }

    // delete employee rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        return employeeService.deleteEmployee(id);
    }
}
