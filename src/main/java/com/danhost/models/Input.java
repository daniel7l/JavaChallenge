package com.danhost.models;


public class Input {

    public Long id;
    public Data[] data;

    public static class Data {
        public String source;
        public String target;
        public int distance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
