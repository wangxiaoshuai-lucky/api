package com.kelab.api.dal.dao;

import com.kelab.api.dal.model.ApiRoleAuthModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ApiRoleAuthModelMapper {

    ApiRoleAuthModel queryByRoleIdAndAuthId(@Param("roleId") Integer roleId, @Param("authId") Integer authId);
}
