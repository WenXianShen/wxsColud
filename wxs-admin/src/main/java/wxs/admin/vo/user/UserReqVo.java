package wxs.admin.vo.user;

import lombok.Data;
import wxs.common.com.BasePagerVo;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

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
    private String  imageUrl;
    private String gender;
    private String telephone;
    private String mobile;
    private String mail;
    private String status;
    private String idcard;
    private String address;
    private String userAccount;
    private String userNameZh;
    private List<String> roleIds;
}
