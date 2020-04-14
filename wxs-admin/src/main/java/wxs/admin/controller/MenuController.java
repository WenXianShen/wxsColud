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
import wxs.admin.vo.menu.MenuResVo;
import wxs.common.response.AppResult;
import wxs.common.response.Result;

import wxs.admin.doman.service.MenuService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
            List<String> ideList = JSON.parseArray(ids, String.class);
            menuService.deleteMenu(ideList);
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

    /**
     * 分配菜单(获取全部菜单tree，以及选中角色所对应的菜单列表)
     */
    @RequestMapping("/getMenuTreeByRoleId")
    @ResponseBody
    public AppResult getMenuTreeByRoleId(String roleId){
        JSONObject jsonObject = new JSONObject();
        // 这里获取全部的菜单tree
        PageInfo menuList = menuService.getMenuList(null);
        List<MenuResVo> allMenulist = menuService.menuTree(menuList.getList());
        //获取用户所对应的菜单
        List<MenuResVo> roleMenuList = menuService.getMenuListByRoleId(roleId);
        jsonObject.put("menuTree",allMenulist);
        //将除一级目录全部筛选出来
        List<MenuResVo> allMenuList =  menuList.getList();
        allMenuList = allMenuList.stream().filter(m -> !m.getLvl().equals("0")).collect(Collectors.toList());
        jsonObject.put("allMenuList",allMenuList);
        jsonObject.put("roleMenuList",roleMenuList);
        return Result.success(jsonObject);
    }

    /**
     * 给角色分配菜单
     */
    @RequestMapping("/updateRoleMenuListByRoleId")
    @ResponseBody
    public AppResult updateRoleMenuListByRoleId(@RequestBody  MenuReqVo menuReqVo){
        try {
           menuService.updateRoleMenuList(menuReqVo);
        }catch (Exception e){
            log.error("给roleId为{}的角色分配菜单时报错:{}",menuReqVo.getId(),menuReqVo.getMenuList().toString());
            return Result.failed("保存失败!");
        }
        return Result.success("保存成功!");
    }

}
