package wxs.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wxs.admin.vo.menu.MenuReqVo;
import wxs.common.response.AppResult;
import wxs.common.response.Result;

import wxs.admin.doman.service.MenuService;

import java.util.List;

/**
 * @author : imperater
 * @date : 2020/4/7
 */
@Controller
@RequestMapping("/menu")
@Slf4j
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

    /**
     * 添加菜单
     */
    @RequestMapping("/saveMenu")
    @ResponseBody
    public AppResult saveMenu(@RequestBody MenuReqVo menuReqVo){
        try {
            menuService.saveMenu(menuReqVo);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加菜单时报错:{}",e.getMessage());
            return Result.failed("添加失败");
        }
        return Result.success("添加成功");
    }


    /**
     * 修改菜单
     */
    @RequestMapping("/updateMenu")
    @ResponseBody
    public AppResult updateMenu(@RequestBody MenuReqVo menuReqVo){
        try {
            menuService.updateMenu(menuReqVo);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("修改菜单时报错:{}",e.getMessage());
            return Result.failed("修改失败");
        }
        return Result.success("修改成功");
    }

    /**
     * 删除菜单
     */
    @RequestMapping("/deleteMenu")
    @ResponseBody
    public AppResult deleteMenu(@RequestBody String ids){
        try {
            List<String> idList = JSON.parseArray(ids,String.class);
            menuService.deleteMenu(idList);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("批量删除菜单时报错:{}",e.getMessage());
            return Result.failed("删除失败");
        }
        return Result.success("删除成功");
    }

    /**
     * 获取菜单详情
     *   },
     */
    @RequestMapping("/getMenuInfoByMenuId")
    @ResponseBody
    public AppResult getMenuInfoByMenuId(String id){
        return Result.success(menuService.getMenuInfoByMenuId(id));
    }
}
