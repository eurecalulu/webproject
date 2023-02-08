package cn.cbirc.dao;


import cn.cbirc.CbircApplication;
import cn.cbirc.model.po.CommentPO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional //支持数据回滚，避免测试数据污染环境
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CbircApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    int id;

    @Before
    public void setUp(){
        CommentPO commentPO = new CommentPO()
                .setPiId(446)
                .setUser(userRepository.getById(8))
                .setComment("评论内容")
                .setTime(new Date());
        commentRepository.save(commentPO);
        System.out.println("id = "+ commentPO.getId());
        id = commentPO.getId();
    }

    @Test
    public void testListByPi(){
        List<CommentPO> commentPOS = commentRepository.findByPiId(446);
        assert commentPOS.size()!=0;
        for(CommentPO commentPO:commentPOS){
            System.out.println(commentPO);
        }
    }

    @Test
    public void testDeleteById(){
        commentRepository.deleteById(id);
        List<CommentPO> commentPOS = commentRepository.findByPiId(446);
        assert commentPOS!=null;
        for(CommentPO commentPO:commentPOS){
            assert commentPO.getId()!=id;
        }
    }
}
