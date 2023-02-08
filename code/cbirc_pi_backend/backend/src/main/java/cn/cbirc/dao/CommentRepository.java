package cn.cbirc.dao;

import cn.cbirc.model.po.CommentPO;
import cn.cbirc.service.CommentService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentPO, Integer> {

    CommentPO save(CommentPO commentPO);

    List<CommentPO> findByPiId(int piId);

    void deleteById(int id);
}
