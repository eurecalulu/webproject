package cn.cbirc.dao;

import cn.cbirc.CbircApplication;
import cn.cbirc.model.po.UserPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@Transactional //支持数据回滚，避免测试数据污染环境
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CbircApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void testGetByName(){
        UserPO userPO = userRepository.getByName("test123");
        assert userPO!=null;
        assert userPO.getId()!=0;
        assert userPO.getName()!=null;
        assert userPO.getPassword()!=null;
    }

    @Test
    public void testSave(){
        UserPO userPO = userRepository.getByName("test8888");
        assert userPO==null;
        UserPO userPO1 = new UserPO("test8888","123456");
        userRepository.save(userPO1);
        userPO = userRepository.getByName("test8888");
        assert userPO!=null;
        assert userPO.getId()!=0;
        assert userPO.getName()!=null;
        assert userPO.getPassword()!=null;
    }
}
