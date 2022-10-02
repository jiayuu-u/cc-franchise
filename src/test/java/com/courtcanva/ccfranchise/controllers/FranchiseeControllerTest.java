package com.courtcanva.ccfranchise.controllers;

import com.alibaba.fastjson.JSON;
import com.courtcanva.ccfranchise.constants.AUState;
import com.courtcanva.ccfranchise.dtos.*;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListPostDto;
import com.courtcanva.ccfranchise.services.FranchiseeService;
import com.courtcanva.ccfranchise.utils.TestHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class FranchiseeControllerTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FranchiseeController franchiseeController;

    private Long mockFranchiseeId;

    private FranchiseeService service;

    @Autowired
    private WebApplicationContext wac;

    @BeforeEach
    void setUp() {
        mockFranchiseeId = franchiseeController.signUpFranchiseeAndStaff(new FranchiseeAndStaffPostDto(new FranchiseePostDto("CourtCanva", "CourtCanva LTD", "12312123111", "123456789", "Melbourne", AUState.VIC, 3000), new StaffPostDto("Taylor", "Swift", "taylor.s@gmail.com", "123456789", "abc st", 3000, AUState.VIC, "sdjkhsd"))).getFranchiseeGetDto().getFranchiseeId();
    }

    @Test
    void shouldReturnStaffAndFranchise() throws Exception {

        FranchiseeAndStaffPostDto franchiseeAndStaffPostDto = TestHelper.createFranchiseeAndStaffPostDto();

        mockMvc.perform(MockMvcRequestBuilders.post("/franchisee/signup")
                        .content(objectMapper.writeValueAsString(franchiseeAndStaffPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.staffGetDto.email").value("baoruoxi@163.com"))
                .andExpect(jsonPath("$.franchiseeGetDto.abn").value("12312123111"));

    }

    @Test
    void shouldReturnSelectSuburbs() throws Exception {

        SuburbListPostDto suburbListPostDto = TestHelper.createSuburbListPostDto();

        mockMvc.perform(MockMvcRequestBuilders.post("/franchisee/" + mockFranchiseeId.toString() + "/service_areas")
                        .content(objectMapper.writeValueAsString(suburbListPostDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(suburbListPostDto)))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.suburbs[*].sscCode").value(Lists.newArrayList(11344L)))
                .andExpect(jsonPath("$.suburbs[*].sscCode").value(Lists.newArrayList(12287L)));


    }
}