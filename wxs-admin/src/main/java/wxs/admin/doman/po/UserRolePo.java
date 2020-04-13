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
@TableName("t_user_role")
public class UserRolePo extends BaseVo {
    // 用户id
    private Long userId;
    //角色id
    private Long roleId;



}
