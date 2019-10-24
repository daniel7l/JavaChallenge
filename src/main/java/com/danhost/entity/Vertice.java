package com.danhost.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Vertice
{
    @Id
    @GeneratedValue
    private Long id;

    public String nome;

    @OneToMany
    public List<Aresta> adj = new ArrayList<Aresta>();

    @Transient
    public Vertice previous;

    @Transient
    public int distance;

    public Vertice getPrevious() {
        return previous;
    }

    public void setPrevious(Vertice previous) {
        this.previous = previous;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
    
    public Vertice(String nome)
    {
        this.nome = nome;
        this.adj = new ArrayList<Aresta>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Aresta> getAdj() {
        return adj;
    }

    public void setAdj(List<Aresta> adj) {
        this.adj = adj;
    }

    public Vertice() {

    }

    void addAdj(Aresta e) {
        adj.add(e);
    }
}


