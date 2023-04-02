package com.sample.microservice.awsdynamodb.service;

import com.sample.microservice.awsdynamodb.domain.ApplicantTable;
import com.sample.microservice.awsdynamodb.exception.DataNotFoundException;
import com.sample.microservice.awsdynamodb.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ApplicantTable create(ApplicantTable employee) {
        return repository.save(employee);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ApplicantTable update(ApplicantTable employee, String id) {

        Optional<ApplicantTable> employeeOptional = repository.findById(id);
        if (employeeOptional.isPresent()) {
            employeeOptional.get().setFirstname(employee.getFirstname());
            employeeOptional.get().setLastname(employee.getLastname());


            return repository.save(employeeOptional.get());
        }
        throw new DataNotFoundException("Employee Id not found");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ApplicantTable> getById(String id) {
        return repository.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ApplicantTable> getAll() {
        return (List<ApplicantTable>) repository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}