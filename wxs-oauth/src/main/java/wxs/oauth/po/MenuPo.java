package wxs.oauth.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import wxs.common.com.BaseVo;

/**
 * @author : imperater
 * @date : 2020/1/3
 */
@Data
@AllArgsConstructor
public class MenuPo extends BaseVo {
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
