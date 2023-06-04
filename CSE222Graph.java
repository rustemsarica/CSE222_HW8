import java.util.*;

public class CSE222Graph {
    private int[][] matrix;
    private ArrayList<Node> nodes;
    int numRows;
    int numCols;
    int startX,startY;
    int endX,endY;
    Node startNode;
    Node targetNode;

    public CSE222Graph(CSE222Map map) {
        this.matrix = map.map;
        this.numRows = map.map.length;
        this.numCols = map.map[1].length;
        this.nodes = new ArrayList<>();
        this.startX = map.startX;
        this.startY = map.startY;
        this.endX = map.endX;
        this.endY = map.endY;
        this.startNode = new Node(map.startX * map.map[1].length + map.startY);
        this.targetNode = new Node(map.endX * map.map[1].length + map.endY);
        initializeGraph();
    }

    private void initializeGraph() {

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                
                Node node = new Node(i * numCols + j);
                nodes.add(node);
                
            }
        }

        int[] colOffsets = {-1, 1, 0, 0,-1,-1, 1, 1};
        int[] rowOffsets = {0, 0, -1, 1,-1, 1, 1,-1};

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (matrix[i][j] == 0) {
                    Node currentNode = getNode(i, j);

                    for (int offset = 0; offset < 8; offset++) {
                        int newRow = i + rowOffsets[offset];
                        int newCol = j + colOffsets[offset];
    
                        if (isValidCell(newRow, newCol) && matrix[newRow][newCol] == 0) {
                            Node neigbor = getNode(newRow, newCol);
                            currentNode.addNeighbor(neigbor);
                        }
                    }                    
                }
            }
        }
    }

    private boolean isValidCell(int row, int col) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols;
    }

    private Node getNode(int row, int col) {
        return nodes.get(row * numCols + col);
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }
}
