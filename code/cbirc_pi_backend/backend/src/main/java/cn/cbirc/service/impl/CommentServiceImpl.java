package cn.cbirc.service.impl;

import cn.cbirc.dao.CommentRepository;
import cn.cbirc.model.po.CommentPO;
import cn.cbirc.model.vo.AddCommentVO;
import cn.cbirc.model.vo.CommentVO;
import cn.cbirc.model.vo.UserInfoVO;
import cn.cbirc.service.CommentService;
import cn.cbirc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommentServiceImpl implements CommentService {
    Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Autowired
    UserService userService;

    @Autowired
    CommentRepository commentRepository;

    @Override
    public int addComment(AddCommentVO addCommentVO) {
        UserInfoVO userInfo = userService.userInfo();
        if (userInfo == null) {
            return 0;
        } // TODO: delete after add filter
        CommentPO commentPO = new CommentPO()
                .setTime(new Date())
                .setUser(userService.getUserById(userInfo.getId()))
                .setComment(addCommentVO.getComment())
                .setRefId(addCommentVO.getRefCommentId()>0?addCommentVO.getRefCommentId():0)
                .setPiId(addCommentVO.getPiId());
        commentRepository.save(commentPO);
        return commentPO.getId();
    }

    @Override
    public boolean deleteComment(int id) {
        UserInfoVO userInfo = userService.userInfo();
        if (userInfo == null) {
            return false;
        } // TODO: delete after add filter
        try {
            commentRepository.deleteById(id);
            logger.error(String.format("user %s delete comment id=%d success",userInfo.getName(),id));
            return true;
        }catch (Exception e){
            logger.error(String.format("user %s delete comment id=%d failed, %s",userInfo.getName(),id,e.getMessage()));
            return false;
        }
    }

    @Override
    public List<CommentVO> getCommentsByPolicyInterpretationId(int id) {
        List<CommentPO> commentPOList = commentRepository.findByPiId(id);
        Map<Integer,CommentVO> map = new HashMap<>();
        List<CommentVO> res = new ArrayList<>();
        for(CommentPO commentPO:commentPOList){
            CommentVO commentVO = new CommentVO(commentPO);
            res.add(commentVO);
            map.put(commentVO.getId(),commentVO);
        }
        for(CommentPO commentPO:commentPOList){
            if(map.containsKey(commentPO.getRefId())){
                map.get(commentPO.getId()).setRef(map.get(commentPO.getRefId()));
            }
        }
        return res;
    }
}
