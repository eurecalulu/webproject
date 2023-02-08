package cn.cbirc.util;
import cn.cbirc.model.vo.WordFrequencyVO;
import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.Word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wuminxuan
 * 词频统计类
 */

public  class WordFrequencyUtils {
    /**
     * 关键词统计主方法
     * @param rawTexts
     * @return
     */
    public static List<WordFrequencyVO> wordCount(List<String> rawTexts){
        List<String> titles=getTitles(rawTexts);
        List<WordFrequencyVO> result=calculateWordFrequency(titles);
        return result;
    }

    /**
     * 从文本中提取书名号之间的内容
     * @param rawTexts
     * @return
     */
    private static List<String> getTitles(List<String> rawTexts){
        List<String> titles=new ArrayList<>();
        for(String rawText:rawTexts){
            int i=0;
            int startIndex=0,endIndex=0;
            while(i<rawText.length()){
                if(rawText.charAt(i)=='《'){
                    startIndex=i++;
                }
                else if(rawText.charAt(i)=='》'){
                    endIndex=i++;
                    titles.add(rawText.substring(startIndex+1,endIndex));
                }
                else{
                    i++;
                }
            }
        }
        return titles;
    }


    /**
     * 分词,统计词出现的次数
     * @param titles
     * @return
     */
    private static List<WordFrequencyVO> calculateWordFrequency(List<String> titles){
        Map<String,Integer> wordCount=new HashMap<>();
        for(String title:titles){
            List<Word> words=WordSegmenter.seg(title);
            for(Word word:words){
                String wordString=word.toString();
                if(wordCount.containsKey(wordString)){
                    wordCount.put(wordString,wordCount.get(wordString)+1);
                }
                else{
                    wordCount.put(wordString,1);
                }
            }
        }
        List<WordFrequencyVO> result=new ArrayList<>();
        for(Map.Entry<String,Integer> entry:wordCount.entrySet()){
            if(entry.getValue()>=3){
                WordFrequencyVO w=new WordFrequencyVO(entry.getKey(),entry.getValue());
                result.add(w);
            }
        }
        return result;
    }
}
