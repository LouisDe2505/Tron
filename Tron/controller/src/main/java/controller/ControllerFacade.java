package controller;

import java.io.IOException;

import model.IMobile;
import model.IModelFacade;
import view.IViewFacade;

public class ControllerFacade implements IControllerFacade, IOrderPerformer {

    /** The game-thread refresh speed. */
    private static final int speed = 100;

    /** The view. */
    private IViewFacade view;

    /** The model. */
    private IModelFacade model;

    /** The stack order. */
    private UserOrder stackOrder;
    
    /** The Red LightCycle. */
    private IMobile redLightcycle;
    
    /** The Blue LightCycle. */
    private IMobile blueLightcycle;
	
    /**
     * Instantiates a new Lorann controller
     * It will be used to move the player, monsters, power and the level and also to check if there is collision, kill ...
     *
     *
     * @param view
     *            the view
     * @param model
     *            the model
     */
	public ControllerFacade(final IViewFacade view, final IModelFacade model) {
		this.setView(view);
	    this.setModel(model);
	    this.clearStackOrder();
		redLightcycle = getModel().getLevel().getRedLightcycle();
		redLightcycle.alive();
		redLightcycle.setDirection(LightcycleDirection.UP);
		blueLightcycle = getModel().getLevel().getBlueLightcycle();
		blueLightcycle.alive();
		blueLightcycle.setDirection(LightcycleDirection.DOWN);
	}

	/**
	 * Drive the game element movement, behavior and threading
	 * @throws IOException 
	 */

	@Override
	public void play() throws InterruptedException, IOException {
		
      //----------------------------------------------------------------------------
      //								GAME LOOP
      //----------------------------------------------------------------------------
		while (redLightcycle.isAlive() && blueLightcycle.isAlive()) { 
            
			Thread.sleep(speed); //make the thread sleep for a little time (in milliseconds)
			
			switch (this.getStackOrder()) { //this case execute the method associated to the user order (move, shot, nothing)
                case P1RIGHT:
                	rightMovePerformer(redLightcycle);
                	noMovePerformer(blueLightcycle);
                    break;
                case P1LEFT:
                	leftMovePerformer(redLightcycle);
                	noMovePerformer(blueLightcycle);
                    break;
                case P2RIGHT:
                	rightMovePerformer(blueLightcycle);
                	noMovePerformer(redLightcycle);
                    break;
                case P2LEFT:
                	leftMovePerformer(blueLightcycle);
                	noMovePerformer(redLightcycle);
                    break;
                case NOP:
                default:
                	noMovePerformer(redLightcycle);
                	noMovePerformer(blueLightcycle);
                	break;
			}
			spawnLightWall(blueLightcycle);
			spawnLightWall(redLightcycle);

			checkLightcycleCollision(redLightcycle, blueLightcycle);
			
			//if a Lightcycle is on something that kill him then we stop the game and say you loose
			if(redLightcycle.isKilled()) killerPerformer(redLightcycle);
			if(blueLightcycle.isKilled()) killerPerformer(blueLightcycle);
			
			
            this.clearStackOrder(); // this reset the user order to NOP so it will not continue to move when you released the key

        }
		if (!redLightcycle.isAlive() && blueLightcycle.isAlive()) this.getView().displayMessage("The voice : Blue player you're the choosen one (winner), congratitulation !"); //when the main loop is break this display the message you loose on a popup 	
		else if (!blueLightcycle.isAlive() && redLightcycle.isAlive()) this.getView().displayMessage("The voice : Red player you're the choosen one (winner), congratitulation !");
		else this.getView().displayMessage("The voice : Hum it look like every body died in this war :/ ");
	}
	
	private void spawnLightWall(IMobile lightcycle) throws IOException {
		int x = lightcycle.getX();
		int y = lightcycle.getY();
		
		switch(lightcycle.getDirection()) {
		case DOWN:
			y = y-1;
			break;
		case UP:
			y = y+1;
			break;
		case RIGHT:
			x = x-1;
			break;
		case LEFT:
			x = x+1;
			break;
		case STOP:
		default:
			break;
		}
		getModel().getLevel().createLightWall(x, y, lightcycle.getLightcycleColor());
		getView().LightwallPaint(x, y);
}
	
	
	private void checkLightcycleCollision(IMobile lightcycle,IMobile lightcycle2) {
		if(lightcycle.getX()==lightcycle2.getX() && lightcycle.getY()==lightcycle2.getY()) {
			lightcycle.die();
			lightcycle2.die();
		}
	}
	
