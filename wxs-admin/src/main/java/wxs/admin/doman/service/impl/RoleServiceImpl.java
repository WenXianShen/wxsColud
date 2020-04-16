package wxs.admin.doman.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wxs.admin.doman.dao.UserRoleMapper;
import wxs.admin.doman.po.RolePo;
import wxs.admin.doman.po.UserRolePo;
import wxs.admin.vo.role.RoleReqVo;
import wxs.admin.doman.dao.RoleMapper;
import wxs.admin.doman.service.RoleService;
import wxs.admin.vo.role.RoleResVo;
import wxs.admin.vo.user.UserReqVo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class RoleServiceImpl  extends ServiceImpl<RoleMapper, RolePo> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public PageInfo getRoleListByVo(RoleReqVo roleReqVo) {
        if (roleReqVo.getPager().getCurrentPage() > 0 && roleReqVo.getPager().getPagesize() > 0) {
            PageHelper.startPage(roleReqVo.getPager().getCurrentPage(),roleReqVo.getPager().getPagesize());
        }
        List<RoleResVo> roleList = roleMapper.getRoleList(roleReqVo);
        PageInfo pageInfo = new PageInfo(roleList);
        return pageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRole(RoleReqVo roleReqVo) {
        RolePo rolePo = new RolePo();
        BeanUtils.copyProperties(roleReqVo,rolePo);
        roleMapper.insert(rolePo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(RoleReqVo roleReqVo) {
        RolePo rolePo = new RolePo();
        BeanUtils.copyProperties(roleReqVo,rolePo);
        rolePo.setUpdateTime(new Date());
        roleMapper.updateById(rolePo);
    }

    @Override
    public RoleResVo getRoleInfoByRoleId(String roleId) {
        return roleMapper.getRoleInfoByRoleId(roleId);
    }

    @Override
    public List<RoleResVo> getUserRoleByUserId(String userId) {
        return roleMapper.getUserRoleByUserId(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserRole(UserReqVo userReqVo) {
        List<UserRolePo> userRolePoList = new ArrayList<>();
        userRoleMapper.deleteUserRoleMapping(String.valueOf(userReqVo.getId()));
        if (userReqVo.getRoleIds().size() > 0 ) {
            userReqVo.getRoleIds().stream().forEach( dx ->{
                UserRolePo userRolePo = new UserRolePo();
                userRolePo.setUserId(userReqVo.getId());
                userRolePo.setRoleId(Long.valueOf(dx));
                userRolePoList.add(userRolePo);
            });
            userRoleMapper.saveUserRoleMapping(userRolePoList);
        }
    }
}
