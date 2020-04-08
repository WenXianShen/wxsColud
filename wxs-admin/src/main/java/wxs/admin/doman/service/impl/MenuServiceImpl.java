package wxs.admin.doman.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxs.admin.vo.menu.MenuReqVo;
import wxs.admin.vo.menu.MenuResVo;
import wxs.admin.vo.user.UserReqVo;
import wxs.admin.doman.dao.MenuMapper;
import wxs.admin.doman.service.MenuService;

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
    public PageInfo getMenuList(MenuReqVo vo) {
        PageHelper.startPage(vo.getPager().getCurrentPage(),vo.getPager().getPagesize());
        List<MenuResVo> menuList = menuMapper.getMenuList(vo);
        PageInfo pageInfo = new PageInfo(menuList);
        return pageInfo;
    }

    @Override
    public List<MenuResVo> getUserMenuList(UserReqVo vo) {
        return menuMapper.getUserMenuList(vo);
    }
}
