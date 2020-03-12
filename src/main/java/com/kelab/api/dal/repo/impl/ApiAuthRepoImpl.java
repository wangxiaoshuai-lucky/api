package com.kelab.api.dal.repo.impl;

import com.kelab.api.constant.enums.CacheConstant;
import com.kelab.api.convert.ApiAuthConvert;
import com.kelab.api.dal.dao.ApiAuthMapper;
import com.kelab.api.dal.domain.ApiAuthDomain;
import com.kelab.api.dal.model.ApiAuthModel;
import com.kelab.api.dal.redis.RedisCache;
import com.kelab.api.dal.repo.ApiAuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ApiAuthRepoImpl implements ApiAuthRepo {

    private final ApiAuthMapper apiAuthMapper;

    private final RedisCache redisCache;

    @Autowired(required = false)
    public ApiAuthRepoImpl(ApiAuthMapper apiAuthMapper, RedisCache redisCache) {
        this.apiAuthMapper = apiAuthMapper;
        this.redisCache = redisCache;
    }

    @Override
    public ApiAuthDomain queryByUrl(String url) {
        ApiAuthModel model = redisCache.cacheOne(CacheConstant.API_AUTH, url, ApiAuthModel.class, apiAuthMapper::queryByUrl);
        return ApiAuthConvert.modelToDomain(model);
    }
}
