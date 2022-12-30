package com.example.Email_Back.Model.Caches;

public class CacheBlock {

    public Cacheable cachedObject;
    public long counter;
    public boolean dirty = false;

    public CacheBlock(Cacheable object, long counter) {
        this.cachedObject = object;
        this.counter = counter;
    }

    public CacheBlock(Cacheable object, long counter, boolean dirty) {
        this.cachedObject = object;
        this.counter = counter;
        this.dirty = dirty;
    }
}
