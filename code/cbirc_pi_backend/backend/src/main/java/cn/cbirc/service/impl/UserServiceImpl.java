package cn.cbirc.service.impl;

import cn.cbirc.config.security.JwtTokenUtils;
import cn.cbirc.dao.UserRepository;
import cn.cbirc.model.po.UserPO;
import cn.cbirc.model.vo.ResponseVO;
import cn.cbirc.model.vo.UserInfoVO;
import cn.cbirc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public int getUserCount() {
        return (int) userRepository.count();
    }

    @Override
    public UserInfoVO userInfo() {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserPO userPO = userRepository.getByName(userDetails.getUsername());
            return new UserInfoVO(userPO);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public ResponseVO registerUser(String name, String password) {
        if(userRepository.getByName(name)!=null){
            return ResponseVO.buildFailure("用户名已存在");
        }
        //加密后再存入数据库
        userRepository.save(new UserPO(name,md5(password)));
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO login(String name, String password) {
        UserPO userPO = userRepository.getByName(name);
        if(userPO ==null){
            return ResponseVO.buildFailure("账号不存在");
        }
        if(userPO.getPassword().equals(md5(password))){
            return ResponseVO.buildSuccess(JwtTokenUtils.generateToken(userPO));
        }
        else{
            return ResponseVO.buildFailure("密码错误");
        }
    }

    @Override
    public UserPO getUserById(int id) {
        return userRepository.getById(id);
    }


    /**
     * 将明文按md5加密
     * @param password
     * @return
     */
    private static String md5(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("md5");

            byte[] bytes = md.digest(password.getBytes());

            String str = Base64.getEncoder().encodeToString(bytes);

            return str;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
