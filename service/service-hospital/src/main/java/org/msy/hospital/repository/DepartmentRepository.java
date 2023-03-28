package org.msy.hospital.repository;

import org.msy.hospital.model.hosp.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 11612
 * @date 2023/3/27
 */
@Repository
public interface DepartmentRepository extends MongoRepository<Department,String> {
    Department getDepartmentByHoscodeAndDepcode(String hoscode, String depcode);

}
