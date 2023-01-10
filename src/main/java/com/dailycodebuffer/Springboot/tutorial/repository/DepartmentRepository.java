package com.dailycodebuffer.Springboot.tutorial.repository;

import com.dailycodebuffer.Springboot.tutorial.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    public Department findByDepartmentName(String departmentName);


    public Department findByDepartmentNameIgnoreCase(String departmentName);

//    @Query(value = "select * from tablename where firstName like %?1", nativeQuery = true)
//    public List<User> findByFirstnameEndsWith(String firstname);
}
//comments added
//test