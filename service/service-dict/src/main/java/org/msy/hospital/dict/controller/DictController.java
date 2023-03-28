package org.msy.hospital.dict.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.msy.hospital.common.result.Result;
import org.msy.hospital.dict.service.DictService;
import org.msy.hospital.model.dict.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 11612
 * @date 2023/3/23
 */
@Api("数据字典接口")
@RestController
@RequestMapping("/admin/dict/dictionary")
public class DictController {
    @Autowired
    private DictService dictService;
    /**
     * 根据id查询子数据列表
     */
    @ApiOperation("根据数据id查询子数据列表")
    @GetMapping("findChildrenData/{id}")
    public Result findChildrenData(@PathVariable("id") String id){
        long l = Long.parseLong(id);
        List<Dict> childrenData =  dictService.findChildrenData(l);
        return Result.ok(childrenData);
    }
    /**
     * 导出数据字典
     */
    @GetMapping("exportData")
    public void exportData(HttpServletResponse response){
        dictService.exportExcel(response);
    }
    /**
     * 导入数据字典
     */
    @PostMapping("importData")
    public Result importData(MultipartFile file){
        dictService.importData(file);
        return Result.ok();
    }
    /**
     * 根据dictcode和value查询name
     */
    @GetMapping("getName/{dictCode}/{value}")
    public String getName(@PathVariable String dictCode,
                          @PathVariable String value) {
        return dictService.getDictName(dictCode,value);
    }
    /**
     * 根据value查询name
     */
    @GetMapping("getName/{value}")
    public String getName(@PathVariable String value) {
        return dictService.getDictName("",value);
    }

}
