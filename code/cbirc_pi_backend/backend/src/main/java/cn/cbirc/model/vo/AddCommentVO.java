package cn.cbirc.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

@Data
@Accessors(chain = true)
public class AddCommentVO {

    @Min(value = 0,message = "引用评论无效")
    int refCommentId; // 引用id，0则无效

    @NotBlank(message = "评论内容不得为空")
    String comment; // 评论内容

    int piId; // 政策解读id
}
