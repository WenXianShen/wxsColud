package wxs.oauth.dao;

import org.apache.ibatis.annotations.Mapper;
import wxs.common.vo.user.UserReqVo;
import wxs.common.vo.user.UserResVo;

import java.util.List;

/**
 * @author : imperater
 * @date : 2020/1/3
 */
@Mapper
public interface UserMapper {
    public UserResVo getUserByNameAndPwd(UserReqVo user);
    public List<UserResVo> getUserListByVo(UserReqVo user);
}
