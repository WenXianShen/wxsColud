package wxs.common.vo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import wxs.common.com.BasePagerVo;

import javax.validation.constraints.NotNull;

/**
 * @author : imperater
 * @date : 2020/1/14
 */
@Data
public class UserReqVo extends BasePagerVo {
    @NotNull(message = "用户名不能为空")
    private String account;
    @NotNull(message = "密码不能为空")
    private String password;
    private String name;
    private String userType;
}
