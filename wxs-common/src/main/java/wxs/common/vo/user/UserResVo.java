package wxs.common.vo.user;

import lombok.Data;

import java.util.Date;

/**
 * @author : imperater
 * @date : 2020/1/14
 */
@Data
public class UserResVo {
        private String id;
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
}
