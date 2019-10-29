package com.danhost.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Vertex
{
    @Id
    @GeneratedValue
    private Long id;

    public String name;

    @OneToMany
    public List<Edge> adj = new ArrayList<>();

    @Transient
    private Vertex previous;

    @Transient
    private int distance;

    public Vertex getPrevious() {
        return previous;
    }

    public void setPrevious(Vertex previous) {
        this.previous = previous;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public List<Edge> getAdj() {
        return adj;
    }

    public Vertex() {

    }

    void addAdj(Edge e) {
        adj.add(e);
    }
}


