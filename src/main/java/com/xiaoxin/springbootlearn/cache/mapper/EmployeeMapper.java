package com.xiaoxin.springbootlearn.cache.mapper;

import com.xiaoxin.springbootlearn.cache.entity.Employee;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author lx
 * @date 2020/12/18
 */
@Repository
@Mapper
public interface EmployeeMapper {

    /**
     * 根据编号查询员工
     * @param id 编号
     * @return 员工
     */
    @Select("select * from employee where id = #{id}")
    Employee getEmpById(String id);

    /**
     * 更新员工
     * @param employee 员工
     */
    @Update("update employee " +
            "set lastName=#{lastName},email=#{email},gender=#{gender},dId=#{dId} " +
            "where id=#{id}")
    void updateEmp(Employee employee);

    /**
     * 根据编号删除员工
     * @param id 编号
     */
    @Delete("delete from employee where id = #{id}")
    void deleteEmpById(String id);

    /**
     * 新增员工
     * @param employee 员工
     */
    @Insert("insert into employee(lastName,email,gender,dId) " +
            "value(#{lastName},#{email},#{gender},#{dId})")
    void insertEmp(Employee employee);

    /**
     * 根据lastName查询员工
     * @param lastName lastName
     * @return 员工
     */
    @Select("select * from employee where lastName = #{lastName} limit 1")
    Employee getEmpByLastName(String lastName);
}
