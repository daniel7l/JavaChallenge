package com.danhost.cotroller;

import com.danhost.entity.*;
import com.danhost.exceptions.NotFoundException;
import com.danhost.models.*;
import com.danhost.entity.repository.ArestaRepository;
import com.danhost.entity.repository.GraphRepository;
import com.danhost.entity.repository.VerticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    private GraphRepository graphRepository;
    private VerticeRepository verticeRepository;
    private ArestaRepository arestaRepository;

    @Autowired
    public Controller(GraphRepository graphRepository, VerticeRepository verticeRepository, ArestaRepository arestaRepository)
    {
        this.graphRepository = graphRepository;
        this.verticeRepository = verticeRepository;
        this.arestaRepository = arestaRepository;
    }

    // 1 - Save graph configuration
    @PostMapping("/graph")
    public Input saveGraph(@RequestBody Input body)
    {
        Graph graph = new Graph();
        graphRepository.save(graph);
        return graph.createGraph(body, graph, arestaRepository, verticeRepository);
    }

    //2 - Retrieve graph configuration
    @GetMapping("/graph/{id}")
    public GraphPayload retrieveGraph(@PathVariable Long id)
    {
        Graph graph = graphRepository.findOne(id);

        if(graph != null) {
            return graph.toGraphPayload(graph);
        }
        else throw new NotFoundException();
    }

    //3 - Find available routes from a given pair of towns on saved graph
    @PostMapping("/routes/{id}/from/{town1}/to/{town2}")
    public Path getRoutes(@PathVariable Long id , @PathVariable String town1, @PathVariable String town2, @RequestParam(value="maxStops", required=false) Long maxStops)
    {
        Graph graph = graphRepository.findOne(id);

        if(graph != null) {
            if(maxStops == null)
                maxStops = Long.valueOf(graph.getVertices().size());

            Path path = new Path();
            return path.traceRoutes(graph.getVertice(town1), graph.getVertice(town2), graph, maxStops);
        }
        else throw new NotFoundException();
    }

    //4 - Find distance for path on saved graph
    @PostMapping("/distance/{id}")
    public PathDistance.Distance findPathDistance(@PathVariable Long id , @RequestBody PathDistance body)
    {
        Graph graph = graphRepository.findOne(id);

        if(graph != null) {
            return body.findPathDistace(graph);
        }
        else throw new NotFoundException();
    }

    //5 - Find distance between two towns on saved graph
    @PostMapping("/distance/{id}/from/{town1}/to/{town2}")
    public Dijkstra.DijkstraPath getShortestPath(@PathVariable Long id , @PathVariable String town1, @PathVariable String town2)
    {
        Graph graph = graphRepository.findOne(id);

        if(graph != null) {
            Dijkstra djk = new Dijkstra();
            return djk.findShortestPath(graph, graph.getVertice(town1), graph.getVertice(town2));
        }
        else
            throw new NotFoundException();
    }

}