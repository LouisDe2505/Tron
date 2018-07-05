package model;

import java.awt.Image;
import java.io.IOException;

public interface ISprite {

	 /**
     * Gets Image.
     *
     * @return Image
     */
	Image getImage();
	
	 /**
     * Load image.
     */
	void loadImage() throws IOException;
	
	 /**
     * Gets Image console char.
     *
     * @return char
     */
	char getConsoleImage();

}