import java.util.*;

    
public class CSE222Dijkstra {

    private List<List<Integer>> graph;
    private int numRows;
    private int numCols;
    private int startX, startY, endX, endY;
    int length;


    public CSE222Dijkstra(CSE222Graph graph) 
    {
        this.graph = graph.graph;
        this.numRows = graph.numRows;
        this.numCols = graph.numCols;
        
        this.startX = graph.startX;
        this.startY = graph.startY;
        this.endX = graph.endX;
        this.endY = graph.endY;
        this.length = 0;
    }

    public List<Integer> findPath() 
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

        pq.offer(new Node(startNode, 0));

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();

            if (currentNode.node == endNode) {
                break;
            }

            if (currentNode.distance > distances[currentNode.node]) {
                continue; 
            }

            for (int adjacentNode : graph.get(currentNode.node)) {
                int newDistance = currentNode.distance + 1;

                if (newDistance < distances[adjacentNode]) {
                    distances[adjacentNode] = newDistance;
                    previous[adjacentNode] = currentNode.node;
                    pq.offer(new Node(adjacentNode, newDistance));
                }
            }
        }

        List<Integer> path = buildPath(previous, endNode);
        if(path.isEmpty()){
            System.out.println("No feasible path is found.");
        }

        return path;
    }

    private List<Integer> buildPath(int[] previous, int endNode) {
        List<Integer> path = new ArrayList<>();
        int currentNode = endNode;

        while (currentNode != -1) {
            path.add(0, currentNode);
            currentNode = previous[currentNode];
        }
        this.length=path.size();
        return path;
    }

    private class Node {
        int node;
        int distance;

        public Node(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

}
