package setup;

import javax.swing.JFrame;

import view.Board;

public class Main {
	public static void main(String[] args) {
		/*
		MainFrame frame = new MainFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		*/
		Board board = new Board();
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.setVisible(true);
	}
}