package org.msy.hospital.service.impl;

import com.alibaba.fastjson.JSONObject;

import org.msy.hospital.model.hosp.Schedule;
import org.msy.hospital.repository.ScheduleRepository;
import org.msy.hospital.service.ScheduleService;
import org.msy.hospital.vo.hosp.ScheduleQueryVo;
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
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public void save(Map<String, Object> paramMap) {
        Schedule schedule =
                JSONObject.parseObject(JSONObject.toJSONString(paramMap), Schedule.class);
        Schedule targetSchedule =
                scheduleRepository.getScheduleByHoscodeAndHosScheduleId(schedule.getHoscode(),
                        schedule.getHosScheduleId());
        if (null != targetSchedule) {
            targetSchedule.setUpdateTime(new Date());
            targetSchedule.setIsDeleted(0);
            scheduleRepository.save(targetSchedule);
        } else {
            schedule.setCreateTime(new Date());
            schedule.setUpdateTime(new Date());
            schedule.setIsDeleted(0);
            scheduleRepository.save(schedule);
        }
    }

    @Override
    public Page<Schedule> selectPage(Integer page, Integer limit, ScheduleQueryVo scheduleQueryVo) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        //0为第一页
        Pageable pageable = PageRequest.of(page-1, limit, sort);
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleQueryVo, schedule);
        schedule.setIsDeleted(0);
//创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认 字符串匹配方式：模糊查询
                .withIgnoreCase(true); //改变默认大小写忽略方式：忽略大小写
//创建实例
        Example<Schedule> example = Example.of(schedule, matcher);
        Page<Schedule> all = scheduleRepository.findAll(example, pageable);
        return all;


    }

    @Override
    public void remove(String hoscode, String hosScheduleId) {
        Schedule id = scheduleRepository.getScheduleByHoscodeAndHosScheduleId(hoscode, hosScheduleId);
        if (null != id){
            scheduleRepository.deleteById(id.getId());
        }
    }
}
