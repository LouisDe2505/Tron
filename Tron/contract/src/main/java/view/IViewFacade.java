package view;

import controller.IOrderPerformer;

public interface IViewFacade {

    /**
     * Display message.
     *
     * @param message
     *            the message
     */
    void displayMessage(String message);

    /**
     * Set the object who perform the orders catched by the view.
     */
	void setOrderPerformer(IOrderPerformer orderPeformer);

    /**
     * Paint a wall behind the Lightcycle
     */
	void LightwallPaint(int x, int y);

}