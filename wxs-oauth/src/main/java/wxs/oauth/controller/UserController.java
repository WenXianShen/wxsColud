package wxs.oauth.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wxs.common.response.AppResult;
import wxs.common.response.Result;
import wxs.common.vo.user.UserReqVo;
import wxs.oauth.doman.service.UserService;

/**
 * @author : imperater
 * @date : 2020/1/14
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    //查询所有用户列表
    @RequestMapping(value="/getUserList")
    @ResponseBody
    public AppResult getUserListByVo(@RequestBody UserReqVo userVo){
        JSONObject jsonObject=new JSONObject();
        PageInfo pageInfo=userService.getUserListByVo(userVo);
        jsonObject.put("totolCount",pageInfo.getTotal());
        jsonObject.put("list",pageInfo.getList());
        return Result.success(jsonObject);
    }
}
