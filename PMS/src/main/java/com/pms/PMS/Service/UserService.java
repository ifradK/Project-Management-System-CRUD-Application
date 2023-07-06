package com.pms.PMS.Service;

import com.pms.PMS.Entity.UserDtls;
import java.util.List;

public interface UserService {

    UserDtls saveUser(UserDtls userDtls);

    UserDtls getUserByUsername(String nm);

    List<UserDtls> getMembersByProject(Long id);

    List<String> getAllUsernames(Long id);
}
