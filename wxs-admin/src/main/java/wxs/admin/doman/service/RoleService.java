package wxs.admin.doman.service;
import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import wxs.admin.doman.po.RolePo;
import wxs.admin.vo.role.RoleReqVo;


public interface RoleService extends IService<RolePo> {
    public PageInfo getRoleListByVo(RoleReqVo roleReqVo);
}
