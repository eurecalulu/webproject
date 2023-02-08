package cn.cbirc.dao;

import cn.cbirc.model.po.PolicyInterpretationPO;
import cn.cbirc.model.vo.DocDepartCountVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface PolicyInterpretationRepository extends JpaRepository<PolicyInterpretationPO,Integer> {

    PolicyInterpretationPO getById(Integer id);

    @Query(value = "select interpret_title from interpretation",nativeQuery = true)
    List<String> getAllInterpretationTitle();

    void deleteById(int id);

    PolicyInterpretationPO save(PolicyInterpretationPO policyInterpretation);

    @Query("select new cn.cbirc.model.vo.DocDepartCountVO(docDepart,count(id)) from PolicyInterpretationPO group by docDepart")
    List<DocDepartCountVO> getDocDepartCount();

    @Modifying
    @Transactional
    @Query(value="update interpretation set doc_text=?2 where id=?1",nativeQuery = true)
    int updatePolicyDocText(int id,String docText);

    Page<PolicyInterpretationPO> findAll(Specification<PolicyInterpretationPO> specification, Pageable pageable);

    List<PolicyInterpretationPO> findByDocTitle(String docTitle);

    @Transactional
    @Modifying//增删改必须有这个注解
    @Query(value = "update interpretation set doc_text=?2  where id =?1",nativeQuery = true)
    void updateDocBody(int id,String text);
}
