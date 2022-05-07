package threads;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.AmericaDataBase;

public class GeneratePersonsWindowThread implements Runnable{
	
	private boolean exit = true;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			initWindow();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void stop() {
		exit = false;
	}

	
	public void initWindow() throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(AmericaDataBase.class.getResource("../view/GeneratePersonsWindow.fxml"));

		Parent parent = null;
		try {
			parent = (Parent) loader.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Stage stage = new Stage();

		Scene scene = new Scene(parent);

		stage.setScene(scene);

		stage.show();
	}
}
