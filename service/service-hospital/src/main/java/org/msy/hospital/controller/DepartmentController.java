package org.msy.hospital.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.msy.hospital.common.result.Result;
import org.msy.hospital.service.DepartmentService;
import org.msy.hospital.vo.hosp.DepartmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 11612
 * @date 2023/3/29
 */
@Api("科室处理")
@RestController
@RequestMapping("/admin/hosp/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentServiceImpl;
    @ApiOperation("查询科室树")
    @GetMapping("getDeptTree/{hosCode}")
    public Result getDeptTree(@PathVariable String hosCode){
        List<DepartmentVo> deptList = departmentServiceImpl.getDeptTree(hosCode);
        return Result.ok(deptList);
    }
}
