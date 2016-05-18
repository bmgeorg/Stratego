package setup;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		/*
		MainFrame frame = new MainFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		*/
		BoardSetup board = new BoardSetup();
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.setVisible(true);
	}
}