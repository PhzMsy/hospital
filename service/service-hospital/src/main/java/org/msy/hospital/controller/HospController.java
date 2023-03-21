package org.msy.hospital.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiParam;
import org.msy.hospital.common.result.Result;
import org.msy.hospital.common.utils.MD5;
import org.msy.hospital.model.hosp.HospitalSet;
import org.msy.hospital.service.HospService;
import org.msy.hospital.vo.hosp.HospitalSetQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * @author 11612
 * @date 2023/3/20
 */
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospController {
    @Autowired
    private HospService hospServiceImpl;
    /*@GetMapping("/findAll")
    public List<HospitalSet> findAllHosptialSets() {
        List<HospitalSet> list = hospServiceImpl.list();
        return list;
    }
    @DeleteMapping("{id}")
    public boolean removeHospSet(@PathVariable long id) {
        return hospServiceImpl.removeById(id);
    }*/
    /**
     * 查询全部医院信息接口
     */
    @GetMapping("/findAll")
    public Result findAllHosptialSet() {
        return Result.ok(hospServiceImpl.list());
    }
    /**
     * 删除接口
     */
    @DeleteMapping("{id}")
    public Result removeHospSet(@PathVariable Long id) {
        boolean flag = hospServiceImpl.removeById(id);
        if(flag) {
            return Result.ok();
        }else{
            return Result.fail();
        }
    }
    @PostMapping("findPageHospSet/{current}/{limit}")
    public Result findPageHospSet(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false) HospitalSetQueryVo vo) {
        Page<HospitalSet> page = new Page<>(current,limit);
        String hoscode = vo.getHoscode();
        String hosname = vo.getHosname();
        QueryWrapper wrapper = new QueryWrapper();
        if(StringUtils.hasLength(hoscode)){
            wrapper.eq("hoscode",hoscode);
        }
        if(StringUtils.hasLength(hosname)){
            wrapper.like("hosname",hosname);
        }
        Page resultPage = hospServiceImpl.page(page, wrapper);
        return Result.ok(resultPage);
    }
    @PostMapping("saveHospitalSet")
    public Result saveHospitalSet(@RequestBody HospitalSet hospitalSet){
        // 设置状态 1-> 可用, 0->不可用
        hospitalSet.setStatus(1);
        // 设置密钥,需与医院对接ID生成规则
        Random random = new Random();
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis()+""+ random.nextInt(1000)));
        //调用service,执行插入
        boolean save = hospServiceImpl.save(hospitalSet);
        if (save){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }
    /**
     * 根据ID获取医院信息
     */
    @GetMapping("getHospSet/{id}")
    public Result getHospSet(@PathVariable Long id) {
        HospitalSet hospitalSet = hospServiceImpl.getById(id);
        return Result.ok(hospitalSet);
    }
    /**
     * 更新医院设置接口
     */
    @PutMapping("updateHospSet")
    public Result updateHospSet(@RequestBody HospitalSet hospitalSet){
        boolean flag = hospServiceImpl.updateById(hospitalSet);
        if(flag) {
            return Result.ok();
        }else{
            return Result.fail();
        }
    }
    /**
     * 批量删除医院设置接口
     */
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<String> idList) {
        boolean flag = hospServiceImpl.removeByIds(idList);
        return Result.ok();
    }
    /**
     * 医院设置锁定与解锁接口
     */
    @PutMapping("lock/{id}/{status}")
    public Result lock(@ApiParam(name = "id",value = "医院设置id",readOnly = true)
                       @PathVariable("id") String id, @ApiParam(name = "status",value = "锁定状态(0:锁定 1:解锁)",required = true)
                       @PathVariable("status") Integer status
                       ){
        HospitalSet byId = hospServiceImpl.getById(id);
        byId.setStatus(status);
        boolean b = hospServiceImpl.updateById(byId);
        if (b){
            return Result.ok();
        }else {
            return Result.ok();
        }
    }
    /**
     * 预留接口: 短信密钥发送
     */
    @GetMapping("sendkey/{id}")
    public Result sendSingKey(@PathVariable long id){
        HospitalSet byId = hospServiceImpl.getById(id);
        String hoscode = byId.getHoscode();
        String signKey = byId.getSignKey();
        return Result.ok();
    }



}
