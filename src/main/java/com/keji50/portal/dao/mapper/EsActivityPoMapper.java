package com.keji50.portal.dao.mapper;

import com.keji50.portal.dao.po.EsActivityPo;
import com.keji50.portal.dao.po.EsActivityPoWithBLOBs;

public interface EsActivityPoMapper {
    int deleteByPrimaryKey(String id);

    int insert(EsActivityPoWithBLOBs record);

    int insertSelective(EsActivityPoWithBLOBs record);

    EsActivityPoWithBLOBs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EsActivityPoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(EsActivityPoWithBLOBs record);

    int updateByPrimaryKey(EsActivityPo record);
}