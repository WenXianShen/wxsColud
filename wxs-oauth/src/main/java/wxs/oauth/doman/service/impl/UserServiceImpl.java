package wxs.oauth.doman.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import wxs.common.po.UserPo;
import wxs.common.vo.user.UserReqVo;
import wxs.common.vo.user.UserResVo;
import wxs.oauth.doman.dao.UserMapper;
import wxs.oauth.doman.service.UserService;

import java.util.List;

/**
 * @author : imperater
 * @date : 2020/1/6
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,UserPo> implements UserService {
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUser(UserReqVo userReqVo) {
        UserPo userPo = new UserPo();
        BeanUtils.copyProperties(userReqVo,userPo);
        //执行保存
        this.insert(userPo);
    }
}
