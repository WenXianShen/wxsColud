package wxs.oauth.doman.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wxs.common.vo.menu.MenuReqVo;
import wxs.common.vo.menu.MenuResVo;
import wxs.common.vo.user.UserReqVo;
import wxs.common.vo.user.UserResVo;

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
