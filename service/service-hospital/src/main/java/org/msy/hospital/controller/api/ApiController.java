package org.msy.hospital.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.msy.hospital.common.exception.YyghException;
import org.msy.hospital.common.result.Result;
import org.msy.hospital.common.result.ResultCodeEnum;
import org.msy.hospital.common.utils.HttpRequestHelper;
import org.msy.hospital.common.utils.MD5;
import org.msy.hospital.controller.BaseController;
import org.msy.hospital.model.hosp.Department;
import org.msy.hospital.model.hosp.Hospital;
import org.msy.hospital.model.hosp.Schedule;
import org.msy.hospital.service.DepartmentService;
import org.msy.hospital.service.HospService;
import org.msy.hospital.service.HospitalService;
import org.msy.hospital.service.ScheduleService;
import org.msy.hospital.vo.hosp.DepartmentVo;
import org.msy.hospital.vo.hosp.ScheduleQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author 11612
 * @date 2023/3/27
 */
@Api(tags = "医院管理API接口")
@RestController
@RequestMapping("/api/hosp")
public class ApiController extends BaseController {
    @Autowired
    private HospitalService hospitalServiceImpl;
    @Autowired
    private DepartmentService departmentServiceImpl;
    @Autowired
    private ScheduleService scheduleServiceImpl;
    @ApiOperation(value = "上传医院")
    @PostMapping("saveHospital")
    public Result saveHospital(HttpServletRequest request) {
        Map<String, Object> paramMap = getParamMap(request);
        checkSignKey(paramMap, hospitalServiceImpl);
//传输过程中“+”转换为了“ ”，因此我们要转换回来
        String logoDataString = (String) paramMap.get("logoData");
        if (StringUtils.hasLength(logoDataString)) {
            String logoData = logoDataString.replaceAll(" ", "+");
            paramMap.put("logoData", logoData);
        }
        hospitalServiceImpl.save(paramMap);
        return Result.ok();
    }

    @ApiOperation(value = "获取医院信息")
    @PostMapping("hospital/show")
    public Result hospital(HttpServletRequest request) {
        Map<String, Object> paramMap = getParamMap(request);
        checkSignKey(paramMap, hospitalServiceImpl);
        Hospital hospital = hospitalServiceImpl.getByHoscode((String)
                paramMap.get("hoscode"));
        return Result.ok(hospital);

    }

    @ApiOperation(value = "上传科室")
    @PostMapping("saveDepartment")
    public Result saveDepartment(HttpServletRequest request) {
        Map<String, Object> paramMap = getParamMap(request);
        checkSignKey(paramMap, hospitalServiceImpl);
        departmentServiceImpl.save(paramMap);
        return Result.ok();
    }

    @ApiOperation(value = "获取分页列表")
    @PostMapping("department/list")
    public Result department(HttpServletRequest request) {
        Map<String, Object> paramMap = getParamMap(request);
        checkSignKey(paramMap, hospitalServiceImpl);
// 条件查询
// 医院编号
        String hoscode = (String) paramMap.get("hoscode");
// 科室编号
        String depcode = (String) paramMap.get("depcode");
// 分页参数
        int page = !StringUtils.hasLength((String) paramMap.get("page")) ? 1 :
                Integer.parseInt((String) paramMap.get("page"));
        int limit = !StringUtils.hasLength((String) paramMap.get("limit")) ? 10 :
                Integer.parseInt((String) paramMap.get("limit"));
        DepartmentVo departmentQueryVo = new DepartmentVo();
        departmentQueryVo.setHoscode(hoscode);
        departmentQueryVo.setDepcode(depcode);
        Page<Department> pageModel = departmentServiceImpl.selectPage(page, limit,
                departmentQueryVo);
        return Result.ok(pageModel);
    }

    @ApiOperation(value = "删除科室")
    @PostMapping("department/remove")
    public Result removeDepartment(HttpServletRequest request) {
        Map<String, Object> paramMap = getParamMap(request);
        checkSignKey(paramMap, hospitalServiceImpl);
        String hoscode = (String) paramMap.get("hoscode");
        String depcode = (String) paramMap.get("depcode");
        departmentServiceImpl.remove(hoscode, depcode);
        return Result.ok();
    }
    @ApiOperation(value = "上传排班")
    @PostMapping("saveSchedule")
    public Result saveSchedule(HttpServletRequest request) {
        Map<String, Object> paramMap = getParamMap(request);
        checkSignKey(paramMap, hospitalServiceImpl);
        scheduleServiceImpl.save(paramMap);
        return Result.ok();
    }
    @ApiOperation(value = "获取排班分页列表")
    @PostMapping("schedule/list")
    public Result schedule(HttpServletRequest request){
        Map<String, Object> paramMap = getParamMap(request);
        checkSignKey(paramMap,hospitalServiceImpl);
        String hoscode = (String) paramMap.get("hoscode");
        String depcode = (String) paramMap.get("depcode");
        int page = !StringUtils.hasLength((String) paramMap.get("page")) ? 1 : Integer.parseInt((String) paramMap.get("page"));
        int limit = !StringUtils.hasLength((String) paramMap.get("limit")) ? 1 : Integer.parseInt((String) paramMap.get("limit"));
        ScheduleQueryVo scheduleQueryVo = new ScheduleQueryVo();
        scheduleQueryVo.setHoscode(hoscode);
        scheduleQueryVo.setDepcode(depcode);
        Page<Schedule> schedules = scheduleServiceImpl.selectPage(page, limit, scheduleQueryVo);
        return Result.ok(schedules);

    }
    @ApiOperation(value = "删除排班")
    @PostMapping("schedule/remove")
    public Result removeSchedule(HttpServletRequest request){
        Map<String, Object> paramMap = getParamMap(request);
        checkSignKey(paramMap,hospitalServiceImpl);
        String hoscode = (String) paramMap.get("hoscode");
        String hosScheduleId = (String) paramMap.get("hosScheduleId");
        scheduleServiceImpl.remove(hoscode,hosScheduleId);
        return Result.ok();
    }
}
