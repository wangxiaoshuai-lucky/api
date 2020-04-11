package com.kelab.api.controller.base;

import com.alibaba.fastjson.JSON;
import com.kelab.api.constant.AuthConstant;
import com.kelab.info.context.Context;
import feign.Response;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*",maxAge = 3600)
@SuppressWarnings("unchecked")
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

    public static class Param extends HashMap<String, Object> {
        public Param param(String key, Object value) {
            put(key, value);
            return this;
        }
    }
    public Param buildParam(Object... params) {
        Param contextParam = JSON.parseObject(JSON.toJSONString(getContext()), Param.class);
        if (params != null) {
            for (Object obj: params) {
                Map<String, Object> objectMap = JSON.parseObject(JSON.toJSONString(obj), Map.class);
                contextParam.putAll(objectMap);
            }
        }
        return contextParam;
    }

    protected Object download(Response response) {
        ResponseEntity<byte[]> result = null;
        InputStream inputStream = null;
        try {
            Response.Body body = response.body();
            inputStream = body.asInputStream();
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);
            HttpHeaders heads = new HttpHeaders();
            Collection<String> strings = response.headers().get(HttpHeaders.CONTENT_DISPOSITION);
            heads.add(HttpHeaders.CONTENT_DISPOSITION, StringUtils.join(strings, ";"));
            heads.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
            result = new ResponseEntity<>(b, heads, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

}
