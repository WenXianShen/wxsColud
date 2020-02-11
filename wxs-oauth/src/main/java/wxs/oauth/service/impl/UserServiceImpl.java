package wxs.oauth.service.impl;


import com.github.pagehelper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxs.common.vo.user.UserReqVo;
import wxs.common.vo.user.UserResVo;
import wxs.oauth.dao.UserMapper;
import wxs.oauth.service.UserService;

import java.util.List;

/**
 * @author : imperater
 * @date : 2020/1/6
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserResVo getUserByNameAndPwd(UserReqVo user) {
        return userMapper.getUserByNameAndPwd(user);
    }

    @Override
    public PageInfo getUserListByVo(UserReqVo user) {
        PageHelper.startPage(user.getPager().getCurrentPage(),user.getPager().getPagesize());
        List<UserResVo> userList = userMapper.getUserListByVo(user);
        PageInfo pageInfo = new PageInfo(userList);
        return pageInfo;
    }
}