	private void killerPerformer(IMobile lightcycle) {
		switch(lightcycle.getDirection()) {
			case DOWN:
				lightcycle.moveUp();
				lightcycle.die();
				break;
			case UP:
				lightcycle.moveDown();
				lightcycle.die();
				break;
			case RIGHT:
				lightcycle.moveLeft();
				lightcycle.die();
				break;
			case LEFT:
				lightcycle.moveRight();
				lightcycle.die();
				break;
			case STOP:
			default:
				lightcycle.doNothing();
				break;	
		}
	}
	
	private void noMovePerformer(IMobile lightcycle) {
		switch(lightcycle.getDirection()) {
			case UP:
				lightcycle.moveUp();
				break;
			case DOWN:
				lightcycle.moveDown();
				break;
			case LEFT:
				lightcycle.moveLeft();
				break;
			case RIGHT:
				lightcycle.moveRight();
				break;
			case STOP:
			default:
				lightcycle.doNothing();
				break;	
		}
	}
	
	private void rightMovePerformer(IMobile lightcycle) {
		switch(lightcycle.getDirection()) {
			case UP:
				lightcycle.moveRight();
				lightcycle.setDirection(LightcycleDirection.RIGHT);
				break;
			case DOWN:
				lightcycle.moveLeft();
				lightcycle.setDirection(LightcycleDirection.LEFT);
				break;
			case LEFT:
				lightcycle.moveUp();
				lightcycle.setDirection(LightcycleDirection.UP);
				break;
			case RIGHT:
				lightcycle.moveDown();
				lightcycle.setDirection(LightcycleDirection.DOWN);
				break;
			case STOP:
			default:
				lightcycle.doNothing();
				break;	
		}
	}
	
	private void leftMovePerformer(IMobile lightcycle) {
		switch(lightcycle.getDirection()) {
			case UP:
				lightcycle.moveLeft();
				lightcycle.setDirection(LightcycleDirection.LEFT);
				break;
			case DOWN:
				lightcycle.moveRight();
				lightcycle.setDirection(LightcycleDirection.RIGHT);
				break;
			case LEFT:
				lightcycle.moveDown();
				lightcycle.setDirection(LightcycleDirection.DOWN);
				break;
			case RIGHT:
				lightcycle.moveUp();
				lightcycle.setDirection(LightcycleDirection.UP);
				break;
			case STOP:
			default:
				lightcycle.doNothing();
				break;	
		}
	}
	
    /**
     * Write the UserOrder in the stack of order (stackOrder)
     */
	@Override
	public void orderPerform(UserOrder userOrder) throws IOException {
		this.setStackOrder(userOrder);
	}
	
    /**
     * Gets the view.
     *
     * @return the view
     */
    private IViewFacade getView() {
        return this.view;
    }
    
    /**
     * Sets the view.
     *
     * @param view
     *            the view to set
     */
    private void setView(final IViewFacade view) {
        this.view = view;
    }
    
    /**
     * Gets the model.
     *
     * @return the model
     */
    private IModelFacade getModel() {
        return this.model;
    }
    
    /**
     * Sets the model.
     *
     * @param model
     *            the model to set
     */
    private void setModel(final IModelFacade model) {
        this.model = model;
    }
    
    /**
     * Gets the stack order.
     *
     * @return the stack order
     */
    private UserOrder getStackOrder() {
        return this.stackOrder;
    }

    /**
     * Sets the stack order.
     *
     * @param stackOrder
     *            the new stack order
     */
    private void setStackOrder(final UserOrder stackOrder) {
        this.stackOrder = stackOrder;
    }

    /**
     * Clear stack order.
     */
    private void clearStackOrder() {
        this.stackOrder = UserOrder.NOP;
    }

   /**
    * Get the order to perform
    */
    @Override
    public IOrderPerformer getOrderPeformer() {
        return this;
    }

}