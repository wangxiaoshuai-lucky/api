package com.kelab.api.controller.base;

import com.kelab.api.constant.AuthConstant;
import com.kelab.info.context.Context;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseController {

    @Autowired(required = false)
    protected HttpServletRequest request;

    @Autowired(required = false)
    protected HttpServletResponse response;

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setContext(Context context) {
        request.setAttribute(AuthConstant.CONTEXT_KEY, context);
    }

    public Context getContext() {
        return ((Context) request.getAttribute(AuthConstant.CONTEXT_KEY));
    }
}
