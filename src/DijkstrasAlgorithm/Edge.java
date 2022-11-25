package DijkstrasAlgorithm;

import java.util.Comparator;
import java.util.Objects;

/**
 * Implements an edge which can be used for graph algorithms such as Dijkstra's Algorithm.
 */
public class Edge {

    private Node from;
    private Node to;
    private Integer weight;

    /**
     * Constructor for Edge Class
     * @param from Node, start of edge
     * @param to Node, end of edge
     * @param weight Integer, weight of edge
     */
    public Edge(Node from ,Node to, Integer weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    /**
     * returns a deep copy of the edge passed
     * @param toBeCopied edge that should be copied
     */
    public Edge(Edge toBeCopied) {
        this.from = toBeCopied.from;
        this.to = toBeCopied.from;
        this.weight = toBeCopied.weight;
    }

    /*
    SETTER
     */

    /**
     * Sets a new start node of this edge
     * @param from Node, at which this edge starts
     */
    public void setFrom(Node from) {
        this.from = from;
    }

    /**
     * Sets a new node as the end of this edge
     * @param to Node, at which this edge ends
     */
    public void setTo(Node to) {
        this.to = to;
    }

    /**
     * Sets a new weight of the edge
     * @param weight new weight of this edge
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /*
    GETTER
     */

    /**
     * @return Weight or costs of this edge
     */
    public Integer getWeight() {
        return this.weight;
    }

    /**
     * @return Node, at which this edge starts
     */
    public Node getFrom() {
        return this.from;
    }

    /**
     * @return Node, in which this edge ends
     */
    public Node getTo() {
        return this.to;
    }

    /*
    OTHER
     */

    /**
     * Checks if an edge has the same start and end as two nodes given to the method
     * @param from the start node of the edge
     * @param to the end node of the edge
     * @return true, if the edge this method is called on has the same nodes as start and end as the arguments, otherwise false
     */
    public boolean edgeIsFromTo(Node from, Node to) {
        //matches IDs of start / end nodes
        return (Objects.equals(this.from.getID(), from.getID()) && Objects.equals(this.to.getID(), to.getID()));
    }

    /**
     * returns deep copy of the edge this method is called on.
     * @return copied edge
     */
    public Edge copy() {
        return new Edge(this);
    }
}

/**
 * Comparator for Edges
 */
class EdgeComp implements Comparator<Edge> {

    /**
     * Compare two edges by weight / cost
     * @param e1 the first edge to be compared.
     * @param e2 the second edge to be compared.
     * @return 0 if costs equal, int < 0 if cost1 < cost2, int > 0 cost1 > cost2
     */
    @Override
    public int compare(Edge e1, Edge e2) {
        return e1.getWeight().compareTo(e2.getWeight());
    }
}
