package com.danhost.models;

import com.danhost.entity.Graph;
import com.danhost.entity.Vertex;

import java.util.ArrayList;
import java.util.List;

public class Dijkstra {

    private List<Vertex> notVisited = new ArrayList<>();

    private List<Vertex> shortestPath = new ArrayList<>();

    public DijkstraPath findShortestPath(Graph graph, Vertex start, Vertex end)
    {
        DijkstraPath dPath = new DijkstraPath();

        dijkstra(graph, start, end);

        if(shortestPath.size() == 0)
        {
            dPath.setDistance(-1);
            return dPath;
        }

        for(int i = shortestPath.size() - 1; i >= 0; i--)
            dPath.getPath().add(shortestPath.get(i).getName());

        dPath.setDistance(shortestPath.get(0).getDistance());

        return dPath;
    }

    private void dijkstra(Graph graph, Vertex start, Vertex end)
    {
        if(start == end)
        {
            start.setDistance(0);
            shortestPath.add(start);
            return;
        }


        initialDistances(graph, start);

        while(!this.notVisited.isEmpty())
        {
            Vertex current = getShortest();

            for(int i = 0; i < current.getAdj().size(); i++)
            {
                Vertex next = current.getAdj().get(i).getTarget();

                if(current.getAdj().get(i).isNotVisited()) {
                    if (next.getDistance() > (current.getDistance() + current.getAdj().get(i).getDistance())) {

                        next.setDistance(current.getDistance() + current.getAdj().get(i).getDistance());

                        next.setPrevious(current);

                        if (next == end) {
                            shortestPath.clear();
                            shortestPath.add(next);
                            Vertex pathVertex = next;

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
    }

    private void initialDistances(Graph g, Vertex start)
    {
        for(int i = 0 ; i < g.getVertices().size(); i++)
        {
            if(g.getVertices().get(i).getName().equals(start.getName()))
                g.getVertices().get(i).setDistance(0);
            else
                g.getVertices().get(i).setDistance(9999);

            this.notVisited.add( g.getVertices().get(i));
        }
    }

    private Vertex getShortest()
    {
        Vertex shortest = new Vertex();
        shortest.setDistance(99999);

        //for(int i = 0 ; i < this.notVisited.size(); i++)

        for(Vertex vAux : this.notVisited)
        {
            if(vAux.getDistance() < shortest.getDistance())
                shortest = vAux;
        }

        return shortest;
    }

    public static class DijkstraPath
    {
        int distance;
        ArrayList<String> path;

        public int getDistance() {
            return distance;
        }

        private void setDistance(int distance) {
            this.distance = distance;
        }

        public ArrayList<String> getPath() {
            return path;
        }

        public void setPath(ArrayList<String> path) {
            this.path = path;

        }

        private DijkstraPath()
        {
            this.distance = 0;
            this.path = new ArrayList<>();
        }


    }
}