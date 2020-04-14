package wxs.admin.vo.menu;

import lombok.Data;
import wxs.common.com.BasePagerVo;

import java.util.List;

/**
 * @author : imperater
 * @date : 2020/1/16
 */
@Data
public class MenuReqVo extends BasePagerVo {
    // 菜单名称
    private String menuName;

    // 菜单级别
    private String lvl;

    // 路径
    private String routerPath;

    // 父菜单ID
    private Long parentId;

    // 显示顺序
    private Long displaySeq;

    //是否显示
    private  String isHidden;

    //菜单icon
    private  String type;

    private List<String>  menuList;
}
