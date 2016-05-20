package setup;

import java.awt.Point;

import javax.swing.ImageIcon;

import model.Type;

public interface CharacterPanelDelegate {
	public void characterSelected(ImageIcon image, Type type, int offsetX, int offsetY);
}