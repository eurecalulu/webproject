package cn.cbirc.service.impl;

import cn.cbirc.dao.PolicySplitRepository;
import cn.cbirc.model.po.PolicySplitPO;
import cn.cbirc.model.vo.PolicyMatchVO;
import cn.cbirc.model.vo.PolicyRetrievalVO;
import cn.cbirc.service.IRService;
import cn.cbirc.util.keyword.TextRankKeyword;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IRServiceImpl implements IRService {

    @Autowired
    PolicySplitRepository policySplitRepository;

    @Override
    public PolicyMatchVO retrieval(PolicyRetrievalVO policyRetrievalVO) {
        PolicyMatchVO res = new PolicyMatchVO();
        // 1. 提取关键词
        Map<String, Float> keywords = TextRankKeyword.getKeyword("", policyRetrievalVO.getText());
        if(keywords.size()<10){
            res.setFound(false);
            res.setMessage("句子过短，请重新选择");
            return res;
        }
        // 2. 关键词匹配
        List<PolicySplitPO> policySplitPOS = policySplitRepository.findByPiId(policyRetrievalVO.getPiId());
        if(!checkSplit(policySplitPOS)){
            res.setFound(false);
            res.setMessage("当前政策文本不支持匹配");
            return res;
        }
        System.out.println(keywords);
        List<Pair<Float, Integer>> scoresIndexPair = retrievalByTFIDF(keywords, policySplitPOS);
        // 3. 分析结果
        float maxScore = 0;
        for (int i = 0; i < scoresIndexPair.size(); i++) {
            maxScore = scoresIndexPair.get(i).getLeft() > maxScore ? scoresIndexPair.get(i).getLeft() : maxScore;
        }
        if (maxScore == 0) {
            res.setFound(false);
            res.setMessage("未找到匹配项");
            return res;
        }else{
            res.setFound(true);
            res.setPolicyDoc(contactByTFIDF(policySplitPOS, scoresIndexPair).toString());
            res.setPolicyMatchItems(getListByTFIDF(policySplitPOS, scoresIndexPair));
            res.setMessage("匹配成功");
        }
        return res;
    }

    /**
     * 根据TF-IDF检索匹配项
     *
     * @param keywords       关键词-权重
     * @param policySplitPOS 政策分割
     * @return List<Pair < Float, Integer>> 列表，得分:index，index为policySplitPOS对应
     */
    private List<Pair<Float, Integer>> retrievalByTFIDF(Map<String, Float> keywords, List<PolicySplitPO> policySplitPOS) {
        List<Map<String, Float>> TFs = new ArrayList<>();
        List<Pair<Float, Integer>> scoresIndexPair = new ArrayList<>();
        Map<String, Integer> wordCounter = new HashMap<>();
        for (String keyword : keywords.keySet()) {
            wordCounter.put(keyword, 0);
        }
        int itemCnt = 0;
        for (PolicySplitPO splitPO : policySplitPOS) {
            Map<String, Float> tf = new HashMap<>();
            if (splitPO.isItem()) {
                itemCnt++;
                for (String keyword : keywords.keySet()) {
                    int cnt = countStr(splitPO.getText(), keyword);
                    tf.put(keyword, (float) cnt/TextRankKeyword.countWord(splitPO.getText()));
//                    tf.put(keyword, (float) cnt);
                    if (cnt > 0) {
                        wordCounter.put(keyword, wordCounter.get(keyword) + 1);
                    }
                }
            }
            TFs.add(tf);
        }
        for (int i = 0; i < policySplitPOS.size(); i++) {
            float score = 0;
            if (policySplitPOS.get(i).isItem()) {
                for (String keyword : keywords.keySet()) {
                    float idf = (float) Math.log((float) itemCnt / (wordCounter.get(keyword) + 1));
                    if (idf < 0) {
                        idf = 0;
                    }
                    score += keywords.get(keyword) * TFs.get(i).get(keyword) * idf;
                }
            }
            scoresIndexPair.add(Pair.of(score, i));
            System.out.println("==============" + score + "==============");
            System.out.println(policySplitPOS.get(i).getText());
        }
        return scoresIndexPair;
    }

    private List<String> getListByTFIDF(List<PolicySplitPO> policySplitPOS, List<Pair<Float, Integer>> scoresIndexPair) {
        Collections.sort(scoresIndexPair, new Comparator<Pair<Float, Integer>>() {
            @Override
            public int compare(Pair<Float, Integer> o1, Pair<Float, Integer> o2) {
                return (o1.getLeft() - o2.getLeft() > 0 ? -1 : 1);
            }
        });
        List<String> res = new ArrayList<>();
        for (Pair<Float, Integer> pair : scoresIndexPair) {
            if (pair.getLeft() > 0) {
                res.add(policySplitPOS.get(pair.getRight()).getText());
            }
        }
        return res;
    }

    private StringBuilder contactByTFIDF(List<PolicySplitPO> policySplitPOS, List<Pair<Float, Integer>> scoresIndexPair) {
//        Collections.sort(scoresIndexPair, new Comparator<Pair<Float, Integer>>() {
//            @Override
//            public int compare(Pair<Float, Integer> o1, Pair<Float, Integer> o2) {
//                return (o1.getLeft() - o2.getLeft() > 0 ? -1 : 1);
//            }
//        });
        float maxScore = 0;
        for (int i = 0; i < scoresIndexPair.size(); i++) {
            maxScore = scoresIndexPair.get(i).getLeft() > maxScore ? scoresIndexPair.get(i).getLeft() : maxScore;
        }
        StringBuilder res = new StringBuilder();
        if (maxScore == 0) {
            return res;
        }
        for (int i = 0; i < policySplitPOS.size(); i++) {
            PolicySplitPO split = policySplitPOS.get(i);
            if (split.isItem()) {
                if (scoresIndexPair.get(i).getLeft() / maxScore > 0.5) {
                    res.append("<highlight>");
                    res.append(split.getText());
                    res.append("</highlight>");
                } else {
                    res.append(split.getText());
                }
            } else {
                res.append(split.getText());
            }
        }
        return res;
    }

    /**
     * 检查划分是否支持匹配
     * @param policySplitPOS
     * @return
     */
    private boolean checkSplit(List<PolicySplitPO> policySplitPOS){
        if(policySplitPOS.size()==0){
            return false;
        }
        for(PolicySplitPO split:policySplitPOS){
            if(split.isItem()){
                return true;
            }
        }
        return false;
    }

    /**
     * count how many str2 is contained in str1
     *
     * @param str1
     * @param str2
     * @return
     */
    private int countStr(String str1, String str2) {
        int counter = 0;
        while (str1.contains(str2)) {
            counter++;
            str1 = str1.substring(str1.indexOf(str2) + str2.length());
        }
        return counter;
    }
}
