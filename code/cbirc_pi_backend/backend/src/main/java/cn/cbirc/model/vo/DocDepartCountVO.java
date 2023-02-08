package cn.cbirc.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@ApiModel("doc_depart统计信息VO")
@NoArgsConstructor
public class DocDepartCountVO {
    String docDepart;
    int docDepartCount;

    public DocDepartCountVO(String docDepart,long docDepartCount){
        this.docDepart = docDepart;
        this.docDepartCount = (int) docDepartCount;
    }
}
