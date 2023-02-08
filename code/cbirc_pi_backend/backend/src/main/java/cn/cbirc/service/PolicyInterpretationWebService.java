package cn.cbirc.service;

import cn.cbirc.model.vo.PolicyInterpretationVO;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService(name = "PolicyInterpretationService", targetNamespace = "http://webservice.cbirc.cn")
public interface PolicyInterpretationWebService {
    @WebMethod
    PolicyInterpretationVO getById(@WebParam(name = "id") int id);

    @WebMethod
    List<PolicyInterpretationVO> list(@WebParam(name = "withBody") boolean withBody,
                                    @WebParam(name = "docTitle") String docTitle,
                                    @WebParam(name = "docIdentifier") String docId,
                                    @WebParam(name = "docDepart") String docDepart,
                                    @WebParam(name = "startTime") String startTime,
                                    @WebParam(name = "endTime") String endTime,
                                    @WebParam(name = "pageNo") int pageNo,
                                    @WebParam(name = "pageSize") int pageSize);

    @WebMethod
    int count(@WebParam(name = "docTitle") String docTitle,
              @WebParam(name = "docIdentifier") String docId,
              @WebParam(name = "docDepart") String docDepart,
              @WebParam(name = "startTime") String startTime,
              @WebParam(name = "endTime") String endTime);

}