package wxs.oauth.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wxs.common.vo.user.UserReqVo;
import wxs.common.vo.user.UserResVo;
import wxs.oauth.interceptor.SessionInterceptor;
import wxs.common.response.AppResult;
import wxs.common.response.Result;
import wxs.oauth.service.UserService;
import wxs.oauth.util.TokenUtil;


import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping(value="/login.do")
    @ResponseBody
    public AppResult loginDo(UserReqVo user , HttpServletRequest request) {
        String checkResult = this.check(user);
        if ("".equals(checkResult)) {//表示验证通过
            UserResVo users=userService.getUserByNameAndPwd(user);
            if (users!=null && users.getAccount().equals(user.getAccount())) {
                if (users.getPassword().equals(user.getPassword())) {
                    JSONObject JSON=new JSONObject();
                    //先添加到session
                    request.getSession().setAttribute("user", users);
                    request.getSession().setAttribute("token", TokenUtil.getToken().makeToken());
                    SessionInterceptor.optionMap.put(users.getId().toString(), request.getSession().getId());
                    JSON.put("user",users);
                    String json="[{ text: '基础设置',type: 'ios-paper',children: [{ type: 'ios-grid',name: 'userManage',text: '用户管理',},{ type: 'ios-grid',name: 'userAdd',text: '新增用户',hidden:true}{ type: 'ios-grid',name: 'menuManage',text: '菜单管理'}]}]";
                    JSON.put("token", request.getSession().getAttribute("token"));
                    JSON.put("menuList",JSONObject.parseArray(json));
                 return Result.success(JSON);
                }
                return Result.failed( "密码错误");
            }
            return Result.failed( "账号不存在");
        }else {
            return Result.failed(checkResult);
        }
    }
    @RequestMapping(value="/login")
    @ResponseBody
    public AppResult loginDo() { return Result.failed( "测试");
    }


    /**
     * 验证用户名密码格式是否正确    此处可扩展正则表达式
     * @param user
     * @return
     */
    public  String check(UserReqVo user){
        if(user.getAccount()==null || "".equals(user.getAccount())) {
            return "用户名不能为空";
        }else if(user.getPassword()==null || "".equals(user.getPassword())) {
            return "密码不能为空";
        }
        return "";
    }
}
