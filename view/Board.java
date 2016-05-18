package view;

import javax.swing.JFrame;

public class Board extends JFrame {
	public Board() {
		add(new CharacterPanel());
		
		this.setResizable(false);
		this.pack();
	}
}
