package model.element.mobile;

import java.io.IOException;

import controller.LightcycleDirection;
import model.ILevel;
import model.ISprite;
import model.Permeability;
import model.PlayerColor;
import model.element.Sprite;



public class Lightcycle extends Mobile implements ISprite{
	
	/** The Constant SPRITE. */
    private static final Sprite sprite = new Sprite('A', "Wall.jpg");
    
	/** The Constant SPRITE. */
    private static final Sprite spriteDieRed = new Sprite('A', "BoomRed.png");

    /** The Constant spriteTurnLeft. */
    private static final Sprite spriteMoveLeftRed = new Sprite('A', "BikeRedLeftB.png");

    /** The Constant spriteTurnRight. */
    private static final Sprite spriteMoveRightRed = new Sprite('A', "BikeRedRightB.png");
    
    /** The Constant spriteMoveUp. */
    private static final Sprite spriteMoveUpRed = new Sprite('A', "BikeRedUpB.png");

    /** The Constant spriteMoveDown. */
    private static final Sprite spriteMoveDownRed = new Sprite('A', "BikeRedDownB.png");
    
	/** The Constant SPRITE. */
    private static final Sprite spriteDieBlue = new Sprite('B', "BoomBlue.png");

    /** The Constant spriteTurnLeft. */
    private static final Sprite spriteMoveLeftBlue = new Sprite('B', "BikeBlueLeftB.png");

    /** The Constant spriteTurnRight. */
    private static final Sprite spriteMoveRightBlue = new Sprite('B', "BikeBlueRightB.png");
    
    /** The Constant spriteMoveUp. */
    private static final Sprite spriteMoveUpBlue = new Sprite('B', "BikeBlueUpB.png");

    /** The Constant spriteMoveDown. */
    private static final Sprite spriteMoveDownBlue = new Sprite('B', "BikeBlueDownB.png");
    
    /** The Variable that store the lightcycle color*/
    private PlayerColor LightcycleColor;
    
    private LightcycleDirection Direction;

	/**
     * Instantiates a new Lightcycle of a specific color.
     *
     * @param x
     *            the x
     * @param y
     *            the y
     * @param level
     *            the level
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public Lightcycle(final int x, final int y, final ILevel level, PlayerColor color) throws IOException {
        super(x, y, sprite, level, Permeability.KILLING);
        setLightcycleColor(color);
        sprite.loadImage();
        if ( getLightcycleColor() == PlayerColor.RED) {
	        spriteDieRed.loadImage();
	        spriteMoveLeftRed.loadImage();
	        spriteMoveRightRed.loadImage();
	        spriteMoveUpRed.loadImage();
	        spriteMoveDownRed.loadImage();
        }

	    else if ( getLightcycleColor() == PlayerColor.BLUE) {
	        spriteDieBlue.loadImage();
	        spriteMoveLeftBlue.loadImage();
	        spriteMoveRightBlue.loadImage();
	        spriteMoveUpBlue.loadImage();
	        spriteMoveDownBlue.loadImage();
	    }
    }
    

    /*
     * (non-Javadoc)
     * @see fr.exia.insanevehicles.model.element.mobile.Mobile#moveLeft()
     */
    @Override
    public final void moveLeft() {
        super.moveLeft();
        
        if ( getLightcycleColor() == PlayerColor.RED) {
	        this.setSprite(spriteMoveLeftRed);
        }

	    else if ( getLightcycleColor() == PlayerColor.BLUE) {
	    	this.setSprite(spriteMoveLeftBlue);
	    }
    }

    /*
     * (non-Javadoc)
     * @see fr.exia.insanevehicles.model.element.mobile.Mobile#moveRight()
     */
    @Override
    public final void moveRight() {
        super.moveRight();
        
        if ( getLightcycleColor() == PlayerColor.RED) {
        	this.setSprite(spriteMoveRightRed);
        }

	    else if ( getLightcycleColor() == PlayerColor.BLUE) {
	    	this.setSprite(spriteMoveRightBlue);
	    }
    }
    
    /*
     * (non-Javadoc)
     * @see fr.exia.insanevehicles.model.element.mobile.Mobile#moveLeft()
     */
    @Override
    public final void moveUp() {
        super.moveUp();
        
        if ( getLightcycleColor() == PlayerColor.RED) {
        	this.setSprite(spriteMoveUpRed);
        }

	    else if ( getLightcycleColor() == PlayerColor.BLUE) {
	    	this.setSprite(spriteMoveUpBlue);
	    }
    }

    /*
     * (non-Javadoc)
     * @see fr.exia.insanevehicles.model.element.mobile.Mobile#moveRight()
     */
    @Override
    public final void moveDown() {
        super.moveDown();
       
        if ( getLightcycleColor() == PlayerColor.RED) {
        	this.setSprite(spriteMoveDownRed);
        }

	    else if ( getLightcycleColor() == PlayerColor.BLUE) {
	    	this.setSprite(spriteMoveDownBlue);
	    }
    }
    
    /*
     * (non-Javadoc)
     * @see fr.exia.insanevehicles.model.element.mobile.Mobile#moveRight()
     */
    @Override
    public final void doNothing() {
        super.doNothing();
       
        if ( getLightcycleColor() == PlayerColor.RED) {
        	this.setSprite(spriteDieRed);
        }

	    else if ( getLightcycleColor() == PlayerColor.BLUE) {
	    	this.setSprite(spriteDieBlue);
	    }
    }
    /*
     * (non-Javadoc)
     * @see fr.exia.insanevehicles.model.element.mobile.Mobile#die()
     */
    @Override
	public final void die() {
        this.setDirection(LightcycleDirection.STOP);
        super.die();
        
        if ( getLightcycleColor() == PlayerColor.RED) {
        	this.setSprite(spriteDieRed);
        }

	    else if ( getLightcycleColor() == PlayerColor.BLUE) {
	    	this.setSprite(spriteDieBlue);
	    }
    }

	@Override
	public void loadImage() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public char getConsoleImage() {
		// TODO Auto-generated method stub
		return 0;
	}	
	
    public PlayerColor getLightcycleColor() {
		return LightcycleColor;
	}


	public void setLightcycleColor(PlayerColor lightcycleColor) {
		LightcycleColor = lightcycleColor;
	}


	public LightcycleDirection getDirection() {
		return Direction;
	}


	public void setDirection(LightcycleDirection direction) {
		Direction = direction;
	}

}
