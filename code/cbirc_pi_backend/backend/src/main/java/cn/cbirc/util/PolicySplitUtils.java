package cn.cbirc.util;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PolicySplitUtils {
    static char[] cnArr = new char[]{'一', '二', '三', '四', '五', '六', '七', '八', '九'};

    public static List<Pair<String,Boolean>> split(String text) {
        List<String> texts = Arrays.stream(text.split("\n")).collect(Collectors.toList());
        return split(texts);
    }

    public static List<Pair<String,Boolean>> split(List<String> texts) {
        for (String line : texts) {
            line = line.replaceAll(" ", " ");
            line = line.replaceAll("　", " ");
            line = line.replaceAll("\\s", "");
            if (line.startsWith("第一条")) {
//                System.out.println("第x条");
                return splitType1(texts);
            } else if (line.startsWith("一、")) {
//                System.out.println("x、");
                return splitType2(texts);
            }
        }
        System.out.println("parse error");
        List<Pair<String,Boolean>> res = new ArrayList<>();
        for(String text:texts){
            res.add(Pair.of(text,false));
        }
        return res;
    }

    private static void addLastItem(List<Pair<String,Boolean>> res, StringBuilder lastItem, boolean isItem){
        String[] labels = {"中国银保监会","附：","2021年","附件","2020年","2019年","2018年"};
        if(!isItem){
            res.add(Pair.of(lastItem.toString(),false));
        }else{
            List<String> texts = Arrays.stream(lastItem.toString().split("\n")).collect(Collectors.toList());
            StringBuilder lastRes = new StringBuilder();
            int i;
            boolean end =false;
            for (i = 0; i < texts.size(); i++) {
                String line = texts.get(i);
                line = line.replaceAll(" ", " ");
                line = line.replaceAll("　", " ");
                line = line.replaceAll("\\s", "");
                for(String label:labels){
                    if(line.startsWith(label)){
                        res.add(Pair.of(lastRes.toString(),true));
                        end = true;
                        break;
                    }
                }
                if(end){
                    break;
                }else{
                    lastRes.append(texts.get(i));
                }
            }
            if(end){
                lastRes = new StringBuilder();
                for (int j = i; j < texts.size(); j++) {
                    lastRes.append(texts.get(j));
                }
                res.add(Pair.of(lastRes.toString(),false));
            }
        }
    }

    // type: 第X条、
    private static List<Pair<String,Boolean>> splitType1(List<String> texts) {
        List<Pair<String,Boolean>> res = new ArrayList<>();
        StringBuilder resLine = new StringBuilder();
        int chapter = 1;
        int index = 1;
        int currLine;
        boolean isItem = false;
        for (currLine = 0; currLine < texts.size(); currLine++) {
            String tmpLine = texts.get(currLine).replaceAll("　", " ");
            tmpLine = tmpLine.replaceAll(" ", "");
            tmpLine = tmpLine.replaceAll("\\s", "");
            if (tmpLine.startsWith("第一章")) {
                chapter++;
            }
            if (tmpLine.startsWith("第一条")) {
                break;
            }
            resLine.append(texts.get(currLine));
            resLine.append("\n");
        }
        for (; currLine < texts.size(); currLine++) {
            String line = texts.get(currLine);
            String tmpLine = line.replaceAll("　", " ");
            tmpLine = tmpLine.replaceAll("\\s", "");
            String mark = "第" + arabicNumToChineseNum(index) + "条";
            if (tmpLine.startsWith("第" + arabicNumToChineseNum(chapter) + "章")) {
                res.add(Pair.of(resLine.toString(),isItem));
                resLine = new StringBuilder(line);
                chapter++;
                isItem = false;
            } else if (tmpLine.startsWith(mark)) {
                res.add(Pair.of(resLine.toString(),isItem));
                resLine = new StringBuilder(line);
                index++;
                isItem = true;
            } else {
                resLine.append(line);
            }
            resLine.append("\n");
        }
        addLastItem(res,resLine,isItem);
//        res.add(Pair.of(resLine.toString(),isItem));
        return res;
    }

    // type: x、
    private static List<Pair<String,Boolean>> splitType2(List<String> texts) {
        List<Pair<String,Boolean>> res = new ArrayList<>();
        StringBuilder resLine = new StringBuilder();
        int index = 1;
        int currLine = 0;
        boolean isItem = false;
        for (currLine = 0; currLine < texts.size(); currLine++) {
            String tmpLine = texts.get(currLine).replaceAll("　", " ");
            tmpLine = tmpLine.replaceAll(" ", "");
            tmpLine = tmpLine.replaceAll("\\s", "");
            if (tmpLine.startsWith("一、")) {
                break;
            }
            resLine.append(texts.get(currLine));
            resLine.append("\n");
        }
        for (; currLine < texts.size(); currLine++) {
            String line = texts.get(currLine);
            String tmpLine = line.replaceAll("　", " ");
            tmpLine = tmpLine.replaceAll("\\s", "");
            String mark = arabicNumToChineseNum(index) + "、";
            if (tmpLine.startsWith(mark)) {
                res.add(Pair.of(resLine.toString(),isItem));
                resLine = new StringBuilder(line);
                index++;
                isItem = true;
            } else {
                resLine.append(line);
            }
            resLine.append("\n");
        }
        addLastItem(res,resLine,isItem);
//        res.add(Pair.of(resLine.toString(),isItem));
        return res;
    }


    /**
     * 将数字转换为中文数字， 这里只写到了万
     *
     * @param intInput
     * @return
     */
    private static String arabicNumToChineseNum(int intInput) {
        String si = String.valueOf(intInput);
        String sd = "";
        if (si.length() == 1) {
            if (intInput == 0) {
                return sd;
            }
            sd += cnArr[intInput - 1];
            return sd;
        } else if (si.length() == 2) {
            if (si.substring(0, 1).equals("1")) {
                sd += "十";
                if (intInput % 10 == 0) {
                    return sd;
                }
            } else
                sd += (cnArr[intInput / 10 - 1] + "十");
            sd += arabicNumToChineseNum(intInput % 10);
        } else if (si.length() == 3) {
            sd += (cnArr[intInput / 100 - 1] + "百");
            if (String.valueOf(intInput % 100).length() < 2) {
                if (intInput % 100 == 0) {
                    return sd;
                }
                sd += "零";
            }
            sd += arabicNumToChineseNum(intInput % 100);
        }
        return sd;
    }
}
