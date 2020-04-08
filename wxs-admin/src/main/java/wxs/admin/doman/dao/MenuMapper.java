package wxs.admin.doman.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wxs.admin.vo.menu.MenuReqVo;
import wxs.admin.vo.menu.MenuResVo;
import wxs.admin.vo.user.UserReqVo;


import java.util.List;

/**
 * @author : imperater
 * @date : 2020/1/3
 */
@Mapper
@Repository
public interface MenuMapper {
    public  List<MenuResVo> getUserMenuList(UserReqVo vo);
    public List<MenuResVo> getMenuList(MenuReqVo vo);
}
