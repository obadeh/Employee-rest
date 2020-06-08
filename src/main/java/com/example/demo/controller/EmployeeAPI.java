package com.example.demo.controller;

import com.example.demo.dao.employeeDao;
import com.example.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeAPI {

    @Autowired
    private employeeDao dao;

    @PostMapping(path = "employee")
    public Employee addEmployee(@RequestBody Employee employee){
        dao.save(employee);
        return employee;
    }

    @GetMapping(path = "employee")
    public List<Employee>  findByfName(@RequestParam(required = false,value = "name") String name,@RequestParam(required = false,value = "dep") String dep){

        if (name == null && dep != null){
           return dao.findByDepartment(dep);
        }
        if (dep == null && name != null){
            return dao.findByFirstName(name);
        }
        if (name != null && dep != null){
            return dao.findByFirstNameAndDepartment(name,dep);
        }
        else {
            return dao.findAll();
        }
    }

    @GetMapping(path = "employee/{id}")
    public Optional<Employee>  find(@PathVariable("id") int id ){
        return dao.findById(id);
    }

    @DeleteMapping(path = "employee/{id}")
    public String delete(@PathVariable("id") int id ){
         dao.deleteById(id);
         return "item deleted";

    }
    @PutMapping(path = "employee/{id}")
    public Employee update(@RequestBody Employee employee,@PathVariable int id){
        employee.setId(id);
        dao.save(employee);
        return employee;
    }


}
