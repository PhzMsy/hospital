package org.msy.hospital.service;

import org.msy.hospital.model.hosp.Schedule;
import org.msy.hospital.vo.hosp.ScheduleQueryVo;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * @author 11612
 * @date 2023/3/27
 */
public interface ScheduleService {
    /**
     * 上传排班信息
     * @param paramMap
     */
    void save(Map<String, Object> paramMap);

    /**
     * 分页查询
     *
     * @param page            当前页码
     * @param limit           每页记录数
     * @param scheduleQueryVo 查询条件
     * @return
     */

    Page<Schedule> selectPage(Integer page, Integer limit, ScheduleQueryVo
            scheduleQueryVo);

    void remove(String hoscode,String hosScheduleId);
}
