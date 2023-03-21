package org.msy.hospital.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.msy.hospital.mapper.HospMapper;
import org.msy.hospital.model.hosp.HospitalSet;
import org.msy.hospital.service.HospService;
import org.springframework.stereotype.Service;

/**
 * @author 11612
 * @date 2023/3/20
 */
@Service
public class HospServiceImpl extends ServiceImpl<HospMapper, HospitalSet> implements HospService {
}
