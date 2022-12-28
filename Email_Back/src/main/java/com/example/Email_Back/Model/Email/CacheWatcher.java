package com.example.Email_Back.Model.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
public class CacheWatcher extends Thread{

    private EmailCache cache;

    public CacheWatcher(EmailCache cache){
        this.cache = cache;
    }

    @Override
    public void run(){
        for (Map.Entry<String, CacheBlock> set : this.cache.getCache().entrySet()) {
            long tempTime = System.currentTimeMillis() - set.getValue().counter;
            if (tempTime > cache.max) {
                cache.max = tempTime;
                cache.toRemove = set.getValue().email.getId();
            }
        }
        System.out.println(cache.toRemove);
    }

}