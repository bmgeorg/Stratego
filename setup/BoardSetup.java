package setup;

import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

public class BoardSetup extends JFrame implements CharacterPanelDelegate {
	private static final long serialVersionUID = 1L;

	private JLayeredPane pane;
	
	private CharacterPanel panel;
	private JLabel selectedCharacterImage = null;
	private model.Type selectedCharacterType = null;
	
	private int offsetX = 0;
	private int offsetY = 0;

	BoardSetup() {

		pane = new JLayeredPane();
		pane.setPreferredSize(new Dimension(500, 700));
		
		panel = new CharacterPanel(this);
		pane.add(panel);
		Dimension size = panel.getPreferredSize();
		panel.setBounds(0, 0, size.width, size.height);

		this.setResizable(false);
		this.add(pane);
		this.pack();

		MouseAdapter mouse = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point p = SwingUtilities.convertPoint(pane, e.getPoint(), panel);
				panel.mousePressed(p.x, p.y);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(selectedCharacterImage != null) {
					pane.remove(selectedCharacterImage);
					selectedCharacterImage = null;
					selectedCharacterType = null;
					pane.repaint();
				}
			}
			@Override
			public void mouseDragged(MouseEvent e) {
				if(selectedCharacterImage != null) {
					
					selectedCharacterImage.setLocation(e.getX()-offsetX, e.getY()-offsetY);
				}
			}
		};
		pane.addMouseListener(mouse);
		pane.addMouseMotionListener(mouse);
	}

	public void characterSelected(ImageIcon image, model.Type type, int offsetX, int offsetY) {
		System.out.println("Character selected " + type.toString());
		selectedCharacterImage = new JLabel(image);
		selectedCharacterType = type;
		this.offsetX = offsetX;
		this.offsetY = offsetY;

		pane.add(selectedCharacterImage, JLayeredPane.DRAG_LAYER);

		Point p = MouseInfo.getPointerInfo().getLocation();
		SwingUtilities.convertPointFromScreen(p, pane);
		Dimension size = selectedCharacterImage.getPreferredSize();
		selectedCharacterImage.setBounds(p.x-offsetX, p.y-offsetY, size.width, size.height);
	}
}