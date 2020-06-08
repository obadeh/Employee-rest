package com.example.demo.dao;

import com.example.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface employeeDao extends JpaRepository<Employee,Integer>{

    List<Employee> findByFirstName(String name);
    List<Employee> findByDepartment(String dep);
    List<Employee> findByFirstNameAndDepartment(String name, String dep);


}
