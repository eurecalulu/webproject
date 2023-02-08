package cn.cbirc.controller;

import cn.cbirc.model.vo.AddCommentVO;
import cn.cbirc.model.vo.CommentVO;
import cn.cbirc.model.vo.ResponseVO;
import cn.cbirc.service.CommentService;
import cn.cbirc.util.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "政策解读评论区接口")
@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @ApiOperation("新增评论")
    @PostMapping("/add")
    ResponseEntity<ResponseVO> addComment(@Valid @RequestBody AddCommentVO addCommentVO){
        int res = commentService.addComment(addCommentVO);
        if(res>0){
            return ResponseUtils.success(String.valueOf(res));
        }else {
            return ResponseUtils.failure("添加失败");
        }
   }

    @ApiOperation("删除评论")
    @DeleteMapping("/delete")
    ResponseEntity<ResponseVO> deleteComment(int id){
        if(commentService.deleteComment(id)){
            return ResponseUtils.success("删除成功");
        }else{
            return ResponseUtils.failure("删除失败");
        }
    }

    @ApiOperation("获取某个政策解读下的全部评论")
    @GetMapping("/listByPolicyInterpretationId")
    ResponseEntity<List<CommentVO>> getCommentsByPolicyInterpretationId(int id){
        return ResponseUtils.success(commentService.getCommentsByPolicyInterpretationId(id));
    }
}
