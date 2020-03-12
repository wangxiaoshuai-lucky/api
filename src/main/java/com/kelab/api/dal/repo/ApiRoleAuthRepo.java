package com.kelab.api.dal.repo;

import com.kelab.api.dal.domain.ApiRoleAuthDomain;

public interface ApiRoleAuthRepo {

    ApiRoleAuthDomain queryByRoleIdAndUrl(Integer roleId, String url);
}
