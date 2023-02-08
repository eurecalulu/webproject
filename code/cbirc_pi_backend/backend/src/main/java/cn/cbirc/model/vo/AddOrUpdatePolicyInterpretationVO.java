package cn.cbirc.model.vo;

import cn.cbirc.valid.TimeChecker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class AddOrUpdatePolicyInterpretationVO {

    private int id;
//
//    private int pk;

    private String docTitle;

    private String docIdentifier;

    private String docDepart;

    private String docBody;

    private String interpretDepart;

    private String interpretTitle;

    private String interpretBody;

    private String interpretAbstract;

    @TimeChecker
    private String time;

    private String status;

}
