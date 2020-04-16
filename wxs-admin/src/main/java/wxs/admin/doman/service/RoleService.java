package wxs.admin.doman.service;
import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import wxs.admin.doman.po.RolePo;
import wxs.admin.vo.role.RoleReqVo;
import wxs.admin.vo.role.RoleResVo;
import wxs.admin.vo.user.UserReqVo;

import java.util.List;


public interface RoleService extends IService<RolePo> {
    public PageInfo getRoleListByVo(RoleReqVo roleReqVo);
    public void saveRole(RoleReqVo roleReqVo);
    public void updateRole(RoleReqVo roleReqVo);
    public RoleResVo getRoleInfoByRoleId (String roleId);
    public List<RoleResVo> getUserRoleByUserId (String userId);
    public void updateUserRole (UserReqVo UserReqVo);
}
