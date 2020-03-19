package wxs.oauth.doman.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxs.common.vo.menu.MenuResVo;
import wxs.common.vo.user.UserReqVo;
import wxs.common.vo.user.UserResVo;
import wxs.oauth.doman.dao.MenuMapper;
import wxs.oauth.doman.dao.UserMapper;
import wxs.oauth.doman.service.MenuService;
import wxs.oauth.doman.service.UserService;

import java.util.List;

/**
 * @author : imperater
 * @date : 2020/1/6
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<MenuResVo> getUserMenuList(UserReqVo vo) {
        return menuMapper.getUserMenuList(vo);
    }
}
