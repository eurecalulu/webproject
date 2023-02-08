package cn.cbirc.controller;

import cn.cbirc.model.vo.PolicyMatchVO;
import cn.cbirc.model.vo.PolicyRetrievalVO;
import cn.cbirc.service.IRService;
import cn.cbirc.util.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "政策解读与具体法规条文关联接口")
@RestController
@RequestMapping("/ir")
public class IRController {

    @Autowired
    IRService irService;

    @ApiOperation("检索相关法规条款")
    @PostMapping("/retrieval")
    ResponseEntity<PolicyMatchVO> retrieval(@RequestBody PolicyRetrievalVO policyRetrievalVO){
        return ResponseUtils.success(irService.retrieval(policyRetrievalVO));
    }
}
