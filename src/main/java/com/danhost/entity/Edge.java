package com.danhost.entity;

import javax.persistence.*;

@Entity
public class Edge {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Vertex source;

    @OneToOne
    public Vertex target;

    public int distance;

    @Transient
    private boolean isNotVisited = true;

    Edge(Vertex source, Vertex target, int dist)
    {
        this.source = source;
        this.target = target;
        this.distance =  dist;
    }

    Edge()
    {
    }

    public Vertex getTarget() {
        return target;
    }

    public boolean isNotVisited() {
        return isNotVisited;
    }

    public void setNotVisited(boolean notVisited) {
        isNotVisited = notVisited;
    }

    public int getDistance() {
        return distance;
    }

    Vertex getSource() {
        return source;
    }
}