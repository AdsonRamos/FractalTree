import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class FractalPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int width, height;
	private static int len = 200;
	
	private boolean antiAliasing = false;

	int i = 1, j = 1;
	double[] coords;
	int x, y;
	
	int x1, y1, x2, y2;

	public FractalPanel(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void paint(Graphics g1) {
		super.paint(g1);
		
		Graphics2D g = (Graphics2D) g1;
		
		if(antiAliasing) {
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		} else {
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		}
		
		g.setColor(Color.BLACK);
		
		g.translate(width / 2, height);

		x = 0;
		y = -len;

		g.drawLine(0, 0, x, y);
		g.translate(x, y);

		drawTree(len, g, 1, x, y, 0);
		
	}
	
	public void setAntiAliasing(boolean antiAliasing) {
		this.antiAliasing = antiAliasing;
		repaint();
	}
	
	public void takePicture() throws IOException {
		BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g = image.createGraphics();
		paint(g);
		ImageIO.write(image, "png", new File("Tree.png"));
	}
	
	public void setTreeLength(int length){ 
		this.len = length;
		repaint();
	}
		
	int sizeMax = 1;
	private double angle = Math.PI/4;
	
	public void setAngle(double angle) {
		this.angle = angle;
		repaint();
	}

	private void drawTree(double length, Graphics g, int level, int xBefore, int yBefore, int n) {
		
		if(level == 1) {
			coords = rotate(angle, 0, -length);
		} else {			
			coords = rotate(n * angle, 0, -length);
		}

		x1 = (int) coords[0];
		y1 = (int) coords[1];

		if(level == 1) {
			g.drawLine(0, 0, 0, 0);
			g.translate(-x1, -y1);
		} else {
			g.drawLine(0, 0, x1, y1);
		}
		
		
		
		// to right
		if (length * 0.67 > sizeMax) {
			g.translate(x1, y1);
			drawTree(length * 0.67, g, level + 1, x1, y1, n + 1);
		}
		
		if(level == 1) {
			coords = rotate(-angle , 0, -length);
		} else {			
			coords = rotate(n* angle , 0, -length);
		}

		x2 = (int) coords[0];
		y2 = (int) coords[1];

		if(level == 1) {
			g.drawLine(0, 0, 0, 0);
			g.translate(-2*x2, 0);
		} else {
			g.drawLine(0, 0, x2, y2);
		}

		//to left
		if (length * 0.67 > sizeMax) {
			g.translate(x2, y2);
			drawTree(length * 0.67, g, level + 1, x2, y2, n - 1);
		}
		
		// se chegar nessa linha de comando, quer dizer que não tem mais nó para aprofundar. e, portanto, a origem do sistema, agora, deve ser a que deu origem ao ramo anterior
		g.translate(-xBefore, -yBefore);

	}

	// this array gives two numbers that are rotated coordinates
	private double[] rotate(double angle, double x, double y) {

		double[] coordinates = new double[2];

		coordinates[0] = (x * Math.cos(angle)) - (y * Math.sin(angle));
		coordinates[1] = (x * Math.sin(angle)) + (y * Math.cos(angle));

		return coordinates;
	}
}
