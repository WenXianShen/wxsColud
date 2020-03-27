package wxs.oauth.doman.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wxs.common.vo.user.UserReqVo;
import wxs.common.vo.user.UserResVo;

import java.util.List;

/**
 * @author : imperater
 * @date : 2020/1/3
 */
@Mapper
@Repository
public interface UserMapper {
    public UserResVo getUserByNameAndPwd(UserReqVo user);
    public List<UserResVo> getUserListByVo(UserReqVo user);
}