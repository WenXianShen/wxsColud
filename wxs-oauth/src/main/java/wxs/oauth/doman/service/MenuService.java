package wxs.oauth.doman.service;
import com.github.pagehelper.PageInfo;
import wxs.common.vo.menu.MenuReqVo;
import wxs.common.vo.menu.MenuResVo;
import wxs.common.vo.user.UserReqVo;
import wxs.common.vo.user.UserResVo;

import java.util.List;

/**
 * @author : imperater
 * @date : 2020/1/6
 */
public interface MenuService {
    public PageInfo getMenuList(MenuReqVo vo);
    public List<MenuResVo> getUserMenuList(UserReqVo vo);
}
