package org.msy.hospital.controller;

import io.swagger.annotations.Api;
import org.msy.hospital.common.result.Result;
import org.msy.hospital.model.hosp.HospitalSet;
import org.msy.hospital.model.test.Test;
import org.msy.hospital.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 11612
 * @date 2023/3/25
 */
@Api("测试")
@RestController
@CrossOrigin
@RequestMapping("/admin/test")
public class TestController {
    @Autowired
    private TestService testServiceImpl;
    /**
     * 查询人物
     */
    @GetMapping("queryAll")
    public Result findTestAll() {
        return Result.ok(testServiceImpl.list());
    }
    /**
     * 根据id查询
     */
    @DeleteMapping("{id}")
    public Result removeHospSet(@PathVariable Long id) {
        boolean flag = testServiceImpl.removeById(id);
        if(flag) {
            return Result.ok();
        }else{
            return Result.fail();
        }
    }
    /**
     * 更新数据
     */
    @PutMapping("updateTest")
    public Result updateHospSet(@RequestBody Test test){
        boolean flag = testServiceImpl.updateById(test);
        if(flag) {
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    /**
     * 插入数据
     * @param test
     * @return
     */
    @PostMapping("insertTest")
    public Result insert(@RequestBody Test test){
        //调用service,执行插入
        boolean save = testServiceImpl.save(test);
        if (save){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }
    @GetMapping("queryById/{id}")
    public Result getHospSet(@PathVariable Long id) {
        Test test = testServiceImpl.getById(id);
        return Result.ok(test);
    }
}
