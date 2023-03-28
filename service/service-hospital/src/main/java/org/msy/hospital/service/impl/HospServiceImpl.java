package org.msy.hospital.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.msy.hospital.common.exception.YyghException;
import org.msy.hospital.common.result.ResultCodeEnum;
import org.msy.hospital.mapper.HospMapper;
import org.msy.hospital.model.hosp.Hospital;
import org.msy.hospital.model.hosp.HospitalSet;
import org.msy.hospital.service.HospService;
import org.msy.hospital.repository.HospitalRepository;
import org.msy.hospital.vo.hosp.HospitalQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author 11612
 * @date 2023/3/20
 */
@Service
public class HospServiceImpl extends ServiceImpl<HospMapper, HospitalSet> implements HospService {
    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private HospMapper hospMapper;

    @Override
    public void save(Map<String, Object> paramMap) {
        // 1. 将paramMap 从Map类型的对象 转换成了Json的字符串
        String json = JSONObject.toJSONString(paramMap);
// 2. 将json字符串转换成了Hospital的实体类对象
        Hospital hospital = JSONObject.parseObject(json, Hospital.class);
// 3. 通过“模拟系统上传到平台的医院信息中的医院编号”去mongoDB中进行查询，并且返回记录
        Hospital targetHospital = hospitalRepository.getHospitalByHoscode(hospital.getHoscode());
// 4. 根据targetHospital是否为null来确定是执行新增操作还是修改操作
        if (null != targetHospital) {
// 5. 修改操作
            hospital.setStatus(targetHospital.getStatus()); // 手动填充，数据不变
            hospital.setCreateTime(targetHospital.getCreateTime()); // 手动填充，数据不变
            hospital.setUpdateTime(new Date()); // 修改时间 -> 当前系统时间
            hospital.setIsDeleted(0);
// 6. 执行修改操作
            hospitalRepository.save(hospital);
        } else {
// 5. 新增操作
// 需要补足json数据中没有的字段
//0：未上线 1：已上线
            hospital.setStatus(0); // 状态
            hospital.setCreateTime(new Date()); // 创建时间
            hospital.setUpdateTime(new Date()); // 修改时间
            hospital.setIsDeleted(0); // 逻辑删除状态
// 6. 执行修改操作
            hospitalRepository.save(hospital);
        }
    }

    /**
     * 获取秘钥方法：
     * 作为医院的模拟系统 所调用的任何平台接口，第一件事就是需要验证医院的秘钥
     * 如果秘钥和平台保存的秘钥解密后相同，此时就可以继续执行，否则退回
     * <p>
     * hoscode : 医院编号
     * @param hoscode
     * @return
     */
    @Override
    public String getSignKey(String hoscode) {
        // 通过医院编号，获取平台mysql数据中的医院设置数据
        HospitalSet hospitalSet = this.getHospitalSetByHoscode(hoscode);
        if (null == hospitalSet) {
// 没有查询到相关的医院设置数据
            throw new YyghException(ResultCodeEnum.HOSPITAL_OPEN);
        }
// status = 0 -> 未上线 status -> 上线
        if (hospitalSet.getStatus().intValue() == 0) {
// 判断医院是否上线，如果没有上线，报错并提示
            throw new YyghException(ResultCodeEnum.HOSPITAL_LOCK);
        }
// 返回根据医院编号查询到的秘钥
        return hospitalSet.getSignKey();
    }

    @Override
    public Hospital getByHoscode(String hoscode) {
        return hospitalRepository.getHospitalByHoscode(hoscode);
    }

    /**
     * 根据hoscode获取医院设置数据
     */
    private HospitalSet getHospitalSetByHoscode(String hoscode) {
// where hoscode = ?
        QueryWrapper wrapper = new QueryWrapper<HospitalSet>().eq("hoscode",
                hoscode);
        return hospMapper.selectOne(wrapper);
    }
    @Override
    public Page<Hospital> selectHospPage(Integer page, Integer limit,
                                         HospitalQueryVo vo) {
        Pageable pageable = PageRequest.of(page - 1,limit);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);
        Hospital hospital = new Hospital();
        BeanUtils.copyProperties(vo,hospital);
        Example<Hospital> example = Example.of(hospital,matcher);
        Page<Hospital> all = hospitalRepository.findAll(example, pageable);
        return all;
    }


}
