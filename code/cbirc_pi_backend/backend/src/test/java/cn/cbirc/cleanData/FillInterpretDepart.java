package cn.cbirc.cleanData;

import cn.cbirc.CbircApplication;
import cn.cbirc.dao.PolicyInterpretationRepository;
import cn.cbirc.dao.UserRepository;
import cn.cbirc.model.http.ListPolicyInterpretFilter;
import cn.cbirc.model.http.PageList;
import cn.cbirc.model.po.PolicyInterpretationPO;
import cn.cbirc.model.po.UserPO;
import cn.cbirc.model.vo.PolicyInterpretationVO;
import cn.cbirc.service.PolicyInterpretService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 填充"解读部门"字段
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CbircApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FillInterpretDepart {

    @Autowired
    PolicyInterpretationRepository piRepository;

    @Autowired
    PolicyInterpretService piService;

    @Autowired
    UserRepository userRepository;

    /**
     * 运行该方法，以（半）自动化的方式从标题中识别解读部门，并更新数据库中的字段
     * 记得备份数据库以防丢失数据
     * ⚠️注意：使用后注释掉此方法以防意外运行
     */
//    @Test
//    public void fill(){
//        String pattern = "([\\s\\S]*)(((联合)?印发|发布)|就)(.*)";
//        Pattern r = Pattern.compile(pattern);
//        int pageNo = 1;
//        UserPO userPO = userRepository.getById(1);
//        while (true){
//            ListPolicyInterpretFilter filter = new ListPolicyInterpretFilter().setPageNo(pageNo).setPageSize(200).setWithBody(true);
//            PageList<PolicyInterpretationVO> pageList =  piService.listPublicByFilter(filter);
//            for(PolicyInterpretationVO policyInterpretationVO:pageList.getData()){
//                if(!policyInterpretationVO.getUser().equals(userPO.getName())){
//                    continue; // skip if not from cbirc.com
//                }
//                System.out.println("================"+policyInterpretationVO.getId()+"==========================");
//                System.out.println(policyInterpretationVO.getInterpretTitle());
//                Matcher m = r.matcher(policyInterpretationVO.getInterpretTitle());
//                if (m.find( )) {
//                    String depart = m.group(1);
//                    depart = depart.trim();
//                    depart = depart.replaceAll("\\s","、");
//                    if(depart.endsWith("就")){
//                        depart = depart.substring(0,depart.length()-1);
//                    }
//                    if(depart.endsWith("联合")){
//                        depart = depart.substring(0,depart.length()-2);
//                    }
//                    System.out.println("Found depart: " + depart);
//                    PolicyInterpretationPO policyInterpretationPO = new PolicyInterpretationPO(policyInterpretationVO);
//                    policyInterpretationPO.setInterpretDepart(depart);
//                    policyInterpretationPO.setUser(userPO);
//                    piRepository.save(policyInterpretationPO);
//                } else {
//                    System.out.println("NO MATCH:");
//                }
//            }
//            if(pageList.getTotal()<=pageNo*200){
//                break;
//            }
//            pageNo++;
//        }
//    }


}
