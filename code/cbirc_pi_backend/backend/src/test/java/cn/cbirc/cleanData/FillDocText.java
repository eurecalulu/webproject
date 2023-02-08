package cn.cbirc.cleanData;

import cn.cbirc.CbircApplication;
import cn.cbirc.dao.PolicyInterpretationRepository;
import cn.cbirc.dao.PolicySplitRepository;
import cn.cbirc.dao.UserRepository;
import cn.cbirc.model.po.PolicyInterpretationPO;
import cn.cbirc.model.po.UserPO;
import cn.cbirc.service.PolicyInterpretService;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 填充征求意见稿的解读正文
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CbircApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FillDocText {


    @Autowired
    PolicyInterpretationRepository piRepository;

    @Autowired
    PolicySplitRepository policySplitRepository;

    /**
     * 运行该方法，以（半）自动化的方式从标题中识别解读部门，并更新数据库中的字段
     * 记得备份数据库以防丢失数据
     * ⚠️注意：使用后注释掉此方法以防意外运行
     */
//    @Test
//    public void fill() {
//        String parentPath = "/Users/chenyanze/Downloads/policyDocList/";
//        List<String> paths = getAllFile(parentPath);
//        for (String path : paths) {
//            if (!".DS_Store".equals(path)) {
//                List<PolicyInterpretationPO> policyInterpretationPOS = piRepository.findByDocTitle(path.split("\\.")[0]);
//                System.out.println(path);
//                String res = readWord(parentPath+path);
//                for (PolicyInterpretationPO policyInterpretationPO : policyInterpretationPOS) {
//                    System.out.println(policyInterpretationPO.getId());
//                    System.out.println(policyInterpretationPO.getDocBody());
//                    policyInterpretationPO.setDocBody(res);
//                    piRepository.updateDocBody(policyInterpretationPO.getId(),res);
//                }
//
//            }
//        }
//    }

    public static String readWord(String path) {
        String buffer = "";
        try {
            if (path.endsWith(".doc")) {
                InputStream is = new FileInputStream(new File(path));
//                return read(is);
                WordExtractor ex = new WordExtractor(is);
                buffer = ex.getText();
                ex.close();
            } else if (path.endsWith("docx")) {
                OPCPackage opcPackage = POIXMLDocument.openPackage(path);
                POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
                buffer = extractor.getText();
                extractor.close();
            } else {
                System.out.println("此文件不是word文件！");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return buffer;
    }

    /**
     * 获取路径下的所有文件/文件夹
     *
     * @param directoryPath 需要遍历的文件夹路径
     * @return
     */
    public static List<String> getAllFile(String directoryPath) {
        List<String> list = new ArrayList<String>();
        File baseFile = new File(directoryPath);
        File[] files = baseFile.listFiles();
        for (File file : files) {
            list.add(file.getName());
        }
        return list;
    }
}
