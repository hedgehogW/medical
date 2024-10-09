package com.example.usermanagement.service.impl;

import com.example.usermanagement.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void storeDataInSession(String key, String value) {

        Session session = sessionRepository.createSession();
        session.setAttribute(key, value);
        sessionRepository.save(session);
    }

    public String getDataFromSession(String key) {
        Session session = sessionRepository.findById(key);
        return session.getAttribute(key);
    }
}
