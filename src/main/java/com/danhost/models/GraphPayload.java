package com.danhost.models;

import java.util.ArrayList;

public class GraphPayload {

    public Long id;
    public ArrayList<GraphData> data = new ArrayList<>();

    public static class GraphData {
        public String source;
        public String target;
        public int distance;

        public GraphData(String source, String target, int distance) {
            this.source = source;
            this.target = target;
            this.distance = distance;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArrayList<GraphData> getData() {
        return data;
    }

    public void setData(ArrayList<GraphData> data) {
        this.data = data;
    }
}
