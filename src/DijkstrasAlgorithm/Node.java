package DijkstrasAlgorithm;

public class Node {

    private Integer id;
    private String name;
    private Integer distance;

    //counts total nodes in graph
    private static Integer biggestFreeID = 0;


    /*
    CONSTRUCTOR
     */
    public Node(String name, Integer id) {
        this.name = name;
        this.id = id;
        this.distance = Integer.MAX_VALUE;
        biggestFreeID += 1;
    }

    public Node(Node old) {
        this.id = old.getID();
        this.name = old.getName();
        this.distance = old.getDistance();
    }

    /*
    GETTER
     */
    public Integer getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Integer getDistance() {
        return this.distance;
    }

    public static Integer getNodeCount() {
        return biggestFreeID;
    }


    /*
    SETTER
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setDistance(Integer dist) {
        this.distance = dist;
    }

    public void setID(Integer id) {
        this.id = id;
    }
}
