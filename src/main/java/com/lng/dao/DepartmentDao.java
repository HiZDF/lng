package com.lng.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.lng.pojo.Department;

public interface DepartmentDao  extends JpaRepository<Department, Object>,JpaSpecificationExecutor<Department>{

}
