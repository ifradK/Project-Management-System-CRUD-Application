package com.pms.PMS.Repository;

import com.pms.PMS.Entity.UserDtls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.jws.soap.SOAPBinding;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<UserDtls, Integer> {

    public UserDtls findByUsername(String nm);

    List<UserDtls> findUserDtlsByProjectDtlsId(Long id);

    @Query("select username from UserDtls")
    public List<String> getAllUsernames();
}
