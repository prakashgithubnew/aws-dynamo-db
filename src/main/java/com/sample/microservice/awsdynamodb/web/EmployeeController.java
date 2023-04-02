package com.sample.microservice.awsdynamodb.web;


import com.sample.microservice.awsdynamodb.domain.ApplicantTable;
import com.sample.microservice.awsdynamodb.exception.DataNotFoundException;
import com.sample.microservice.awsdynamodb.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping()
    public List<ApplicantTable> all() {
        return employeeService.getAll();

    }

    @PostMapping()
    public ApplicantTable save(@RequestBody ApplicantTable employee) {
        return employeeService.create(employee);
    }

    @PutMapping(value = "/{id}")
    public ApplicantTable update(@PathVariable("id") String id, @RequestBody ApplicantTable employee) {
        return employeeService.update(employee,id);
    }

    @GetMapping("/{id}")
    public ApplicantTable getEmployeeById(@PathVariable(value = "id") String id) {
        return employeeService.getById(id).orElseThrow(() -> new DataNotFoundException("not found"));
    }
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable(value = "id") String id) {
        employeeService.delete(id);
    }

}
