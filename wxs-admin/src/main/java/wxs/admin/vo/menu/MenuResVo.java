package wxs.admin.vo.menu;

import wxs.common.com.BaseVo;

import java.util.List;

/**
 * @author : imperater
 * @date : 2020/1/16
 */
public class MenuResVo extends BaseVo {
    // 菜单名称
    private String name;
    private String menuName;
    // 菜单级别
    private String lvl;

    // 路径
    private String text;
    //路由名称
    private  String routerPath;



    // 父菜单ID
    private Long parentId;
    private String parentName;

    // 显示顺序
    private Long displaySeq;
    //是否显示
    private  String isHidden;


    //菜单icon
    private  String type;

    public List<MenuResVo> getChildren() {
        return children;
    }

    public void setChildren(List<MenuResVo> children) {
        this.children = children;
    }

    private  Boolean hidden;
    public List<MenuResVo> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLvl() {
        return lvl;
    }

    public void setLvl(String lvl) {
        this.lvl = lvl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getDisplaySeq() {
        return displaySeq;
    }

    public void setDisplaySeq(Long displaySeq) {
        this.displaySeq = displaySeq;
    }

    public String getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(String isHidden) {
        if("0".equals(isHidden)){
            this.hidden=false;
        }else if("1".equals(isHidden)){
            this.hidden=true;
        }
        this.isHidden = isHidden;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }
    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    public String getRouterPath() {
        return routerPath;
    }

    public void setRouterPath(String routerPath) {
        this.routerPath = routerPath;
    }
}
