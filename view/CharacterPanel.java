package view;

import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Type;

public class CharacterPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private ImageIcon[][] images = new ImageIcon[6][2];
	private Type[][] types = new Type[6][2];
	private int[][] counts = new int[6][2];

	CharacterPanel() {
		String src = "src/images/";
		images[0][0] = new ImageIcon(src + "marshal.png");
		images[0][1] = new ImageIcon(src + "general.png");
		images[1][0] = new ImageIcon(src + "colonel.png");
		images[1][1] = new ImageIcon(src + "major.png");
		images[2][0] = new ImageIcon(src + "captain.png");
		images[2][1] = new ImageIcon(src + "lieutenant.png");
		images[3][0] = new ImageIcon(src + "sergeant.png");
		images[3][1] = new ImageIcon(src + "miner.png");
		images[4][0] = new ImageIcon(src + "scout.png");
		images[4][1] = new ImageIcon(src + "spy.png");
		images[5][0] = new ImageIcon(src + "bomb.png");
		images[5][1] = new ImageIcon(src + "flag.png");
		
		types[0][0] = Type.MARSHAL;
		types[0][1] = Type.GENERAL;
		types[1][0] = Type.COLONEL;
		types[1][1] = Type.MAJOR;
		types[2][0] = Type.CAPTAIN;
		types[2][1] = Type.LIEUTENANT;
		types[3][0] = Type.SERGEANT;
		types[3][1] = Type.MINER;
		types[4][0] = Type.SCOUT;
		types[4][1] = Type.SPY;
		types[5][0] = Type.BOMB;
		types[5][1] = Type.FLAG;
		
		counts[0][0] = 1;
		counts[0][1] = 1;
		counts[1][0] = 2;
		counts[1][1] = 3;
		counts[2][0] = 4;
		counts[2][1] = 4;
		counts[3][0] = 4;
		counts[3][1] = 5;
		counts[4][0] = 8;
		counts[4][1] = 1;
		counts[5][0] = 6;
		counts[5][1] = 1;
		
		setLayout(new GridLayout(6, 2));
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 2; j++) {
				Box box = new Box(BoxLayout.Y_AXIS);
				JLabel img = new JLabel(images[i][j]);
				img.setAlignmentX(JComponent.CENTER_ALIGNMENT);
				box.add(img);
				JLabel count = new JLabel(String.valueOf(counts[i][j]));
				box.add(count);
				add(box);
				
				//box.addmouse
			}
		}
		
	}
}
