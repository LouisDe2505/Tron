package model;

import java.util.Observable;

public interface ILevel {

    /**
     * Gets the width.
     *
     * @return the width
     */
    int getWidth();

    /**
     * Gets the height.
     *
     * @return the height
     */
    int getHeight();

    /**
     * Gets the on the road XY.
     *
     * @param x
     *            the x
     * @param y
     *            the y
     * @return the on the road XY
     */
    IElement getOnTheLevelXY(int x, int y);

    /**
     * Sets the mobile has changed.
     */
    void setMobileHasChanged();

    /**
     * Gets the observable.
     *
     * @return the observable
     */
    Observable getObservable();

    /**
     * Gets the Lightcycle.
     *
     * @return the Lightcycle
     */
	IMobile getBlueLightcycle();
	
    /**
     * Gets the Lightcycle.
     *
     * @return the Lightcycle
     */
	IMobile getRedLightcycle();
	
	IElement createLightWall(int x, int y, PlayerColor color);

}