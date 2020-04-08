package wxs.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wxs.admin.doman.service.RoleService;
import wxs.admin.vo.role.RoleReqVo;
import wxs.common.response.AppResult;
import wxs.common.response.Result;

/**
 * @author : imperater
 * @date : 2020/4/7
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/getRoleList")
    @ResponseBody
    public AppResult getRoleList(RoleReqVo roleReqVo){
        return Result.success(roleService.getRoleListByVo(roleReqVo));
    }
}
