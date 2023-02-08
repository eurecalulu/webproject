//package cn.cbirc.crawler;
//
//import cn.cbirc.CbircApplication;
//import cn.cbirc.dao.PolicyInterpretationRepository;
//import cn.cbirc.model.enums.PIStatusEnums;
//import cn.cbirc.model.po.PolicyInterpretationPO;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.jsoup.Jsoup;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = CbircApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class PolicyInterpretationAdd {
//    /**
//     * 准备抓取的目标地址，%E4%BA%92%E8%81%94%E7%BD%91 为utf-8格式的 互联网
//     */
//    private static int page = 1;
//    private static int docId;
//    private static String title;
//    private static final Map<String, String> interpretationToSource = new HashMap<>();
//    private static final Map<String, Integer> sourceToDocId = new HashMap<>();
//    private static final List<Interpretation> interpretationList = new ArrayList<>();
//    private static final Map<Integer,SourceDoc> sourceDocList = new HashMap<>();
//
//    @Autowired
//    PolicyInterpretationRepository policyInterpretationRepository;
//
//    @Test
//    public void createInterpretation() throws Exception {
//        main(null);
//        for(Interpretation interpretation:interpretationList){
//            PolicyInterpretationPO policyInterpretationPO=new PolicyInterpretationPO();
//            policyInterpretationPO.setInterpretTitle(interpretation.title).
//                    setInterpretAbstract(interpretation.abstactContent).
//                    setInterpretBody(interpretation.text);
//            if(interpretationToSource.containsKey(interpretation.title)){
//                if(sourceToDocId.get(interpretationToSource.get(interpretation.title))!=null){
//                    int sourceDocId=sourceToDocId.get(interpretationToSource.get(interpretation.title));
//                    SourceDoc sourceDoc=sourceDocList.get(sourceDocId);
//                    policyInterpretationPO.
//                            setDocDepart(sourceDoc.agencyTypeName).
//                            setDocIdentifier(sourceDoc.documentNo).
//                            setTime(sourceDoc.builddate).
//                            setDocTitle(sourceDoc.docSubtitle).
//                            setStatus(PIStatusEnums.ABOLISH.getStatus());
//                    policyInterpretationRepository.save(policyInterpretationPO);
//                }
//            }
//        }
//    }
//
//
//    public static void main(String[] args) throws Exception {
//        establishMaps();
//        for(String sourceDocTitle:sourceToDocId.keySet()){
//            title=sourceDocTitle;
//            docId=sourceToDocId.get(sourceDocTitle);
//            System.out.println(docId);
//            saveSourceDocInfo();
//        }
////        docId=961830;
////        saveSourceDocInfo();
//    }
//
//    public static void establishMaps() throws Exception {
//        for (int j = 1; j < 20; j++) {
//            page = j;
//            String str1 = getResponse(getInfoMessage());
//            JSONObject infoResponce = new JSONObject(str1);
//            JSONArray responceArray = infoResponce.getJSONObject("data").getJSONArray("rows");
//            for (int i = 0; i < responceArray.length(); i++) {
//                title = responceArray.getJSONObject(i).getString("docSubtitle");
//                docId = responceArray.getJSONObject(i).getInt("docId");
//                System.out.println(title);
//                System.out.println(docId);
//                interpretationList.add(saveDocContent());
//                String sourceTitle = getSourceTitle(title);
//                if (sourceTitle != null) {
//                    mapSourceDocId(sourceTitle);
//                }
//            }
//        }
//        for (String key : sourceToDocId.keySet()) {
//            System.out.println(key + "=" + sourceToDocId.get(key));
//        }
//    }
//
//    private static String getResponse(String urlStr) throws IOException {
//        URL url = new URL(urlStr);
//        URLConnection uc = url.openConnection();
//        //这段代码中需要指定获取内容的编码格式，默认为UTF-8。
//        //然后数据就被存放在Buffer里面，你可以把Buffer看成一个水管，使用readline（）读取水管里面传的数据（水滴）
//        BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "utf-8"));
//        String str1 = "";
//        String str;
//        while ((str = in.readLine()) != null) {
//            str1 = str1 + str;
//        }
//        return str1;
//    }
//
//    private static String getSourceTitle(String str) {
//        String pattern = "《.*》";
//        Pattern r = Pattern.compile(pattern);
//        Matcher m = r.matcher(str);
//        if (m.find()) {
//            String sourceTitle = m.group(0);
//            sourceTitle = sourceTitle.substring(1, sourceTitle.length() - 1);
//            interpretationToSource.put(str, sourceTitle);
//            return sourceTitle;
//        }
//        return null;
//    }
//
//    private static SourceDoc saveSourceDocInfo() throws IOException, JSONException {
//        String str1 = getResponse(getSourceDocUrl());
//        JSONObject docInfoRespence = new JSONObject(str1).getJSONObject("data");
//        //System.out.println(docInfoRespence.toString());
//        String indexNo = docInfoRespence.getString("indexNo");
//        String interviewTypeName = docInfoRespence.getString("interviewTypeName");
//        String agencyTypeName = docInfoRespence.getString("agencyTypeName");
//        String builddate = docInfoRespence.getString("builddate");
//        String docSubtitle = docInfoRespence.getString("docSubtitle");
//        String documentNo= docInfoRespence.get("documentNo").toString();
//        String docContent = docInfoRespence.getString("docClob");
//        Document d = Jsoup.parse(docContent);
//        Element abstracts = d.getElementsByTag("title").first();
//        String abstractContent = abstracts.text();
//        Element body = d.getElementsByTag("body").first();
//        String text = body.text();
//        SourceDoc sourceDoc = new SourceDoc(title, abstractContent, text, docId, indexNo, interviewTypeName,
//                agencyTypeName, builddate, docSubtitle, documentNo);
//        sourceDocList.put(docId, sourceDoc);
//        System.out.println(sourceDoc);
//        return sourceDoc;
//    }
//
//    private static Interpretation saveDocContent() throws IOException, JSONException {
//        String str1 = getResponse(getSourceDocUrl());
//        JSONObject docRespence = new JSONObject(str1);
//        String docContent = docRespence.getJSONObject("data").getString("docClob");
//        Document d = (Document) Jsoup.parse(docContent);
//        Element abstracts = d.getElementsByTag("title").first();
//        if(abstracts==null){
//            return new Interpretation(title,"无",d.text(),docId);
//        }
//        String abstractContent = abstracts.text();
//        Element body = d.getElementsByTag("body").first();
//        String text = body.text();
//        Interpretation interpretation = new Interpretation(title, abstractContent, text, docId);
//        System.out.println(interpretation);
//        return interpretation;
//    }
//
//    private static void mapSourceDocId(String sourceDocTitle) throws IOException, JSONException {
//        String str1 = getResponse(getSourceDocUrl());
//        JSONObject docRespence = new JSONObject(str1);
//        String docContent = docRespence.getJSONObject("data").getString("docClob");
//        Document d = Jsoup.parse(docContent);
//        Elements hrefs = d.getElementsByTag("a");
//        for (Element e : hrefs) {
//            String href = e.text();
//            if (href.contains("generaltype=1")) {
//                String pattern = "docId=\\d*";
//                Pattern r = Pattern.compile(pattern);
//                Matcher m = r.matcher(href);
//                if (m.find()) {
//                    sourceToDocId.put(sourceDocTitle, Integer.parseInt(m.group(0).substring(6)));
//                }
//            }
//        }
//    }
//
//    private static String getInfoMessage() {
//        return "http://www.cbirc.gov.cn/cbircweb/DocInfo/SelectDocByItemIdAndChild?itemId=917&pageSize=18&pageIndex=" + page;
//    }
//
//    private static String getSourceDocUrl() {
//        return "https://www.cbirc.gov.cn/cn/static/data/DocInfo/SelectByDocId/data_docId=" + docId + ".json";
//    }
//
//}
