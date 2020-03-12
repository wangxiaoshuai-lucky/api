package com.kelab.api.convert;

import com.kelab.api.dal.domain.ApiAuthDomain;
import com.kelab.api.dal.model.ApiAuthModel;
import org.springframework.beans.BeanUtils;

public class ApiAuthConvert {

    /**
     * model to domain
     */
    public static ApiAuthDomain modelToDomain(ApiAuthModel model) {
        if (model == null) {
            return null;
        }
        ApiAuthDomain domain = new ApiAuthDomain();
        BeanUtils.copyProperties(model, domain);
        return domain;
    }
}
