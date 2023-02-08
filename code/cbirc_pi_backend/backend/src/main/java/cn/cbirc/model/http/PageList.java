package cn.cbirc.model.http;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 用于包装分页结果
 */
@Data
@AllArgsConstructor
public class PageList<T> {
    /**
     * 总条目
     */
    int total;

    /**
     * 分页后的数据条目
     */
    List<T> data;
}
