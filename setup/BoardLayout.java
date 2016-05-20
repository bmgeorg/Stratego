package setup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardLayout extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel[][] grid = new JLabel[10][10];

	BoardLayout() {
		//Use -1 pixel vgap and hgap to allow borders of same width
		this.setLayout(new GridLayout(10, 10, -1, -1));
		//this.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		//this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		Dimension size = new Dimension(86, 86);
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				grid[i][j] = new JLabel();
				grid[i][j].setPreferredSize(size);
				grid[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				this.add(grid[i][j]);
			}
		}
	}
}
