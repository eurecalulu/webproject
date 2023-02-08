package vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import valid.TimeChecker;

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

    public AddOrUpdatePolicyInterpretationVO(PolicyInterpretationVO policyInterpretationVO){
        this.id = policyInterpretationVO.getId();
        this.docBody = policyInterpretationVO.getDocBody();
        this.docDepart = policyInterpretationVO.getDocDepart();
        this.docIdentifier = policyInterpretationVO.getDocIdentifier();
        this.docTitle = policyInterpretationVO.getDocTitle();
        this.interpretAbstract = policyInterpretationVO.getInterpretAbstract();
        this.interpretBody = policyInterpretationVO.getInterpretBody();
        this.interpretDepart = policyInterpretationVO.getInterpretDepart();
        this.interpretTitle= policyInterpretationVO.getInterpretTitle();
        this.time = policyInterpretationVO.getTime();
        this.status = policyInterpretationVO.getStatus();
    }

}
