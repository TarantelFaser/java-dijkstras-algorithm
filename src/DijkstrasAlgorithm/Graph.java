package DijkstrasAlgorithm;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Implements graphs, needs Node and Edge class to function correctly.
 */
public class Graph {
    private ArrayList<Node> nWithShortestDistFound; //all nodes with a known shortest path length
    private ArrayList<Node> nWithoutShortestDistFound; //all nodes with an unknown shortest path length
    private ArrayList<Node> Nodes; //all nodes in original graph
    private ArrayList<Edge> Edges; //all edges

    private boolean printDebugInfo;

    /*
    CONSTRUCTOR
     */

    /**
     * Constructor for class graph
     */
    public Graph() {
        this.nWithShortestDistFound = new ArrayList<Node>();
        this.nWithoutShortestDistFound = new ArrayList<Node>();
        this.Edges = new ArrayList<Edge>();
        this.printDebugInfo = false;
    }

    /*
    SETTER
     */

    /**
     * defines a set of nodes for the graph
     * @param nodesInGraph ArrayList of objects of Node class
     */
    public void setNodes(ArrayList<Node> nodesInGraph) {
        this.Nodes = nodesInGraph;
        for (Node n: this.Nodes) {
            this.nWithoutShortestDistFound.add(n.copy());
        }
    }

    /**
     * defines a set of edges for the graph
     * @param edgesInGraph ArrayList of objects of Edge class
     */
    public void setEdges(ArrayList<Edge> edgesInGraph) {
        this.Edges = edgesInGraph;
    }

    /**
     * Enables / Disables additional infos being printed to console
     * @param printInfo true: print info, false: don't print info
     */
    public void setPrintDebugInfo(boolean printInfo) {
        this.printDebugInfo = printInfo;
    }

    /*
    GETTER
     */

    /**
     * get all edges in this graph
     * @return ArrayList of Edge objects
     */
    public ArrayList<Edge> getEdges() {
        return this.Edges;
    }

    /**
     * get all nodes in this graph
     * @return ArrayList of Node objects
     */
    public ArrayList<Node> getNodes() {
        return this.Nodes;
    }

    /*
    OTHER
     */

    /**
     * Calculate the shortest path lengths using <a href="https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm">Dijkstra's algorithm</a>.
     * @return ArrayList of Node objects, which contain the length of the shortest paths from the start node in their distance property.
     */
    public ArrayList<Node> getShortestDistancesDijkstra() {
        this.dijkstrasAlgorithm();
        return this.nWithShortestDistFound;
    }

    /**
     * Implements dijkstra's algorithm
     */
    private void dijkstrasAlgorithm() {
        Node start = this.nWithoutShortestDistFound.get(0);
        this.nWithoutShortestDistFound.remove(0);
        start.setDistance(0);
        this.nWithShortestDistFound.add(start);

        //print additional info
        if (printDebugInfo) {
            printToConsole(nWithShortestDistFound, true, "abgearbeitete Knoten (" + nWithShortestDistFound.size() + "):\t");
            printToConsole(nWithoutShortestDistFound, true, "Suchfront (" + nWithoutShortestDistFound.size() + "):\t\t\t\t");
            System.out.println("=".repeat(60));
        }

        //searching for n-1 (node count - 1) shortest path lengths (one to every node)
        for (int i = 1; i < Node.getNodeCount(); i++) {
            //for every node without the shortest path length found
            for (Node w: this.nWithoutShortestDistFound) {
                //check every edge from nodes with the shortest path length found to w
                Integer currentShortest = Integer.MAX_VALUE;
                for (Node v: this.nWithShortestDistFound) {
                    for (Edge e : findEdges(v,w)) { //often only 1 edge between two nodes
                        Integer dist = v.getDistance() + getShortestEdgeLength(v,w); //new distance ist distance of already found node + shortest edge between w and v
                        if (dist < currentShortest) {
                            currentShortest = dist; //only the shortest edge of all possibilities is the right one
                            v.setPrev(w.copy());
                        }
                    }
                }
                w.setDistance(currentShortest);
            }
            //in every iteration of the outer for loop only the node
            //with the smallest path length gets new distance assigned
            Node nodeSmallestDist = Collections.min(this.nWithoutShortestDistFound, new NodeComp());
            this.nWithShortestDistFound.add(nodeSmallestDist.copy());
            this.nWithoutShortestDistFound.remove(nodeSmallestDist);

            //print additional info
            if (printDebugInfo) {
                printToConsole(nWithShortestDistFound, true, "abgearbeitete Knoten (" + nWithShortestDistFound.size() + "):\t");
                printToConsole(nWithoutShortestDistFound, true, "Suchfront (" + nWithoutShortestDistFound.size() + "):\t\t\t\t");
                System.out.println("");
            }
        }
    }

    /**
     * Search for the shortest edge between two nodes and return its length
     * @param from Node at which the edge starts
     * @param to Node at which the edge ends
     * @return length of the shortest edge between two nodes.
     */
    public Integer getShortestEdgeLength(Node from, Node to) {
        ArrayList<Edge> edgesFromTo = findEdges(from, to);
        return Collections.min(edgesFromTo, new EdgeComp()).getWeight();
    }

    /**
     * Search for all edges from start to end.
     * @param start Node at which the edges start
     * @param end Node at which the edges end
     * @return ArrayList of Edges objects, all beginning at start and ending at end
     */
    public ArrayList<Edge> findEdges(Node start, Node end) {
        ArrayList<Edge> EdgesFromTo = new ArrayList<Edge>();
        for (Edge e: this.Edges) {
            if (e.edgeIsFromTo(start, end)) {
                EdgesFromTo.add(e);
            }
        }
        return EdgesFromTo;
    }

    /**
     * Prints an ArrayList of nodes in a human-readable kind of way to the console.
     * @param nodeList ArrayList of Nodes to be printed.
     * @param printDistances true: print node names and distance, false: only print node names.
     * @param prefix String prefix for console output, string will be printed at the beginning of the line.
     */
    public void printToConsole(ArrayList<Node> nodeList, boolean printDistances, String prefix) {
        StringBuilder s = new StringBuilder(prefix);
        //if empty print 'null' instead of nothing
        if (nodeList.isEmpty()) {
            s.append("null");
            System.out.println(s);
            return;
        }
        for (Node n: nodeList) {
            s.append(n.getName());
            if (!n.getDistance().equals(Integer.MAX_VALUE)) {
                s.append(printDistances ? ":" + n.getDistance() + "\t" : "\t");
            } else {
                s.append(printDistances ? ":inf\t" : "\t");
            }
        }
        System.out.println(s);
    }
}
