import java.util.*;

    
public class CSE222Dijkstra {

    private CSE222Graph graph;
    private int numRows;
    private int numCols;
    private int startX, startY, endX, endY;
    int length;


    public CSE222Dijkstra(CSE222Graph graph) 
    {
        this.graph = graph;
        this.numRows = graph.numRows;
        this.numCols = graph.numCols;
        
        this.startX = graph.startX;
        this.startY = graph.startY;
        this.endX = graph.endX;
        this.endY = graph.endY;
        this.length = 0;
    }

    public List<Node> findPath() 
    {
        int startNode = startX * numCols + startY;
        int endNode = endX * numCols + endY;
        int numNodes = numRows * numCols;

        int[] distances = new int[numNodes];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startNode] = 0;

        int[] previous = new int[numNodes];
        Arrays.fill(previous, -1);

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));

        pq.offer(new Node(startNode));

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();

            if (currentNode.getData() == endNode) {
                break;
            }

            if (currentNode.distance > distances[currentNode.getData()]) {
                continue; 
            }

            for (Node adjacentNode : graph.getNodes().get(currentNode.getData()).getNeighbors()) {
                int newDistance = currentNode.distance + 1;

                if (newDistance < distances[adjacentNode.getData()]) {
                    distances[adjacentNode.getData()] = newDistance;
                    previous[adjacentNode.getData()] = currentNode.getData();
                    Node newNode = new Node(adjacentNode.getData());
                    newNode.distance = newDistance;
                    pq.offer(newNode);
                }
            }
        }

        List<Node> path = buildPath(previous, endNode);
        if(path.isEmpty()){
            System.out.println("No feasible path is found.");
        }

        return path;
    }

    private List<Node> buildPath(int[] previous, int endNode) {
        List<Node> path = new ArrayList<>();
        Node currentNode = new Node(endNode);

        while (currentNode.getData() != -1) {
            path.add(0, currentNode);
            currentNode = new Node(previous[currentNode.getData()]);
        }
        this.length=path.size();
        return path;
    }

}
