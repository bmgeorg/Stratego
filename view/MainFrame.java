package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class MainFrame extends JFrame implements OpponentFinderDelegate {
	private static final long serialVersionUID = 1L;
	
	public void foundOpponent(Socket socket) {
		System.out.println("Found oppponent: " + socket.toString());
	}

	MainFrame() {
		Box box = new Box(BoxLayout.Y_AXIS);
		box.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		JButton findButton = new JButton("Find game");
		JButton waitButton = new JButton("Wait for game");
		
		findButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		waitButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		Dimension separatorSize = new Dimension(200, 20);

		box.add(Box.createRigidArea(separatorSize));
		box.add(findButton);
		box.add(Box.createRigidArea(separatorSize));
		box.add(waitButton);
		box.add(Box.createRigidArea(separatorSize));
		
		this.setResizable(false);
		this.add(box);
		this.pack();

		// Need access to this for dialog ctors
		final MainFrame mainFrame = this;
		
		findButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { 
				FindOpponent finder = new FindOpponent(mainFrame, mainFrame);
				finder.setVisible(true);
			} 
		});
		waitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { 
				WaitForOpponent waiter = new WaitForOpponent(mainFrame, mainFrame);
				waiter.setVisible(true);
			} 
		});
	}
}