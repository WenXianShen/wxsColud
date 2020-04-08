package wxs.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wxs.admin.vo.menu.MenuReqVo;
import wxs.common.response.AppResult;
import wxs.common.response.Result;

import wxs.admin.doman.service.MenuService;

/**
 * @author : imperater
 * @date : 2020/4/7
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 查询菜单列表
     */
    @RequestMapping("/getMenuList")
    @ResponseBody
    public AppResult getMenuList(@RequestBody MenuReqVo menuReqVo){
        JSONObject jsonObject = new JSONObject();
        PageInfo pageInfo = menuService.getMenuList(menuReqVo);
        jsonObject.put("totolCount",pageInfo.getTotal());
        jsonObject.put("list",pageInfo.getList());
        return Result.success(jsonObject);
    }
}
