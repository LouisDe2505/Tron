package model.element.motionless;

import java.io.IOException;

import model.ISprite;
import model.Permeability;
import model.element.Sprite;

public class Wall extends MotionlessElement {
	
	/** The Constant SPRITE. */
	private static final ISprite SPRITE = new Sprite('w', "Wall.jpg");

	/**
	* Instantiates a new wall.
	*/
	Wall() {
	    super(SPRITE, Permeability.KILLING);
    	try {
			SPRITE.loadImage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}