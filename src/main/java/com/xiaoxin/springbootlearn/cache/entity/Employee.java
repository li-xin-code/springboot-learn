package com.xiaoxin.springbootlearn.cache.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lx
 * @date 2020/12/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee{
    private String  id;
    private String lastName;
    private String email;
    private Integer gender;
    private String dId;
}
