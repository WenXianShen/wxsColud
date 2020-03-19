package wxs.common.po;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import wxs.common.com.BaseVo;

/**
 * @author : imperater
 * @date : 2020/1/3
 */
@Data
@AllArgsConstructor
@TableName("t_role")
public class RolePo extends BaseVo {
    // 角色名称
    private String roleName;
    //角色类型
    private String roleType;

    private String description;


}
