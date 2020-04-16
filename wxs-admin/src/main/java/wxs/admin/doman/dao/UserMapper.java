package wxs.admin.doman.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wxs.admin.doman.po.UserPo;
import wxs.admin.vo.user.UserReqVo;
import wxs.admin.vo.user.UserResVo;
import wxs.common.vo.UserLoginResVo;


import java.util.List;

/**
 * @author : imperater
 * @date : 2020/1/3
 */
@Mapper
@Repository
public interface UserMapper  extends BaseMapper<UserPo> {
    public UserLoginResVo getUserByNameAndPwd(UserReqVo user);
    public List<UserResVo> getUserListByVo(UserReqVo user);
}
