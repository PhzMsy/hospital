package org.msy.hospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.msy.hospital.model.hosp.HospitalSet;

/**
 * @author 11612
 * @date 2023/3/20
 */
@Mapper
public interface HospMapper extends BaseMapper<HospitalSet> {
}
