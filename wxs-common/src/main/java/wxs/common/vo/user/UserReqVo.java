package wxs.common.vo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import wxs.common.com.BasePagerVo;

/**
 * @author : imperater
 * @date : 2020/1/14
 */
@Data
public class UserReqVo extends BasePagerVo {
    private String account;
    private String password;
    private String name;
}
