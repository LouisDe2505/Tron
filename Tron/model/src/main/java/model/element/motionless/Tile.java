package model.element.motionless;

import java.io.IOException;

import model.ISprite;
import model.Permeability;
import model.element.Sprite;

public class Tile extends MotionlessElement {
	
	/** The Constant SPRITE. */
    private static final ISprite SPRITE = new Sprite(' ', "Tile.jpg");

    /**
    * Instantiates a new tile.
    */
    Tile() {
        super(SPRITE, Permeability.PENETRABLE);
    	try {
			SPRITE.loadImage();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
