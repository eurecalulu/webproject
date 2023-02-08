package cn.cbirc.service;


import cn.cbirc.CbircApplication;
import cn.cbirc.model.po.UserPO;
import cn.cbirc.model.vo.AddOrUpdatePolicyInterpretationVO;
import cn.cbirc.model.vo.ResponseVO;
import cn.cbirc.model.vo.UserInfoVO;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional //支持数据回滚，避免测试数据污染环境
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CbircApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceTest {
    @Autowired
    UserService userService;


    @Before
    public void setUp(){
        // set up user context
        List<GrantedAuthority> authorities = new ArrayList<>();
        UserPO principal = new UserPO().setName("办公厅").setId(1);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principal, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    public void testGetUserInfo(){
        UserInfoVO userInfoVO = userService.userInfo();
        assert userInfoVO!=null;
        assert userInfoVO.getId()==1;
        assert userInfoVO.getName().equals("办公厅");
    }

    @Test
    public void testRegisterAndLogin(){
        // 注册失败
        ResponseVO responseVO1 = userService.registerUser("办公厅","123456");
        assert !responseVO1.isSuccess();
        ResponseVO responseVO2 = userService.registerUser("456465465456","123456");
        assert responseVO2.isSuccess();
        ResponseVO responseVO3 = userService.login("456465465456","123456");
        assert responseVO3.isSuccess();
    }

    @Test
    public void testGetUserById(){
        // 注册失败
        UserPO userPO = userService.getUserById(1);
        assert userPO!=null;
        assert StringUtils.isNotEmpty(userPO.getName());
        assert StringUtils.isNotEmpty(userPO.getPassword());
    }
}
