package cn.cbirc.service.impl;

import cn.cbirc.dao.PolicyInterpretationRepository;
import cn.cbirc.dao.PolicySplitRepository;
import cn.cbirc.model.enums.PIStatusEnums;
import cn.cbirc.model.http.ListPolicyInterpretFilter;
import cn.cbirc.model.http.PageList;
import cn.cbirc.model.po.PolicyInterpretationPO;
import cn.cbirc.model.po.PolicySplitPO;
import cn.cbirc.model.po.UserPO;
import cn.cbirc.model.vo.*;
import cn.cbirc.service.PolicyInterpretService;
import cn.cbirc.service.UserService;
import cn.cbirc.util.PolicySplitUtils;
import lombok.Synchronized;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cyz
 */
@Service
public class PolicyInterpretServiceImpl implements PolicyInterpretService {

    Logger logger = LoggerFactory.getLogger(PolicyInterpretServiceImpl.class);

    @Autowired
    PolicyInterpretationRepository piRepository;

    @Autowired
    UserService userService;

    @Autowired
    PolicySplitRepository policySplitRepository;

    @Override
    public PolicyInterpretationVO getById(Integer id) {
        return new PolicyInterpretationVO(piRepository.getById(id));
    }

    @Override
    public boolean updateInterpretation(AddOrUpdatePolicyInterpretationVO policyInterpretation) {
        //TODO 这里获取用户信息的部分被我硬编码了。后面要改
        UserInfoVO userInfo = new UserInfoVO(userService.getUserById(1));
        if (userInfo == null) {
            return false;
        }
        PolicyInterpretationPO policyInterpretationPO = new PolicyInterpretationPO(policyInterpretation);
        policyInterpretationPO.setUser(userService.getUserById(userInfo.getId()));
        piRepository.save(policyInterpretationPO);
        return true;
    }

    @Override
    @Synchronized
    @Transactional
    public boolean updatePolicyDocText(UpdatePolicyDocTextVO updatePolicyDocTextVO) {
        //interpretation的id和sourceDoc的原文
        int res = piRepository.updatePolicyDocText(updatePolicyDocTextVO.getId(),
                updatePolicyDocTextVO.getDocText());
        if (res == 0) {
            return false;
        }
        // 更新划分
        policySplitRepository.deleteByPiId(updatePolicyDocTextVO.getId());
        List<Pair<String, Boolean>> splitRes = PolicySplitUtils.split(updatePolicyDocTextVO.getDocText());
        for (Pair<String, Boolean> pair : splitRes) {
            PolicySplitPO policySplitPO = new PolicySplitPO()
                    .setPiId(updatePolicyDocTextVO.getId())
                    .setItem(pair.getRight())
                    .setText(pair.getLeft());
            policySplitRepository.save(policySplitPO);
        }
        return true;
    }


    @Override
    public int addInterpretation(AddOrUpdatePolicyInterpretationVO policyInterpretation) {
        //TODO 这里获取用户信息的部分被我硬编码了。后面要改
// generate userId
//        UserInfoVO userInfo = userService.userInfo();
//        if (userInfo == null) {
//            return 0;
//        }
        //先存没有原文的interpretation
        PolicyInterpretationPO policyInterpretationPO = new PolicyInterpretationPO(policyInterpretation);
//        policyInterpretationPO.setUser(userService.getUserById(userInfo.getId()));
        policyInterpretationPO.setUser(userService.getUserById(1));
        piRepository.save(policyInterpretationPO);
        return policyInterpretationPO.getId();
    }

    @Override
    public boolean updateInterpretationStatus(int id, String status) {
        PolicyInterpretationPO policyInterpretationPO = piRepository.getById(id);
        policyInterpretationPO.setStatus(status);
        piRepository.save(policyInterpretationPO);
        return true;
    }

    @Override
    public boolean deleteById(int id) {
        piRepository.deleteById(id);
        return true;
    }

    @Override
    public List<DocDepartCountVO> getDocDepartCount() {
        return piRepository.getDocDepartCount();
    }

