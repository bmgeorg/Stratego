package setup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Type;

public class BoardLayout extends JPanel {
	private static final long serialVersionUID = 1L;

	private CharacterDragger delegate;

	private JLabel[][] cells = new JLabel[10][10];
	private Type[][] characters = new Type[4][10];

	BoardLayout(CharacterDragger delegate) {
		this.delegate = delegate;

		//Use -1 pixel vgap and hgap to allow borders of same width
		this.setLayout(new GridLayout(10, 10, -1, -1));
		//this.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		//this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		Dimension size = new Dimension(70, 70);
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				cells[i][j] = new JLabel();
				cells[i][j].setPreferredSize(size);
				cells[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				this.add(cells[i][j]);
			}
		}
		
		this.setBackground(Color.BLUE);
	}

	// x and y are in BoardLayout coordinate system
	// Returns true if placement is successful
	boolean placeCharacter(Type character, Icon icon, int x, int y) {
		int row = y*10/this.getHeight();
		int col = x*10/this.getWidth();

		// If invalid placement, return false
		if(row < 6 || row > 9 || col < 0 || col > 9) {
			return false;
		}

		characters[row-6][col] = character;
		cells[row][col].setIcon(icon);
		return true;
	}

	// x and y are in BoardLayout coordinate system
	Type getCharacter(int x, int y) {
		int row = y*10/this.getHeight();
		int col = x*10/this.getWidth();

		// If invalid placement, return null
		if(row < 6 || row > 9 || col < 0 || col > 9) {
			return null;
		}
		return characters[row-6][col];
	}

	// x and y are in BoardLayout coordinate system
	void mousePressed(int x, int y) {
		int row = y*10/this.getHeight();
		int col = x*10/this.getWidth();
		if(row < 6 || row > 9 || col < 0 || col > 9) {
			return;
		}
		Type type = characters[row-6][col];
		if(type != null) {
			int offsetX = x - col*this.getWidth()/10;
			int offsetY = y - row*this.getHeight()/10;
			delegate.characterSelected(cells[row][col].getIcon(), type, offsetX, offsetY);
			
			characters[row-6][col] = null;
			cells[row][col].setIcon(null);
		}
	}
}
