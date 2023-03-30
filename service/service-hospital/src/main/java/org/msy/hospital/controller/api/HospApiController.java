package org.msy.hospital.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.msy.hospital.common.result.Result;
import org.msy.hospital.model.hosp.Hospital;
import org.msy.hospital.service.DepartmentService;
import org.msy.hospital.service.HospitalService;
import org.msy.hospital.vo.hosp.DepartmentVo;
import org.msy.hospital.vo.hosp.HospitalQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 11612
 * @date 2023/3/30
 */
@Api(value = "用户系统-首页-医院相关接口")
@RestController
@RequestMapping("/api/hosp/hospital")
public class HospApiController {
    @Autowired
    private HospitalService hospitalServiceImpl;
    @Autowired
    private DepartmentService departmentServiceImpl;

    @ApiOperation(value = "查询医院列表")
    @GetMapping("findHospList/{page}/{limit}")
    public Result findHospital(@PathVariable Integer page, @PathVariable Integer limit, HospitalQueryVo hospitalQueryVo){
        Page<Hospital> hospitals = hospitalServiceImpl.selectHospPage(page, limit, hospitalQueryVo);
        return Result.ok(hospitals);
    }
    @ApiOperation(value = "根据医院名称模糊查询")
    @GetMapping("findByHosName/{hosname}")
    public Result findByHosName(@PathVariable String hosname) {
        List<Hospital> hospList = hospitalServiceImpl.findByHosname(hosname);
        return Result.ok(hospList);
    }
    @ApiOperation(value = "获取科室列表")
    @GetMapping("department/{hoscode}")
    public Result index(@PathVariable String hoscode){
        List<DepartmentVo> deptList = departmentServiceImpl.getDeptTree(hoscode);
        return Result.ok(deptList);
    }
    @ApiOperation(value = "医院预约挂号详情")
    @GetMapping("{hoscode}")
    public Result item(@PathVariable String hoscode) {
        return Result.ok(hospitalServiceImpl.item(hoscode));
    }



}
