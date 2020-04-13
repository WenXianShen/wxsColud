package wxs.admin.doman.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wxs.admin.doman.po.MenuPo;
import wxs.admin.vo.menu.MenuReqVo;
import wxs.admin.vo.menu.MenuResVo;
import wxs.admin.vo.user.UserReqVo;
import wxs.admin.doman.dao.MenuMapper;
import wxs.admin.doman.service.MenuService;

import java.util.Date;
import java.util.List;

/**
 * @author : imperater
 * @date : 2020/1/6
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuPo> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public PageInfo getMenuList(MenuReqVo vo) {
        if (vo.getPager().getCurrentPage() > 0 && vo.getPager().getPagesize() > 0) {
            PageHelper.startPage(vo.getPager().getCurrentPage(),vo.getPager().getPagesize());
        }
        List<MenuResVo> menuList = menuMapper.getMenuList(vo);
        PageInfo pageInfo = new PageInfo(menuList);
        return pageInfo;
    }

    @Override
    public List<MenuResVo> getUserMenuList(UserReqVo vo) {
        return menuMapper.getUserMenuList(vo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveMenu(MenuReqVo menuReqVo) {
        MenuPo menuPo = new MenuPo();
        BeanUtils.copyProperties(menuReqVo,menuPo);
        menuMapper.insert(menuPo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMenu(MenuReqVo menuReqVo) {
        MenuPo menuPo = new MenuPo();
        menuReqVo.setUpdateTime(new Date());
        BeanUtils.copyProperties(menuReqVo,menuPo);
        menuMapper.updateById(menuPo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMenu(List<String> ids) {
        menuMapper.deleteMenuByIds(ids);
    }

    @Override
    public MenuResVo getMenuInfoByMenuId(String menuId) {
        return  menuMapper.getMenuByMenuId(menuId);
    }
}
