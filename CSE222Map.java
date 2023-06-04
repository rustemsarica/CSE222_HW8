import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

import javax.imageio.ImageIO;

public class CSE222Map {

    int[][] map = new int[500][500];
    int startX, startY, endX, endY;

    public CSE222Map(String file) 
    {
        try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			int lineNumber=0;

			while ((line = reader.readLine()) != null) {
				String[] coordinates = line.split(",");

				if(lineNumber == 0){
					this.startY = Integer.parseInt(coordinates[0]); 
					this.startX = Integer.parseInt(coordinates[1]);
				}
				else if(lineNumber == 1)
				{
					this.endY = Integer.parseInt(coordinates[0]); 
					this.endX = Integer.parseInt(coordinates[1]);

				}
				else if(lineNumber > 1)
				{
					for(int i=0; i<coordinates.length; i++ ){
                        int coordinate = Integer.parseInt(coordinates[i]);
                        if(coordinate==-1){
                            coordinate = 1;
                        }
						this.map[i][lineNumber-2] = coordinate;
					}

				}
				lineNumber++;
			}
		
			reader.close();			
		} catch (IOException e) {
			e.printStackTrace();
		}        
    }

    public void convertPNG()
    {
        int width = map[0].length;
        int height = map[1].length;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
		g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

		for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (map[x][y] == 1) {
                    image.setRGB(x, y, Color.BLACK.getRGB());
                }
            }
        }
        writeImage(image, "map");
        g.dispose();
    }

    public void writeImage(BufferedImage image , String name){
        File output = new File(name+".png");
        try {
            ImageIO.write(image, "png", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawLine(List<Node> list, String fileName)
    {
        int width = map[0].length;
        int height = map[1].length;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
		g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

		for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (map[x][y] == 1) {
                    image.setRGB(x, y, Color.BLACK.getRGB());
                }
            }
        }
        
        for (Node node : list) {
            int row = node.getData() / map[0].length;
            int col = node.getData() % map[0].length;
            image.setRGB(row, col, Color.BLUE.getRGB());
        }
        writeImage(image,fileName);
        g.dispose();
    }

    public void writePath(List<Node> list, String fileName)
    {
        String filePath = fileName+".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            for (Node node : list) {
                int row = node.getData() / map[0].length;
                int col = node.getData() % map[0].length;
                writer.write(row+","+col);
                writer.newLine();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
