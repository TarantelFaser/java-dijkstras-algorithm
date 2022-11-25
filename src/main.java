import DijkstrasAlgorithm.Edge;
import DijkstrasAlgorithm.Graph;
import DijkstrasAlgorithm.Node;

import java.util.ArrayList;
import java.util.LinkedList;

public class main {

    public static void main(String[] args) {

        Graph g = new Graph();

        ArrayList<Node> nodesInGraph = new ArrayList<Node>();
        nodesInGraph.add(new Node("s"));
        nodesInGraph.add(new Node("t"));
        nodesInGraph.add(new Node("u"));
        nodesInGraph.add(new Node("v"));
        nodesInGraph.add(new Node("w"));
        nodesInGraph.add(new Node("x"));

        ArrayList<Edge> edgesInGraph = new ArrayList<Edge>();
        edgesInGraph.add(new Edge(nodesInGraph.get(0), nodesInGraph.get(1), 5));
        edgesInGraph.add(new Edge(nodesInGraph.get(0), nodesInGraph.get(2), 1));
        edgesInGraph.add(new Edge(nodesInGraph.get(0), nodesInGraph.get(3), 7));
        edgesInGraph.add(new Edge(nodesInGraph.get(2), nodesInGraph.get(5), 1));
        edgesInGraph.add(new Edge(nodesInGraph.get(1), nodesInGraph.get(4), 3));
        edgesInGraph.add(new Edge(nodesInGraph.get(3), nodesInGraph.get(4), 1));
        edgesInGraph.add(new Edge(nodesInGraph.get(5), nodesInGraph.get(4), 6));
        edgesInGraph.add(new Edge(nodesInGraph.get(5), nodesInGraph.get(1), 2));

        g.setNodes(nodesInGraph);
        g.setEdges(edgesInGraph);
        g.setPrintDebugInfo(true);

        ArrayList<Node> result = g.getShortestDistancesDijkstra();

        g.printToConsole(result, false, "test");

        for (Node n : result) {
            if (n.getPrev() == null) {
                System.out.println(n.getName() + ":none");
            } else {
                System.out.println(n.getName() + ":" + n.getPrev().getName());
            }
        }
    }
}
