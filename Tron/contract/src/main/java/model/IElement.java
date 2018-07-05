package model;

import java.awt.Image;
import showboard.ISquare;


public interface IElement extends ISquare{
    /**
     * Gets the sprite.
     *
     * @return the sprite
     */
    ISprite getSprite();

    /**
     * Gets the permeability.
     *
     * @return the permeability
     */
    Permeability getPermeability();

    /*
     * (non-Javadoc)
     * @see fr.exia.showboard.ISquare#getImage()
     */
    @Override
    Image getImage();

	void setPermeability(Permeability permeability);

}