package vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;
import po.PolicyInterpretationPO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PolicyInterpretationVO {

    private int id;

    private String docTitle;

    private String docIdentifier;

    private String docDepart;

    private String docBody;

    private String interpretDepart;

    private String interpretTitle;

    private String interpretBody;

    private String interpretAbstract;

    private String time;

    private String user;

    private String status;

    public PolicyInterpretationVO(PolicyInterpretationPO pi){
        if(pi!=null) {
            BeanUtils.copyProperties(pi, this);
            this.user = pi.getUser().getName();
        }
    }
}
