package com.danhost.models;

import com.danhost.entity.Graph;
import com.danhost.entity.Vertice;

public class PathDistance {

    public String[] path;

    public Distance findPathDistace(Graph g)
    {
        //Creates new distance with value 0, for sure
        Distance pathDistance = new Distance(0);

        if(path.length <= 1)
            return pathDistance;

        //Gets the last vertex from de path
        Vertice lastVertex = g.getVertice(path[this.path.length -1]);

        //Iterates through the path list
        for(int i = 0; i < this.path.length -1 ; i++)
        {
            Vertice actual = g.getVertice(path[i]);
            Vertice next = g.getVertice(path[i+1]);

            //Iteration on the list of adj
            for(int j = 0; j < actual.adj.size(); j++)
            {
                //If the adj is equal to next vertex on the path, continues
                if(actual.adj.get(j).target.nome.equals(next.nome))
                {
                    //Increments the distance with the value of the "aresta"
                    pathDistance.distance += actual.adj.get(j).distance;

                    //If the next vertex is the last one, means that the distance is already considered
                    if(next.nome.equals(lastVertex.nome))
                        return pathDistance;

                }
            }
        }

        return new Distance(-1);
    }

    public class Distance
    {
        public int distance;

        Distance(int distance)
        {
            this.distance = distance;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }
    }
}
