package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FindOpponent extends JDialog {
	private static final long serialVersionUID = 1L;
	private final Box box;
	private final Box infoBox;
	private final JLabel waitingLabel;
	private final JLabel connectionFailedLabel;

	FindOpponent(JFrame parent) {
		super(parent, true);

		box = new Box(BoxLayout.Y_AXIS);

		JLabel label = new JLabel("Opponent IP address");
		JTextField ipField = new JTextField();
		JButton searchButton = new JButton("Search");

		label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		ipField.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		searchButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		ipField.setMaximumSize(new Dimension(180, Integer.MAX_VALUE));

		infoBox = new Box(BoxLayout.Y_AXIS);

		ImageIcon waitingIcon = new ImageIcon("src/loading.gif");
		waitingLabel= new JLabel(waitingIcon);
		waitingLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		connectionFailedLabel = new JLabel("Failed to connect");
		connectionFailedLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		Dimension separatorSize = new Dimension(200, 20);

		box.add(Box.createRigidArea(separatorSize));
		box.add(label);
		box.add(Box.createRigidArea(separatorSize));
		box.add(ipField);
		box.add(Box.createRigidArea(separatorSize));
		box.add(searchButton);
		box.add(Box.createRigidArea(separatorSize));
		box.add(infoBox);
		box.add(Box.createRigidArea(separatorSize));

		ipField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchForOpponent(ipField.getText());
			}
		});
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchForOpponent(ipField.getText());
			}
		});

		this.setResizable(false);
		this.add(box);
		this.pack();
	}

	private void searchForOpponent(String ipAddress) {
		infoBox.remove(connectionFailedLabel);
		infoBox.add(waitingLabel);
		box.validate();
		this.pack();

		Thread connectThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Socket socket = new Socket();
					socket.connect(new InetSocketAddress(ipAddress, 4005), 5000); 
					System.out.println("Connected");
				} catch (IOException e) {
					System.out.println("Failed");		
				}
			}
		});
		connectThread.start();
		
		/*
		infoBox.remove(waitingLabel);
		infoBox.add(connectionFailedLabel);
		box.validate();
		this.pack();
		*/
	}
}
