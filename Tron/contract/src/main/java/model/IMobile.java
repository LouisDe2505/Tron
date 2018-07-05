package model;

import java.awt.Point;

import controller.LightcycleDirection;
import showboard.IPawn;

public interface IMobile extends IPawn, IElement {


	/**
     * Move up.
     */
    void moveUp();

    /**
     * Move left.
     */
    void moveLeft();

    /**
     * Move down.
     */
    void moveDown();

    /**
     * Move right.
     */
    void moveRight();
    
	void doNothing();
	
    /**
     * Gets the x.
     *
     * @return the x
     */
    @Override
    int getX();

    /**
     * Gets the y.
     *
     * @return the y
     */
    @Override
    int getY();

    /**
     * Checks if is alive.
     *
     * @return the alive
     */
    Boolean isAlive();
    
    /**
     * Kill the player
     */
	void die();
    
    /**
     * Set player alive
     */
	void alive();
	
    /**
     * Checks if a lightcycle as to be killed.
     *
     * @return the boolean
     */
	Boolean isKilled();

	/**
	 * GetPosition
	 */
    @Override
    Point getPosition();

	void setDirection(LightcycleDirection down);
	
	LightcycleDirection getDirection();
	
	PlayerColor getLightcycleColor();

	
}