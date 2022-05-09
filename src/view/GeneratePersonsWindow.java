package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ui.AmericaDataBase;

public class GeneratePersonsWindow implements Initializable {

	@FXML
	private TextField amountOfPersonsTF;
	
	@FXML
	private TextField timeTF;

	@FXML
	private Button generateDataBaseBTN;

	@FXML
	private ProgressBar progressBar;

	@FXML
	private Label txtState;

	@FXML
	private ProgressIndicator pind;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		timeTF.setEditable(false);
		txtState.setText("");
		// progress bar
		progressBar.indeterminateProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
				if (t1) {
					txtState.setText("Calculating Time");
				} else {
					txtState.setText("In Progress");

				}
			}
		});
		progressBar.progressProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {

				if (t1.doubleValue() == 1) {
					txtState.setText("Work Done");
					txtState.setTextFill(Color.GREEN);
					launchSearchWindow();
				}
			}

		});
		// indicator
		pind.indeterminateProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
				if (t1) {
					txtState.setText("Calculating Time");
					txtState.setTextFill(Color.BLUE);
				} else {
					txtState.setText("In Progress");
				}
			}
		});

	}

	@FXML
	void handle(ActionEvent event) {
		int limit = 0;
		if (amountOfPersonsTF.getText().equals("") || amountOfPersonsTF.getText().equals("0")) {
			amountOfPersonsTF.setText("");
			AlertsCreator.loadAlert(Alert.AlertType.WARNING,"Warning" , "Empty field!","The field can not be empty nor 0");
		} else {
			try {
				limit = Integer.parseInt(amountOfPersonsTF.getText());
			} catch (NumberFormatException e) {
				amountOfPersonsTF.setText("");
				AlertsCreator.loadAlert(Alert.AlertType.ERROR,"Error" , "Invalid input!","The input must be an integer number");
			}
			if (limit > (6782 * 162253)) {
				AlertsCreator.loadAlert(Alert.AlertType.WARNING,"Warning" , "Maximum amount of persons reached!","The maximum amount of persons is " + 6782 * 162253);
			} else if (limit != 0) {
				Task task = taskCreator(limit);
				progressBar.progressProperty().unbind();
				progressBar.progressProperty().bind(task.progressProperty());
				pind.progressProperty().unbind();
				pind.progressProperty().bind(task.progressProperty());
				new Thread(task).start();
				generateDataBase(event);
				int i = 0;
				while(task.isRunning()) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					timeTF.setText(i + " seconds");
				}
				AlertsCreator.loadAlert(Alert.AlertType.CONFIRMATION, "Confirmation", "People was generated successfully", "People generated: " + limit);
			}
		}

	}

	private Task taskCreator(int limit) {
		return new Task() {
			
			@Override
			protected Object call() throws Exception {
				for (int i = 0; i < 10; i++) {
					Thread.sleep((int) (limit)/834);
					updateProgress(i + 1, 10);
				}
				return true;
			}
			
		};
	}

	private void generateDataBase(ActionEvent event) {
		int limit = 0;
		limit = Integer.parseInt(amountOfPersonsTF.getText());
		if (amountOfPersonsTF.getText().toUpperCase().equals("MAX")) {
			limit = 6782 * 162253;
		}
		AmericaDataBase.initDB(limit);

	}

	/**
	 * This method launches search window
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void launchSearchWindow() {
		FXMLLoader loader = new FXMLLoader(AmericaDataBase.class.getResource("../view/SearchWindow.fxml"));

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
