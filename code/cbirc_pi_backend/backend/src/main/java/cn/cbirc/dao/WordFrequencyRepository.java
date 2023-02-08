package cn.cbirc.dao;
import cn.cbirc.model.po.WordFrequencyPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WordFrequencyRepository extends JpaRepository<WordFrequencyPO,Integer> {
    List<WordFrequencyPO> findAll();

    WordFrequencyPO save(WordFrequencyPO w);

    void deleteAll();

    @Query(value = "select * from word_frequency order by frequency desc limit ?",nativeQuery = true)
    List<WordFrequencyPO> getRankNWordFrequency(int n);
}
