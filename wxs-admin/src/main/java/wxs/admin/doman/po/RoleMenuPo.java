package wxs.admin.doman.po;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import wxs.common.com.BaseVo;

/**
 * @author : imperater
 * @date : 2020/1/3
 */
@Data
@TableName("t_role_menu")
public class RoleMenuPo extends BaseVo {
    // 角色id
    private Long roleId;
    //菜单id
    private Long menuId;



}
