package com.xiaoxin.springbootlearn.cache.controller;

import com.xiaoxin.springbootlearn.cache.annotation.CurrentLimiting;
import com.xiaoxin.springbootlearn.cache.entity.Employee;
import com.xiaoxin.springbootlearn.cache.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lx
 * @date 2020/12/18
 */
@RestController
@RequestMapping("/emp")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/get/{id}")
    @CurrentLimiting(maxCount = 3)
    public Employee get(@PathVariable("id") Integer id) {
        return employeeService.getEmpById(id);
    }

    @PostMapping("/update")
    public Employee update(@RequestBody Employee employee) {
        return employeeService.updateEmp(employee);
    }

    @GetMapping("/del/{id}")
    public String del(@PathVariable String id) {
        employeeService.deleteEmpById(id);
        return "success";
    }

    @GetMapping("/get/lastname/{lastname}")
    public Employee getByLastName(@PathVariable("lastname") String lastname) {
        return employeeService.getEmpByLastName(lastname);
    }
}
