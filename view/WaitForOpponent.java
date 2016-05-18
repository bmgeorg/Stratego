package view;

import java.awt.Dimension;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

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

		// Need access to this in SwingWorker
		JDialog dialog = this;
		SwingWorker<Socket, Void> worker = new SwingWorker<Socket, Void>() {
			@Override
			public Socket doInBackground() {
				final int PORT = 4005;
				Socket socket = null;
				try(ServerSocket serverSocket = new ServerSocket(PORT)) {
					socket = serverSocket.accept();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return socket;
			}

			@Override
			public void done() {
				Socket socket = null;
				try {
					socket = get();
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		};
		worker.execute();
	}
}
