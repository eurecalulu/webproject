package cn.cbirc.service;

import cn.cbirc.model.vo.PolicyMatchVO;
import cn.cbirc.model.vo.PolicyRetrievalVO;

public interface IRService {
    PolicyMatchVO retrieval(PolicyRetrievalVO policyRetrievalVO);
}
