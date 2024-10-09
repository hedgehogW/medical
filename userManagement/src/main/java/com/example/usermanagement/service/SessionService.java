package com.example.usermanagement.service;

public interface SessionService {
    public void storeDataInSession(String key, String value);

    public String getDataFromSession(String key);
}
