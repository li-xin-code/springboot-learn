package com.xiaoxin.springbootlearn;

import com.xiaoxin.springbootlearn.cache.entity.Employee;
import com.xiaoxin.springbootlearn.cache.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author lx
 * @date 2020/12/18
 */
@SpringBootTest
public class EmployeeMapperTest {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    public void getEmpById(){
        System.out.println(employeeMapper.getEmpById("1"));
    }

    @Test
    public void updateEmp(){}

    @Test
    public void deleteEmpById(){}

    @Test
    public void insertEmp(){
        Employee employee = new Employee("1", "张三", "zhangsan@lx.com", 1, "1");
        employeeMapper.insertEmp(employee);
    }

}
