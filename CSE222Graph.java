import java.util.*;

public class CSE222Graph {
    
    List<List<Integer>> graph; 
    
    int numRows;
    int numCols;
    int startX, startY, endX, endY;

    public CSE222Graph(CSE222Map map) {
        this.graph = new ArrayList<>();
        this.numRows = map.map.length;
        this.numCols = map.map[0].length;

        this.startX = map.startX;
        this.startY = map.startY;
        this.endX = map.endX;
        this.endY = map.endY;
    
        int numNodes = numRows * numCols;

        for (int i = 0; i < numNodes; i++) {
            this.graph.add(new ArrayList<>());
        }

        int[] colOffsets = {-1, 1, 0, 0,-1,-1, 1, 1};
        int[] rowOffsets = {0, 0, -1, 1,-1, 1, 1,-1};

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (map.map[row][col] == 1) {
                    continue;
                }

                int currentNode = row * numCols + col;

                for (int i = 0; i < 8; i++) {
                    int newRow = row + rowOffsets[i];
                    int newCol = col + colOffsets[i];

                    if (isValidCell(newRow, newCol) && map.map[newRow][newCol] == 0) {
                        int adjacentNode = newRow * numCols + newCol;
                        graph.get(currentNode).add(adjacentNode);
                    }
                }
            }
        }
    }

    private boolean isValidCell(int row, int col) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols;
    }

    public void addEdge(int source, int destination) {
        graph.get(source).add(destination);
        graph.get(destination).add(source); 
    }

    public List<Integer> get(int vertex) {
        return graph.get(vertex);
    }

}
