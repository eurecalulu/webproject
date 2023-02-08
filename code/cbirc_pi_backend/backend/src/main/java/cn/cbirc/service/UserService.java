package cn.cbirc.service;

import cn.cbirc.model.po.UserPO;
import cn.cbirc.model.vo.ResponseVO;
import cn.cbirc.model.vo.UserInfoVO;

public interface UserService {
    int getUserCount();

    /**
     * 获取当前用户信息
     * @return
     * not null: 当前用户信息
     * null: 未登录
     */
    UserInfoVO userInfo();

    /**
     * 注册用户
     * @return 是否成功
     */
    ResponseVO registerUser(String name,String password);

    /**
     * 登录
     * @return 如果成功，msg为token； 如果失败，msg为错误信息
     */
    ResponseVO login(String name, String password);

    UserPO getUserById(int id);
}
