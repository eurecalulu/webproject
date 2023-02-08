package vo;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import po.CommentPO;

import java.text.SimpleDateFormat;

@Data
@ApiModel("评论条目")
@Accessors(chain = true)
@NoArgsConstructor
public class CommentVO {
    int id;
    int piId;
    String comment;
    UserInfoVO userInfo;
    String time;
    CommentVO ref;

    public CommentVO(CommentPO commentPO){
        this.id = commentPO.getId();
        this.piId = commentPO.getPiId();
        this.comment = commentPO.getComment();
        this.userInfo = new UserInfoVO(commentPO.getUser());
        this.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(commentPO.getTime());
    }

}
