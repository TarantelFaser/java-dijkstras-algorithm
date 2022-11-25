package DijkstrasAlgorithm;

import java.util.Comparator;

public class Node {

    private Integer id;
    private String name;
    private Integer distance;

    //counts total nodes
    private static Integer biggestFreeID = 0;
    private static Integer totalNodeCount = 0;

    /*
    CONSTRUCTOR
     */

    /**
     * Constructor of Node class, constructs a new Node object, automatically sets id, initialises its distance from start to Integer.MAX_VALUE, increments totalNodeCount. The passed name is useful for usability reasons, i.e. output in terminal.
     * @param name Name for the node
     */
    public Node(String name) {
        this.name = name;
        this.distance = Integer.MAX_VALUE;

        //automatically assign unique ID (starting from 0)
        this.id = Node.biggestFreeID;
        Node.biggestFreeID += 1;

        Node.totalNodeCount += 1;
    }

    /**
     * Constructor for Node class, returns a deep copy of the node passed as an argument.
     * @param toBeCopied Node to be copied
     */
    public Node(Node toBeCopied) {
        this.id = toBeCopied.getID();
        this.name = toBeCopied.getName();
        this.distance = toBeCopied.getDistance();

        //don't increment biggestFreeID nor totalNodeCount here, since node is just being copied
    }

    /*
    GETTER
     */

    /**
     * Returns (mostly automatically distributed) id this node.
     * @return id of node
     */
    public Integer getID() {
        return this.id;
    }

    /**
     * Returns string name, useful for readable output.
     * @return name of node
     */
    public String getName() {
        return this.name;
    }

    /**
     * distance from a start node when using Dijkstra's algorithm. Default: Integer.MAX_VALUE;
     * @return distance from start
     */
    public Integer getDistance() {
        return this.distance;
    }

    /*
    SETTER
     */

    /**
     * Sets new (string) name of a node, Dijkstra's algorithm implementation uses only the id's to calculate the shortest paths, this is only for usability.
     * @param name new Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets new distance of Node from starting node when using Dijkstra's algorithm.
     * @param dist new distance
     */
    public void setDistance(Integer dist) {
        this.distance = dist;
    }

    /**
     * <b style="color:red;">ATTENTION</b>: IDs are automatically given to nodes on Constructor call (continuously counting up). Set ID on own risk!
     * @param id new ID for this node
     */
    public void setID(Integer id) {
        this.id = id;
    }

    /*
    OTHER
     */

    /**
     * deletes a node, removes name, sets distance, id to Integer.MAX_VALUE, decrements totalNodeCount. <b>ATTENTION</b>: this id, which is now free, won't be distributed on Constructor call again -> set it manually!
     * @return ID of this node
     */
    public Integer delete() {
        int id = this.id;
        this.name = null;
        this.distance = Integer.MAX_VALUE;
        this.id = Integer.MAX_VALUE;

        totalNodeCount -= 1;
        return id;
    }

    /**
     * Copies this node and returns the new instance
     * @return copied node
     */
    public Node copy() {
        return new Node(this);
    }

    /*
    STATIC METHODS
     */

    /**
     * Returns unique node count, copied nodes (with same id, name, distance) are not counted twice
     * @return total nodes instances of this class
     */
    public static Integer getNodeCount() {
        return totalNodeCount;
    }
}

/**
 * Comparator for two Nodes -> Collections.min(col, new NodeComp() ); works!
 */
class NodeComp implements Comparator<Node> {

    /**
     * Compare two nodes by distance
     * @param n1 the first node to be compared.
     * @param n2 the second node to be compared.
     * @return 0 if distances equal, int < 0 if distance1 < distance2, int > 0 if distance1 > distance2
     */
    @Override
    public int compare(Node n1, Node n2) {
        return n1.getDistance().compareTo(n2.getDistance());
    }
}
