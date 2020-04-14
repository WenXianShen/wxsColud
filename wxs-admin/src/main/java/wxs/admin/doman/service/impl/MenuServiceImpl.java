package wxs.admin.doman.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wxs.admin.doman.dao.RoleMenuMapper;
import wxs.admin.doman.po.MenuPo;
import wxs.admin.doman.po.RoleMenuPo;
import wxs.admin.vo.menu.MenuReqVo;
import wxs.admin.vo.menu.MenuResVo;
import wxs.admin.vo.user.UserReqVo;
import wxs.admin.doman.dao.MenuMapper;
import wxs.admin.doman.service.MenuService;

import java.util.ArrayList;
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

    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Override
    public PageInfo getMenuList(MenuReqVo vo) {
        if (null != vo) {
            if (vo.getPager().getCurrentPage() > 0 && vo.getPager().getPagesize() > 0) {
                PageHelper.startPage(vo.getPager().getCurrentPage(),vo.getPager().getPagesize());
            }
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

    /**
     * 组装菜单树
     */
    @Override
    public   List<MenuResVo> menuTree(List<MenuResVo> menuList){

        List<MenuResVo> newMenuList = new ArrayList<MenuResVo>();
        menuList.forEach(dx ->{
            //先拿到一级菜单
            if (null == dx.getParentId()) {
                newMenuList.add(dx);
            }
        });
        //为一级菜单设置子菜单,getChild是递归调用的
        newMenuList.forEach(dx ->{
            dx.setChildren(getChild(dx.getId(),menuList));
        });
        return newMenuList;
    }

    @Override
    public List<MenuResVo> getMenuListByRoleId(String roleId) {
        return menuMapper.getMenuListByRoleId(roleId);
    }

    /**
     * 给角色分配菜单
     * @param id
     * @param menus
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRoleMenuList(MenuReqVo menuReqVo) {
        //先将之前角色对应的菜单记录全部删除，然后将新菜单列表保存进来
        roleMenuMapper.deleteRoleMenuByRoles(menuReqVo.getId().toString());
        List<RoleMenuPo> roleMenuPos = new ArrayList<RoleMenuPo>();
        if(menuReqVo.getMenuList().size() > 0 ){
            menuReqVo.getMenuList().stream().forEach(dx -> {
                RoleMenuPo roleMenuPo = new RoleMenuPo();
                roleMenuPo.setRoleId(menuReqVo.getId());
                roleMenuPo.setMenuId(Long.valueOf(dx));
                roleMenuPos.add(roleMenuPo);
            });
            //执行批量新增
            roleMenuMapper.saveRoleMenuList(roleMenuPos);
        }
    }

    /**
     * 递归查找子菜单
     *
     * @param id
     *            当前菜单id
     * @param rootMenu
     *            要查找的列表
     * @return
     */
    private List<MenuResVo> getChild(Long id, List<MenuResVo> rootMenu) {
        List<MenuResVo>childList = new ArrayList<MenuResVo>();
        /**
         * 遍历所有节点，将父节点Id与之比较
         */
        rootMenu.forEach(dx -> {
            if( null != dx.getParentId() && dx.getParentId().equals(id)){
                childList.add(dx);
            }
        });
        /**
         * 子节点再次循环
         */
        childList.forEach(dx -> {
            getChild(dx.getId(),rootMenu);
        });
        //结束递归的条件
        if(childList.size() == 0){
            return  null;
        }
        return childList;
    }
}
