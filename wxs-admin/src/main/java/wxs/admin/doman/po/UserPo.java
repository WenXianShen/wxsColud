package wxs.admin.doman.po;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import wxs.common.com.BasePo;

import java.util.Date;

@Data
@TableName("t_user")
public class UserPo extends BasePo {
    private String userAccount;
    private String password;
    private String userNameZh;
    // 头像url
    private String  imageUrl;

    // 性别
    private String gender;

    // 电话
    private String telephone;

    // 邮箱
    private String mail;

    // 账号状态
    private String status;

    private String address;
    private String userType;

}
