package org.msy.hospital.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.msy.hospital.common.result.Result;
import org.msy.hospital.model.hosp.Hospital;
import org.msy.hospital.service.HospService;
import org.msy.hospital.service.HospitalService;
import org.msy.hospital.vo.hosp.HospitalQueryVo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 11612
 * @date 2023/3/28
 */
@RestController
@RequestMapping("/admin/hosp/hospital")
public class HospitalController {
    @Autowired
    private HospitalService hospitalServiceImpl;
    @GetMapping("list/{page}/{limit}")
    public Result listHosp(@PathVariable Integer page,
                           @PathVariable Integer limit,
                           HospitalQueryVo vo){
        Page<Hospital> hospitals = hospitalServiceImpl.selectHospPage(page, limit, vo);
        return Result.ok(hospitals);
    }
    @ApiOperation("更新医院上线状态 ")
    @GetMapping("updateHospStatus/{id}/{status}")
    public Result updateHospStatus( @PathVariable String id, @PathVariable Integer status){
        hospitalServiceImpl.updateHospStatus(id, status);
        return Result.ok();
    }
    @ApiOperation(value = "医院详情信息")
    @GetMapping("showHospDetail/{id}")
    public Result showHospDetail(@PathVariable String id){
        Map<String,Object> map = hospitalServiceImpl.getHospById(id);
        return Result.ok(map);
    }

}
