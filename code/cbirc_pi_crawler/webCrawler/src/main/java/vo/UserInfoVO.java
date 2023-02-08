package vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import po.UserPO;

@Data
@ApiModel("用户详情VO")
public class UserInfoVO {
    private int id;
    private String name;

    public UserInfoVO(UserPO userPO){
        BeanUtils.copyProperties(userPO,this);
    }
}
