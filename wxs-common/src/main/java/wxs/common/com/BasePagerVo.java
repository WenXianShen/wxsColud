package wxs.common.com;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 支持翻页功能的VO基础类
 *
 
 * @since org.ebiz.common.vo.BaseVo
 */
@Data
public class BasePagerVo extends BaseVo {

    // serialVersionUID
    private static final long serialVersionUID = 2115510158079758308L;
    /**
     * 分页
     */
    private Pagination pager = new Pagination();

}
