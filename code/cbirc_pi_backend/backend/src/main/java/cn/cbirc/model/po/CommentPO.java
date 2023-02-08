package cn.cbirc.model.po;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Data
@Accessors(chain = true)
@Entity
@Table(name="_comment")
@NoArgsConstructor
public class CommentPO {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserPO user;

    @Column(name = "ref_id")
    private int refId;

    @Column(name = "interpretation_id")
    private int piId;

    @Column(name="time")
    private Date time;
}
