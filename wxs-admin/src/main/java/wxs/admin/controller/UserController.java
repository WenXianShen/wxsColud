package wxs.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wxs.admin.vo.user.UserReqVo;
import wxs.common.response.AppResult;
import wxs.common.response.Result;
import wxs.admin.doman.service.UserService;

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
        JSONObject jsonObject = new JSONObject();
        PageInfo pageInfo = userService.getUserListByVo(userVo);
        jsonObject.put("totolCount",pageInfo.getTotal());
        jsonObject.put("list",pageInfo.getList());
        return Result.success(jsonObject);
    }


    //新增用户
    @RequestMapping(value="/insertUser")
    @ResponseBody
    public AppResult insertUser(@RequestBody UserReqVo userVo){
        try{
            userService.saveUser(userVo);
        }catch (Exception e){
            return Result.failed("添加失败");
        }
        return Result.success("添加成功");
    }


    //修改用户信息
    @RequestMapping(value="/updateUser")
    @ResponseBody
    public AppResult updateUser(@RequestBody UserReqVo userVo){
        try{
            userService.updateUser(userVo);
        }catch (Exception e){
            return Result.failed("修改失败");
        }
        return Result.success("修改成功");
    }
}
