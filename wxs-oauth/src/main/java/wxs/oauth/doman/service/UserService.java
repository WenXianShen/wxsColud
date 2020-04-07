package wxs.oauth.doman.service;
import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import wxs.common.po.UserPo;
import wxs.common.vo.user.UserReqVo;
import wxs.common.vo.user.UserResVo;

/**
 * @author : imperater
 * @date : 2020/1/6
 */
public interface UserService extends IService<UserPo> {
    public UserResVo getUserByNameAndPwd(UserReqVo user);
    public PageInfo getUserListByVo(UserReqVo user);
    public  void saveUser(UserReqVo userReqVo);
}
