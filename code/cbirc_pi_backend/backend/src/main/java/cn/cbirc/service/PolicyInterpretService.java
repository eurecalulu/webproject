package cn.cbirc.service;

import cn.cbirc.model.http.ListPolicyInterpretFilter;
import cn.cbirc.model.http.PageList;
import cn.cbirc.model.vo.AddOrUpdatePolicyInterpretationVO;
import cn.cbirc.model.vo.DocDepartCountVO;
import cn.cbirc.model.vo.PolicyInterpretationVO;
import cn.cbirc.model.vo.UpdatePolicyDocTextVO;

import java.util.List;

/**
 * @author cyz
 */
public interface PolicyInterpretService {
    /**
     * get policy interpretation by id
     * @param id id
     * @return pi
     */
    PolicyInterpretationVO getById(Integer id);

    /**
     * update policy interpretation
     * @return success or failed
     */
    boolean updateInterpretation(AddOrUpdatePolicyInterpretationVO policyInterpretation);

    /**
     * update policy interpretation content
     */
    boolean updatePolicyDocText(UpdatePolicyDocTextVO updatePolicyDocTextVO);

    /**
     * add policy interpretation
     * @return id
     */
    int addInterpretation(AddOrUpdatePolicyInterpretationVO policyInterpretation);

    /**
     *
     * @param id
     * @param status
     * @return success or failed
     */
    boolean updateInterpretationStatus(int id,String status);

    /**
     * delete policy interpretation by id
     * @param id id
     * @return success or failed
     */
    boolean deleteById(int id);


    /**
     *
     * @return List<DocDepartCountVO>
     */
    List<DocDepartCountVO> getDocDepartCount();

    /**
     * list policy interpretation by filter and user token
     * @param filter select filter
     * @return pi
     */
    PageList<PolicyInterpretationVO> listByUserAndFilter(ListPolicyInterpretFilter filter);

    /**
     * list policy interpretation by filter and user token
     * @param filter select filter
     * @return pi
     */
    PageList<PolicyInterpretationVO> listPublicByFilter(ListPolicyInterpretFilter filter);

}
