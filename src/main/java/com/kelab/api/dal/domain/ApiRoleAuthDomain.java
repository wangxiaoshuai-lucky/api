package com.kelab.api.dal.domain;

public class ApiRoleAuthDomain {
    private Integer id;

    private Integer roleId;

    private Integer authId;

    private ApiAuthDomain authDomain;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }

    public ApiAuthDomain getAuthDomain() {
        return authDomain;
    }

    public void setAuthDomain(ApiAuthDomain authDomain) {
        this.authDomain = authDomain;
    }
}
