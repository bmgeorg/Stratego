package setup;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class BoardSetup extends JFrame implements CharacterPanelDelegate {
	private static final long serialVersionUID = 1L;

	BoardSetup() {
		add(new CharacterPanel(this));
		
		this.setResizable(false);
		this.pack();
	}

	@Override
	public void characterSelected(ImageIcon image, model.Type type) {
		System.out.println("Character selected " + type.toString());
	}
}
