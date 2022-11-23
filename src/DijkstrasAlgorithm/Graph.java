package DijkstrasAlgorithm;
import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {
    private ArrayList<Node> foundShortestPath; //Nodes with shortest paths found
    private LinkedList<Node> Suchfront; //all nodes without shortest path found
    private ArrayList<Edge> Edges; //all edges

    public Graph(LinkedList<Node> nodesWithoutStart, ArrayList<Edge> edges) {
        this.foundShortestPath = new ArrayList<Node>();
        this.Suchfront = nodesWithoutStart;
        this.Edges = edges;
    }

    public ArrayList<Node> getShortestPaths(Node start) {
        this.dijkstrasAlgorithm(start);
        return this.foundShortestPath;
    }

    private void dijkstrasAlgorithm(Node start) {
        start.setDistance(0);
        this.foundShortestPath.add(start);

        for (int i = 1; i < Node.getNodeCount(); i++) {
            for (Node w: this.Suchfront) {
                Integer currentShortest = Integer.MAX_VALUE;
                for (Node v: this.foundShortestPath) {
                    for (Edge e : findEdges(v,w)) {
                        Integer dist = v.getDistance() + getShortestEdge(v,w);
                        if (currentShortest > dist) currentShortest = dist;
                    }
                }
                w.setDistance(currentShortest);
            }

            Integer currentSmallest = Integer.MAX_VALUE;
            Node nodeSmallestDist = this.Suchfront.getFirst();
            for (Node n : this.Suchfront) {
                if (n.getDistance() < currentSmallest) {
                    currentSmallest = n.getDistance();
                    nodeSmallestDist = n;
                }
            }
            this.foundShortestPath.add(new Node(nodeSmallestDist));
            this.Suchfront.remove(nodeSmallestDist);
        }
    }

    private Integer getShortestEdge(Node from, Node to) {
        Integer currentShortestDist = Integer.MAX_VALUE;
        for (Edge edge: this.Edges) {
            if (edge.edgeIsFromTo(from.getID(),to.getID()) && edge.getWeight() < currentShortestDist) {
                currentShortestDist = edge.getWeight();
            }
        }
        return currentShortestDist;
    }

    public ArrayList<Edge> findEdges(Node start, Node end) {
        ArrayList<Edge> EdgesFromTo = new ArrayList<Edge>();
        for (Edge e: this.Edges) {
            if (e.edgeIsFromTo(start.getID(), end.getID())) {
                EdgesFromTo.add(e);
            }
        }
        return EdgesFromTo;
    }
}
