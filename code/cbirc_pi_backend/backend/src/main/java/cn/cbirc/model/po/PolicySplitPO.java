package cn.cbirc.model.po;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity
@Table(name="policy_split")
@NoArgsConstructor
public class PolicySplitPO {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "piId")
    private int piId;

    @Column(name = "text")
    private String text;

    @Column(name = "is_item")
    private boolean isItem;

}
