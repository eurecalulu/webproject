package cn.cbirc.splitPolicy;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SplitPolicy {
    static char[] cnArr = new char [] {'一','二','三','四','五','六','七','八','九'};

    public static void main(String[] args) {
        SplitPolicy splitPolicy = new SplitPolicy();
        List<String> files = getFiles("/Users/chenyanze/Documents/课程资料/大四上/互联网实践/policy/");
        for(String file:files){
            System.out.println("policy:"+file);
            List<String> res = splitPolicy.split(splitPolicy.readFakeText(file));
            if(res!=null){
                for (int i = 0; i < res.size(); i++) {
                    System.out.println("=========="+i+"============");
                    System.out.println(res.get(i));
                }
            }
        }
    }

    /**
     * 获取某个目录下所有直接下级文件
     */
    private static List<String> getFiles(String path) {
        List<String> files = new ArrayList<String>();
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                files.add(tempList[i].toString());
            }
        }
        return files;
    }

    public List<String> readFakeText(String fileName){
        File file = new File(fileName);
        BufferedReader reader = null;
        List<String> res = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                if(!StringUtils.isEmpty(tempString)){
                    res.add(tempString);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return res;
    }

    public List<String> split(List<String> texts){
        for (String line:texts) {
            line = line.replaceAll("　"," ");
            line = line.replaceAll("\\s","");
            if(line.startsWith("第一条")){
                System.out.println("第x条");
                return splitType1(texts);
            }else if(line.startsWith("一、")){
                System.out.println("x、");
                return splitType2(texts);
            }
        }
        System.out.println("parse error");
        return null;
    }

    // type: 第X条、
    private List<String> splitType1(List<String> texts){
        List<String> res = new ArrayList<>();
        StringBuilder resLine = null;
        int chapter = 1;
        int index = 1;
        int currLine = 0;
        for (currLine = 0; currLine < texts.size(); currLine++) {
            String tmpLine = texts.get(currLine).replaceAll("　"," ");
            tmpLine = tmpLine.replaceAll("\\s","");
            if(tmpLine.startsWith("第一章")){
                chapter++;
            }
            if(tmpLine.startsWith("第一条")){
                break;
            }
        }
        for (; currLine < texts.size(); currLine++) {
            String line = texts.get(currLine);
            String tmpLine = line.replaceAll("　"," ");
            tmpLine = tmpLine.replaceAll("\\s","");
            String mark = "第"+arabicNumToChineseNum(index)+"条";
            if(tmpLine.startsWith("第"+arabicNumToChineseNum(chapter)+"章")){
                chapter++;
                continue;
            }
            if(tmpLine.startsWith(mark)){
                if(resLine!=null){
                    res.add(resLine.toString());
                }
                resLine = new StringBuilder(line);
                index++;
            }else{
                if(!StringUtils.isEmpty(tmpLine)){
                    resLine.append("\n");
                    resLine.append(line);
                }
            }
        }
        res.add(resLine.toString());
        return res;
    }

    // type: x、
    private List<String> splitType2(List<String> texts){
        List<String> res = new ArrayList<>();
        StringBuilder resLine = null;
        int index = 1;
        int currLine = 0;
        for (currLine = 0; currLine < texts.size(); currLine++) {
            String tmpLine = texts.get(currLine).replaceAll("　"," ");
            tmpLine = tmpLine.replaceAll("\\s","");
            if(tmpLine.startsWith("一、")){
                break;
            }
        }
        for (; currLine < texts.size(); currLine++) {
            String line = texts.get(currLine);
            String tmpLine = line.replaceAll("　"," ");
            tmpLine = tmpLine.replaceAll("\\s","");
            String mark = arabicNumToChineseNum(index)+"、";
            if(tmpLine.startsWith(mark)){
                if(resLine!=null){
                    res.add(resLine.toString());
                }
                resLine = new StringBuilder(line);
                index++;
            }else{
                resLine.append("\n");
                resLine.append(line);
            }
        }
        res.add(resLine.toString());
        return res;
    }


    /**
     * 将数字转换为中文数字， 这里只写到了万
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
            }
            else
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
