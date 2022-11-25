import DijkstrasAlgorithm.Edge;
import DijkstrasAlgorithm.Graph;
import DijkstrasAlgorithm.Node;

import java.util.ArrayList;

public class main {

    public static void main(String[] args) {

        testGraph();

    }

    /**
     * example
     */
    public static void testGraph() {
        Graph g = new Graph();

        //create a new ArrayList<Node> containing all Nodes
        ArrayList<Node> nodesInGraph = new ArrayList<Node>();
        nodesInGraph.add(new Node("s"));
        nodesInGraph.add(new Node("t"));
        nodesInGraph.add(new Node("u"));
        nodesInGraph.add(new Node("v"));
        nodesInGraph.add(new Node("w"));
        nodesInGraph.add(new Node("x"));

        //create a new ArrayList<Edge> containing all Edges
        ArrayList<Edge> edgesInGraph = new ArrayList<Edge>();
        edgesInGraph.add(new Edge(nodesInGraph.get(0), nodesInGraph.get(1), 5));
        edgesInGraph.add(new Edge(nodesInGraph.get(0), nodesInGraph.get(2), 1));
        edgesInGraph.add(new Edge(nodesInGraph.get(0), nodesInGraph.get(3), 7));
        edgesInGraph.add(new Edge(nodesInGraph.get(2), nodesInGraph.get(5), 1));
        edgesInGraph.add(new Edge(nodesInGraph.get(1), nodesInGraph.get(4), 3));
        edgesInGraph.add(new Edge(nodesInGraph.get(3), nodesInGraph.get(4), 1));
        edgesInGraph.add(new Edge(nodesInGraph.get(5), nodesInGraph.get(4), 6));
        edgesInGraph.add(new Edge(nodesInGraph.get(5), nodesInGraph.get(1), 2));

        //set nodes and edges
        g.setNodes(nodesInGraph);
        g.setEdges(edgesInGraph);

        //turn on debug / additional info -> prints current paths / distances found for every iteration
        g.setPrintDebugInfo(true);

        //use Dijkstra's Algorithm to calculate the shortest Paths
        //the algorithm uses the first node in nodesInGraph ArrayList<Node> as the starting point
        ArrayList<Node> result = g.getShortestPathsDijkstra();
    }
}
