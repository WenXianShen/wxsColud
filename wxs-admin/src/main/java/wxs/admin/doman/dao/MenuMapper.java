package wxs.admin.doman.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wxs.admin.doman.po.MenuPo;
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
public interface MenuMapper  extends BaseMapper<MenuPo> {
    public  List<MenuResVo> getUserMenuList(UserReqVo vo);
    public List<MenuResVo> getMenuList(MenuReqVo vo);
    public  MenuResVo getMenuByMenuId(@Param("id") String menuId);
    public  void deleteMenuByIds(List<String> ids);
}
