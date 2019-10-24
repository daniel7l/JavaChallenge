package com.danhost.models;

import com.danhost.entity.Graph;
import com.danhost.entity.Vertice;

import java.util.ArrayList;
import java.util.List;

public class Dijkstra {

    public List<Vertice> notVisited = new ArrayList<Vertice>();

    public List<Vertice> shortestPath = new ArrayList<Vertice>();

    public Vertice pathVertex = new Vertice();

    public Vertice current = new Vertice();

    public Vertice next = new Vertice();

    public DijkstraPath findShortestPath(Graph g, Vertice start, Vertice end)
    {
        DijkstraPath dPath = new DijkstraPath();

        dijkstra(g, start, end);

        if(shortestPath.size() == 0)
        {
            dPath.setDistance(-1);
            return dPath;
        }

        for(int i = shortestPath.size() - 1; i >= 0; i--)
            dPath.getPath().add(shortestPath.get(i).getNome());

        dPath.setDistance(shortestPath.get(0).getDistance());

        return dPath;
    }

    public List<Vertice> dijkstra(Graph g, Vertice start, Vertice end)
    {
        if(start == end)
        {
            start.setDistance(0);
            shortestPath.add(start);
            return shortestPath;
        }


        initialDistances(g, start);

        while(!this.notVisited.isEmpty())
        {
            current = getShortest();

            for(int i = 0; i < current.getAdj().size(); i++)
            {
                next = current.getAdj().get(i).getTarget();

                if(current.getAdj().get(i).isNotVisited()) {
                    if (next.getDistance() > (current.getDistance() + current.getAdj().get(i).getDistance())) {

                        next.setDistance(current.getDistance() + current.getAdj().get(i).getDistance());

                        next.setPrevious(current);

                        if (next == end) {
                            shortestPath.clear();
                            shortestPath.add(next);
                            pathVertex = next;

                            while (pathVertex.getPrevious() != null) {
                                shortestPath.add(pathVertex.getPrevious());
                                pathVertex = pathVertex.getPrevious();
                            }
                        }
                    }
                }
                current.getAdj().get(i).setNotVisited(false);
            }
            this.notVisited.remove(current);
        }
        return shortestPath;
    }

    public void initialDistances(Graph g, Vertice start)
    {
        for(int i = 0 ; i < g.getVertices().size(); i++)
        {
            if(g.getVertices().get(i).getNome().equals(start.getNome()))
                g.getVertices().get(i).setDistance(0);
            else
                g.getVertices().get(i).setDistance(9999);

            this.notVisited.add( g.getVertices().get(i));
        }
    }

    public Vertice getShortest()
    {
        Vertice aux = new Vertice();
        aux.setDistance(99999);

        for(int i = 0 ; i < this.notVisited.size(); i++)
        {
            if(this.notVisited.get(i).getDistance() < aux.getDistance())
                aux = this.notVisited.get(i);
        }

        return aux;
    }

    public class DijkstraPath
    {
        int distance;
        ArrayList<String> path;

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public void addDistance(int distance) {
            this.distance += distance;
        }

        public ArrayList<String> getPath() {
            return path;
        }

        public void setPath(ArrayList<String> path) {
            this.path = path;

        }

        public DijkstraPath()
        {
            this.distance = 0;
            this.path = new ArrayList<String>();
        }


    }
}