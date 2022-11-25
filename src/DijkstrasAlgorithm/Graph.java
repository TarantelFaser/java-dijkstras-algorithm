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
    public ArrayList<Node> getShortestPathsDijkstra() {
        this.dijkstrasAlgorithm();
        return this.nWithShortestDistFound;
    }

    /**
     * Implements dijkstra's algorithm
     */
    private void dijkstrasAlgorithm() {
        Nodes.get(0).setDistance(0);
        Node start = this.nWithoutShortestDistFound.get(0);
        this.nWithoutShortestDistFound.remove(0);
        start.setDistance(0);
        this.nWithShortestDistFound.add(start);

        //print additional info
        if (printDebugInfo) {
            printDistancesToConsole(nWithShortestDistFound, true, "abgearbeitete Knoten (" + nWithShortestDistFound.size() + "):\t");
            printDistancesToConsole(nWithoutShortestDistFound, true, "Suchfront (" + nWithoutShortestDistFound.size() + "):\t\t\t\t");
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
                            w.setPrev(v.copy()); //save the father node in the shortest path
                        }
                    }
                }
                w.setDistance(currentShortest);

                //update information in ArrayList that contains all nodes
                Nodes.get(w.getID()).setDistance(w.getDistance());
                Nodes.get(w.getID()).setPrev(w.getPrev());
            }
            //in every iteration of the outer for loop only the node
            //with the smallest path length gets new distance assigned
            Node nodeSmallestDist = Collections.min(this.nWithoutShortestDistFound, new NodeCompDistances());
            this.nWithShortestDistFound.add(nodeSmallestDist.copy());
            this.nWithoutShortestDistFound.remove(nodeSmallestDist);

            //print additional info
            if (printDebugInfo) {
                printDistancesToConsole(nWithShortestDistFound, true, "abgearbeitete Knoten (" + nWithShortestDistFound.size() + "):\t");
                printDistancesToConsole(nWithoutShortestDistFound, true, "Suchfront (" + nWithoutShortestDistFound.size() + "):\t\t\t\t");
                //printDistancesToConsole(Nodes, true, "alle Knoten: \t\t\t\t");
                printPathsToConsole(Nodes, true);
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
        return Collections.min(findEdges(from, to), new EdgeComp()).getWeight();
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

    /*
    OUTPUT / PRINT TO CONSOLE
     */

    /**
     * Prints an ArrayList of nodes in a human-readable kind of way to the console.
     * @param nodeList ArrayList of Nodes to be printed.
     * @param printDistances true: print node names and distance, false: only print node names.
     * @param prefix String prefix for console output, string will be printed at the beginning of the line.
     */
    public void printDistancesToConsole(ArrayList<Node> nodeList, boolean printDistances, String prefix) {
        StringBuilder s = new StringBuilder(prefix);
        Collections.sort(nodeList, new NodeCompName()); //sort nodeList by name, for consistency
        //if empty print 'null' instead of nothing
        if (nodeList.isEmpty()) {
            s.append("null");
            System.out.println(s);
            return;
        }
        for (Node n: nodeList) {
            s.append(n.getName());
            if (!n.getDistance().equals(Integer.MAX_VALUE)) {
                s.append(printDistances ? ":" + n.getDistance() + "\t\t" : "\t\t");
            } else {
                s.append(printDistances ? ":inf\t" : "\t\t");
            }
        }
        System.out.println(s);
    }

    /**
     * Print the shortest paths in console.
     * @param nodeList List of nodes for which the path should be printed
     * @param printDistances true: print distance + path, false: just print path
     */
    public void printPathsToConsole(ArrayList<Node> nodeList, boolean printDistances) {
        System.out.println("Wege zum Knoten (Distanz): ");
        String path;
        for (Node n: nodeList) {
            path = "\t\t";
            if (printDistances) {
                if (n.getDistance().equals(Integer.MAX_VALUE)) {
                    path += n.getName() + " (inf): " + getPrevRec(n);
                } else {
                    path += n.getName() + " (" + n.getDistance() + "): " + getPrevRec(n);
                }
            } else {
                path += n.getName() + ": " + getPrevRec(n);
            }
            System.out.println(path);
        }
    }

    /**
     * Follow the prev property of every node to construct the complete path recursively.
     * @param n end of path
     * @return string with complete path from start to end (argument Node n)
     */
    private String getPrevRec(Node n) {
        if (n.getPrev() == null) return n.getName();
        return getPrevRec(n.getPrev()) + " -> " + n.getName();
    }
}