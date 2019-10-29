package com.danhost.entity;

import com.danhost.models.GraphPayload;
import com.danhost.models.Input;
import com.danhost.entity.repository.ArestaRepository;
import com.danhost.entity.repository.VerticeRepository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Graph
{
    private static int numGraphs = 0;

    @Id
    @GeneratedValue
    private long id;

    @OneToMany
    private List<Vertex> vertices = new ArrayList<>();

    @OneToMany
    private List<Edge> edges = new ArrayList<>();

    public Graph() {
    }

    private long getId() {
        return id;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public Vertex addVertex(String name)
    {
        Vertex v = new Vertex();
        v.setName(name);
        vertices.add(v);
        return v;
    }

    public Edge addAresta(Vertex origem, Vertex destino, int dist)
    {
        Edge a = new Edge(origem,destino, dist);
        origem.addAdj(a);
        edges.add(a);
        return a;
    }

    public Vertex getVertice(String name)
    {
        for(Vertex v : vertices){
            if(name.equals(v.name))
                return v;
        }
        return null;
    }

    public Input createGraph(Input body, Graph g, ArestaRepository arestaRepository, VerticeRepository verticeRepository)
    {
        numGraphs++;
        this.id = numGraphs;
        body.id = this.id;

        for(int i = 0 ; i < body.data.length ; i++)
        {
            Vertex source  = g.getVertice(body.data[i].source);
            if(source == null) {
                source = g.addVertex(body.data[i].source);
                verticeRepository.save(source);
            }

            Vertex target = g.getVertice(body.data[i].target);
            if(target == null) {
                target = g.addVertex(body.data[i].target);
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

        for(Vertex v : vertices) {
            for (Edge a : v.adj){
                GraphPayload.GraphData data = new GraphPayload.GraphData(a.getSource().getName(), a.getTarget().getName(), a.getDistance());
                graphPayload.getData().add(data);
            }

        }
        return graphPayload;
    }
}



