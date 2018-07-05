package model.element.motionless;

import java.io.IOException;

import model.ISprite;
import model.Permeability;
import model.element.Sprite;

public class LightWallBlue extends MotionlessElement {
	
    /** The Constant SPRITE. */
    private static final ISprite SPRITE = new Sprite('2', "WallBlue.jpg");


    /**
    * Instantiates a new LightWall of the same color as the lightcolor.
    */
    LightWallBlue() {
    	super(SPRITE, Permeability.KILLING);
    	try {
			SPRITE.loadImage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
