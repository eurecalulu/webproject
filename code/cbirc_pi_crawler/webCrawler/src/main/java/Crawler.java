import com.beust.ah.A;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.client.RestTemplate;
import po.PolicyInterpretationPO;
import vo.AddOrUpdatePolicyInterpretationVO;
import vo.PolicyInterpretationVO;
import vo.UpdatePolicyDocTextVO;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Crawler {
    /**
     * 准备抓取的目标地址，%E4%BA%92%E8%81%94%E7%BD%91 为utf-8格式的 互联网
     */
    private static int page = 1;
    private static int docId;
    private static String title;
    private static FileWriter fw;
    //这个哈希表映射了带书名号的标题和不带字符串的标题，其实就是去掉了书名号
    private static final Map<String, String> interpretationToSource = new HashMap<>();
    private static final Map<String, Integer> sourceToDocId = new HashMap<>();
    private static final List<Interpretation> interpretationList = new ArrayList<>();
    private static final List<SourceDoc> sourceDocList = new ArrayList<>();
    private static final HashMap<Integer, ArrayList<Integer>> prIdTosdId = new HashMap<>();
    private static final String WorkingDir = System.getProperty("user.dir").toString();
    private static RestTemplate restTemplate = new RestTemplate();
    private static final String url = "http://localhost:8002/";

    public static void main(String[] args) throws Exception {
        establishMaps();
        //在map外面处理是为了保证这些政策文件没有被重复获取
        for(String sourceDocTitle:sourceToDocId.keySet()){
            title=sourceDocTitle;
            docId=sourceToDocId.get(sourceDocTitle);
            System.out.println(docId);
            sourceDocList.add(saveSourceDocInfo());
        }
        //获得了所有原文的数据，此时更新所有的interpretation
        for(SourceDoc sourceDoc: sourceDocList){
            Integer sourceId = sourceDoc.docId;
            ArrayList<Integer> interpretations = prIdTosdId.get(sourceId);
            for(Integer interpretationId :interpretations){
                PolicyInterpretationVO policyInterpretationVO = restTemplate.getForEntity(url + "policyInterpretation/getById?id={id}", PolicyInterpretationVO.class, interpretationId).getBody();
                AddOrUpdatePolicyInterpretationVO addOrUpdatePolicyInterpretationVO = new AddOrUpdatePolicyInterpretationVO(policyInterpretationVO);
//                addOrUpdatePolicyInterpretationVO.setDocBody(sourceDoc.text);
                addOrUpdatePolicyInterpretationVO.setDocIdentifier(sourceDoc.documentNo);
                addOrUpdatePolicyInterpretationVO.setDocDepart(sourceDoc.agencyTypeName);
                addOrUpdatePolicyInterpretationVO.setDocTitle(sourceDoc.title);
                //TODO 这里的时间应该是解释的时间，而不是实际上原文的时间
                addOrUpdatePolicyInterpretationVO.setTime(sourceDoc.builddate);

                //更新interpretation数据

                restTemplate.postForEntity(url + "policyInterpretation/updateInterpretation", addOrUpdatePolicyInterpretationVO, AddOrUpdatePolicyInterpretationVO.class);
                //更新政策原文
                UpdatePolicyDocTextVO updatePolicyDocTextVO = new UpdatePolicyDocTextVO();
                updatePolicyDocTextVO.setDocText(sourceDoc.text);
                updatePolicyDocTextVO.setId(interpretationId);
                restTemplate.postForEntity(url + "policyInterpretation/updateDocText", updatePolicyDocTextVO, UpdatePolicyDocTextVO.class);

            }
        }
        //在这里更新词频
//        docId=961830;
////        saveSourceDocInfo();
    }

    public static void establishMaps() throws Exception {
        //一共有25页数据
        for (int j = 1; j < 26; j++) {
            page = j;
            String str1 = getResponse(getInfoMessage());
            JSONObject infoResponce = new JSONObject(str1);
            JSONArray responceArray = infoResponce.getJSONObject("data").getJSONArray("rows");
            for (int i = 0; i < responceArray.length(); i++) {
                title = responceArray.getJSONObject(i).getString("docSubtitle");
                docId = responceArray.getJSONObject(i).getInt("docId");
                System.out.println(title);
                System.out.println(docId);
                Interpretation interpretation = saveDocContent();
                interpretationList.add(interpretation);

                //将interpretation写入数据库
                AddOrUpdatePolicyInterpretationVO addOrUpdatePolicyInterpretationVO = new AddOrUpdatePolicyInterpretationVO();
                addOrUpdatePolicyInterpretationVO.setId(docId);
                addOrUpdatePolicyInterpretationVO.setInterpretBody(interpretation.getText());
                addOrUpdatePolicyInterpretationVO.setInterpretTitle(interpretation.getTitle());
                addOrUpdatePolicyInterpretationVO.setInterpretAbstract(interpretation.getAbstactContent());
                addOrUpdatePolicyInterpretationVO.setStatus("PUBLIC");
                restTemplate.postForEntity(url + "policyInterpretation/addInterpretation", addOrUpdatePolicyInterpretationVO, AddOrUpdatePolicyInterpretationVO.class);

                //保存interpretation
//                fw = new FileWriter(WorkingDir + "\\Interpretation\\" + docId + ".txt");
//                fw.write("title: " + title + "\n");
//                fw.write("docId: " + docId + "\n");
//                fw.write("abstactContent: " + interpretation.getAbstactContent() + "\n");
//                fw.write("text: " + interpretation.getText() + "\n");
//                fw.close();

                String sourceTitle = getSourceTitle(title);
                //判断政策解读有没有原文，有的话去找原文。
                if (sourceTitle != null) {
                    Integer sourceId = mapSourceDocId(sourceTitle);
                    if(!prIdTosdId.containsKey(sourceId)){
                        prIdTosdId.put(sourceId,new ArrayList<>());
                    }
                    prIdTosdId.get(sourceId).add(docId);
                }
            }
        }
        for (String key : sourceToDocId.keySet()) {
            System.out.println(key + "=" + sourceToDocId.get(key));
        }
    }

    private static String getResponse(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        URLConnection uc = url.openConnection();
        //这段代码中需要指定获取内容的编码格式，默认为UTF-8。
        //然后数据就被存放在Buffer里面，你可以把Buffer看成一个水管，使用readline（）读取水管里面传的数据（水滴）
        BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "utf-8"));
        String str1 = "";
        String str;
        while ((str = in.readLine()) != null) {
            str1 = str1 + str;
        }
        return str1;
    }

    private static String getSourceTitle(String str) {
        String pattern = "《.*》";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        if (m.find()) {
            String sourceTitle = m.group(0);
            sourceTitle = sourceTitle.substring(1, sourceTitle.length() - 1);
            interpretationToSource.put(str, sourceTitle);
            return sourceTitle;
        }
        return null;
    }

    private static SourceDoc saveSourceDocInfo() throws IOException {
        String str1 = getResponse(getSourceDocUrl());
        JSONObject docInfoRespence = new JSONObject(str1).getJSONObject("data");
        //System.out.println(docInfoRespence.toString());
        String indexNo = docInfoRespence.getString("indexNo");
        String interviewTypeName = docInfoRespence.getString("interviewTypeName");
        String agencyTypeName = docInfoRespence.getString("agencyTypeName");
        String builddate = docInfoRespence.getString("builddate");
        String docSubtitle = docInfoRespence.getString("docSubtitle");
        String documentNo= docInfoRespence.get("documentNo").toString();
        String docContent = docInfoRespence.getString("docClob");
        Document d = Jsoup.parse(docContent);
        Element abstracts = d.getElementsByTag("title").first();
        String abstractContent = abstracts.text();
        Element body = d.getElementsByTag("body").first();
        String text = body.text();
        SourceDoc sourceDoc = new SourceDoc(title, abstractContent, text, docId, indexNo, interviewTypeName,
                agencyTypeName, builddate, docSubtitle, documentNo);
        System.out.println(sourceDoc);


        //保存原文信息
//        fw = new FileWriter(WorkingDir + "\\SourceDoc\\" + docId + ".txt");
//        fw.write("title: " + title + "\n");
//        fw.write("abstactContent: " + abstractContent + "\n");
//        fw.write("text: " + text + "\n");
//        fw.write("docId: " + docId + "\n");
//        fw.write("indexNo: " + indexNo + "\n");
//        fw.write("interviewTypeName: " + interviewTypeName + "\n");
//        fw.write("agencyTypeName: " + agencyTypeName + "\n");
//        fw.write("builddate: " + builddate + "\n");
//        fw.write("docSubtitle: " + docSubtitle + "\n");
//        fw.write("documentNo: " + documentNo + "\n");
//        fw.close();


        return sourceDoc;
    }

    private static Interpretation saveDocContent() throws IOException {
        String str1 = getResponse(getSourceDocUrl());
        JSONObject docRespence = new JSONObject(str1);
        //获取json里面的html文档信息
        String docContent = docRespence.getJSONObject("data").getString("docClob");
        //解析html文档信息
        Document d = Jsoup.parse(docContent);
        Element abstracts = d.getElementsByTag("title").first();
        if(abstracts==null){
            return new Interpretation(title,"无",d.text(),docId);
        }
        String abstractContent = abstracts.text();
        Element body = d.getElementsByTag("body").first();
        String text = body.text();
        Interpretation interpretation = new Interpretation(title, abstractContent, text, docId);
        System.out.println(interpretation);
        return interpretation;
    }

    private static Integer mapSourceDocId(String sourceDocTitle) throws IOException {
        String str1 = getResponse(getSourceDocUrl());
        JSONObject docRespence = new JSONObject(str1);
        String docContent = docRespence.getJSONObject("data").getString("docClob");
        Document d = Jsoup.parse(docContent);
        Elements hrefs = d.getElementsByTag("a");
        for (Element e : hrefs) {
            String href = e.text();
            //过滤，在政策里面有通知有政策，只有generalType=1的才满足要求
            if (href.contains("generaltype=1")) {
                String pattern = "docId=\\d*";
                Pattern r = Pattern.compile(pattern);
                Matcher m = r.matcher(href);
                if (m.find()) {
                    //一个原文件标题到原文件id的映射
                    Integer sourceDocId = Integer.parseInt(m.group(0).substring(6));
                    sourceToDocId.put(sourceDocTitle, sourceDocId);
                    return sourceDocId;
                }
            }
        }
        return -1;
    }

    private static String getInfoMessage() {
        return "http://www.cbirc.gov.cn/cbircweb/DocInfo/SelectDocByItemIdAndChild?itemId=917&pageSize=18&pageIndex=" + page;
    }

    private static String getSourceDocUrl() {
        return "https://www.cbirc.gov.cn/cn/static/data/DocInfo/SelectByDocId/data_docId=" + docId + ".json";
    }
}
