package com.pms.PMS.Repository;

import com.pms.PMS.Entity.UserDtls;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.jws.soap.SOAPBinding;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<UserDtls, Integer> {

    public UserDtls findByUsername(String nm);

//    Set<UserDtls> findUserDtlsByProjectId(Long id);
    List<UserDtls> findUserDtlsByProjectDtlsId(Long id);
}