    @Override
    public PageList<PolicyInterpretationVO> listByUserAndFilter(ListPolicyInterpretFilter filter) {
        List<PolicyInterpretationVO> data = new ArrayList<>();
        int count = 0;
        // get non-public pi, query by user
        UserInfoVO userInfo = userService.userInfo();
        UserPO userPO = userService.getUserById(userInfo.getId());
        if (userInfo != null) {
            // 构造自定义查询条件
            Specification<PolicyInterpretationPO> queryCondition = new Specification<PolicyInterpretationPO>() {
                @Override
                public Predicate toPredicate(Root<PolicyInterpretationPO> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    List<Predicate> predicateList = new ArrayList<>();
                    if (StringUtils.isNotEmpty(filter.getDocTitle())) {
                        predicateList.add(criteriaBuilder.like(root.get("docTitle"), "%" + filter.getDocTitle() + "%"));
                    }
                    if (StringUtils.isNotEmpty(filter.getDocIdentifier())) {
                        predicateList.add(criteriaBuilder.like(root.get("docIdentifier"), "%" + filter.getDocIdentifier() + "%"));
                    }
                    if (StringUtils.isNotEmpty(filter.getDocDepart())) {
                        predicateList.add(criteriaBuilder.equal(root.get("docDepart"), filter.getDocDepart()));
                    }
                    predicateList.add(criteriaBuilder.equal(root.get("status"), filter.getStatus()));
                    predicateList.add(criteriaBuilder.equal(root.get("user"), userPO));
                    if (StringUtils.isNotEmpty(filter.getStartTime()) && StringUtils.isNotEmpty(filter.getEndTime())) {
                        predicateList.add(criteriaBuilder.between(root.get("time"), filter.getStartTime(), filter.getEndTime()));
                    }
                    return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
                }
            };
            // 分页
            try {
                Page<PolicyInterpretationPO> res = piRepository.findAll(
                        queryCondition,
                        PageRequest.of(filter.getPageNo() - 1, filter.getPageSize(), Sort.by(Sort.Direction.DESC, "time")));
                count = (int) res.getTotalElements();
                if (filter.isWithBody()) {
                    data = res.getContent().stream().map(PolicyInterpretationVO::new).collect(Collectors.toList());
                } else {
                    data = res.getContent().stream().map(i -> i.setDocBody("")).map(i -> i.setInterpretBody("")).map(PolicyInterpretationVO::new).collect(Collectors.toList());
                }
            } catch (Exception e) {
                logger.error("--queryPolicyInterpretationPOByCondition-- error : ", e);
            }
        }
        logger.info("get personal pi, all count: " + count + ", status: " + filter.getStatus());
        return new PageList<>(count, data);
    }

    @Override
    public PageList<PolicyInterpretationVO> listPublicByFilter(ListPolicyInterpretFilter filter) {
        List<PolicyInterpretationVO> data = new ArrayList<>();
        int count = 0;
        // 构造自定义查询条件
        Specification<PolicyInterpretationPO> queryCondition = new Specification<PolicyInterpretationPO>() {
            @Override
            public Predicate toPredicate(Root<PolicyInterpretationPO> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (StringUtils.isNotEmpty(filter.getDocTitle())) {
                    predicateList.add(criteriaBuilder.like(root.get("docTitle"), "%" + filter.getDocTitle() + "%"));
                }
                if (StringUtils.isNotEmpty(filter.getDocIdentifier())) {
                    predicateList.add(criteriaBuilder.like(root.get("docIdentifier"), "%" + filter.getDocIdentifier() + "%"));
                }
                if (StringUtils.isNotEmpty(filter.getDocDepart())) {
                    predicateList.add(criteriaBuilder.equal(root.get("docDepart"), filter.getDocDepart()));
                }
                predicateList.add(criteriaBuilder.equal(root.get("status"), PIStatusEnums.PUBLIC.getStatus()));
                if (StringUtils.isNotEmpty(filter.getStartTime()) && StringUtils.isNotEmpty(filter.getEndTime())) {
                    predicateList.add(criteriaBuilder.between(root.get("time"), filter.getStartTime(), filter.getEndTime()));
                }
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
        // 分页
        try {
            Page<PolicyInterpretationPO> res = piRepository.findAll(
                    queryCondition,
                    PageRequest.of(filter.getPageNo() - 1, filter.getPageSize(), Sort.by(Sort.Direction.DESC, "time")));
            count = (int) res.getTotalElements();
            if (filter.isWithBody()) {
                data = res.getContent().stream().map(PolicyInterpretationVO::new).collect(Collectors.toList());
            } else {
                data = res.getContent().stream().map(i -> i.setDocBody("")).map(i -> i.setInterpretBody("")).map(PolicyInterpretationVO::new).collect(Collectors.toList());
            }
        } catch (Exception e) {
            logger.error("--queryPolicyInterpretationPOByCondition-- error : ", e);
        }
        logger.info("get public pi, all count " + count);
        return new PageList<>(count, data);
    }
}