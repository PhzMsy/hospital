package org.msy.hospital.controller;

import org.msy.hospital.common.result.Result;
import org.msy.hospital.model.hosp.Hospital;
import org.msy.hospital.service.HospService;
import org.msy.hospital.vo.hosp.HospitalQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 11612
 * @date 2023/3/28
 */
@RestController
@RequestMapping("/admin/hosp/hospital")
public class HospitalController {
    @Autowired
    private HospService hospServiceImpl;
    @GetMapping("list/{page}/{limit}")
    public Result listHosp(@PathVariable Integer page,
                           @PathVariable Integer limit,
                           HospitalQueryVo vo){
        Page<Hospital> hospitals = hospServiceImpl.selectHospPage(page, limit, vo);
        return Result.ok(hospitals);
    }

}
