package DijkstrasAlgorithm;

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

    public boolean edgeIsFromTo(Integer from, Integer to) {
        return (this.from.getID() == from && this.to.getID() == to);
    }
}
