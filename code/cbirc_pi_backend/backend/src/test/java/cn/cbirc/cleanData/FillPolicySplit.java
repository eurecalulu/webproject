package cn.cbirc.cleanData;

import cn.cbirc.CbircApplication;
import cn.cbirc.dao.PolicySplitRepository;
import cn.cbirc.dao.UserRepository;
import cn.cbirc.model.http.ListPolicyInterpretFilter;
import cn.cbirc.model.http.PageList;
import cn.cbirc.model.po.PolicySplitPO;
import cn.cbirc.model.po.UserPO;
import cn.cbirc.model.vo.PolicyInterpretationVO;
import cn.cbirc.service.PolicyInterpretService;
import cn.cbirc.util.PolicySplitUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 分割政策并写入db
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CbircApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FillPolicySplit {

    @Autowired
    PolicyInterpretService piService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PolicySplitRepository policySplitRepository;

    /**
     * 运行该方法，以（半）自动化的方式从标题中识别解读部门，并更新数据库中的字段
     * 记得备份数据库以防丢失数据
     * ⚠️注意：使用后注释掉此方法以防意外运行
     */
//    @Test
//    public void fill(){
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
//                if(policySplitRepository.findByPiId(policyInterpretationVO.getId()).size()>0){
//                    System.out.println("skip");
//                    continue;
//                }
//                if(!StringUtils.isEmpty(policyInterpretationVO.getDocBody())){
//                    List<Pair<String,Boolean>> splitRes = PolicySplitUtils.split(policyInterpretationVO.getDocBody());
//                    for(Pair<String,Boolean> pair:splitRes){
//                        PolicySplitPO policySplitPO = new PolicySplitPO()
//                                .setPiId(policyInterpretationVO.getId())
//                                .setItem(pair.getRight())
//                                .setText(pair.getLeft());
//                        policySplitRepository.save(policySplitPO);
////                        System.out.println(pair.getRight()+":"+pair.getLeft());
//                    }
//                }
//            }
//            if(pageList.getTotal()<=pageNo*200){
//                break;
//            }
//            pageNo++;
//        }
//    }



}

