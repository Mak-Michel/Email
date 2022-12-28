package com.example.Email_Back.Model.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
public class CacheWatcher extends Thread{

    private HashMap<String, CacheBlock> cache;

    public CacheWatcher(HashMap<String, CacheBlock> cache){
        this.cache = cache;
    }

    @Override
    public void run(){
        long max = 0;
        String toRemove = "";
        for (Map.Entry<String, CacheBlock> set : this.cache.entrySet()) {
            long tempTime = System.currentTimeMillis() - set.getValue().counter;
            if (tempTime > max) {
                max = tempTime;
                toRemove = set.getKey();
            }
        }
        System.out.println("Removing " + toRemove + "\tSize: " + this.cache.size());
        this.cache.remove(toRemove);
    }

}
