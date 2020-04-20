package wxs.admin.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wxs.admin.doman.service.MenuService;
import wxs.admin.doman.service.UserService;
import wxs.admin.vo.menu.MenuResVo;
import wxs.admin.vo.user.UserReqVo;
import wxs.common.response.AppResult;
import wxs.common.response.Result;
import wxs.common.util.RedisHelper;
import wxs.admin.util.TokenUtil;
import wxs.common.vo.UserLoginResVo;
import wxs.oauth.interceptor.SessionInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : imperater
 * @date : 2019/12/6
 */
@Controller
@RequestMapping("/WebLogin")
@Slf4j
public class LoginControler {

    @Autowired
    private RedisHelper redisHelper;
    /**
     * token过期时间
     */
    private long time = 60*60*24;

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
    public AppResult loginDo(@Valid UserReqVo user , HttpServletRequest request, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {//表示验证通过
            UserLoginResVo users = userService.getUserByNameAndPwd(user);
            if (users!=null) {
                    JSONObject JSON=new JSONObject();
                        String token = TokenUtil.getToken().makeToken();;  //令牌
                        redisHelper.set(token,users,time);
                        redisHelper.set(users.getId().toString(),token);
                        JSON.put("user",users);
                        UserReqVo reqVo = new UserReqVo();
                        reqVo.setId(users.getId());
                        //根据用户id对应的角色，获取菜单列表
                        List<MenuResVo> menuList = menuService.getUserMenuList(reqVo);
                 /*  String json="[{ text: '基础设置',type: 'ios-paper',children: [{ type: 'ios-grid',name: 'userManage',text: '用户管理',},{ type: 'ios-grid',name: 'userAdd',text: '新增用户',hidden:true}{ type: 'ios-grid',name: 'menuManage',text: '菜单管理'}]}]";
                       JSON.put("menuList",JSONObject.parseArray(json));*/
                        JSON.put("token",token);
                        //这里将菜单列表组装成树
                        JSON.put("menuList",menuService.menuTree(menuList));
                 return Result.success(JSON);
                }
            return Result.failed( "用户名或密码错误");
        }else {
            return Result.failed(bindingResult.getFieldError().getDefaultMessage());
        }
    }

    /**
     * 退出登录
     * @param user
     * @return
     */
    @RequestMapping(value="/loginOut.do")
    @ResponseBody
    public AppResult loginOut(@RequestBody UserReqVo user) {
        try{
            String userToken = (String) redisHelper.get(user.getId().toString());
            redisHelper.del(userToken);
            redisHelper.del(user.getId().toString());
        }catch (Exception e){
            e.printStackTrace();
            return  Result.success( "退出失败!");
        }
        return  Result.success( "退出成功!");
    }


}
