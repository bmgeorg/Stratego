package setup;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
	
	private ImageIcon[][] images = new ImageIcon[6][2];
	private Type[][] types = new Type[6][2];
	private int[] counts = new int[12];
	private JLabel[] countLabels = new JLabel[12];

	CharacterPanel(CharacterPanelDelegate delegate) {
		this.delegate = delegate;
		
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
				JLabel img = new JLabel(images[i][j]);
				img.setAlignmentX(JComponent.CENTER_ALIGNMENT);
				box.add(img);
				countLabels[type.ordinal()] = new JLabel(String.valueOf(counts[type.ordinal()]));
				box.add(countLabels[type.ordinal()]);
				add(box);
				
				final int row = i;
				final int col = j;
				box.addMouseListener(new MouseListener() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(counts[type.ordinal()] > 0) {
							delegate.characterSelected(images[row][col], types[row][col]);
						}
					}
					@Override
					public void mousePressed(MouseEvent e) {}
					@Override
					public void mouseReleased(MouseEvent e) {}
					@Override
					public void mouseEntered(MouseEvent e) {}
					@Override
					public void mouseExited(MouseEvent e) {}
				});
			}
		}
	}
	
	public void characterPlaced(Type type) {
		counts[type.ordinal()]--;
		countLabels[type.ordinal()].setText(String.valueOf(counts[type.ordinal()]));
	}
}
