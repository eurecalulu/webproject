package cn.cbirc.controller;

import cn.cbirc.model.enums.PIStatusEnums;
import cn.cbirc.model.http.ListPolicyInterpretFilter;
import cn.cbirc.model.http.PageList;
import cn.cbirc.model.vo.*;
import cn.cbirc.service.PolicyInterpretService;
import cn.cbirc.util.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Api(tags = "政策解读接口")
@RestController
@RequestMapping("/policyInterpretation")
public class PolicyInterpretationController {

    @Autowired
    PolicyInterpretService piService;

    @ApiOperation("新增政策解读")
    @PostMapping("/addInterpretation")
    ResponseEntity<ResponseVO> addInterpretation(@Valid @RequestBody AddOrUpdatePolicyInterpretationVO policyInterpretation){
        int res = piService.addInterpretation(policyInterpretation);
        if(res>0){
            return ResponseUtils.success(String.valueOf(res));
        }else {
            return ResponseUtils.failure("添加失败");
        }
    }

    @ApiOperation("获取docDepart统计信息")
    @GetMapping("/getDocDepartCount")
    ResponseEntity<List<DocDepartCountVO>> getDocDepartCount(){
        return ResponseUtils.success(piService.getDocDepartCount());
    }

    @ApiOperation("更新政策解读条目的状态")
    @PostMapping("/updateInterpretationStatus")
    ResponseEntity<ResponseVO> updateInterpretationStatus(int id,String status){
        if(piService.updateInterpretationStatus(id,status)){
            return ResponseUtils.success("更新状态成功");
        }
        else {
            return ResponseUtils.failure("更新状态失败");
        }
    }

    @ApiOperation("更新政策解读")
    @PostMapping("/updateInterpretation")
    ResponseEntity<ResponseVO> updateInterpretation(@Valid @RequestBody AddOrUpdatePolicyInterpretationVO policyInterpretation){
        if(piService.updateInterpretation(policyInterpretation)){
            return ResponseUtils.success("更新成功");
        }else{
            return ResponseUtils.failure("更新失败");
        }
    }

    @ApiOperation("更新政策正文")
    @PostMapping("/updateDocText")
    ResponseEntity<ResponseVO> updateDocText(@Valid @RequestBody UpdatePolicyDocTextVO updatePolicyDocTextVO){
        if(piService.updatePolicyDocText(updatePolicyDocTextVO)){
            return ResponseUtils.success("更新正文成功");
        }
        else{
            return ResponseUtils.failure("更新正文失败");
        }
    }

    @ApiOperation("删除政策解读")
    @PostMapping("/deleteById")
    ResponseEntity<ResponseVO> deleteById(int id){
        if(piService.deleteById(id)){
            return ResponseUtils.success("删除成功");
        }else{
            return ResponseUtils.failure("删除失败");
        }

    }

    @ApiOperation("根据id获取政策解读")
    @GetMapping("/getById")
    ResponseEntity<PolicyInterpretationVO> getById(@RequestParam("id") Integer id){
        return ResponseUtils.success(piService.getById(id));
    }

    @ApiOperation("筛选政策解读列表")
    @PostMapping("/list")
    ResponseEntity<PageList<PolicyInterpretationVO>> listPublic(@Valid @RequestBody ListPolicyInterpretFilter filter){
        if(PIStatusEnums.PUBLIC.getStatus().equals(filter.getStatus())){
            return ResponseUtils.success(piService.listPublicByFilter(filter));
        }else{
            return ResponseUtils.success(piService.listByUserAndFilter(filter));
        }
    }
}
