package wxs.common.vo.user;

import lombok.Data;
import wxs.common.com.BasePagerVo;
import wxs.common.com.BaseVo;

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
        private Date birthday;
        private String idcard;
        private String address;
        private String userType;
}
