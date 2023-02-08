package cn.cbirc.service;

import cn.cbirc.model.vo.AddCommentVO;
import cn.cbirc.model.vo.CommentVO;

import java.util.List;

public interface CommentService {
    /**
     * 添加新评论
     * @param addCommentVO 评论内容
     * @return 新评论id
     */
    int addComment(AddCommentVO addCommentVO);

    /**
     * 删除评论
     * @param id 删除id
     * @return 是否成功
     */
    boolean deleteComment(int id);

    /**
     * 获取某个政策解读下的全部评论
     * @param id 政策解读id
     * @return 全部评论列表
     */
    List<CommentVO> getCommentsByPolicyInterpretationId(int id);
}
