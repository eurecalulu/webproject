package cn.cbirc.service;

import cn.cbirc.CbircApplication;
import cn.cbirc.model.vo.WordFrequencyVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional //支持数据回滚，避免测试数据污染环境
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CbircApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WordFrequencyServiceTest {
    @Autowired
    WordFrequencyService wordFrequencyService;

    @Test
    public void testGetAllWordFrequency(){
       List<WordFrequencyVO> res = wordFrequencyService.getAllWordFrequency();
       assert res.size()!=0;
    }

    @Test
    public void testGetRankNWordFrequency(){
       List<WordFrequencyVO> res = wordFrequencyService.getRankNWordFrequency(10);
       assert res.size()==10;
    }
}
