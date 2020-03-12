package com.kelab.api.dal.repo.impl;

import com.kelab.api.constant.enums.CacheConstant;
import com.kelab.api.convert.ApiRoleAuthConvert;
import com.kelab.api.dal.dao.ApiRoleAuthModelMapper;
import com.kelab.api.dal.domain.ApiAuthDomain;
import com.kelab.api.dal.domain.ApiRoleAuthDomain;
import com.kelab.api.dal.model.ApiRoleAuthModel;
import com.kelab.api.dal.redis.RedisCache;
import com.kelab.api.dal.repo.ApiAuthRepo;
import com.kelab.api.dal.repo.ApiRoleAuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ApiRoleAuthRepoImpl implements ApiRoleAuthRepo {


    private ApiRoleAuthModelMapper apiRoleAuthModelMapper;

    private ApiAuthRepo apiAuthRepo;

    private RedisCache redisCache;

    @Autowired(required = false)
    public ApiRoleAuthRepoImpl(ApiRoleAuthModelMapper apiRoleAuthModelMapper, ApiAuthRepo apiAuthRepo, RedisCache redisCache) {
        this.apiRoleAuthModelMapper = apiRoleAuthModelMapper;
        this.apiAuthRepo = apiAuthRepo;
        this.redisCache = redisCache;
    }

    @Override
    public ApiRoleAuthDomain queryByRoleIdAndUrl(Integer roleId, String url) {
        ApiAuthDomain authDomain = apiAuthRepo.queryByUrl(url);
        if (authDomain == null) {
            return null;
        }
        ApiRoleAuthModel apiRoleAuthModel = redisCache.cacheOne(CacheConstant.API_ROLE
                , buildCacheKey(roleId, authDomain.getId()), ApiRoleAuthModel.class
                , missKey -> apiRoleAuthModelMapper.queryByRoleIdAndAuthId(roleId, authDomain.getId()));
        ApiRoleAuthDomain apiRoleAuthDomain = ApiRoleAuthConvert.modelToDomain(apiRoleAuthModel);
        if (apiRoleAuthDomain == null) {
            return null;
        } else {
            apiRoleAuthDomain.setAuthDomain(authDomain);
        }
        return apiRoleAuthDomain;
    }

    private String buildCacheKey(Integer roleId, Integer authId) {
        return String.format("%s::%s", roleId, authId);
    }
}
