package com.pms.PMS.Service.impl;

import com.pms.PMS.Entity.UserDtls;
import com.pms.PMS.Repository.UserRepository;
import com.pms.PMS.Service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public UserDtls saveUser(UserDtls userDtls) {
        return userRepository.save(userDtls);
    }

    @Override
    public UserDtls getUserByUsername(String nm) {
        return userRepository.findByUsername(nm);
    }

    @Override
    public List<UserDtls> getMembersByProject(Long id) {
        return userRepository.findUserDtlsByProjectDtlsId(id);
    }

    @Override
    public List<String> getAllUsernames() {
        return userRepository.getAllUsernames();
    }

}
