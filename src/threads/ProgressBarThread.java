package threads;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.AmericaDataBase;

public class ProgressBarThread extends Application implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(AmericaDataBase.class.getResource("../view/ProgressBarWindow.fxml"));

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
