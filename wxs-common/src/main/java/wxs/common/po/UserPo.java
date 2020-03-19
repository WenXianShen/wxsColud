package wxs.common.po;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import wxs.common.com.BaseVo;

import java.util.Date;

@Data
@TableName("t_user")
@AllArgsConstructor
public class UserPo extends BaseVo {
    private String account;
    private String password;
    private String userNameZh;
    // 头像url
    private String  imageUrl;

    // 性别
    private String gender;

    // 固定电话
    private String telephone;

    // 手机
    private String mobile;

    // 邮箱
    private String mail;

    // 账号状态
    private String status;

    // 生日
    private Date birthday;
    private String idcard;
    private String address;
    private String userType;

}
