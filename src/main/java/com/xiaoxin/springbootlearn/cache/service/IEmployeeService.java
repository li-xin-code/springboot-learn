package com.xiaoxin.springbootlearn.cache.service;

import com.xiaoxin.springbootlearn.cache.entity.Employee;
/**
 * @author lx
 * @date 2020/12/18
 */
public interface IEmployeeService {
    /**
     * 根据编号查询员工
     * @param id 编号
     * @return 员工
     */
    Employee getEmpById(Integer id);

    /**
     * 根据lastname查询员工
     * @param lastName lastname
     * @return 员工
     */
    Employee getEmpByLastName(String lastName);

    /**
     * 更新员工
     * @param employee 员工
     * @return 员工
     */
    Employee updateEmp(Employee employee);

    /**
     * 根据编号删除员工
     * @param id 编号
     */
    void deleteEmpById(String id);

    /**
     * 新增员工
     * @param employee 员工
     */
    void insertEmp(Employee employee);
}
