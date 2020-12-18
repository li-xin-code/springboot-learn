drop database if exists springbootlearn ;
create database springbootlearn;
use springbootlearn;

drop table if exists department;
create table department (
    id int auto_increment primary key,
    departmentName varchar(20)
) default charset utf8 ;

drop table if exists employee;
create table employee (
    id int auto_increment primary key,
    lastName varchar(50),
    email varchar(50),
    gender varchar(20),
    dId varchar(50)
) default charset utf8 ;