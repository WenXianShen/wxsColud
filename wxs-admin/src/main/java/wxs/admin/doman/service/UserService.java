package wxs.admin.doman.service;
import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import wxs.admin.doman.po.UserPo;
import wxs.admin.vo.user.UserReqVo;
import wxs.admin.vo.user.UserResVo;
import wxs.common.vo.UserLoginResVo;


/**
 * @author : imperater
 * @date : 2020/1/6
 */
public interface UserService extends IService<UserPo> {
    public UserLoginResVo getUserByNameAndPwd(UserReqVo user);
    public PageInfo getUserListByVo(UserReqVo user);
    public  void saveUser(UserReqVo userReqVo);
    public  void updateUser(UserReqVo userReqVo);
    public UserResVo getUserInfoById(String userId);
    public  void deleteUser(String userId);

}
