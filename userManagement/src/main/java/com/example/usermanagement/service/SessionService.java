package com.example.usermanagement.service;

import com.alibaba.fastjson.JSONObject;

public interface SessionService {
    public void storeDataInSession(String key, String value);

    public String getDataFromSession(String key);
}
