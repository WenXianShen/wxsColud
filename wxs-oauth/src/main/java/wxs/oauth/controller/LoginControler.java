package wxs.oauth.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.util.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import wxs.common.vo.menu.MenuResVo;
import wxs.common.vo.user.UserReqVo;
import wxs.common.vo.user.UserResVo;
import wxs.oauth.doman.service.MenuService;
import wxs.oauth.interceptor.SessionInterceptor;
import wxs.common.response.AppResult;
import wxs.common.response.Result;
import wxs.oauth.doman.service.UserService;
import wxs.oauth.util.TokenUtil;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : imperater
 * @date : 2019/12/6
 */
@Controller
@RequestMapping("/WebLogin")
@Slf4j
public class LoginControler {

    /**
     * 用户登录
     * @param user
     * @param request
     * @return
     * 此处先验证用户名密码是否为空，然后在去数据库查询当前用户是否存在
     */

    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;

    @RequestMapping(value="/login.do")
    @ResponseBody
    public AppResult loginDo(UserReqVo user , HttpServletRequest request, BindingResult bindingResult) {

        if (!bindingResult.hasErrors()) {//表示验证通过
            UserResVo users = userService.getUserByNameAndPwd(user);
            if (users!=null && users.getAccount().equals(user.getAccount())) {
                if (users.getPassword().equals(user.getPassword())) {
                    JSONObject JSON=new JSONObject();
                    //先添加到session
                    request.getSession().setAttribute("user", users);
                    request.getSession().setAttribute("token", TokenUtil.getToken().makeToken());
                    SessionInterceptor.optionMap.put(users.getId().toString(), request.getSession().getId());
                    JSON.put("user",users);
                    UserReqVo reqVo = new UserReqVo();
                    reqVo.setId(users.getId());
                    //根据用户id对应的角色，获取菜单列表
                    List<MenuResVo> menuList = menuService.getUserMenuList(reqVo);
                 /*  String json="[{ text: '基础设置',type: 'ios-paper',children: [{ type: 'ios-grid',name: 'userManage',text: '用户管理',},{ type: 'ios-grid',name: 'userAdd',text: '新增用户',hidden:true}{ type: 'ios-grid',name: 'menuManage',text: '菜单管理'}]}]";
                       JSON.put("menuList",JSONObject.parseArray(json));*/
                    JSON.put("token", request.getSession().getAttribute("token"));
                    //这里将菜单列表组装成树
                    JSON.put("menuList",menuTree(menuList));

                 return Result.success(JSON);
                }
                return Result.failed( "密码错误");
            }
            return Result.failed( "账号不存在");
        }else {
            return Result.failed(bindingResult.getFieldError().getDefaultMessage());
        }
    }
    @RequestMapping(value="/login")
    @ResponseBody
    public AppResult loginDo() { return Result.failed( "测试");
    }

    /**
     * 组装菜单树
     */
    public   List<MenuResVo> menuTree(List<MenuResVo> menuList){

        List<MenuResVo> newMenuList = new ArrayList<MenuResVo>();
        menuList.forEach(dx ->{
            //先拿到一级菜单
            if (null == dx.getParentId()) {
                newMenuList.add(dx);
            }
        });
        //为一级菜单设置子菜单,getChild是递归调用的
        newMenuList.forEach(dx ->{
            dx.setChildren(getChild(dx.getId(),menuList));
        });
        return newMenuList;
    }

    /**
     * 递归查找子菜单
     *
     * @param id
     *            当前菜单id
     * @param rootMenu
     *            要查找的列表
     * @return
     */
    private List<MenuResVo> getChild(Long id, List<MenuResVo> rootMenu) {
        List<MenuResVo>childList = new ArrayList<MenuResVo>();
        /**
         * 遍历所有节点，将父节点Id与之比较
         */
        rootMenu.forEach(dx -> {
            if( null != dx.getParentId() && dx.getParentId().equals(id)){
                childList.add(dx);
            }
        });
       /* *//**
         * 子节点再次循环
         *//*
        childList.forEach(dx -> {
           getChild(dx.getId(),rootMenu);
        });*/
        //结束递归的条件
        if(childList.size() == 0){
            return  null;
        }
        return childList;
    }
}
