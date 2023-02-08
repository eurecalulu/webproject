package vo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import po.WordFrequencyPO;

@ApiModel("词频统计信息")
@Data
public class WordFrequencyVO {
    String keyWord;
    int frequency;

    public WordFrequencyVO(String key, Integer value) {
        keyWord=key;
        frequency=value;
    }

    public WordFrequencyVO(WordFrequencyPO wordFrequencyPO) {
        BeanUtils.copyProperties(wordFrequencyPO,this);
    }

    @Override
    public String toString() {
        return "WordFrequencyVO{" +
                "keyWord='" + keyWord + '\'' +
                ", frequency=" + frequency +
                '}';
    }

}
