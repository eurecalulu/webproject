package cn.cbirc.dao;

import cn.cbirc.CbircApplication;
import cn.cbirc.model.enums.PIStatusEnums;
import cn.cbirc.model.po.PolicyInterpretationPO;
import cn.cbirc.model.vo.PolicyInterpretationVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Transactional //支持数据回滚，避免测试数据污染环境
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CbircApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PolicyInterpretationRepositoryTest {

    @Autowired
    PolicyInterpretationRepository policyInterpretationRepository;

    @Autowired
    UserRepository userRepository;

    @Before
    public void setUp() {
        PolicyInterpretationPO policyInterpretation = new PolicyInterpretationPO()
                .setDocTitle("关于资产支持计划和保险私募基金登记有关事项")
                .setDocIdentifier("银保监办发[2021]103号")
                .setDocDepart("资金部")
                .setDocBody("政策正文")
                .setTime("2021-10-01")
                .setUser(userRepository.getById(1))
                .setInterpretAbstract("abstract")
                .setInterpretBody("正文")
                .setInterpretDepart("有关部门")
                .setInterpretTitle("解读标题")
                .setStatus(PIStatusEnums.DRAFT.getStatus());
        policyInterpretationRepository.save(policyInterpretation);
        System.out.println("set up id = " + policyInterpretation.getId());
    }


    @Test
    public void testList() {
        // 构造自定义查询条件
        Specification<PolicyInterpretationPO> queryCondition = new Specification<PolicyInterpretationPO>() {
            @Override
            public Predicate toPredicate(Root<PolicyInterpretationPO> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                predicateList.add(criteriaBuilder.like(root.get("docTitle"), "%关于资产支持计划和保险私募基金登记有关事项%"));
                predicateList.add(criteriaBuilder.like(root.get("docIdentifier"), "%银保监办发[2021]103号%"));
                predicateList.add(criteriaBuilder.equal(root.get("docDepart"), "资金部"));
                predicateList.add(criteriaBuilder.equal(root.get("status"), PIStatusEnums.DRAFT.getStatus()));
                predicateList.add(criteriaBuilder.equal(root.get("user"), userRepository.getById(1)));
                predicateList.add(criteriaBuilder.between(root.get("time"), "2021-09-30", "2021-10-01"));
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
        // 分页
        Page<PolicyInterpretationPO> res = policyInterpretationRepository.findAll(
                queryCondition,
                PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "time")));
        List<PolicyInterpretationVO> policyInterpretationList = res.getContent().stream().map(PolicyInterpretationVO::new).collect(Collectors.toList());
        assert policyInterpretationList.size() != 0;
        for (int i = 0; i < policyInterpretationList.size(); i++) {
            assert policyInterpretationList.get(i).getInterpretBody() != null;
            assert policyInterpretationList.get(i).getInterpretAbstract() != null;
            assert policyInterpretationList.get(i).getInterpretDepart() != null;
            assert policyInterpretationList.get(i).getInterpretTitle() != null;
            assert policyInterpretationList.get(i).getTime() != null;
            assert policyInterpretationList.get(i).getUser() != null;
            assert policyInterpretationList.get(i).getDocDepart() != null;
            assert policyInterpretationList.get(i).getDocIdentifier() != null;
            assert policyInterpretationList.get(i).getDocTitle() != null;
            assert policyInterpretationList.get(i).getStatus() != null;
            assert policyInterpretationList.get(i).getDocBody() != null;
        }
    }
}
