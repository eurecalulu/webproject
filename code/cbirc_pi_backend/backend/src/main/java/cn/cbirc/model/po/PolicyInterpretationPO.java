package cn.cbirc.model.po;
import cn.cbirc.model.vo.AddOrUpdatePolicyInterpretationVO;
import cn.cbirc.model.vo.PolicyInterpretationVO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name="interpretation")
public class PolicyInterpretationPO {

    @Id
    @Column(name="id")
    private int id;

//    @Column(name="docid")
//    private int pk;

    @Column(name="doc_title")
    private String docTitle;

    @Column(name="doc_identifier")
    private String docIdentifier;

    @Column(name="doc_depart")
    private String docDepart;

    @Column(name="interpret_depart")
    private String interpretDepart;

    @Column(name="interpret_title")
    private String interpretTitle;

    @Column(name="interpret_text")
    private String interpretBody;

    @Column(name="interpret_content")
    private String interpretAbstract;

    @Column(name="enter_time")
    private String time;

    @Column(name="status")
    private String status;

    @Column(name = "doc_text")
    private String docBody;

    @ManyToOne
    @JoinColumn(name="enter_user")
    private UserPO user;

    public PolicyInterpretationPO(PolicyInterpretationVO pi){
        BeanUtils.copyProperties(pi,this);
    }

    public PolicyInterpretationPO(AddOrUpdatePolicyInterpretationVO pi){
        BeanUtils.copyProperties(pi,this);
    }




}
