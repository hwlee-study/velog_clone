package com.gteam.glog.register.service;

import com.gteam.glog.common.utils.JWTTokenUtils;
import com.gteam.glog.domain.dto.UserInfoDTO;
import com.gteam.glog.register.repository.RegisterRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private final RegisterRepository registerRepository;
    private final JWTTokenUtils jwtTokenUtils;
    PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterService(RegisterRepository registerRepository, JWTTokenUtils jwtTokenUtils) {
        this.registerRepository = registerRepository;
        this.jwtTokenUtils = jwtTokenUtils;
    }

    public String createUserInfo(UserInfoDTO userInfoDTO) {
        UserInfoDTO setUserData = new UserInfoDTO();

        setUserData.setUserId(userInfoDTO.getUserId());
        setUserData.setNikName(userInfoDTO.getNikName());
        setUserData.setUserPwd(passwordEncoder.encode(userInfoDTO.getUserPwd()));

        registerRepository.createUserInfo(setUserData);

        if(!registerRepository.duplicateCheck(userInfoDTO.getUserId()) || userInfoDTO.getUserId() != null) {
            return "success";
        }

        return "false";
    }

    public String unRegistUser(String token) {
        Claims tokenData = jwtTokenUtils.getAllClaimsFromToken(token);

        try {
            registerRepository.unRegistUser((String) tokenData.get("email"));
            return "ok";
        } catch (Exception e) {
            return e.toString();
        }
    }
}
