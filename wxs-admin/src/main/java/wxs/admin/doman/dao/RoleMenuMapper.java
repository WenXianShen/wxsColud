package wxs.admin.doman.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wxs.admin.doman.po.RoleMenuPo;
import wxs.admin.doman.po.RolePo;
import wxs.admin.vo.role.RoleReqVo;
import wxs.admin.vo.role.RoleResVo;

import java.util.List;

/**
 * @author : imperater
 * @date : 2020/1/3
 */
@Mapper
@Repository
public interface RoleMenuMapper {
    public void deleteRoleMenuByRoles(@Param("roleId") String roleId);
    public void saveRoleMenuList(List<RoleMenuPo>roleMenuPoList);
}
