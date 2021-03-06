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
    
    AccountPo selectByCondition(Map<String, Object> condition);
    
    int insert(AccountPo po);
    
    int update(AccountPo po);
    
    int updatePasswordByUsername(@Param("username") String username, @Param("usernameType") String usernameType, @Param("password") String password);
    
    int updatePasswordById(@Param("id") int id, @Param("oldPassword") String oldPassword, @Param("newPassword") String newPassword);
    
    int count(Map<String, Object> condition);
    
}

