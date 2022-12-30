package com.example.Email_Back.Model.Caches.Threads;

import com.example.Email_Back.Model.Caches.CacheBlock;
import com.example.Email_Back.Model.Email.Email;
import com.example.Email_Back.Model.Gateways.EmailGateway;
import com.example.Email_Back.Model.Gateways.Gateway;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Queue;

public class EmptyBuffer extends Thread{
    private HashMap<String, CacheBlock> cache;
    private Queue<String> buffer;
    private Gateway database;

    public EmptyBuffer(HashMap<String, CacheBlock> cache, Queue<String> buffer, Gateway database){
        this.cache = cache;
        this.buffer = buffer;
        this.database = database;
    }
    @Override
    public void run(){
        Iterator<String> itr = buffer.iterator();
        while (itr.hasNext())
        {
            String currentItr = itr.next();
            System.out.println("Iterator is: " + currentItr);
            if (this.cache.containsKey(currentItr)){
                try {
                    this.database.save(this.cache.get(currentItr).cachedObject);
                    this.cache.get(currentItr).dirty = false;
                }catch (Exception e){System.out.println(e.getMessage());}
            }
            else
                this.database.delete(currentItr);
        }
        try {
            ((EmailGateway)this.database).saveAll();
            ((EmailGateway)this.database).closeMemory();
        }catch (Exception e){System.out.println("Saving users!!");}

        this.buffer.clear();
    }
}
