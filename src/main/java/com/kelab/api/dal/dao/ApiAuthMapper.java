package com.kelab.api.dal.dao;

import com.kelab.api.dal.model.ApiAuthModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ApiAuthMapper {

    ApiAuthModel queryByUrl(@Param("url") String url);
}
