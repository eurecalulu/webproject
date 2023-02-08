package cn.cbirc.service;

import cn.cbirc.webservice.PolicyInterpretationService;
import cn.cbirc.webservice.PolicyInterpretationVO;
import cn.cbirc.webservice.PolicyInterpretationWebServiceImplService;
import org.junit.Test;

public class WebServiceTest {

    PolicyInterpretationWebServiceImplService policyInterpretationServiceImplService=new PolicyInterpretationWebServiceImplService();
    PolicyInterpretationService webService=policyInterpretationServiceImplService
            .getPolicyInterpretationWebServiceImplPort();


//    @Test
    public void testGetById(){
        PolicyInterpretationVO policyInterpretationVO = webService.getById(446);
        assert policyInterpretationVO!=null;
        assert policyInterpretationVO.getId()==446;
    }

//    @Test
    public void testGet(){
        assert webService.count("","","","","")>0;
        assert webService.list(false,"","","","","",1,10).size()>0;
    }
}
