package other;

import java.awt.*;
import java.awt.event.*;

class Grids extends Canvas {

	
	private static final long serialVersionUID = 1L;
	int width, height, rows, columns;

	Grids(int w, int h, int r, int c) {
		setSize(width = w, height = h);
		rows = r;
		columns = c;
	}

	public void paint(Graphics g) {
		int k;
		width = getSize().width;
		height = getSize().height;

		int htOfRow = height / (rows);
		for (k = 0; k < rows; k++)
			g.drawLine(0, k * htOfRow, width, k * htOfRow);

		int wdOfRow = width / (columns);
		for (k = 0; k < columns; k++)
			g.drawLine(k * wdOfRow, 0, k * wdOfRow, height);
	}
}

public class DrawGrid extends Frame {
	DrawGrid(String title, int w, int h, int rows, int columns) {
		setTitle(title);
		Grids grid = new Grids(w, h, rows, columns);
		add(grid);
	}

	public static void main(String[] args) {
		new DrawGrid("Draw Grids", 200, 200, 2, 10).setVisible(true);
	}
}