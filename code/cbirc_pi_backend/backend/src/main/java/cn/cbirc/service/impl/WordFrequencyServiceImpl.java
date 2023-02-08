package cn.cbirc.service.impl;

import cn.cbirc.dao.PolicyInterpretationRepository;
import cn.cbirc.dao.WordFrequencyRepository;
import cn.cbirc.model.po.WordFrequencyPO;
import cn.cbirc.model.vo.WordFrequencyVO;
import cn.cbirc.service.WordFrequencyService;
import cn.cbirc.util.WordFrequencyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordFrequencyServiceImpl implements WordFrequencyService {
    @Autowired
    WordFrequencyRepository wordFrequencyRepository;

    @Autowired
    PolicyInterpretationRepository policyInterpretationRepository;

    @Scheduled(cron="0 0 0 * * *")
    @Override
    public void updateWordFrequency() {
        List<String> titleList= policyInterpretationRepository.getAllInterpretationTitle();
        List<String> rawText=new ArrayList<>();
        for(String title:titleList){
            if(title.contains("《")&&title.contains("》")){
                rawText.add(title);
            }
        }
        List<WordFrequencyVO> wordFrequencyVOS= WordFrequencyUtils.wordCount(rawText);
        wordFrequencyRepository.deleteAll();
        for(WordFrequencyVO wordFrequencyVO:wordFrequencyVOS){
            wordFrequencyRepository.save(new WordFrequencyPO(wordFrequencyVO));
        }
    }

    @Override
    public List<WordFrequencyVO> getAllWordFrequency() {
        return wordFrequencyRepository.findAll().stream().map(WordFrequencyVO::new).collect(Collectors.toList());
    }

    @Override
    public List<WordFrequencyVO> getRankNWordFrequency(int n) {
        return wordFrequencyRepository.getRankNWordFrequency(n).stream().map(WordFrequencyVO::new).collect(Collectors.toList());
    }

}
