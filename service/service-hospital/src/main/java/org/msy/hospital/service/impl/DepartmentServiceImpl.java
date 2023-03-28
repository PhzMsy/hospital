package org.msy.hospital.service.impl;


import com.alibaba.fastjson.JSONObject;
import org.msy.hospital.model.hosp.Department;
import org.msy.hospital.repository.DepartmentRepository;
import org.msy.hospital.service.DepartmentService;
import org.msy.hospital.vo.hosp.DepartmentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author 11612
 * @date 2023/3/27
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public void save(Map<String, Object> paramMap) {
        Department department =
                JSONObject.parseObject(JSONObject.toJSONString(paramMap), Department.class);
        Department targetDepartment =
                departmentRepository.getDepartmentByHoscodeAndDepcode(department.getHoscode(),
                        department.getDepcode());
        if (null != targetDepartment) {
            targetDepartment.setUpdateTime(new Date());
            targetDepartment.setIsDeleted(0);
            departmentRepository.save(targetDepartment);
        } else {
            department.setCreateTime(new Date());
            department.setUpdateTime(new Date());
            department.setIsDeleted(0);
            departmentRepository.save(department);
        }
    }

    @Override
    public Page<Department> selectPage(Integer page, Integer limit, DepartmentVo departmentQueryVo) {
        // 现在是是由createTime 模拟排序， 思考如何查找热门科室
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
//0为第一页
        Pageable pageable = PageRequest.of(page-1, limit, sort);
        Department department = new Department();
// 拷贝科室实体类对象
        BeanUtils.copyProperties(departmentQueryVo,department);
// 设置数据默认为未删除
        department.setIsDeleted(0);
//创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching()
//改变默认字符串匹配方式：模糊查询
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
//改变默认大小写忽略方式：忽略大小写
                .withIgnoreCase(true);
        Example<Department> example = Example.of(department,matcher);
        Page<Department> all =  departmentRepository.findAll(example,pageable);
        return all;
    }
    @Override
    public void remove(String hoscode, String depcode) {
        Department dept =
                departmentRepository.getDepartmentByHoscodeAndDepcode(hoscode, depcode);
        if(null != dept) {
            departmentRepository.deleteById(dept.getId());
        }
    }
}

