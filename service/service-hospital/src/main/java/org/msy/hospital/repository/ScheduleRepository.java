package org.msy.hospital.repository;

import org.msy.hospital.model.hosp.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 11612
 * @date 2023/3/27
 */
@Repository
public interface ScheduleRepository extends MongoRepository<Schedule,String> {
    Schedule getScheduleByHoscodeAndHosScheduleId(String hoscode, String hosScheduleId);
}
