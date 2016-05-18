package setup;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutionException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

public class FindOpponent extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private OpponentFinderDelegate delegate;
	private final Box box;
	private final Box infoBox;
	private final JLabel waitingLabel;
	private final JLabel connectionFailedLabel;

	FindOpponent(JFrame parent, OpponentFinderDelegate delegate) {
		super(parent, true);
		this.delegate = delegate;

		box = new Box(BoxLayout.Y_AXIS);

		JLabel label = new JLabel("Opponent IP address");
		JTextField ipField = new JTextField();
		JButton searchButton = new JButton("Search");

		label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		ipField.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		searchButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		ipField.setMaximumSize(new Dimension(180, Integer.MAX_VALUE));

		infoBox = new Box(BoxLayout.Y_AXIS);

		ImageIcon waitingIcon = new ImageIcon("src/images/loading.gif");
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

		this.setResizable(false);
		this.add(box);
		this.pack();

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
	}

	private void searchForOpponent(String ipAddress) {
		infoBox.remove(connectionFailedLabel);
		infoBox.add(waitingLabel);
		box.validate();
		this.pack();

		// Need access to this in SwingWorker
		JDialog dialog = this;
		SwingWorker<Socket, Void> worker = new SwingWorker<Socket, Void>() {
			@Override
			public Socket doInBackground() {
				Socket socket = new Socket();
				final int PORT = 4005;
				final int TIMEOUT = 5000; // ms
				try {
					socket.connect(new InetSocketAddress(ipAddress, PORT), TIMEOUT);
					return socket;
				} catch (IOException e) {
					return null;
				} 
			}

			@Override
			public void done() {
				Socket socket = null;
				try {
					socket = get();
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
				if(socket == null) {
					// Failed connection
					infoBox.remove(waitingLabel);
					infoBox.add(connectionFailedLabel);
					box.validate();
					dialog.pack();
				} else {
					// Successful connection
					infoBox.remove(waitingLabel);
					infoBox.remove(connectionFailedLabel);
					box.validate();
					dialog.pack();
					delegate.foundOpponent(socket);
				}
			}
		};
		worker.execute();
	}
}
