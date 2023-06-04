import java.util.*;

public class CSE222BFS {
    private CSE222Graph graph;
    
    private ArrayList<Node> visitedNodes;
    private ArrayList<Node> shortestPath;
    Node startNode;
    Node targetNode;
    int length;


    public CSE222BFS(CSE222Graph graph) 
    {
        this.graph = graph;
        this.startNode = graph.getNodes().get(graph.startX * graph.numCols + graph.startY);
        this.targetNode = graph.getNodes().get(graph.endX * graph.numCols + graph.endY);
        this.length = 0;
    }

    public List<Node> findPath(){
        visitedNodes = new ArrayList<>();
        shortestPath = new ArrayList<>();

        if (startNode == null || targetNode == null){
            return shortestPath;
        }
        

        Queue<Node> queue = new LinkedList<>();
        queue.offer(startNode);
        visitedNodes.add(startNode);

        boolean pathFound = false;

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            if (currentNode == targetNode) {
                pathFound = true;
                break;
            }

            ArrayList<Node> neighbors = currentNode.getNeighbors();
            for (Node neighbor : neighbors) {
                if (!visitedNodes.contains(neighbor)) {
                    queue.offer(neighbor);
                    visitedNodes.add(neighbor);
                    neighbor.setParent(currentNode);
                }
            }
        }

        if (pathFound) {
            buildShortestPath(targetNode);
        }

        this.length = shortestPath.size();
        return shortestPath;
    }

    private void buildShortestPath(Node targetNode) {
        Node currentNode = targetNode;
        shortestPath.add(currentNode);

        while (currentNode.getParent() != null) {
            currentNode = currentNode.getParent();
            shortestPath.add(currentNode);
        }

        reverseList(shortestPath);
    }

    private void reverseList(ArrayList<Node> list) {
        int start = 0;
        int end = list.size() - 1;
        while (start < end) {
            Node temp = list.get(start);
            list.set(start, list.get(end));
            list.set(end, temp);
            start++;
            end--;
        }
    }
}
