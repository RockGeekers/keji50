package com.keji50.portal.dao.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.keji50.portal.dao.po.AccountPo;

/**
 * 作者信息持久层接口
 *
 * @author   chao.li
 * @version  
 * @since    Ver 1.1
 * @Date	 2015年12月12日		下午2:03:32
 *
 * @see 	 
 */
public interface AccountPoMapper {

    AccountPo selectById(int id);
    
    AccountPo selectByPassword(@Param("username") String username, @Param("password") String password);
    
    int insert(AccountPo po);
    
    int update(AccountPo po);
    
    int count(Map<String, Object> conditions);
    
}

