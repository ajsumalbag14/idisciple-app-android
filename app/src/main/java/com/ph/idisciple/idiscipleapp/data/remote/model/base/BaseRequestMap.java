package com.ph.idisciple.idiscipleapp.data.remote.model.base;

import java.util.HashMap;
import java.util.Map;

public class BaseRequestMap {
    public Map<String, String> mParams = new HashMap<>();

    public Map<String, String> getParams() {
        return mParams;
    }
}
