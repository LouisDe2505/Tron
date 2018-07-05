package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Observable;

import model.element.mobile.Lightcycle;
import model.element.motionless.MotionlessElementFactory;

public class Level extends Observable implements ILevel {
    /** The width. */
    public int          width = 60;

    /** The height. */
    public int          height = 40;

    /** The on the level. */
    private IElement[][] onTheLevel;
    
    /** The red Lightcycle. */
    private IMobile redLightcycle;
    
    /** The blue Lightcycle. */
    private IMobile blueLightcycle;
    
    /**
     * Instantiates a new level with the content of the db.
     *
     * @param fileName
     *            the file name where the map of the road is
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws SQLException 
     */
    Level(final int idlevel) throws IOException, SQLException {       
    	super();
        this.loadLevel(idlevel);
    }

    /**
     * Loads the level stored all motion less position, instantiate all mobile element.
     *
     * @param fileName
     *            the file name
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws SQLException 
     */
	private void loadLevel(final int idlevel) throws IOException {
		
        this.onTheLevel = new IElement[this.getWidth()][this.getHeight()];

    	for (int n=0; n<height; n++)
    	{
    		for (int i=0; i < width;i++)
    		{
    			this.setOnTheLevelXY(MotionlessElementFactory.getFromFileSymbol(' '), i, n);
    		}
    	}

        final BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(( "level/" + Integer.toString(idlevel) + ".txt"))));
        String line;
        int y = 0;

        line = buffer.readLine();
        while (line != null) {
            for (int x = 0; x < line.toCharArray().length; x++) {
            	char temp = line.toCharArray()[x];
            	
            	switch (temp) {
            	case 'B':
            		setRedLightcycle(new Lightcycle(x, y, this, PlayerColor.RED));
            		break;
            	case 'A':
            		setBlueLightcycle(new Lightcycle(x, y, this, PlayerColor.BLUE));
                	break;
                default:
                	this.setOnTheLevelXY(MotionlessElementFactory.getFromFileSymbol(temp), x, y);
                break;
                }
            	
            }
            line = buffer.readLine();
            y++;
        }

        buffer.close();
    }
	
	public IElement createLightWall(int x, int y, PlayerColor color) {
			if(color == PlayerColor.RED) this.setOnTheLevelXY(MotionlessElementFactory.createLightWallRed(), x, y);
			else if(color == PlayerColor.BLUE) this.setOnTheLevelXY(MotionlessElementFactory.createLightWallBLue(), x, y);
		return this.getOnTheLevelXY(x, y);
	}
    
    /**
     * get the width
     */
    @Override
    public final int getWidth() {
        return this.width;
    }

    /**
     * get the height
     */
    @Override
    public final int getHeight() {
        return this.height;
    }

    /**
     * get element by XY
     */
    @Override
    public final IElement getOnTheLevelXY(final int x, final int y) {
        return this.onTheLevel[x][y];
    }

    /**
     * Sets the on the level XY.
     *
     * @param element
     *            the element
     * @param x
     *            the x
     * @param y
     *            the y
     */
    public void setOnTheLevelXY(final IElement element, final int x, final int y) {
        this.onTheLevel[x][y] = element;
    }

    /**
     * Notify view of change
     */
    @Override
    public final void setMobileHasChanged() {
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Get the observable
     */
    @Override
    public Observable getObservable() {
        return this;
    }

    @Override
	public IMobile getRedLightcycle() {
		return redLightcycle;
	}

	public void setRedLightcycle(IMobile redLightcycle) {
		this.redLightcycle = redLightcycle;
	}

    @Override
	public IMobile getBlueLightcycle() {
		return blueLightcycle;
	}

	public void setBlueLightcycle(IMobile blueLightcycle) {
		this.blueLightcycle = blueLightcycle;
	}
}