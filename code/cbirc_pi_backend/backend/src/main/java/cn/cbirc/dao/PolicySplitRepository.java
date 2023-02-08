package cn.cbirc.dao;

import cn.cbirc.model.po.PolicySplitPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PolicySplitRepository extends JpaRepository<PolicySplitPO, Integer> {
    PolicySplitPO save(PolicySplitPO policySplitPO);

    List<PolicySplitPO> findByPiId(int id);

    @Transactional(rollbackFor = Exception.class)
    void deleteByPiId(int id);
}
