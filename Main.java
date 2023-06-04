import java.util.*;

class Node {
    private int data;
    private ArrayList<Node> neighbors;
    private Node parent;
    int distance;

    public Node(int data) {
        this.data = data;
        this.neighbors = new ArrayList<>();
        this.parent = null;
        this.distance = 0;
    }

    public int getData() {
        return data;
    }

    public ArrayList<Node> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(Node neighbor) {
        neighbors.add(neighbor);
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}

public class Main {

    public static void main(String args[]){

		String InputFile = "TextFiles/map01.txt";
		int X_LENGTH = 500;
		int Y_LENGTH = 500;

		CSE222Map map = new CSE222Map(InputFile);

		CSE222Graph graph = new CSE222Graph(map);

		CSE222Dijkstra dijkstra = new CSE222Dijkstra(graph);

		List<Node> DijkstraPath = dijkstra.findPath();

		CSE222BFS BFS = new CSE222BFS (graph);

		List<Node> BFSPath = BFS.findPath();

		map.convertPNG();

		map.drawLine(DijkstraPath, "DijkstraPath");

		map.drawLine(BFSPath, "BFSPath");

		map.writePath(DijkstraPath, "DijkstraPath");

		map.writePath(BFSPath, "BFSPath");

		System.out.println("Dijkstra Path: "+ dijkstra.length);
		System.out.println("BFS Path: "+ BFS.length);

    }
}

