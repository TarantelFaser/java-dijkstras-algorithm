package DijkstrasAlgorithm;

public class Node {

    private Integer id;
    private String name;

    private static Integer biggestFreeID = 0;

    private Integer distance;


    public Node(String name, Integer id) {
        this.name = name;
        this.id = id;
        biggestFreeID += 1;
    }

    public Node(Node old) {
        this.id = old.getID();
        this.name = old.getName();
        this.distance = old.getDistance();
    }


    public static Integer getNodeCount() {
        return biggestFreeID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setDistance(Integer dist) {
        this.distance = dist;
    }

    public Integer getDistance() {
        return this.distance;
    }
}
