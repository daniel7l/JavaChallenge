package com.danhost.entity;

import javax.persistence.*;

@Entity
public class Aresta {

    @Id
    @GeneratedValue
    private Long Id;

    @OneToOne
    public Vertice source = new Vertice();

    @OneToOne
    public Vertice target = new Vertice();

    public int distance;

    @Transient
    public boolean isNotVisited = true;

    Aresta(Vertice source, Vertice target, int dist)
    {
        this.source = source;
        this.target = target;
        this.distance =  dist;
    }

    public Aresta() {
    }


    public Vertice getTarget() {
        return target;
    }

    public void setTarget(Vertice dest) {
        this.target = dest;
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

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Vertice getSource() {
        return source;
    }

    public void setSource(Vertice source) {
        this.source = source;
    }
}