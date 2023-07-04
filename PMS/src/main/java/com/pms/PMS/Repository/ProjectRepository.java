package com.pms.PMS.Repository;

import com.pms.PMS.Entity.ProjectDtls;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ProjectRepository extends JpaRepository<ProjectDtls, Long> {

    List<ProjectDtls> findByStartGreaterThanEqualAndEndLessThanEqual(Date startDate, Date endDate);

}
