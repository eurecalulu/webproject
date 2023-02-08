package cn.cbirc.service;

import cn.cbirc.model.vo.WordFrequencyVO;

import java.util.List;

public interface WordFrequencyService {
    void updateWordFrequency();

    List<WordFrequencyVO> getAllWordFrequency();

    List<WordFrequencyVO> getRankNWordFrequency(int n);
}
