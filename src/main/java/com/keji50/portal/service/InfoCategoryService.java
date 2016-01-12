package com.keji50.portal.service;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import com.keji50.portal.dao.po.InfoCategoryPo;

/**
 * 文章目录业务service
 *
 * @author chao.li
 * @version
 * @since Ver 1.1
 * @Date 2015年12月9日 下午3:34:50
 *
 * @see
 */
@Service(value = "infoCategoryService")
public class InfoCategoryService {

    @Setter
    @Getter
    private volatile List<InfoCategoryPo> infoCatetories;

}
