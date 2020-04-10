package wxs.common.vo;

import lombok.Data;
import wxs.common.com.BasePo;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author : imperater
 * @date : 2020/4/8
 */
@Data
public class UserLoginResVo extends BasePo {
    private String account;
    private String password;
    private String name;
    private String userType;
    private String  imageUrl;
    private String gender;
    private String telephone;
    private String mobile;
    private String mail;
    private String status;
    private Date birthday;
    private String idcard;
    private String address;
}
