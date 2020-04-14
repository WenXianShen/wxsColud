package wxs.common.com;


import lombok.Data;


import java.util.Date;

/**
 * VO基础类
 *
 * @see java.io.Serializable
 */
@Data
public class BaseVo implements java.io.Serializable {
    // serialVersionUID
    private static final long serialVersionUID = 6834473165049250202L;
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
    /**
     * 乐观锁版本字段
     * author:guyifeng
     */
    private Long version = 0L;

}
