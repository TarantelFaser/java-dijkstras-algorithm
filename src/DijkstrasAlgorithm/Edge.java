package DijkstrasAlgorithm;

import java.util.Objects;

public class Edge {

    private Node from;
    private Node to;
    private Integer weight;

    private static Integer NodeCount = 0;

    public Edge(Node from ,Node to, Integer weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;

        NodeCount += 1;
    }

    /*
    GETTER
     */

    public Integer getNodeCount() {
        return NodeCount;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public Node getFrom() {
        return this.from;
    }

    public Node getTo() {
        return this.to;
    }

    public boolean edgeIsFromTo(Node from, Node to) {
        return (Objects.equals(this.from.getID(), from.getID()) && Objects.equals(this.to.getID(), to.getID()));
    }
}
