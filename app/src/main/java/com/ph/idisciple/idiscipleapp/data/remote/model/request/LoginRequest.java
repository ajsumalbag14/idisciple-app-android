package com.ph.idisciple.idiscipleapp.data.remote.model.request;

import com.ph.idisciple.idiscipleapp.data.remote.model.base.BaseRequestMap;

public class LoginRequest extends BaseRequestMap {

    public void setEmail(String email) {
        mParams.put("email", email);
    }

    public void setPassword(String password) {
        mParams.put("password", password);
    }

}