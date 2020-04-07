package wxs.common.com;


import lombok.Data;

import java.util.Date;

/**
 * PO基础类
 *
 
 */
@Data
public class BasePo {

    // ID
    protected Long id;

    // 逻辑删除标记
    protected String isDeleted = "2";

    // 创建人ID
    protected Long createBy;

    // 创建时间
    protected Date createTime;

    // 更新者ID
    protected Long updateBy;

    // 更新时间
    protected Date updateTime;
    // 版本号
    protected Long version;



    

}
