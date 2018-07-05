package model.element.motionless;

import java.io.IOException;

import model.ISprite;
import model.Permeability;
import model.element.Sprite;

public class LightWallRed extends MotionlessElement {
	
	/** The Constant SPRITE. */
    private static final ISprite SPRITE = new Sprite('1', "WallRed.jpg");


    /**
    * Instantiates a new LightWall of the same color as the lightcolor.
    */
    LightWallRed() {
    	super(SPRITE, Permeability.KILLING);
    	try {
			SPRITE.loadImage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
