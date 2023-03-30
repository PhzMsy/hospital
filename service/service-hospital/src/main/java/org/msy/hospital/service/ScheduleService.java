package org.msy.hospital.service;

import org.msy.hospital.model.hosp.Schedule;
import org.msy.hospital.vo.hosp.ScheduleQueryVo;
import org.springframework.data.domain.Page;

import java.util.List;
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
    /**
     * 根据医院编号、科室编号，查询排班规则数据
     */
    Map<String, Object> getScheduleRule(long page, long limit, String hosCode,
                                        String depCode);
    /**
     * 根据医院编号、科室编号和工作日期，查询排班信息
     */
    List<Schedule> getScheduleDetail(String hosCode, String depCode, String
            workDate);

}
