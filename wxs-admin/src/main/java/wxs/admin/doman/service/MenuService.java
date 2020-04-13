package wxs.admin.doman.service;
import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import wxs.admin.doman.po.MenuPo;
import wxs.admin.vo.menu.MenuReqVo;
import wxs.admin.vo.menu.MenuResVo;
import wxs.admin.vo.user.UserReqVo;


import java.util.List;

/**
 * @author : imperater
 * @date : 2020/1/6
 */
public interface MenuService   extends IService<MenuPo> {
    public PageInfo getMenuList(MenuReqVo vo);
    public List<MenuResVo> getUserMenuList(UserReqVo vo);
    public  void saveMenu(MenuReqVo menuReqVo);
    public  void updateMenu(MenuReqVo menuReqVo);
    public  void deleteMenu(List<String> ids);
    public  MenuResVo getMenuInfoByMenuId(String menuId);
}
