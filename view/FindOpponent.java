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

		this.setResizable(false);
		this.add(box);
		this.pack();
	}
}
