package wxs.admin.vo.user;

import lombok.Data;
import wxs.common.com.BasePagerVo;

import java.util.Date;

/**
 * @author : imperater
 * @date : 2020/1/14
 */
@Data
public class UserResVo extends BasePagerVo {
        private String account;
        private String password;
        private String name;
        private String  imageUrl;
        private String gender;
        private String telephone;
        private String mobile;
        private String mail;
        private String status;
        private String address;
        private String userType;
        private String userAccount;
        private String userNameZh;
        private Long roleId;
        private String roleName;
}
