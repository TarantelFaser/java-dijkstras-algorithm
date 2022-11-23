import DijkstrasAlgorithm.Edge;
import DijkstrasAlgorithm.Graph;
import DijkstrasAlgorithm.Node;

import java.util.ArrayList;
import java.util.LinkedList;

public class main {

    public static void main(String[] args) {

        Node start = new Node("s",0);

        LinkedList<Node> Nodes = new LinkedList<Node>();
        Nodes.add(new Node("t",1));
        Nodes.add(new Node("u",2));
        Nodes.add(new Node("v",3));
        Nodes.add(new Node("w",4));
        Nodes.add(new Node("x",5));

        ArrayList<Edge> Edges = new ArrayList<Edge>();
        Edges.add(new Edge(start, Nodes.get(0), 5));
        Edges.add(new Edge(start, Nodes.get(1), 1));
        Edges.add(new Edge(start, Nodes.get(2), 7));
        Edges.add(new Edge(Nodes.get(1), Nodes.get(4), 1));
        Edges.add(new Edge(Nodes.get(0), Nodes.get(3), 3));
        Edges.add(new Edge(Nodes.get(2), Nodes.get(3), 1));
        Edges.add(new Edge(Nodes.get(4), Nodes.get(3), 6));
        Edges.add(new Edge(Nodes.get(4), Nodes.get(0), 2));

        Graph g = new Graph(Nodes, Edges);
        ArrayList<Node> result = g.getShortestPaths(start);

        for (Node n: result) {
            System.out.println(n.getName() + "   " + n.getDistance());
        }
    }
}
