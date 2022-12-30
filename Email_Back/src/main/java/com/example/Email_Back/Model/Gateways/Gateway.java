package com.example.Email_Back.Model.Gateways;

import com.example.Email_Back.Model.Caches.Cacheable;

public interface Gateway {

    Cacheable load(String id);

    void save(Cacheable cacheObject);

    void delete(String id);
}
