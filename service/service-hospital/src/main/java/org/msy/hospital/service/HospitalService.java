package org.msy.hospital.service;

import org.springframework.data.domain.Page;
import org.msy.hospital.model.hosp.Hospital;
import org.msy.hospital.vo.hosp.HospitalQueryVo;


import java.util.List;
import java.util.Map;

/**
 * @author 11612
 * @date 2023/3/29
 */
public interface HospitalService {
    /**
     * 上传医院信息
     */
    void save(Map<String,Object> paramMap);
    /**
     * 获取签名key
     */
    String getSignKey(String hoscode);
    /**
     * 查询医院
     */
    Hospital getByHoscode(String hoscode);
    /**
     * 条件、分页查询医院信息
     */
    Page<Hospital> selectHospPage(Integer page, Integer limit, HospitalQueryVo vo);
    /**
     * 更新医院上线状态
     */
    void updateHospStatus(String id,Integer  status);
    /**
     * 医院详情
     */
    Map<String,Object> getHospById(String id);
    /**
     * 根据编号获取名称
     */
    String getHospName(String hosCode);
    /**
     * 根据医院名称查询医院信息(用户系统-首页)
     */
    List<Hospital> findByHosname(String hosname);
    /**
     * 医院预约挂号详情
     */
    Map<String,Object> item(String hoscode);
}
