package com.example.Email_Back.Model.Caches.Threads;

import com.example.Email_Back.Model.Caches.CacheBlock;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

public class FreeCacheSpace{

    public void free(HashMap<String, CacheBlock> cache){
        Queue<CacheBlock> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a.counter));
        for(HashMap.Entry<String, CacheBlock> set : cache.entrySet())
            pq.add(set.getValue());
        int counter = 20;
        while (counter != 0 && !pq.isEmpty()){
            CacheBlock front = pq.poll();
            if(!front.dirty) {
                cache.remove(front.cachedObject.getId());
                counter--;
            }
        }
    }
}
