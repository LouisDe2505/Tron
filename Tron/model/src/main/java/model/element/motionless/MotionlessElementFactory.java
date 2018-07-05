package model.element.motionless;

public abstract class MotionlessElementFactory {
    
    /** The Constant Tile (black background). */
    private static final Tile tile = new Tile();
    
    /** The Constant Tile (black background). */
    private static final Wall wall = new Wall();
    
    /** The Constant Tile (black background). */
    private static final LightWallRed lightwallred = new LightWallRed();
    
    private static final LightWallBlue lightwallblue = new LightWallBlue();
	
    private static MotionlessElement[]       motionlessElements  = {
            tile,
            wall,
            lightwallred,
            lightwallblue
            };
    
    /**
     * Creates a new bone MotionlessElements object.
     *
     * @return the motionless element
     */
    public static MotionlessElement createWall() {
        return wall;
    }
    
    /**
     * Creates a new bone MotionlessElements object.
     *
     * @return the motionless element
     */
    public static MotionlessElement createLightWallRed() {
        return lightwallred;
    }
    
    /**
     * Creates a new bone MotionlessElements object.
     *
     * @return the motionless element
     */
    public static MotionlessElement createLightWallBLue() {
        return lightwallblue;
    }
    

    /**
     * Gets the good MotionlessElement from file symbol.
     *
     * @param fileSymbol
     *            the file symbol
     * @return the from file symbol
     */
    public static MotionlessElement getFromFileSymbol(final char fileSymbol) {
    	
        for (final MotionlessElement motionlessElement : motionlessElements) {
            if (motionlessElement.getSprite().getConsoleImage() == fileSymbol) {
                return motionlessElement;
            }
        }
        return tile;
    }

}