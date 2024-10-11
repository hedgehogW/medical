package com.example.usermanagement.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.usermanagement.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    HttpServletRequest request;

    @Override
    public void storeDataInSession(String key, String value) {
        //第一步：获取session
        HttpSession session = request.getSession();
//        Session session = sessionRepository.createSession();
        session.setAttribute(key, value);
//        sessionRepository.save(session);
    }

    public String getDataFromSession(String key) {
//        Session session = sessionRepository.findById(key);
        HttpSession session = request.getSession();
        return (String) session.getAttribute(key);
    }
}
