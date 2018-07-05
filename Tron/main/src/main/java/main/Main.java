package main;

import java.io.IOException;
import java.sql.SQLException;

import controller.ControllerFacade;
import controller.IControllerFacade;
import model.IModelFacade;
import model.ModelFacade;
import view.IViewFacade;
import view.ViewFacade;



public class Main {
   
	/**
	 * The main function of Tron Game
	 * It instantiate a model, view and controller
	 * It make the view updating in function of the controller
	 * It launch the heart of the game (play) which is a loop that run until a Lightcycle touch a Lightwall
	 * 
	 * @param args
	 * 		the args
	 * @throws IOException
	 * 		the IO exception
	 * @throws InterruptedException
	 * 		the thread exception
	 * @throws SQLException
	 * 	the SQL exception
	 */
	
	public static void main(final String[] args) throws IOException, InterruptedException, SQLException {
	
		final IModelFacade model = new ModelFacade(2); 									
		final IViewFacade view = new ViewFacade(model.getLevel()); 						
        final IControllerFacade controller = new ControllerFacade(view, model); 		
        view.setOrderPerformer(controller.getOrderPeformer()); 							

        controller.play(); 																
	}
}