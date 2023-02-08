package cn.cbirc.model.http;

import cn.cbirc.valid.StatusChecker;
import cn.cbirc.valid.TimeChecker;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;


@Data
@Accessors(chain = true)
public class ListPolicyInterpretFilter {
    boolean withBody;
    String docTitle;
    String docIdentifier;
    String docDepart;
    @TimeChecker
    String startTime;
    @TimeChecker
    String endTime;
    @StatusChecker
    String status;
    @Min(value = 1,message = "当前页数需大于等于1")
    int pageNo;
    int pageSize;
}
