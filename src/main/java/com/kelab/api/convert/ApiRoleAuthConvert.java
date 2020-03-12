package com.kelab.api.convert;

import com.kelab.api.dal.domain.ApiAuthDomain;
import com.kelab.api.dal.domain.ApiRoleAuthDomain;
import com.kelab.api.dal.model.ApiAuthModel;
import com.kelab.api.dal.model.ApiRoleAuthModel;
import org.springframework.beans.BeanUtils;

public class ApiRoleAuthConvert {

    /**
     * model to domain
     */
    public static ApiRoleAuthDomain modelToDomain(ApiRoleAuthModel model) {
        if (model == null) {
            return null;
        }
        ApiRoleAuthDomain domain = new ApiRoleAuthDomain();
        BeanUtils.copyProperties(model, domain);
        return domain;
    }
}
