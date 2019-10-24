package com.danhost.entity;

import com.danhost.models.GraphPayload;
import com.danhost.models.Input;
import com.danhost.entity.repository.ArestaRepository;
import com.danhost.entity.repository.VerticeRepository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
public class Graph
{
    private static int numGraphs = 0;

    @Id
    @GeneratedValue
    private long id;

    @OneToMany
    private List<Vertice> vertices = new ArrayList<Vertice>();

    @OneToMany
    private List<Aresta> arestas = new ArrayList<Aresta>();

    public Graph() {
    }

    public Graph(List<Vertice> vertices, List<Aresta> arestas) {
        this.vertices = vertices;
        this.arestas = arestas;
    }

    public long getId() {
        return id;
    }

    public List<Vertice> getVertices() {
        return vertices;
    }

    public List<Aresta> getArestas() {
        return arestas;
    }


    public void setVertices(List<Vertice> vertices) {
        this.vertices = vertices;
    }

    public void setArestas(List<Aresta> arestas) {
        this.arestas = arestas;
    }



    public Vertice addVertice(String nome)
    {
        Vertice v = new Vertice(nome);
        vertices.add(v);
        return v;
    }

    public Aresta addAresta(Vertice origem, Vertice destino, int dist)
    {
        Aresta a = new Aresta(origem,destino, dist);
        origem.addAdj(a);
        arestas.add(a);
        return a;
    }

    public Vertice getVertice(String name)
    {
        for(Iterator iterator = vertices.iterator(); iterator.hasNext();) {
            Vertice v = (Vertice) iterator.next();
            if(name.equals(v.nome))
                return v;
        }

        /*for(int i = 0 ; i < vertices.size(); i++)
            if(vertices.get(i).nome == name)
                return vertices.get(i);*/

        return null;
    }

    public Input createGraph(Input body, Graph g, ArestaRepository arestaRepository, VerticeRepository verticeRepository)
    {
        numGraphs++;
        this.id = numGraphs;
        body.id = this.id;

        for(int i = 0 ; i < body.data.length ; i++)
        {
            Vertice source  = g.getVertice(body.data[i].source);
            if(source == null) {
                source = g.addVertice(body.data[i].source);
                verticeRepository.save(source);
            }

            Vertice target = g.getVertice(body.data[i].target);
            if(target == null) {
                target = g.addVertice(body.data[i].target);
                verticeRepository.save(target);
            }

            arestaRepository.save(g.addAresta(source, target, body.data[i].distance));
        }
        return body;
    }

    public GraphPayload toGraphPayload(Graph graph)
    {
        GraphPayload graphPayload = new GraphPayload();
        graphPayload.setId(graph.getId());

        for(Vertice v : vertices) {
            for (Aresta a : v.adj){
                GraphPayload.GraphData data = new GraphPayload.GraphData(a.getSource().getNome(), a.getTarget().getNome(), a.getDistance());
                graphPayload.getData().add(data);
            }

        }
        return graphPayload;
    }
}



