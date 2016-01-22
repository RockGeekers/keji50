package com.keji50.portal.dao.po;

import java.util.Date;
import lombok.Data;

/**
 * ClassName:BasePo
 *
 * @author   leslie
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年1月22日		下午1:02:07
 *
 * @see 	 
 */

@Data
public class BasePo {
    
    private String createBy;
    
    private Date createTime;
    
    private String updateBy;
    
    private Date updateTime;
    
}

