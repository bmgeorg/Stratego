package setup;

import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

public class BoardSetup extends JFrame implements CharacterDragger {
	private static final long serialVersionUID = 1L;

	private JLayeredPane pane;

	private CharacterPanel panel;
	private BoardLayout board;

	private JLabel selectedCharacterImage = null;
	private model.Type selectedCharacterType = null;

	private int offsetX = 0;
	private int offsetY = 0;

	BoardSetup() {

		pane = new JLayeredPane();
		pane.setPreferredSize(new Dimension(1100, 860));

		panel = new CharacterPanel(this);
		Dimension panelSize = panel.getPreferredSize();
		panel.setBounds(0, 0, panelSize.width, panelSize.height);
		pane.add(panel);

		board = new BoardLayout(this);
		Dimension boardSize = board.getPreferredSize();
		board.setBounds(panelSize.width+20, 0, boardSize.width, boardSize.height);
		pane.add(board);

		this.setResizable(false);
		this.add(pane);
		this.pack();

		MouseAdapter mouse = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(panel.getBounds().contains(e.getPoint())) {
					Point p = SwingUtilities.convertPoint(pane, e.getPoint(), panel);
					panel.mousePressed(p.x, p.y);
				}
				else if(board.getBounds().contains(e.getPoint())) {
					Point p = SwingUtilities.convertPoint(pane, e.getPoint(), board);
					board.mousePressed(p.x, p.y);
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(selectedCharacterImage != null) {
					if(board.getBounds().contains(e.getPoint())) {
						Point p = SwingUtilities.convertPoint(pane, e.getPoint(), board);
						model.Type replacedType = board.getCharacter(p.x, p.y);
						boolean success = board.placeCharacter(selectedCharacterType,
								selectedCharacterImage.getIcon(), p.x, p.y);
						if(success && replacedType != null) {
							panel.returnCharacter(replacedType);
						}
						if(!success) {
							panel.returnCharacter(selectedCharacterType);
						}
					} else {
						panel.returnCharacter(selectedCharacterType);
					}

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

	public void characterSelected(Icon image, model.Type type, int offsetX, int offsetY) {
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