package wxs.admin.doman.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxs.admin.doman.po.RolePo;
import wxs.admin.vo.role.RoleReqVo;
import wxs.admin.doman.dao.RoleMapper;
import wxs.admin.doman.service.RoleService;
import wxs.admin.vo.role.RoleResVo;

import java.util.List;


@Service
public class RoleServiceImpl  extends ServiceImpl<RoleMapper, RolePo> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageInfo getRoleListByVo(RoleReqVo roleReqVo) {
        if (roleReqVo.getPager().getCurrentPage() > 0 && roleReqVo.getPager().getPagesize() > 0) {
            PageHelper.startPage(roleReqVo.getPager().getCurrentPage(),roleReqVo.getPager().getPagesize());
        }
        List<RoleResVo> roleList = roleMapper.getRoleList(roleReqVo);
        PageInfo pageInfo = new PageInfo(roleList);
        return pageInfo;
    }
}
