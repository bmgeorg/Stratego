package view;

import java.awt.Dimension;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class WaitForOpponent extends JDialog {
	private static final long serialVersionUID = 1L;

	private String getIpAddress() {
		String ipAddress;
		try {
			ipAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			ipAddress = "unfound";
		}
		return ipAddress;
	}

	WaitForOpponent(JFrame parent) {
		super(parent, true);

		Box box = new Box(BoxLayout.Y_AXIS);

		String ipAddress = getIpAddress();
		JLabel ipLabel1 = new JLabel("Your IP address");
		JLabel ipLabel2 = new JLabel(ipAddress);

		ipLabel1.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		ipLabel2.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		// Cap width of ip address label
		// Fuck this god-awful class Dimension. Why do they make it this hard?
		int maxWidth = Math.min(200, ipLabel2.getPreferredSize().width);
		ipLabel2.setMaximumSize(new Dimension(maxWidth, ipLabel2.getPreferredSize().height));
		ipLabel2.setToolTipText(ipAddress);

		ImageIcon waitingIcon = new ImageIcon("src/loading.gif");
		JLabel waitingLabel = new JLabel(waitingIcon);

		waitingLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		Dimension separatorSize = new Dimension(200, 20);

		box.add(Box.createRigidArea(separatorSize));
		box.add(ipLabel1);
		box.add(Box.createRigidArea(separatorSize));
		box.add(ipLabel2);
		box.add(Box.createRigidArea(separatorSize));
		box.add(waitingLabel);
		box.add(Box.createRigidArea(separatorSize));

		this.setResizable(false);
		this.add(box);
		this.pack();

		Thread connectThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					final int PORT = 4005;
					ServerSocket serverSocket = new ServerSocket(PORT);
					Socket clientSocket = serverSocket.accept();
					System.out.println("Connected");
				} catch (IOException e) {
					System.out.println("Failed");		
				}
			}
		});
		connectThread.start();
	}
}
