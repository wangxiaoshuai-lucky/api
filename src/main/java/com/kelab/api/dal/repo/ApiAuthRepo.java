package com.kelab.api.dal.repo;

import com.kelab.api.dal.domain.ApiAuthDomain;

public interface ApiAuthRepo {

    ApiAuthDomain queryByUrl(String url);
}
