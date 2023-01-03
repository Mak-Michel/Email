package com.example.Email_Back.Model.Caches.Threads;

import com.example.Email_Back.Model.Caches.CacheBlock;
import com.example.Email_Back.Model.Email.Email;
import com.example.Email_Back.Model.Gateways.EmailGateway;
import com.example.Email_Back.Model.Gateways.Gateway;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Queue;

public class EmptyBuffer{

    public void empty(HashMap<String, CacheBlock> cache, Queue<String> buffer, Gateway database){
        Iterator<String> itr = buffer.iterator();
        while (itr.hasNext())
        {
            String currentItr = itr.next();
            System.out.println("Iterator is: " + currentItr);
            if (cache.containsKey(currentItr)){
                try {
                    database.save(cache.get(currentItr).cachedObject);
                    cache.get(currentItr).dirty = false;
                }catch (Exception e){System.out.println(e.getMessage());}
            }
            else
                database.delete(currentItr);
        }
        try {
            ((EmailGateway)database).saveAll();
            ((EmailGateway)database).closeMemory();
        }catch (Exception e){System.out.println("Saving users!!");}

        buffer.clear();
    }
}
