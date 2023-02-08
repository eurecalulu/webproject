package cn.cbirc.service;

import cn.cbirc.CbircApplication;
import cn.cbirc.config.security.JwtTokenUtils;
import cn.cbirc.model.http.ListPolicyInterpretFilter;
import cn.cbirc.model.http.PageList;
import cn.cbirc.model.po.UserPO;
import cn.cbirc.model.vo.AddOrUpdatePolicyInterpretationVO;
import cn.cbirc.model.vo.PolicyInterpretationVO;
import cn.cbirc.util.PolicySplitUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional //支持数据回滚，避免测试数据污染环境
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CbircApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PolicyInterpretServiceTest {
    @Autowired
    PolicyInterpretService policyInterpretService;
    int id;

    @Before
    public void setUp(){
        // set up user context
        List<GrantedAuthority> authorities = new ArrayList<>();
        UserPO principal = new UserPO().setName("办公厅").setId(1);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principal, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        AddOrUpdatePolicyInterpretationVO addOrUpdatePolicyInterpretationVO = new AddOrUpdatePolicyInterpretationVO()
                .setDocDepart("docDepart")
                .setDocIdentifier("docIdentifier")
                .setDocTitle("docTitle")
                .setDocBody("docBody")
                .setInterpretTitle("intTitle")
                .setInterpretDepart("intDepart")
                .setInterpretBody("body")
                .setInterpretAbstract("abstract")
                .setTime("2021-11-01")
                .setStatus("DRAFT");
        id = policyInterpretService.addInterpretation(addOrUpdatePolicyInterpretationVO);
    }

    @Test
    public void testGetById(){
        PolicyInterpretationVO policyInterpretationVO = policyInterpretService.getById(id);
        assert policyInterpretationVO!=null;
        assert StringUtils.isNotEmpty(policyInterpretationVO.getDocDepart());
        assert StringUtils.isNotEmpty(policyInterpretationVO.getDocIdentifier());
        assert StringUtils.isNotEmpty(policyInterpretationVO.getDocTitle());
        assert StringUtils.isNotEmpty(policyInterpretationVO.getDocBody());
        assert StringUtils.isNotEmpty(policyInterpretationVO.getInterpretAbstract());
        assert StringUtils.isNotEmpty(policyInterpretationVO.getInterpretBody());
        assert StringUtils.isNotEmpty(policyInterpretationVO.getInterpretDepart());
        assert StringUtils.isNotEmpty(policyInterpretationVO.getInterpretTitle());
        assert StringUtils.isNotEmpty(policyInterpretationVO.getTime());
        assert StringUtils.isNotEmpty(policyInterpretationVO.getUser());
        assert policyInterpretationVO.getId()==id;
    }

    @Test
    public void testUpdate(){
        AddOrUpdatePolicyInterpretationVO update = new AddOrUpdatePolicyInterpretationVO().setId(id)
            .setDocDepart("update_docDepart")
            .setDocIdentifier("update_docIdentifier")
            .setDocTitle("update_docTitle")
            .setDocBody("update_docBody")
            .setInterpretTitle("update_intTitle")
            .setInterpretDepart("update_intDepart")
            .setInterpretBody("update_body")
            .setInterpretAbstract("update_abstract")
            .setTime("2021-11-02")
            .setStatus("DRAFT");
        policyInterpretService.updateInterpretation(update);
        PolicyInterpretationVO policyInterpretationVO = policyInterpretService.getById(id);
        assert policyInterpretationVO!=null;
        assert policyInterpretationVO.getDocDepart().equals(update.getDocDepart());
        assert policyInterpretationVO.getDocIdentifier().equals(update.getDocIdentifier());
        assert policyInterpretationVO.getDocTitle().equals(update.getDocTitle());
        assert policyInterpretationVO.getInterpretAbstract().equals(update.getInterpretAbstract());
        assert policyInterpretationVO.getInterpretBody().equals(update.getInterpretBody());
        assert policyInterpretationVO.getInterpretDepart().equals(update.getInterpretDepart());
        assert policyInterpretationVO.getInterpretTitle().equals(update.getInterpretTitle());
        assert policyInterpretationVO.getTime().equals(update.getTime());
        assert policyInterpretationVO.getId()==id;
    }

    @Test
    public void testUpdateStatus(){
        boolean res1 = policyInterpretService.updateInterpretationStatus(id,"ABOLISH");
        assert res1;
        PolicyInterpretationVO policyInterpretationVO = policyInterpretService.getById(id);
        System.out.println(policyInterpretationVO);
        assert "ABOLISH".equals(policyInterpretationVO.getStatus());
        boolean res2 = policyInterpretService.updateInterpretationStatus(id,"PUBLIC");
        assert res2;
        policyInterpretationVO = policyInterpretService.getById(id);
        assert "PUBLIC".equals(policyInterpretationVO.getStatus());
    }

    @Test
    public void testListByUserAndFilter(){
        ListPolicyInterpretFilter filter = new ListPolicyInterpretFilter()
                .setDocDepart("docDepart")
                .setDocIdentifier("docId")
                .setDocTitle("Title")
                .setStartTime("2021-11-01")
                .setEndTime("2021-11-02")
                .setPageNo(1)
                .setPageSize(10)
                .setStatus("DRAFT");
        PageList<PolicyInterpretationVO> res = policyInterpretService.listByUserAndFilter(filter);
        assert res.getTotal()>0;
    }

    @Test
    public void listPublicByFilter(){
        ListPolicyInterpretFilter filter = new ListPolicyInterpretFilter()
                .setPageNo(1)
                .setPageSize(10)
                .setStatus("PUBLIC");
        PageList<PolicyInterpretationVO> res = policyInterpretService.listPublicByFilter(filter);
        assert res.getTotal()>0;
    }

    @Test
    public void testDeleteById(){
        policyInterpretService.deleteById(id);
    }
}
