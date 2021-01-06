package com.xiaoxin.springbootlearn.cache.service.impl;

import com.xiaoxin.springbootlearn.cache.entity.Employee;
import com.xiaoxin.springbootlearn.cache.mapper.EmployeeMapper;
import com.xiaoxin.springbootlearn.cache.service.IEmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

/**
 * @author lx
 * @date 2020/12/18
 */
@Service
@CacheConfig(cacheNames = {"emp"})
@Slf4j
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    @Cacheable(keyGenerator = "empKeyGenerator",
            condition = "#id > 0 and #a0 <100",
            unless = "#root.args[0] == 3")
    public Employee getEmpById(Integer id) {
        log.info("get:" + id);
        return employeeMapper.getEmpById(id.toString());
    }

    @Override
    @CachePut(key = "#employee.id")
    public Employee updateEmp(Employee employee) {
        log.info("update" + employee);
        employeeMapper.updateEmp(employee);
        return employee;
    }

    @Override
    @CacheEvict(value = "emp", key = "#id")
    public void deleteEmpById(String id) {
        log.info("delete: " + id);
//        employeeMapper.deleteEmpById(id);
    }

    /**
     * 因为加了 @CachePut 所以该方法必定会执行；@CachePut默认需要先执行方法再更新缓存
     * @param lastName lastname
     * @return Employee
     */
    @Override
    @Caching(
            cacheable = {
                    @Cacheable(key = "#lastName")
            },
            put = {
                    @CachePut(key = "#result.id"),
                    @CachePut(key = "#result.email")
            }
    )
    public Employee getEmpByLastName(String lastName) {

        return employeeMapper.getEmpByLastName(lastName);
    }

    @Override
    public void insertEmp(Employee employee) {

    }
}
