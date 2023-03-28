package org.msy.hospital.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.msy.hospital.model.hosp.Hospital;
import org.msy.hospital.model.hosp.HospitalSet;
import org.msy.hospital.vo.hosp.HospitalQueryVo;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * @author 11612
 * @date 2023/3/20
 */
public interface HospService extends IService<HospitalSet> {
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
}
