package setup;

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
	
	private CharacterPanelDelegate delegate;
	
	private Type[][] types = new Type[6][2];
	// All indexed by Type ordinal
	private ImageIcon[] images = new ImageIcon[12];
	private int[] counts = new int[12];
	private JLabel[] countLabels = new JLabel[12];

	CharacterPanel(CharacterPanelDelegate delegate) {
		this.delegate = delegate;
		
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
		
		String src = "src/images/";
		images[Type.MARSHAL.ordinal()] = new ImageIcon(src + "marshal.png");
		images[Type.GENERAL.ordinal()] = new ImageIcon(src + "general.png");
		images[Type.COLONEL.ordinal()] = new ImageIcon(src + "colonel.png");
		images[Type.MAJOR.ordinal()] = new ImageIcon(src + "major.png");
		images[Type.CAPTAIN.ordinal()] = new ImageIcon(src + "captain.png");
		images[Type.LIEUTENANT.ordinal()] = new ImageIcon(src + "lieutenant.png");
		images[Type.SERGEANT.ordinal()] = new ImageIcon(src + "sergeant.png");
		images[Type.MINER.ordinal()] = new ImageIcon(src + "miner.png");
		images[Type.SCOUT.ordinal()] = new ImageIcon(src + "scout.png");
		images[Type.SPY.ordinal()] = new ImageIcon(src + "spy.png");
		images[Type.BOMB.ordinal()] = new ImageIcon(src + "bomb.png");
		images[Type.FLAG.ordinal()] = new ImageIcon(src + "flag.png");
		
		counts[Type.FLAG.ordinal()] = 1;
		counts[Type.BOMB.ordinal()] = 6;
		counts[Type.SPY.ordinal()] = 1;
		counts[Type.SCOUT.ordinal()] = 8;
		counts[Type.MINER.ordinal()] = 5;
		counts[Type.SERGEANT.ordinal()] = 4;
		counts[Type.LIEUTENANT.ordinal()] = 4;
		counts[Type.CAPTAIN.ordinal()] = 4;
		counts[Type.MAJOR.ordinal()] = 3;
		counts[Type.COLONEL.ordinal()] = 2;
		counts[Type.GENERAL.ordinal()] = 1;
		counts[Type.MARSHAL.ordinal()] = 1;
		
		setLayout(new GridLayout(6, 2));
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 2; j++) {
				final Type type = types[i][j];
				Box box = new Box(BoxLayout.Y_AXIS);
				JLabel img = new JLabel(images[type.ordinal()]);
				img.setAlignmentX(JComponent.CENTER_ALIGNMENT);
				box.add(img);
				countLabels[type.ordinal()] = new JLabel(String.valueOf(counts[type.ordinal()]));
				box.add(countLabels[type.ordinal()]);
				this.add(box);
			}
		}
	}
	
	// x and y are in CharacterPanel coordinate system
	public void mousePressed(int x, int y) {
		int row = y*6/this.getHeight();
		int col = x*2/this.getWidth();
		if(row < 0 || row > 5 || col < 0 || col > 1) {
			return;
		}
		Type type = types[row][col];
		if(counts[type.ordinal()] > 0) {
			int offsetX = x - col*this.getWidth()/2;
			int offsetY = y - row*this.getHeight()/6;
			delegate.characterSelected(images[type.ordinal()], type, offsetX, offsetY);
		}
	}
	
	public void decrementCount(Type type) {
		counts[type.ordinal()]--;
		countLabels[type.ordinal()].setText(String.valueOf(counts[type.ordinal()]));
	}
	
	public void incrementCount(Type type) {
		counts[type.ordinal()]++;
		countLabels[type.ordinal()].setText(String.valueOf(counts[type.ordinal()]));
	}
}
