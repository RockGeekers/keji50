package com.keji50.portal.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.keji50.portal.dao.po.AccountValidatePo;

/**
 * ClassName:AccountValidateMapper
 *
 * @author   sophia
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年1月22日		下午10:46:48
 *
 * @see 	 
 */
public interface AccountValidatePoMapper {

    int insert(AccountValidatePo po);
    
    AccountValidatePo selectById(@Param("id") int id);
}

