package cn.cbirc.model.po;

import cn.cbirc.model.vo.WordFrequencyVO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Data
@Entity
@Table(name = "word_frequency")
@NoArgsConstructor
public class WordFrequencyPO {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    int id;
    @Column(name = "keyWord")
    String keyWord;
    @Column(name = "frequency")
    int frequency;

    public WordFrequencyPO(WordFrequencyVO wordFrequencyVO){
        BeanUtils.copyProperties(wordFrequencyVO,this);
    }
}
