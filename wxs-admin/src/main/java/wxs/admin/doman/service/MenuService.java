package wxs.admin.doman.service;
import com.github.pagehelper.PageInfo;
import wxs.admin.vo.menu.MenuReqVo;
import wxs.admin.vo.menu.MenuResVo;
import wxs.admin.vo.user.UserReqVo;


import java.util.List;

/**
 * @author : imperater
 * @date : 2020/1/6
 */
public interface MenuService {
    public PageInfo getMenuList(MenuReqVo vo);
    public List<MenuResVo> getUserMenuList(UserReqVo vo);
}
