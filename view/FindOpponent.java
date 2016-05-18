package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FindOpponent extends JDialog {
	private static final long serialVersionUID = 1L;

	FindOpponent(JFrame parent) {
		super(parent, true);

		Box box = new Box(BoxLayout.Y_AXIS);
		
		JLabel label = new JLabel("Opponent IP address");
		JTextField ipField = new JTextField();
		JButton searchButton = new JButton("Search");
		
		label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		ipField.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		searchButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		ipField.setMaximumSize(new Dimension(180, Integer.MAX_VALUE));
		
		Dimension separatorSize = new Dimension(200, 20);
		
		box.add(Box.createRigidArea(separatorSize));
		box.add(label);
		box.add(Box.createRigidArea(separatorSize));
		box.add(ipField);
		box.add(Box.createRigidArea(separatorSize));
		box.add(searchButton);
		box.add(Box.createRigidArea(separatorSize));
		
		ipField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		/*
		String ipAddress;
		try {
			ipAddress = InetAddress.getLocalHost().getHostAddress();
			//ipAddress = "12348823.12.34.1234.1j99238428931.123.412351235.123412235412.999";
		} catch (UnknownHostException e) {
			ipAddress = "unfound";
		}
		JLabel ipLabel1 = new JLabel("Your IP address");
		JLabel ipLabel2 = new JLabel(ipAddress)

		// Fuck this god-awful class Dimension. Why do they make it this hard?
		int maxWidth = Math.min(200, ipLabel2.getPreferredSize().width);
		ipLabel2.setMaximumSize(new Dimension(maxWidth, ipLabel2.getPreferredSize().height));
		ipLabel2.setToolTipText(ipAddress);
		*/

		this.setResizable(false);
		this.add(box);
		this.pack();
	}
}
