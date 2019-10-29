package com.danhost.models;

import com.danhost.entity.Graph;
import com.danhost.entity.Vertex;

import java.util.ArrayList;


public class Path {

    public ArrayList<Route> routes = new ArrayList();

    private Route r = new Route();

    public static class Route
    {
        public String route = "";
        public int stops = 0;

        public Route(String route, int stops)
        {
            this.route = route;
            this.stops = stops;
        }

        Route()
        {

        }
    }

    public Path traceRoutes(Vertex start, Vertex end, Graph g, Long maxStops)
    {
        r.route += start.name;

        if(start == end && r.stops != 0)
        {
            if(r.stops <= maxStops)
            {
                Route availableRoute = new Route(r.route, r.stops);

                this.routes.add(availableRoute);
            }
            r.route = r.route.substring(0, r.route.length()-1);
            //r.stops--;
            return this;
        }

        r.stops++;

        for( int i = 0 ; i < start.adj.size(); i++ )
        {

            if(start.adj.get(i).isNotVisited())
            {
                start.adj.get(i).setNotVisited(false);//isNotVisited = false;
                traceRoutes(start.adj.get(i).target, end, g, maxStops);
                start.adj.get(i).setNotVisited(true);// isNotVisited = true;
            }
            else
            {
                r.route = r.route.substring(0, r.route.length()-1);
                r.stops--;
                return this;
            }
        }
        r.route = r.route.substring(0, r.route.length()-1);
        r.stops--;
        return this;
    }
}
