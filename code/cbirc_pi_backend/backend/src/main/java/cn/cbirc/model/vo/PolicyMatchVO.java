package cn.cbirc.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class PolicyMatchVO {
    boolean found;
    String policyDoc;
    List<String> policyMatchItems;
    String message;
}
