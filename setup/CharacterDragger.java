package setup;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import model.Type;

public interface CharacterDragger {
	public void characterSelected(Icon image, Type type, int offsetX, int offsetY);
}