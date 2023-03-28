package org.msy.hospital.repository;

import org.msy.hospital.model.hosp.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author 11612
 * @date 2023/3/27
 */
public interface HospitalRepository extends MongoRepository<Hospital,String> {
    /**
     * 根据医院编号获取医院数据
     */
    Hospital getHospitalByHoscode(String hoscode);

}
