package org.msy.hospital.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.msy.hospital.mapper.TestMapper;
import org.msy.hospital.model.test.Test;
import org.msy.hospital.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @author 11612
 * @date 2023/3/25
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {
}
