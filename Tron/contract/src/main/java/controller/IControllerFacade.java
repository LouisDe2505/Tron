package controller;

import java.io.IOException;

public interface IControllerFacade {

    /**
     * Play.
     *
     * @throws InterruptedException
     *             the interrupted exception
     * @throws IOException 
     */
    void play() throws InterruptedException, IOException;

    /**
     * Gets the order peformer.
     *
     * @return the order peformer
     */
    IOrderPerformer getOrderPeformer();

}