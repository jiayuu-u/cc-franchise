package com.courtcanva.ccfranchise.controllers;

import com.courtcanva.ccfranchise.dtos.*;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListGetDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListPostDto;
import com.courtcanva.ccfranchise.services.FranchiseeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/franchisee")
@RequiredArgsConstructor
public class FranchiseeController {

    private final FranchiseeService franchiseeService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public FranchiseeAndStaffDto signUpFranchiseeAndStaff(@RequestBody @Valid FranchiseeAndStaffPostDto franchiseeAndStaffPostDto) {

        return franchiseeService.createFranchiseeAndStaff(franchiseeAndStaffPostDto.getFranchiseePostDto(),
                franchiseeAndStaffPostDto.getStaffPostDto());

    }

    @PostMapping("/{franchiseeId}/service_areas")
    @ResponseStatus(HttpStatus.CREATED)
    public SuburbListGetDto addDutyAreas(@RequestBody @Valid SuburbListPostDto suburbListPostDto, @PathVariable(value = "franchiseeId") Long franchiseeId ){
        return franchiseeService.addDutyAreas(suburbListPostDto,franchiseeId);
    }

}
