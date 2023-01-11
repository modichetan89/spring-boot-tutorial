package com.dailycodebuffer.Springboot.tutorial.service;

import com.dailycodebuffer.Springboot.tutorial.entity.Department;
import com.dailycodebuffer.Springboot.tutorial.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentName("EEE")
                .departmentAddress("Pune")
                .departmentCode("EEE-06")
                .departmentId(1L)
                .build();

        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("EEE"))
                .thenReturn(department);
        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("CS"))
                .thenReturn(null);
    }

    @Test
    @DisplayName("Get data based on valid department name")
    @Disabled
    public void whenValidDepartmentName_thenDepartmentShouldFound(){
        String departmentName = "EEE";
        Department found = departmentService.fetchDepartmentByName(departmentName);
        assertEquals(departmentName, found.getDepartmentName());
    }

    @Test
    @DisplayName("Return null on invalid department name")
    public void whenInvalidDepartmentName_thenDepartmentShouldNotFound(){
        String departmentName = "CS";
        Department found = departmentService.fetchDepartmentByName(departmentName);
        assertNull(found);
    }

}