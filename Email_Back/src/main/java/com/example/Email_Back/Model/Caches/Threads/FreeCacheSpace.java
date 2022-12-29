package com.example.Email_Back.Model.Caches.Threads;

import com.example.Email_Back.Model.Caches.CacheBlock;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

public class FreeCacheSpace extends Thread{
    private HashMap<String, CacheBlock> cache;
    private Queue<CacheBlock> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a.counter));

    public FreeCacheSpace(HashMap<String, CacheBlock> cache){
        this.cache = cache;
    }
    @Override
    public void run(){
        for(HashMap.Entry<String, CacheBlock> set : cache.entrySet())
            pq.add(set.getValue());
        int counter = 20;
        while (counter != 0 && !pq.isEmpty()){
            CacheBlock front = pq.poll();
            if(!front.dirty) {
                this.cache.remove(front.cachedObject.getId());
                counter--;
            }
        }
    }
}
