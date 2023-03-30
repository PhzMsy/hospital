package org.msy.hospital.service;

import org.msy.hospital.model.hosp.Department;
import org.msy.hospital.vo.hosp.DepartmentVo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @author 11612
 * @date 2023/3/27
 */
public interface DepartmentService {
    /**
     * 上传科室信息
     * 说明：参数使用Map，减少对象封装，有利于签名校验，后续会体验到
     */
    void save(Map<String, Object> paramMap);
    /**
     * 分页查询
     *
     * @param page              当前页码
     * @param limit             每页记录数
     * @param departmentQueryVo 查询条件
     * @return
     */
    Page<Department> selectPage(Integer page, Integer limit, DepartmentVo departmentQueryVo);
    /**
     * 删除科室
     * @param hoscode
     * @param depcode
     */
    void remove(String hoscode, String depcode);

    List<DepartmentVo> getDeptTree(String hosCode);
    /**
     * 根据医院编号、科室编号 查询科室名称
     */
    String getDepName(String hoscode, String depcode);

}
