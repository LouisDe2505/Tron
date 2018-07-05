package view;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import controller.IOrderPerformer;
import controller.UserOrder;
import model.ILevel;
import showboard.BoardFrame;



public class ViewFacade implements IViewFacade, Runnable, KeyListener {

    /** The Constant width square number of the levels. */
    private static final int squareNumberWidth =60;
    
    /** The Constant height square number of the levels. */
    private static final int squareNumberHeight = 40;
    
    /** The Constant squareSize to make the window bigger or smaller but keeping the proportions . */
    private static final int squareSize = 20;

    /** The Constant closeView its the window launch by the thread. */
    private Rectangle        closeView;
    
    /** The Constant BoardFrame*/
    private BoardFrame boardFrame;
    
    /** The level. */
    private ILevel           level;

    /** The order performer. */
    private IOrderPerformer  orderPerformer;
    
    /**
     * Instantiates a new View.
     * It will create the window, a frame and a kind of plate with square to place element of the level
     * All of this his load and shown by the thread
     *
     * @param level
     *            the level
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public ViewFacade(final ILevel level) throws IOException {
    	this.setLevel(level); //create in memory the level (x,y,picture)
    	//this defines a Rectangle in the closing view that begins at the coordinates 0,0 and whose width and height are those of the level (square unity)
        this.setCloseView(new Rectangle(0, 0, squareNumberWidth, squareNumberHeight));
        //this launch the thread it launch the method run(), apart the all game
        SwingUtilities.invokeLater(this); 
    }

   /**
    * Display a message in a popup
    */
    @Override
    public final void displayMessage(final String message) {
        JOptionPane.showMessageDialog(null, message);
    }

   /**
    * Thread that run the window
    */
	@Override
    public final void run() { 
        boardFrame = new BoardFrame("TRON");
        boardFrame.setDimension(new Dimension(squareNumberWidth, squareNumberHeight)); // set the dimension of the panel to the level (square unity)
        boardFrame.setDisplayFrame(this.getCloseView()); //say what to display in the frame
        boardFrame.setSize(squareNumberWidth * squareSize, squareNumberHeight * squareSize); //set the size of the frame (pixel unity) 
        boardFrame.addKeyListener(this); //the window can listen to keyboard entry

        for (int x = 0; x < squareNumberWidth; x++) { //this double for set each square to his sprite picture
            for (int y = 0; y < squareNumberHeight; y++) {
                boardFrame.addSquare(this.level.getOnTheLevelXY(x, y), x, y);
            }
        }
        
        boardFrame.addPawn(getLevel().getRedLightcycle()); //this spawn the red Lightcycle over a square
        boardFrame.addPawn(getLevel().getBlueLightcycle()); //this spawn the blue Lightcycle over a square
        
        this.getLevel().getObservable().addObserver(boardFrame.getObserver()); //the view is registred to be observed by the level
        boardFrame.setVisible(true); //make the game appear in first plan
    }
	
	@Override
	public void LightwallPaint(int x, int y) {
		
		boardFrame.addSquare(this.level.getOnTheLevelXY(x, y), x, y);	

	}

	
    /**
     * Key code to user order.
     * It choose the right user order in function of the keycode
     *
     * @param keyCode
     *            the key code
     * @return the user order
     */
    static UserOrder keyCodeToUserOrder(final int keyCode) {
        UserOrder userOrder;
                
        switch (keyCode) {
            case KeyEvent.VK_RIGHT:
            	userOrder = UserOrder.P1RIGHT;
                break;
            case KeyEvent.VK_LEFT:
                userOrder = UserOrder.P1LEFT;
                break;
            case KeyEvent.VK_D:
            	userOrder = UserOrder.P2RIGHT;
                break;
            case KeyEvent.VK_Q:
                userOrder = UserOrder.P2LEFT;
                break;
            default:
                userOrder = UserOrder.NOP;
                break;
        }
        return userOrder;
    }

    /**
     * Not used
     */
    @Override
    public void keyTyped(final KeyEvent keyEvent) {
        // Not used
    }

    /**
     * Catch the user keyPressed, conver it and send it to the controller
     */
    @Override
    public final void keyPressed(final KeyEvent keyEvent) {
        try { 
        	//we get the keycode and send it to keycodeUserOrder to transform it into a userOrder
        	//then we send the userOrder to orderPerfrom who will stack the order in stackOrder
            this.getOrderPerformer().orderPerform(keyCodeToUserOrder(keyEvent.getKeyCode()));
        } catch (final IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Not used
     */
    @Override
    public void keyReleased(final KeyEvent keyEvent) {
        // Not used
    }

    /**
     * Gets the level.
     *
     * @return the level
     */
    private ILevel getLevel() {
        return this.level;
    }

    /**
     * Sets the level.
     *
     * @param level
     *            the new level
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    private void setLevel(final ILevel level) throws IOException {
        this.level = level;
        for (int x = 0; x < ViewFacade.squareNumberWidth; x++) { //this double for made the level
            for (int y = 0; y < ViewFacade.squareNumberHeight; y++) {
                this.getLevel().getOnTheLevelXY(x, y).getSprite().loadImage(); //it place in memory the picture corresponding to a postion X Y
            }
        }
    }


    /**
     * Gets the close view.
     *
     * @return the close view
     */
    private Rectangle getCloseView() {
        return this.closeView;
    }

    /**
     * Sets the close view.
     *
     * @param closeView
     *            the new close view
     */
    private void setCloseView(final Rectangle closeView) {
        this.closeView = closeView;
    }

    /**
     * Gets the order performer.
     *
     * @return the order performer
     */
    private IOrderPerformer getOrderPerformer() {
        return this.orderPerformer;
    }

    /**
     * Sets the order performer.
     *
     * @param orderPerformer
     *            the new order performer
     */
    public final void setOrderPerformer(final IOrderPerformer orderPerformer) {
        this.orderPerformer = orderPerformer;
    }

}