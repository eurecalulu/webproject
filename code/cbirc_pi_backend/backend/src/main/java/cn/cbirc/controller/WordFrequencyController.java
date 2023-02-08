package cn.cbirc.controller;

import cn.cbirc.model.vo.ResponseVO;
import cn.cbirc.model.vo.WordFrequencyVO;
import cn.cbirc.service.WordFrequencyService;
import cn.cbirc.util.ResponseUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "词频统计接口")
@RestController
@RequestMapping("/wordFrequency")
public class WordFrequencyController {
    @Autowired
    WordFrequencyService wordFrequencyService;

    @PostMapping("/updateWordFrequency")
    ResponseEntity<ResponseVO> updateWordFrequency(){
        wordFrequencyService.updateWordFrequency();
        return ResponseUtils.success("更新成功");
    }

    @GetMapping("/getAllWordFrequency")
    ResponseEntity<List<WordFrequencyVO>> getAllWordFrequency(){
        List<WordFrequencyVO> wordFrequencyVOList= wordFrequencyService.getAllWordFrequency();
        return ResponseUtils.success(wordFrequencyVOList);
    }

    @GetMapping("/getRankNWordFrequency")
    ResponseEntity<List<WordFrequencyVO>> getRankNWordFrequency(@RequestParam int n){
        List<WordFrequencyVO> wordFrequencyVOList= wordFrequencyService.getRankNWordFrequency(n);
        return ResponseUtils.success(wordFrequencyVOList);
    }
}
