package wxs.admin.vo.role;

import lombok.Data;
import wxs.common.com.BasePagerVo;


@Data
public class RoleReqVo extends BasePagerVo {
    // 角色名称
    private String roleName;
    //角色类型
    private String roleType;

    private String description;
}
