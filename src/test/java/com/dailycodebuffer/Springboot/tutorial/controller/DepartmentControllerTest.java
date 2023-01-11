package com.dailycodebuffer.Springboot.tutorial.controller;

import com.dailycodebuffer.Springboot.tutorial.entity.Department;
import com.dailycodebuffer.Springboot.tutorial.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private Department department;


    @BeforeEach
    void setUp() {
        department = Department.builder()
                .departmentName("Computer Science")
                .departmentCode("CSE - 32")
                .departmentAddress("Delhi")
                .departmentId(1L)
                .build();
    }

    @Test
    void saveDepartment() throws Exception {
        Department  inputDepartment = Department.builder()
                .departmentName("Computer Science")
                .departmentCode("CSE - 32")
                .departmentAddress("Delhi")
                .build();
        Mockito.when(departmentService.saveDepartment(inputDepartment)).thenReturn(department);

        mockMvc.perform(post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"departmentName\":\"Computer Science\",\n" +
                        "\t\"departmentAddress\":\"Delhi\",\n" +
                        "\t\"departmentCode\":\"CSE - 32\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }
//we did static import to make code cleaner
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; - for post() method
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*; - for status() method
    @Test
    void fetchDepartmentById() throws Exception {
        Mockito.when(departmentService.fetchDepartmentById(1L)).thenReturn(department);
        //we passed 1 because we are having only 1 as id in department object
        mockMvc.perform(get("/departments/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName").value(department.getDepartmentName()));
    }
}