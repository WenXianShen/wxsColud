package wxs.admin.vo.menu;

import lombok.Data;
import wxs.common.com.BasePagerVo;

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
    private String menuPath;

    // 父菜单ID
    private Long parentId;

    // 显示顺序
    private Long displaySeq;
}
